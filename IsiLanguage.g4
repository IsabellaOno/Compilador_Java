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
    
    public void setHasValue(String id) {
        symbolTable.setHasValue(id);
    }
    
    public void generateCode(){
		program.generateTarget();
	}
}

programa	: 'programa' (declara bloco |bloco)? 'fimprog'
			  {
                program.setsymbolTable(symbolTable);
                program.setCommandList(stack.pop());
           	  }
        	;

declara 	: (declaravar)+
            ; 

bloco 		: (comando.)+
			;

declaravar	:	'declare' { currentDecl.clear(); } ID { currentDecl.add(new Var(_input.LT(-1).getText()));}
      			( VIRG ID { currentDecl.add(new Var(_input.LT(-1).getText()));} )*
      			DP (
        		'numero' {currentType = Types.NUMBER;} //Alterei se der erro dps pode ser isso.
        		| 'texto' {currentType = Types.TEXT;}
      			) { updateType(); } PV
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
    			String id = _input.LT(-1).getText();
    			if (!isDeclared(id)) {
        			throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
    			}
    			symbolTable.setHasValue(id); // Marca a variável como inicializada
    			symbolTable.markAsUsed(id); // Marca a variável como usada
    
    			Command cmdLeitura = new ReadCommand(symbolTable.get(id));
   	 			stack.peek().add(cmdLeitura);
				} FP PV
		  ;

cmdEscrita: 'escreva' AP (
            TEXTO { Command cmdEscrita = new WriteCommand(_input.LT(-1).getText(), true);
                  stack.peek().add(cmdEscrita);
            }
            | termo { Command cmdEscrita = new WriteCommand(_input.LT(-1).getText()); 
                     stack.peek().add(cmdEscrita);
            }
            ) FP PV { rightType = null;}
		  ;

cmdAttrib :		ID { 
                   String id = _input.LT(-1).getText();
                   if (!isDeclared(id)) {
                       throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
                   }
                   symbolTable.get(id).setInitialized(true);
                   leftType = symbolTable.get(id).getType();
                 } OP_AT expr PV {
                 System.out.println("Left Side Expression Type = " + leftType);
                 System.out.println("Right Side Expression Type = " + rightType);
                 
                 if (leftType != null && rightType != null && leftType.getValue() < rightType.getValue()) {
                    throw new IsiLanguageSemanticException("Type Mismatching on Assignment");
                 }

                 AttribCommand attribCommand = new AttribCommand(_input.LT(-5).getText(), strExpr, symbolTable);
                 
                 stack.peek().add(attribCommand);

                 strExpr = "";
                 rightType = null;
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
					FP PV { 
                     	DoWhileCommand DoWhileCommand = new DoWhileCommand(strExpr, stack.pop()); 
                     	stack.peek().add(DoWhileCommand); 
                  	}
                ;

cmdPara:	'para' AP ID OP_AT expr { String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
		      } PV expr OPREL expr { String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
		      } PV ID ('++' | '--') { String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
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
                    if (rightType == null){
                       rightType = symbolTable.get(_input.LT(-1).getText()).getType();
                       //System.out.println("Encontrei pela 1a vez uma variavel = "+rightType);
                    }   
                    else{
                       if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()){
                          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
                          //System.out.println("Ja havia tipo declarado e mudou para = "+rightType);
                          
                       }
                    }
                  }
			| NUMERO {  if (rightType == null) {
			 				rightType = Types.NUMBER;
			 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
			            }
			               else{
			                   if (rightType.getValue() < Types.NUMBER.getValue()){			                    			                   
			                	   rightType = Types.NUMBER;
			                	   //System.out.println("Mudei o tipo para Number = "+rightType);
			                   }
			               }
			            }
			| TEXTO {  if (rightType == null) {
			 				   rightType = Types.TEXT;
			 				   System.out.println("Encontrei pela 1a vez um texto ="+ rightType);
			               }
			               else{
			                   if (rightType.getValue() < Types.TEXT.getValue()){			                    
			                	   rightType = Types.TEXT;
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

AP		: '('
		;

FP		: ')'
		;

DP		: ':'
		;

TEXTO	: '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-')* '"'
		;

WS		: (' ' | '\n' | '\r' | '\t') -> skip;