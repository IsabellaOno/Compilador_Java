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
    private ArrayList<String> exTypeList = new ArrayList<String>();
    private int currentType;
    private Program program = new Program();
    private String leftType;
    private String strExpr = "";
    private String contExpr = "";
    private IfCommand currentIfCommand;
    private Stack<ArrayList<Command>> stack = new Stack<>();
    private Stack<String> stackExprDecision = new Stack<String>();
    
    
    public void exibirVar() {
    for (Symbol sym : symbolTable.getAll()) { 
        System.out.println(sym);
    	}
	}
	
	public String getTypeById(String id) {
		return symbolTable.getTypeById(id);
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
        if(!symbolTable.exists(id))
            throw new IsiLanguageSemanticException("Símbolo "+id+" não inicializado.");
    }
    
    public void setHasValue(String id) {
        symbolTable.setHasValue(id);
    }
    
    public void verificaAtribuicao(String id) {
		symbolTable.verificaAtribuicao(id);
	}
	
    public void exprReset() {
		exTypeList = new ArrayList<String>();
	}
	
	public void checkTypeAttrib(String leftType, String id, String expression) { 
		for (String type : exTypeList) {
			if (leftType != type) {
				throw new IsiLanguageSemanticException("Tipos incompatíveis entre " + leftType + " e " + type + "\n\t na sentenca " + id+" := " + expression);
			}
		}
	}
	
	public void stringType(String id) {
		symbolTable.stringType(id);
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

declaravar: 'declare' tipo
      		ID {
      			String id_var = _input.LT(-1).getText();
          		Symbol sym = new Var(id_var, null, currentType);
          		if (!symbolTable.exists(id_var)){
	            	symbolTable.add(sym);	
	            }
	            else{
	                throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
	            } 
      		}
      		( VIRG ID { 
      			String id_vari = _input.LT(-1).getText();
          		Symbol symb = new Var(id_vari, null, currentType);
          		if (!symbolTable.exists(id_vari)){
	            	symbolTable.add(symb);	
	            }
	            else{
	               throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
	            }
      		} )*
      		PO
    	  ;

tipo	 : 'inteiro' { currentType = Var.NUMBER;}
    	 | 'real' { currentType = Var.REALNUMBER;}
    	 | 'texto' { currentType = Var.TEXT;}
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
					checkInitialized(_input.LT(-1).getText());
    				String ident = _input.LT(-1).getText();
    			} FP PO
    			{
    				Var var = (Var)symbolTable.get(ident);
              		Command cmdLeitura = new ReadCommand(ident, var);
              		stack.peek().add(cmdLeitura);
					setHasValue(ident);
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
			}	
			;
				  
cmdAttrib:		ID { 
                   		String id = _input.LT(-1).getText();
                   		checkInitialized(id);
                   		leftType = getTypeById(id);
                   		String id_dois = id;
                   		exprReset();
                 	} 
                (SOMA|SUB|MULT|DIV)? {
                 		String op = _input.LT(-1).getText();
                 		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                 			contExpr = id_dois + op;
                 	}} 
                 OP_AT((
                 		expr PO {
                 			AttribCommand cmdAttrib = new AttribCommand(id_dois, contExpr);
							checkTypeAttrib(leftType, id_dois, contExpr);
							setHasValue(id_dois);
							stack.peek().add(cmdAttrib);
					})
				| (STRING {	
						String str = _input.LT(-1).getText();
						stringType(id_dois);
						AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
						stack.peek().add(cmdAttrib);
				} PO))
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

expr	:	 termo (SOMA termo { contExpr += '+'; } 
           	| SUB termo { contExpr += '-'; exTypeList.add("NUMBER"); })*
        ;

termo	: 	fator (MULT fator { contExpr += '*'; exTypeList.add("NUMBER"); }
            | DIV fator { contExpr += '/'; exTypeList.add("NUMBER"); })*
        ;

fator	:	ID { String id = _input.LT(-1).getText();
				 checkInitialized(id);
				 verificaAtribuicao(id);
				 String type = getTypeById(id);
				 contExpr += id; 
				 exTypeList.add(type);}
			| NUMERO {  contExpr += _input.LT(-1).getText();
					exTypeList.add("NUMBER");}
			| NUMERO_REAL {contExpr += _input.LT(-1).getText();
					exTypeList.add("REALNUMBER");}
			| TEXTO {  contExpr += _input.LT(-1).getText();
					exTypeList.add("TEXT");} 
			| AP { contExpr += _input.LT(-1).getText();} 
			expr FP { contExpr += _input.LT(-1).getText();}         
		;

SOMA	: '+'
		;

SUB		: '-'
		;

DIV		: '/'
		;

MULT	: '*'
		;

OP_AT	: ':='
		;

OPREL	: '>' | '<' | '>=' | '<=' | '<>' | '=='
		;

ID		: [a-z] ( [a-z] | [A-Z] | [0-9])*
		;

NUMERO	: [0-9]+
		;

NUMERO_REAL : [0-9]+ ('.' [0-9]+)?
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

STRING: '"' ( '\\"' | .)*? '"';

WS		: (' ' | '\n' | '\r' | '\t') -> skip;