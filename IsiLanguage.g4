grammar IsiLanguage;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
}

@members {
	private SymbolTable symbolTable = new SymbolTable();
	private ArrayList<Var> currentDecl = new ArrayList<Var>();
	private Types currentType;
	private Types leftType=null, rightType=null;
	private Program program = new Program();
	private String strExpr = "";
	private IfCommand currentIfCommand;
	private WhileCommand whileCommand;
    private AtribCommand atribCommand;
	    
	private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
	    
	    
	public void updateType() {
	    for (Var v : currentDecl) {
	        v.setType(currentType);
	        symbolTable.add(v);
	    }
	}
	    
	public void exibirVar() {
	    for (Var var : symbolTable.getAll()) { 
	        System.out.println(var);
	    }
	}
	    
	public Program getProgram(){
	    return this.program;
	}
	    
	public boolean isDeclared(String id){
	    return symbolTable.get(id) != null;
	}
    

	public void checkUnused(String id) {
		Var var = (Var) symbolTable.get(id);
		if ((var.isInitialized() && !var.isUsed()) || !(var.isInitialized() && var.isUsed())) {
	       	System.out.println("Warning - Variable " + var.getId() + " was declared but not used."); 
		}	
	}

	public void checkInitialized(String id) {
        if(!symbolTable.checkInitialized(id))
            throw new IsiLanguageSemanticException("Símbolo "+id+" não inicializado.");
    }

    public void setInitialized(String id) {
        symbolTable.setInitializedBy(id);
    }

	public void generateCode(){
		program.generateTarget();
	}
}

programa	:	'programa' CLASS  { program.setName(_input.LT(-1).getText());
                                stack.push(new ArrayList<Command>());
                              }
               declaravar+
               'inicio'
               comando+
               'fim'
               'fimprograma'

               {
                  program.setSymbolTable(symbolTable);
                  program.setCommandList(stack.pop());
                  checkUnusedVariables();
               }
         	;

declara 	:	declaravar+
            	
            ; 

declaravar	:	tipo ID  {
	                  	if (isDeclared(_input.LT(-1).getText())) {
              				throw new SemanticException("Variable " + _input.LT(-1).getText() + " already declared " + _input.LT(-1).getLine() + ", column " + _input.LT(-1).getCharPositionInLine() + ".");
          				}
          				currentDecl.add(new Var(_input.LT(-1).getText()));
                    }

              		(  VIRG 
              	 		ID {
	                  		if (isDeclared(_input.LT(-1).getText())) {
              					throw new SemanticException("Variable '" + _input.LT(-1).getText() + "' already declared at line " + _input.LT(-1).getLine() + ", column " + _input.LT(-1).getCharPositionInLine() + ".");
          					}
          					currentDecl.add(new Var(_input.LT(-1).getText()));
        				}
      				)*
      				DP
      				(
        			'numero' {currentType = Types.NUMERO;}
        			|
        			'texto' {currentType = Types.TEXTO;}
        			|
        			'booleano' {currentType = Types.BOOLEANO;}
      				)
      				{ updateType(); }
      				PV
           	;

tipo       : 'numero' 	 { _tipo = Var.NUMBER;  }
           | 'texto' 	 { _tipo = Var.TEXT;  } 
           | 'booleano'  { _tipo = Var.BOOLEAN;  }
           ;
			
comando  :	cmdAttrib
		 | cmdLeitura
	 	 | cmdEscrita
		 | cmdSe
		 | cmdEnquanto
		 | cmdFacaEnquanto
		 ;

