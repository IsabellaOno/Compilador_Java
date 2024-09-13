grammar IsiLanguage;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;
}

// Definição de variáveis e estruturas usadas no processo de análise e geração de código.
// Inclui o symbolTable para rastreamento de variáveis, lista e pilhas de comandos, 
// e variáveis temporárias para controle de tipos e expressões.

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
    
    // Exibe as variáveis armazenadas na tabela de símbolos
    public void exibirVar() {
        for (Symbol sym : symbolTable.getAll()) { 
            System.out.println(sym);
    	}
	}
	
    // Retorna o tipo de uma variável baseado no seu identificador
	public String getTypeById(String id) {
		return symbolTable.getTypeById(id);
	}

    // Retorna o programa atual
    public Program getProgram(){
    	return this.program;
    }
    
    // Verifica se a variável foi declarada
    public boolean isDeclared(String id){
    	return symbolTable.get(id) != null;
    }
    
    // Verifica que a variável foi declarada e não utilizada, gerando um alerta
    public void checkUnused(String id) {
		Symbol sym = (Symbol) symbolTable.get(id);
		if ((sym.isInitialized() && !sym.isUsed()) || !(sym.isInitialized() && sym.isUsed())) {
	       	System.out.println("Warning - Variável " + sym.getId() + " foi declarada, mas não foi utilizada."); 
		}	
	}
	
    // Verifica que a variável foi inicializada antes de seu uso
	public void checkInitialized(String id) {
        if(!symbolTable.exists(id))
            throw new IsiLanguageSemanticException("Símbolo "+id+" não inicializado.");
    }
    
    // Marca a variável como tendo um valor atribuído
    public void setHasValue(String id) {
        symbolTable.setHasValue(id);
    }
    
    // Verifica se a variável foi atribuída corretamente
    public void verificaAtribuicao(String id) {
		symbolTable.verificaAtribuicao(id);
	}
	
    public void exprReset() {
    	contExpr = "";
		exTypeList = new ArrayList<String>();
	}
	
    // Verificação de compatibilidade de tipos em atribuições
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

    	for (String tipo : listTypes) {
        	if (!tipo.equals(tipoBase)) {
            	throw new IsiLanguageSemanticException("Elementos do lado " + lado + " possuem tipos incompatíveis.");
        	}
    	}
   		return tipoBase;
	}
	
	public void stringType(String id) {
		symbolTable.stringType(id);
	}
    
    // Geração de código final do programa
    public void generateCode(){
		program.generateTarget();
	}
}

// Definição das estruturas necessárias
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

// Declaração de variáveis, com verificação para caso a variável já tenha sido declarada            
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

// Definição de tipos suportados: inteiro, real e texto          
tipo	 : 'inteiro' { currentType = Var.NUMBER;}
    	 | 'real' { currentType = Var.REALNUMBER;}
    	 | 'texto' { currentType = Var.TEXT;}
    	 ;

// Conjunto de comandos suportados, como leitura, escrita, atribuições, condicionais e laços de repetições
comando  :	cmdAttrib
		 | cmdLeitura
	 	 | cmdEscrita
		 | cmdSe
		 | cmdEnquanto
		 | cmdFacaEnquanto
		 ;

// Comando de leitura, verificando se a variável foi inicializada antes
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

// Comando de escrita          
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

// Comando de atribuição, com inclusão de operadores aritméticos             
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
                 			Command cmdAttrib = new AttribCommand(id_dois, contExpr);
							typeAttrib(leftType, id_dois, contExpr);
							setHasValue(id_dois);
							stack.peek().add(cmdAttrib);
					})
				| (STRING {	
						String str = _input.LT(-1).getText();
						stringType(id_dois);
						Command cmdAttrib = new AttribCommand(id_dois, str);
						stack.peek().add(cmdAttrib);
				} PO))
			;

// Estrutura condicional IF...ELSE              
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
					} 'entao' AC{
						comList = new ArrayList<Command>(); 
            			stack.push(comList); 
            		}(comando)+ 
            		FC{
						listT = stack.pop();
						String Dec = exprDecision.pop();
            			Command cmdSe = new IfCommand(Dec, listT, listaVazia);
                   	   	stack.peek().add(cmdSe);}
            		(
					'senao' AC {
                   	 	comList = new ArrayList<Command>();
                   	 	stack.push(comList);
                   	 } (comando+) FC {
                   		listF = stack.pop();
						stack.peek().remove(stack.peek().size() - 1); 
                   		Command cmdSeNao = new IfCommand(Dec, listT, listF);
                   		stack.peek().add(cmdSeNao);
                     })?
           ;

// Estrutura de repetição WHILE            
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
							op_nova = op_atual + contExpr;
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
					   Command cmdEnquanto = new WhileCommand(exprDecision.pop(), whileCommands);
                   	   stack.peek().add(cmdEnquanto);
                    }
                ;

// Estrutura de repetição DO...WHILE
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
            		  Command cmdFacaEnquanto = new DoWhileCommand(exprDecision.pop(), doWhileCommands);
            		  stack.peek().add(cmdFacaEnquanto);
	        		}
    			;

// Definição da estrutura das expressões, termos e fatores        
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

// Definição dos tokens utilizados na linguagem        
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
