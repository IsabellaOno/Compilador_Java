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
    private int currentType;
    private int leftType = -1, rightType = -1;
    private Program program = new Program();
    private String strExpr = "";
    private IfCommand currentIfCommand;
    private Stack<ArrayList<Command>> stack = new Stack<>();
    private Stack<String> stackExprDecision = new Stack<String>();
    
    
    public void exibirVar() {
    for (Symbol sym : symbolTable.getAll()) { 
        System.out.println(sym);
    	}
	}
    
    public Program getProgram(){
    	return this.program;
    	}
    
    public boolean isDeclared(String id){
    	return symbolTable.get(id) != null;
    }
    
    public void checkUnused(String id) {
		Symbol sym = (Symbol) symbolTable.get(id);
		if ((sym.isInitialized() && !sym.isUsed()) || !(sym.isInitialized() && sym.isUsed())) {
	       	System.out.println("Warning - Variable " + sym.getId() + " was declared but not used."); 
		}	
	}
	
	public void checkInitialized(String id) {
        if(!symbolTable.checkInitialized(id))
            throw new IsiLanguageSemanticException("Símbolo "+id+" não inicializado.");
    }
    
    public void setHasValue(String id) {
        symbolTable.setHasValue(id);
    }
    
    public void generateCode(){
		program.generateTarget();
	}
}

programa	: 'programa' ((declara bloco) |bloco)? 'fimprog.'
			  {
                program.setsymbolTable(symbolTable);
                program.setCommandList(stack.pop());
           	  }
        	;

declara 	: (declaravar)+
            ; 

bloco 		: {
			  stack.push(new ArrayList<Command>());
			  }
			  (comando)+
			;

declaravar: 'declare' tipo { currentDecl.clear(); }
      		ID {
      			String id_var = _input.LT(-1).getText();
          		Symbol sym = new Var(id_var, null, currentType);
          		if (!symbolTable.exists(id_var)){
	                     symbolTable.add(sym);	
	                  }
	                  else{
	                  	 throw new IsiLanguageSemanticException("Symbol "+id_var+" already declared");
	                  } 
      		}
      		( VIRG ID { 
      			String id_vari = _input.LT(-1).getText();
          		Symbol symb = new Var(id_vari, null, currentType);
          		if (!symbolTable.exists(id_vari)){
	                     symbolTable.add(symb);	
	                  }
	                  else{
	                  	 throw new IsiLanguageSemanticException("Symbol "+id_var+" already declared");
	                  }
      		} )*
      		PO
    	  ;

tipo	 : 'inteiro' { currentType = Var.NUMBER; }
    	 | 'real' { currentType = Var.REALNUMBER; }
    	 | 'texto' { currentType = Var.TEXT; }
    	 ;
			
comando  :	cmdAttrib
		 | cmdLeitura
	 	 | cmdEscrita
		 | cmdSe
		 | cmdEnquanto
		 | cmdFacaEnquanto
		 | cmdPara
		 ;