cmdAttrib	:	ID 	{ 
					strExpr = "";
          			if (!isDeclared(_input.LT(-1).getText())) {
              			throw new SemanticException("Undeclared Variable During assignment: " + _input.LT(-1).getText());
          			}
          			symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
          			symbolTable.get(_input.LT(-1).getText()).setUsed(true);
					leftType = symbolTable.get(_input.LT(-1).getText()).getType();
					atribCommand = new AtribCommand(symbolTable.get(_input.LT(-1).getText()));
      			}
      			OP_AT {
          			strOp = _input.LT(-1).getText();
          			atribCommand.setStrOp(strOp);
      			}
      			expr {
          			atribCommand.setExprString(strExpr);

				if (strOp.equalsIgnoreCase("++") || strOp.equalsIgnoreCase("--")) {
					System.out.println("Left  Side Expression Type = " + leftType);
					if (leftType != Types.NUMBER) {
						throw new SemanticException("Operator " + strOp + " is only allowed for numeric variables at line "
							+ _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
					}
					atribCommand.setExprString(null);
				}

				else if (strOp.equalsIgnoreCase("+=") || strOp.equalsIgnoreCase("-=")) {
					System.out.println("Left Side Expression Type = " + leftType);
					System.out.println("Right Side Expression Type = " + rightType);
					if (leftType != Types.NUMBER || rightType != Types.NUMBER) {
						throw new SemanticException("Operator " + strOp + " requires both sides to be numeric at line "
							+ _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
					}
					stack.peek().add(atribCommand);
				}

				else {
					System.out.println("Left Side Expression Type = " + leftType);
					System.out.println("Right Side Expression Type = " + rightType);

				if (!((leftType == Types.NUMBER && rightType == Types.NUMBER) ||
						(leftType == Types.TEXT && rightType == Types.TEXT) ||
						(leftType == Types.BOOL && rightType == Types.BOOL))) {
					throw new SemanticException("Type mismatch: cannot assign '" + rightType + "' to variable of type '" + leftType + "' at line "
						+ _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
				}
				stack.peek().add(atribCommand);
			}
		}
		PV;
			;

cmdSe	:  'se' 	{ stack.push(new ArrayList<Command>());
                      exprList = new ArrayList<>();
                      currentIfCommand = new IfCommand();
                    }
					AP
					exprList {
								for (String var : exprList) {
									if (symbolTable.containsKey(var)) {
										symbolTable.get(var).setUsed(true);
									}
								}
								
								if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
									throw new SemanticException("Missing or invalid condition in 'if' statement at line " + _input.LT(1).getLine() + ".");
								}
							}
					FP  {
							if (exprList.size() == 1 && leftType != Types.BOOL) {
								throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line "
								+ _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
							}
							currentIfCommand.setExpressions(exprList);
						}
					'entao'
					comando+
					{
						currentIfCommand.setTrueList(stack.pop());
					}
					( 'senao'
						{ stack.push(new ArrayList<Command>()); }
						comando+
						{
						currentIfCommand.setFalseList(stack.pop());
						}
					)?
					'fimse'
					{
						stack.peek().add(currentIfCommand);
					}
					;

cmdLeitura	: 'leia' AP
			  		ID {
						if (!isDeclared(_input.LT(-1).getText())) {
							throw new SemanticException("Undeclared Variable During reading: " + _input.LT(-1).getText());
						}
						symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
						symbolTable.get(_input.LT(-1).getText()).setUsed(true);
						Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
						stack.peek().add(cmdRead);
					}
					FP 
					PV
			;

cmdEscrita	:	'escreva' AP (
                 			 termo  { 
									String content = _input.LT(-1).getText();										
									if (!content.startsWith("\"")) {
										Types varType = symbolTable.get(content).getType();
										Command cmdWrite = new WriteCommand(content, varType);
										stack.peek().add(cmdWrite);
									} else {
										Command cmdWrite = new WriteCommand(content, Types.TEXT);
										stack.peek().add(cmdWrite);
									}
								}
							)
              				FP 
							PV { rightType = null;}
		   	;

cmdEnquanto	: 'enquanto' 	{ stack.push(new ArrayList<Command>());
							exprList = new ArrayList<>();
							whileCommand = new WhileCommand(false);
							}
						AP
						exprList {
								for (String var : exprList) {
									if (symbolTable.containsKey(var)) {
										symbolTable.get(var).setUsed(true);
									}
								}
								
								if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
									throw new SemanticException("Missing or invalid condition in 'while' statement at line " + _input.LT(1).getLine() + ".");
								}
							}
						FP	{
							if (exprList.size() == 1 && leftType != Types.BOOL) {
								throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
							}
							whileCommand.setExpressions(exprList);
							}
						'faca'
						comando+ {whileCommand.setTrueList(stack.pop());}
						'fimenquanto'
						{
						stack.peek().add(whileCommand);
						}
           ;

cmdFacaEnquanto:	'faca' 	{ stack.push(new ArrayList<Command>());
							exprList = new ArrayList<>();
							whileCommand = new WhileCommand(true);
							}
							comando+ {whileCommand.setTrueList(stack.pop());}
							'enquanto' { strExpr = "";}
							AP
							exprList {
								for (String var : exprList) {
									if (symbolTable.containsKey(var)) {
										symbolTable.get(var).setUsed(true);
									}
								}
								
								if (exprList.isEmpty() || exprList.get(0).trim().isEmpty()) {
									throw new SemanticException("Missing or invalid condition in 'do-while' statement at line " + _input.LT(1).getLine() + ".");
								}
							}
							FP  {
                        	if (exprList.size() == 1 && leftType != Types.BOOL) {
                           		throw new SemanticException("Single expression '" + exprList.get(0) + "' in condition must be boolean at line " + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ".");
                        	}
                        	whileCommand.setExpressions(exprList);
                    		}
     						'fimenquanto'
     						{
                   			stack.peek().add(whileCommand);
                 			}
                ;

expr	:	returns [Types type]
			: termo {
				$type = rightType;
			}
			(exprl)*
			;
		;

exprl returns [Types type]
    : (OP_SUM | OP_SUB) {
          strExpr += _input.LT(-1).getText();
          BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
          bin.setLeft(exprStack.pop());
          exprStack.push(bin);
      }
      termo {
          AbstractExpression top = exprStack.pop();
          BinaryExpression root = (BinaryExpression)exprStack.pop();
          root.setRight(top);
          exprStack.push(root);

          if (leftType != rightType) {
            if (!(leftType == Types.BOOL && rightType == null))
              throw new SemanticException("Type mismatch: incompatible types '" + leftType + "' and '" + rightType + "' in operation at line "
              + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ". Expression: " + _input.LT(1).getText());
          }
          leftType = rightType;
          System.out.println("Expression value " + root.evaluate());
          System.out.println(root.toJSON());
      }
    ;

termo returns [Types type]
    : fator {
		strExpr += _input.LT(-1).getText();
        $type = rightType;
      }
      (termol[$type])*
    ;

termol[Types inheritedType] returns [Types type]
    : (OP_MUL | OP_DIV) {
          strExpr += _input.LT(-1).getText();
          BinaryExpression bin = new BinaryExpression(_input.LT(-1).getText().charAt(0));
          bin.setLeft(exprStack.pop());
          exprStack.push(bin);
      }
      termo {
          AbstractExpression rightExpr = exprStack.pop();
          BinaryExpression root = (BinaryExpression) exprStack.pop();
          root.setRight(rightExpr);
          exprStack.push(root);

          if (leftType != rightType) {
            if (!(leftType == Types.BOOL && rightType == null))
             throw new SemanticException("Type mismatch: incompatible types '" + leftType + "' and '" + rightType + "' in operation at line "
             + _input.LT(1).getLine() + ", column " + _input.LT(1).getCharPositionInLine() + ". Expression: " + _input.LT(1).getText());
          }
          $type = rightType;
      }
    ;



OP		: '+' | '-' | '*' | '/'
		;

OP_AT	: ':='
		;

OPREL	: '>' | '<' | '>=' | '<=' | '<>' | '=='
		;

ID		: [a-z] ( [a-z] | [A-Z] | [0-9])*
		;

NUMERO	: [0-9]+ ('.' [0-9]+)?
		;

VIRG	: ','	
		;

PF      : '.'
		;

PV		: ';'
		;

AP		: '('
		;

FP		: ')'
		;

DP		: ':'
		;

TEXTO	: '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-')* '"'
		;

BOOLEANO : 'true' | 'false'
        ;

WS		: (' ' | '\n' | '\r' | '\t') -> skip;
