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
    private String leftType = "";
    private String rightType = "";
    private String strExpr = "";
    private String contExpr = "";
    private String operacao;
    private	String op_atual;
	private	String op_nova;
    private IfCommand currentIfCommand;
    private Stack<ArrayList<Command>> stack = new Stack<>();
    private Stack<String> exprDecision = new Stack<String>();
    private ArrayList<Command> doWhileCommands;
    private ArrayList<Command> whileCommands;
    private ArrayList<Command> listaVazia;
    private ArrayList<Command> listT;
    private ArrayList<Command> listF;
    private ArrayList<Command> comList;
    
    
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
    	contExpr = "";
		exTypeList = new ArrayList<String>();
	}
	
	public void typeAttrib(String leftType, String id, String expression) { 
		for (String type : exTypeList) {
			if (leftType != type) {
				throw new IsiLanguageSemanticException("Tipos incompatíveis entre " + leftType + " e " + type + "\n\t na sentenca " + id+" := " + expression);
			}
		}
	}
	
	public String getTypeIfValid(ArrayList<String> listTypes, String lado, String expressao) {
    	if (listTypes.isEmpty()) {
        	throw new IsiLanguageSemanticException("A lista de tipos está vazia para a expressão " + expressao);
    	}
    	String tipoBase = listTypes.get(0);

    	// Verifica se todos os tipos são iguais ao tipo base
    	for (String tipo : listTypes) {
        	if (!tipo.equals(tipoBase)) {
            	throw new IsiLanguageSemanticException("Elementos do lado " + lado + " possuem tipos incompatíveis.");
        	}
    	}
    	// Se todos os tipos são iguais, retorna o tipo base
   		return tipoBase;
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
							typeAttrib(leftType, id_dois, contExpr);
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

cmdSe	:	'se' AP {
						exprReset();
					} expr {
						exprDecision.push(contExpr);
						leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
					} 
					OPREL { 
						operacao = _input.LT(-1).getText();
						op_atual = exprDecision.pop();
						op_nova = op_atual + operacao;
						exprDecision.push(op_nova);
						exprReset();
					} expr {
						op_atual = exprDecision.pop();
						op_nova = op_atual + contExpr;
						exprDecision.push(op_nova);
						rightType = getTypeIfValid(exTypeList, "direito", op_nova);
					} 
					FP {
						if (rightType != leftType) { 
							throw new IsiLanguageSemanticException("Não é possível compará-los");
						}
					}( 
			  'entao' AC { 
            			comList = new ArrayList<Command>(); 
            			stack.push(comList);
        			}
      				(comando)+ FC {
            				listT = stack.pop();
            				String expreDecision = exprDecision.pop();
            				listaVazia = new ArrayList<Command>();
            				IfCommand ifCommand = new IfCommand(exprDecision, listT, listaVazia);
            				stack.peek().add(ifCommand);
        			}
					AC { 
						comList = new ArrayList<Command>(); 
                      	stack.push(comList);
                    } (comando)+ FC {
                       listT = stack.pop();	
					   String expDeci = exprDecision.pop();
					   listaVazia = new ArrayList<Command>();
					   IfCommand cmdEntao = new IfCommand(exprDecision, listT, listaVazia);
                   	   stack.peek().add(cmdEntao);
                    })? (
			'senao' AC {
                   	 	comList = new ArrayList<Command>();
                   	 	stack.push(comList);
                   	 } (comando+) FC {
                   		listF = stack.pop();
						int index = stack.peek().size() - 1; 
						stack.peek().remove(index); 
                   		IfCommand cmdSeNao = new IfCommand(exprDecision, listT, listF);
                   		stack.peek().add(cmdSeNao);
                     })?
           ;

cmdEnquanto:	'enquanto' AP {
							exprReset();
					} expr {
							exprDecision.push(contExpr);
							leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
					} OPREL { 
							operacao = _input.LT(-1).getText();
							op_atual = exprDecision.pop();
							op_nova = op_atual + operacao;
							exprDecision.push(op_nova);
							exprReset();
					} expr {
							op_atual = exprDecision.pop();
							System.out.println(op_atual);
							System.out.println(contExpr);
							op_nova = op_atual + contExpr;
							System.out.println(op_nova);
							exprDecision.push(op_nova);
							rightType = getTypeIfValid(exTypeList, "direito", op_nova);
					} FP {
						if (rightType != leftType) { 
							throw new IsiLanguageSemanticException("Não é possível compará-los");
						}
					
					} AC {
						comList = new ArrayList<Command>(); 
            			stack.push(comList); 
                    } (comando)+ FC {
                       whileCommands = stack.pop();	
					   WhileCommand cmdEnquanto = new WhileCommand(exprDecision.pop(), whileCommands);
                   	   stack.peek().add(cmdEnquanto);
                    };


cmdFacaEnquanto:   'faca' 
        			AC { 
            			comList = new ArrayList<Command>(); 
            			stack.push(comList); 
        			} 
        			(comando)+ 
        			FC 
        			'enquanto' AP { 
            			exprReset(); 
       			 	} 
        			expr { 
            			exprDecision.push(contExpr); 
            			leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr); 
        			} 
        			OPREL { 
            			operacao = _input.LT(-1).getText(); 
            			op_atual = exprDecision.pop(); 
            			op_nova = op_atual + operacao; 
            			exprDecision.push(op_nova); 
            			exprReset(); 
        			} 
        			expr { 
            			op_atual = exprDecision.pop(); 
            			op_nova = op_atual + contExpr; 
            			exprDecision.push(op_nova); 
            			rightType = getTypeIfValid(exTypeList, "direito", op_nova); 
        			} 
        			FP { 
            			if (rightType != leftType) { 
                			throw new IsiLanguageSemanticException("Não é possível compará-los"); 
            			}
        			} 
        			{ doWhileCommands = stack.pop(); 
            		  DoWhileCommand cmdFacaEnquanto = new DoWhileCommand(exprDecision.pop(), doWhileCommands);
            		  stack.peek().add(cmdFacaEnquanto);
	        		}
    			;

        
expr	: termo expr_ad
		;
		
termo	: fator termo_ad
		;

expr_ad	:	(SUB { 
				contExpr += '-'; exTypeList.add("NUMBER");}
			termo expr_ad 
           	| SOMA { 
           		contExpr += '+'; exTypeList.add("NUMBER");}
           	termo expr_ad
           	)?
        ;

termo_ad: 	(DIV { contExpr += '/'; exTypeList.add("NUMBER");
			}
			fator termo_ad	
            | MULT { contExpr += '*'; exTypeList.add("NUMBER"); }
            fator termo_ad
            )?
        ;
        
fator	:	ID { String id = _input.LT(-1).getText();
				 checkInitialized(id);
				 verificaAtribuicao(id);
				 String type = getTypeById(id);
				 contExpr += id; 
				 exTypeList.add(type);
			}
			| NUMERO {  contExpr += _input.LT(-1).getText();
					exTypeList.add("NUMBER");
			}
			| NUMERO_REAL {contExpr += _input.LT(-1).getText();
					exTypeList.add("REALNUMBER");
			}
			| TEXTO {  contExpr += _input.LT(-1).getText();
					exTypeList.add("TEXT");
			} 
			| AP { contExpr += _input.LT(-1).getText();
			} 
			expr FP { contExpr += _input.LT(-1).getText();
			}         
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

AC		: '{'
		;

FC		: '}'
		;
		
DP		: ':'
		;

TEXTO	: '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-')* '"'
		;

STRING: '"' ( '\\"' | .)*? '"';

WS		: (' ' | '\n' | '\r' | '\t') -> skip;