cmdLeitura: 'leia' AP ID {
					checkInitialized(_input.LT(-1).getText();
    				String ident = _input.LT(-1).getText();
    			} 
    				FP PO
    			{
    				Var var = (Var)symbolTable.get(ident);
              		Command cmdLeitura = new ReadCommand(ident, var);
              		stack.peek().add(cmdLeitura);
					setAtribuicao(ident);
			 }	
		  ;

cmdEscrita: 'escreva' AP (
    		TEXTO { 
        		String text = _input.LT(-1).getText();
        		Command cmdEscrita = new WriteCommand(text, true); // Literal
        		stack.peek().add(cmdEscrita);
    		}
    		| termo { 
       			String termoText = _input.LT(-1).getText();
        		if (!isDeclared(termoText)) {
            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
        		}
        		Command cmdEscrita = new WriteCommand(termoText, false); 
        		stack.peek().add(cmdEscrita);
    		}
			) FP PO { 
    		rightType = -1;
			}	
			;
				  
cmdAttrib:		ID { 
                   String id = _input.LT(-1).getText();
                   if (!isDeclared(id)) {
                       throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
                   }
                   symbolTable.get(id).setInitialized(true);
                   leftType = symbolTable.get(id).getType();
                 } OP_AT expr PO {
                 System.out.println("Left Side Expression Type = " + leftType);
                 System.out.println("Right Side Expression Type = " + rightType);
                 
                 if (leftType != -1 && rightType != -1 && leftType < rightType) {
                    throw new IsiLanguageSemanticException("Type Mismatching on Assignment");
                 }

                 AttribCommand attribCommand = new AttribCommand(_input.LT(-5).getText(), strExpr, symbolTable);
                 
                 stack.peek().add(attribCommand);

                 strExpr = "";
                 rightType = -1;
              }
              ;

cmdSe	:	'se' { stack.push(new ArrayList<Command>());
                     strExpr = "";
                      currentIfCommand = new IfCommand();
                  } AP expr OPREL { strExpr += _input.LT(-1).getText(); } expr FP { currentIfCommand.setExpression(strExpr); 
		      	} 'entao' comando+ { 
                  currentIfCommand.setTrueList(stack.pop());                            
            	} (
			'senao' { stack.push(new ArrayList<Command>()); } comando+ {
                     currentIfCommand.setFalseList(stack.pop());
                 }
			)? 'fimse' {
               	stack.peek().add(currentIfCommand);
               }
        ;

cmdEnquanto:	'enquanto' { 
                  stack.push(new ArrayList<Command>());
                  strExpr = ""; 
               	} AP expr OPREL { strExpr += _input.LT(-1).getText(); } expr FP 'faca' comando+
				'fimenquanto' { 
                  WhileCommand WhileCommand = new WhileCommand(strExpr, stack.pop()); 
                  stack.peek().add(WhileCommand);
               	}
           ;

cmdFacaEnquanto:	'faca' { 
                     	stack.push(new ArrayList<Command>());
                  	} comando+ 'enquanto' AP expr OPREL { strExpr += _input.LT(-1).getText(); } expr
					FP PO { 
                     	DoWhileCommand DoWhileCommand = new DoWhileCommand(strExpr, stack.pop()); 
                     	stack.peek().add(DoWhileCommand); 
                  	}
                ;

cmdPara:	'para' AP ID OP_AT expr { String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
		      } PO expr OPREL expr { String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
		      } PO ID ('++' | '--') { String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
		      } FP 'faca' { 
               stack.push(new ArrayList<Command>());
              } comando+ 'fimpara' {
               ForCommand ForCommand = new ForCommand(initialization, condition, increment, stack.pop()); 
               stack.peek().add(ForCommand);
              }
        ;

expr	: termo { strExpr += _input.LT(-1).getText(); } exprl
		;

termo	:	ID { if (!isDeclared(_input.LT(-1).getText())) {
                       throw new IsiLanguageSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                    }
                    if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()){
                       throw new IsiLanguageSemanticException("Variable "+_input.LT(-1).getText()+" has no value assigned");
                    }
                    if (rightType == -1){
                       rightType = 2;
                       rightType = symbolTable.get(_input.LT(-1).getText()).getType();
                       //System.out.println("Encontrei pela 1a vez uma variavel = "+rightType);
                    }   
                    else{
                       if (rightType < 2) {
                       	  rightType = 2;
                       	  System.out.println("Mudei o tipo para TEXT = " + rightType);
                          //System.out.println("Ja havia tipo declarado e mudou para = "+rightType);
                          
                       }
                    }
                  }
			| NUMERO {  if (rightType == -1) {
			 				rightType = Var.NUMBER;
			 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
			            }
			               else{
			                   if (rightType.getValue() < Var.NUMBER.getValue()){			                    			                   
			                	   rightType = Var.NUMBER;
			                	   //System.out.println("Mudei o tipo para Number = "+rightType);
			                   }
			               }
			            }
			| TEXTO {  if (rightType == -1) {
			 				   rightType = Var.TEXT;
			 				   System.out.println("Encontrei pela 1a vez um texto ="+ rightType);
			               }
			               else{
			                   if (rightType.getValue() < Var.TEXT.getValue()){			                    
			                	   rightType = Var.TEXT;
			                	   System.out.println("Mudei o tipo para TEXT = "+rightType);
			                	
			                   }
			               }
			            }
		;

exprl	: (
			OP { strExpr += _input.LT(-1).getText(); } termo { strExpr += _input.LT(-1).getText(); }
		  )*
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

PV		: ';'
		;
		
PO		:'.'
		;
		
AP		: '('
		;

FP		: ')'
		;

DP		: ':'
		;

TEXTO	: '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-')* '"'
		;

WS		: (' ' | '\n' | '\r' | '\t') -> skip;