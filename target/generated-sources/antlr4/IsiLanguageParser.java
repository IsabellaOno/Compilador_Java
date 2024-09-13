// Generated from IsiLanguage.g4 by ANTLR 4.4

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, SOMA=19, SUB=20, DIV=21, MULT=22, OP_AT=23, OPREL=24, 
		ID=25, NUMERO=26, NUMERO_REAL=27, VIRG=28, PV=29, PO=30, AP=31, FP=32, 
		AC=33, FC=34, DP=35, TEXTO=36, STRING=37, WS=38;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'senao'", "'inteiro'", "'--'", "'++'", "'programa'", 
		"'faca'", "'fimpara'", "'escreva'", "'fimenquanto'", "'enquanto'", "'fimprog.'", 
		"'para'", "'entao'", "'declare'", "'leia'", "'texto'", "'real'", "'+'", 
		"'-'", "'/'", "'*'", "':='", "OPREL", "ID", "NUMERO", "NUMERO_REAL", "','", 
		"';'", "'.'", "'('", "')'", "'{'", "'}'", "':'", "TEXTO", "STRING", "WS"
	};
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_cmdPara = 12, RULE_expr = 13, RULE_termo = 14, RULE_expr_ad = 15, 
		RULE_termo_ad = 16, RULE_fator = 17;
	public static final String[] ruleNames = {
		"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
		"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
		"cmdPara", "expr", "termo", "expr_ad", "termo_ad", "fator"
	};

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramaContext extends ParserRuleContext {
		public DeclaraContext declara() {
			return getRuleContext(DeclaraContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); match(T__12);
			setState(41);
			switch (_input.LA(1)) {
			case T__3:
				{
				{
				setState(37); declara();
				setState(38); bloco();
				}
				}
				break;
			case T__17:
			case T__11:
			case T__9:
			case T__7:
			case T__5:
			case T__2:
			case ID:
				{
				setState(40); bloco();
				}
				break;
			case T__6:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(43); match(T__6);

			                program.setsymbolTable(symbolTable);
			                program.setCommandList(stack.pop());
			           	  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaraContext extends ParserRuleContext {
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDeclara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDeclara(this);
		}
	}

	public final DeclaraContext declara() throws RecognitionException {
		DeclaraContext _localctx = new DeclaraContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46); declaravar();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlocoContext extends ParserRuleContext {
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

						  stack.push(new ArrayList<Command>());
						  
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52); comando();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclaravarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode VIRG(int i) {
			return getToken(IsiLanguageParser.VIRG, i);
		}
		public List<TerminalNode> VIRG() { return getTokens(IsiLanguageParser.VIRG); }
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(T__3);
			setState(58); tipo();
			setState(59); match(ID);

			      			String id_var = _input.LT(-1).getText();
			          		Symbol sym = new Var(id_var, null, currentType);
			          		if (!symbolTable.exists(id_var)){
				            	symbolTable.add(sym);	
				            }
				            else{
				                throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
				            } 
			      		
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(61); match(VIRG);
				setState(62); match(ID);
				 
				      			String id_vari = _input.LT(-1).getText();
				          		Symbol symb = new Var(id_vari, null, currentType);
				          		if (!symbolTable.exists(id_vari)){
					            	symbolTable.add(symb);	
					            }
					            else{
					               throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
					            }
				      		
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69); match(PO);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tipo);
		try {
			setState(77);
			switch (_input.LA(1)) {
			case T__15:
				enterOuterAlt(_localctx, 1);
				{
				setState(71); match(T__15);
				 currentType = Var.NUMBER;
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(73); match(T__0);
				 currentType = Var.REALNUMBER;
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(75); match(T__1);
				 currentType = Var.TEXT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComandoContext extends ParserRuleContext {
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
		}
		public CmdSeContext cmdSe() {
			return getRuleContext(CmdSeContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdFacaEnquantoContext cmdFacaEnquanto() {
			return getRuleContext(CmdFacaEnquantoContext.class,0);
		}
		public CmdParaContext cmdPara() {
			return getRuleContext(CmdParaContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_comando);
		try {
			setState(86);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(79); cmdAttrib();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); cmdLeitura();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(81); cmdEscrita();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 4);
				{
				setState(82); cmdSe();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(83); cmdEnquanto();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 6);
				{
				setState(84); cmdFacaEnquanto();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 7);
				{
				setState(85); cmdPara();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(T__2);
			setState(89); match(AP);
			setState(90); match(ID);

								checkInitialized(_input.LT(-1).getText());
			    				String ident = _input.LT(-1).getText();
			    			
			setState(92); match(FP);
			setState(93); match(PO);

			    				Var var = (Var)symbolTable.get(ident);
			              		Command cmdLeitura = new ReadCommand(ident, var);
			              		stack.peek().add(cmdLeitura);
								setHasValue(ident);
						 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(T__9);
			setState(97); match(AP);
			setState(103);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(98); match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(100); termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText, false); 
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(105); match(FP);
			setState(106); match(PO);
			 
						
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode SOMA() { return getToken(IsiLanguageParser.SOMA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode MULT() { return getToken(IsiLanguageParser.MULT, 0); }
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode STRING() { return getToken(IsiLanguageParser.STRING, 0); }
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdAttrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); match(ID);
			 
			                   		String id = _input.LT(-1).getText();
			                   		checkInitialized(id);
			                   		leftType = getTypeById(id);
			                   		String id_dois = id;
			                   		exprReset();
			                 	
			setState(112);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOMA) | (1L << SUB) | (1L << DIV) | (1L << MULT))) != 0)) {
				{
				setState(111);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOMA) | (1L << SUB) | (1L << DIV) | (1L << MULT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}


			                 		String op = _input.LT(-1).getText();
			                 		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
			                 			contExpr = id_dois + op;
			                 	}
			setState(115); match(OP_AT);
			setState(123);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case NUMERO_REAL:
			case AP:
			case TEXTO:
				{
				{
				setState(116); expr();
				setState(117); match(PO);

				                 			AttribCommand cmdAttrib = new AttribCommand(id_dois, contExpr);
											typeAttrib(leftType, id_dois, contExpr);
											setHasValue(id_dois);
											stack.peek().add(cmdAttrib);
									
				}
				}
				break;
			case STRING:
				{
				{
				setState(120); match(STRING);
					
										String str = _input.LT(-1).getText();
										stringType(id_dois);
										AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
										stack.peek().add(cmdAttrib);
								
				setState(122); match(PO);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdSeContext extends ParserRuleContext {
		public TerminalNode AC(int i) {
			return getToken(IsiLanguageParser.AC, i);
		}
		public TerminalNode FC(int i) {
			return getToken(IsiLanguageParser.FC, i);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> FC() { return getTokens(IsiLanguageParser.FC); }
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public List<TerminalNode> AC() { return getTokens(IsiLanguageParser.AC); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdSeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdSe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdSe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdSe(this);
		}
	}

	public final CmdSeContext cmdSe() throws RecognitionException {
		CmdSeContext _localctx = new CmdSeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); match(T__17);
			setState(126); match(AP);

									exprReset();
								
			setState(128); expr();

									exprDecision.push(contExpr);
									leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(130); match(OPREL);
			 
									operacao = _input.LT(-1).getText();
									op_atual = exprDecision.pop();
									op_nova = op_atual + operacao;
									exprDecision.push(op_nova);
									exprReset();
								
			setState(132); expr();

									op_atual = exprDecision.pop();
									op_nova = op_atual + contExpr;
									exprDecision.push(op_nova);
									rightType = getTypeIfValid(exTypeList, "direito", op_nova);
								
			setState(134); match(FP);

									if (rightType != leftType) { 
										throw new IsiLanguageSemanticException("Não é possível compará-los");
									}
								
			setState(156);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(136); match(T__4);
				setState(137); match(AC);
				 
				            			comList = new ArrayList<Command>(); 
				            			stack.push(comList);
				        			
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(139); comando();
					}
					}
					setState(142); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
				setState(144); match(FC);

				            				listT = stack.pop();
				            				String expreDecision = exprDecision.pop();
				            				listaVazia = new ArrayList<Command>();
				            				IfCommand ifCommand = new IfCommand(expreDecision, listT, listaVazia);
				            				stack.peek().add(ifCommand);
				        			
				setState(146); match(AC);
				 
										comList = new ArrayList<Command>(); 
				                      	stack.push(comList);
				                    
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148); comando();
					}
					}
					setState(151); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
				setState(153); match(FC);

				                       listT = stack.pop();	
									   String expDeci = exprDecision.pop();
									   listaVazia = new ArrayList<Command>();
									   IfCommand cmdEntao = new IfCommand(expreDecision, listT, listaVazia);
				                   	   stack.peek().add(cmdEntao);
				                    
				}
			}

			setState(169);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(158); match(T__16);
				setState(159); match(AC);

				                   	 	comList = new ArrayList<Command>();
				                   	 	stack.push(comList);
				                   	 
				{
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(161); comando();
					}
					}
					setState(164); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
				}
				setState(166); match(FC);

				                   		listF = stack.pop();
										int index = stack.peek().size() - 1; 
										stack.peek().remove(index); 
				                   		IfCommand cmdSeNao = new IfCommand(expreDecision, listT, listF);
				                   		stack.peek().add(cmdSeNao);
				                     
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdEnquantoContext extends ParserRuleContext {
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdEnquanto(this);
		}
	}

	public final CmdEnquantoContext cmdEnquanto() throws RecognitionException {
		CmdEnquantoContext _localctx = new CmdEnquantoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(T__7);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(173); match(AP);
			setState(174); expr();
			setState(175); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(177); expr();
			setState(178); match(FP);
			setState(179); match(T__11);
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(180); comando();
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			setState(185); match(T__8);
			 
			                  WhileCommand WhileCommand = new WhileCommand(strExpr, stack.pop()); 
			                  stack.peek().add(WhileCommand);
			               	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdFacaEnquantoContext extends ParserRuleContext {
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdFacaEnquantoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFacaEnquanto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdFacaEnquanto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdFacaEnquanto(this);
		}
	}

	public final CmdFacaEnquantoContext cmdFacaEnquanto() throws RecognitionException {
		CmdFacaEnquantoContext _localctx = new CmdFacaEnquantoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdFacaEnquanto);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(T__11);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(191); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(190); comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(193); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(195); match(T__7);
			setState(196); match(AP);
			setState(197); expr();
			setState(198); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(200); expr();
			setState(201); match(FP);
			setState(202); match(PO);
			 
			                     	DoWhileCommand DoWhileCommand = new DoWhileCommand(strExpr, stack.pop()); 
			                     	stack.peek().add(DoWhileCommand); 
			                  	
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode PO(int i) {
			return getToken(IsiLanguageParser.PO, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public List<TerminalNode> PO() { return getTokens(IsiLanguageParser.PO); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public CmdParaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdPara; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdPara(this);
		}
	}

	public final CmdParaContext cmdPara() throws RecognitionException {
		CmdParaContext _localctx = new CmdParaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205); match(T__5);
			setState(206); match(AP);
			setState(207); match(ID);
			setState(208); match(OP_AT);
			setState(209); expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(211); match(PO);
			setState(212); expr();
			setState(213); match(OPREL);
			setState(214); expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(216); match(PO);
			setState(217); match(ID);
			setState(218);
			_la = _input.LA(1);
			if ( !(_la==T__14 || _la==T__13) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(220); match(FP);
			setState(221); match(T__11);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(224); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(223); comando();
				}
				}
				setState(226); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			setState(228); match(T__10);

			               ForCommand ForCommand = new ForCommand(initialization, condition, increment, stack.pop()); 
			               stack.peek().add(ForCommand);
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Expr_adContext expr_ad() {
			return getRuleContext(Expr_adContext.class,0);
		}
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231); termo();
			setState(232); expr_ad();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermoContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public Termo_adContext termo_ad() {
			return getRuleContext(Termo_adContext.class,0);
		}
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234); fator();
			setState(235); termo_ad();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_adContext extends ParserRuleContext {
		public Expr_adContext expr_ad() {
			return getRuleContext(Expr_adContext.class,0);
		}
		public TerminalNode SOMA() { return getToken(IsiLanguageParser.SOMA, 0); }
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public Expr_adContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_ad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr_ad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr_ad(this);
		}
	}

	public final Expr_adContext expr_ad() throws RecognitionException {
		Expr_adContext _localctx = new Expr_adContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expr_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			switch (_input.LA(1)) {
			case SUB:
				{
				setState(237); match(SUB);
				 
								contExpr += '-';
								String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(239); termo();
				setState(240); expr_ad();
				}
				break;
			case SOMA:
				{
				setState(242); match(SOMA);
				 
				           		contExpr += '+'; String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(244); termo();
				setState(245); expr_ad();
				}
				break;
			case OPREL:
			case PO:
			case FP:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Termo_adContext extends ParserRuleContext {
		public FatorContext fator() {
			return getRuleContext(FatorContext.class,0);
		}
		public TerminalNode MULT() { return getToken(IsiLanguageParser.MULT, 0); }
		public Termo_adContext termo_ad() {
			return getRuleContext(Termo_adContext.class,0);
		}
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public Termo_adContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo_ad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo_ad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo_ad(this);
		}
	}

	public final Termo_adContext termo_ad() throws RecognitionException {
		Termo_adContext _localctx = new Termo_adContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_termo_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			switch (_input.LA(1)) {
			case DIV:
				{
				setState(249); match(DIV);
				 
				        		contExpr += '/'; 
				        		String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para divisão.");
				        		}
				        		exTypeList.add(tipo);
				    		
				setState(251); fator();
				setState(252); termo_ad();
				}
				break;
			case MULT:
				{
				setState(254); match(MULT);
				 
				        		contExpr += '*'; 
				        		String tipo = getTypeById(_input.LT(-1).getText()); 
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) {  
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para multiplicação.");
				        		}
				        		exTypeList.add(tipo); 
				    		
				setState(256); fator();
				setState(257); termo_ad();
				}
				break;
			case SOMA:
			case SUB:
			case OPREL:
			case PO:
			case FP:
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode NUMERO() { return getToken(IsiLanguageParser.NUMERO, 0); }
		public TerminalNode NUMERO_REAL() { return getToken(IsiLanguageParser.NUMERO_REAL, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public FatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterFator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitFator(this);
		}
	}

	public final FatorContext fator() throws RecognitionException {
		FatorContext _localctx = new FatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fator);
		try {
			setState(275);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(261); match(ID);
				 String id = _input.LT(-1).getText();
								 checkInitialized(id);
								 verificaAtribuicao(id);
								 String type = getTypeById(id);
								 contExpr += id; 
								 exTypeList.add(type);
							
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(263); match(NUMERO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("NUMBER");
							
				}
				break;
			case NUMERO_REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(265); match(NUMERO_REAL);
				contExpr += _input.LT(-1).getText();
									exTypeList.add("REALNUMBER");
							
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(267); match(TEXTO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("TEXT");
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(269); match(AP);
				 contExpr += _input.LT(-1).getText();
							
				setState(271); expr();
				setState(272); match(FP);
				 contExpr += _input.LT(-1).getText();
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3(\u0118\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\3\6\3\62\n\3\r\3"+
		"\16\3\63\3\4\3\4\6\48\n\4\r\4\16\49\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5C\n"+
		"\5\f\5\16\5F\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6P\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\tj\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\ns\n\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n~\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u008f\n\13\r\13\16\13"+
		"\u0090\3\13\3\13\3\13\3\13\3\13\6\13\u0098\n\13\r\13\16\13\u0099\3\13"+
		"\3\13\3\13\5\13\u009f\n\13\3\13\3\13\3\13\3\13\6\13\u00a5\n\13\r\13\16"+
		"\13\u00a6\3\13\3\13\3\13\5\13\u00ac\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\6\f\u00b8\n\f\r\f\16\f\u00b9\3\f\3\f\3\f\3\r\3\r\3\r\6\r\u00c2"+
		"\n\r\r\r\16\r\u00c3\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\6\16\u00e3\n\16\r\16\16\16\u00e4\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\5\21\u00fa\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u0106\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u0116\n\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"$\2\4\3\2\25\30\3\2\6\7\u0125\2&\3\2\2\2\4\61\3\2\2\2\6"+
		"\65\3\2\2\2\b;\3\2\2\2\nO\3\2\2\2\fX\3\2\2\2\16Z\3\2\2\2\20b\3\2\2\2\22"+
		"o\3\2\2\2\24\177\3\2\2\2\26\u00ad\3\2\2\2\30\u00be\3\2\2\2\32\u00cf\3"+
		"\2\2\2\34\u00e9\3\2\2\2\36\u00ec\3\2\2\2 \u00f9\3\2\2\2\"\u0105\3\2\2"+
		"\2$\u0115\3\2\2\2&+\7\b\2\2\'(\5\4\3\2()\5\6\4\2),\3\2\2\2*,\5\6\4\2+"+
		"\'\3\2\2\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-.\7\16\2\2./\b\2\1\2/\3\3\2\2"+
		"\2\60\62\5\b\5\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2"+
		"\2\64\5\3\2\2\2\65\67\b\4\1\2\668\5\f\7\2\67\66\3\2\2\289\3\2\2\29\67"+
		"\3\2\2\29:\3\2\2\2:\7\3\2\2\2;<\7\21\2\2<=\5\n\6\2=>\7\33\2\2>D\b\5\1"+
		"\2?@\7\36\2\2@A\7\33\2\2AC\b\5\1\2B?\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2"+
		"\2\2EG\3\2\2\2FD\3\2\2\2GH\7 \2\2H\t\3\2\2\2IJ\7\5\2\2JP\b\6\1\2KL\7\24"+
		"\2\2LP\b\6\1\2MN\7\23\2\2NP\b\6\1\2OI\3\2\2\2OK\3\2\2\2OM\3\2\2\2P\13"+
		"\3\2\2\2QY\5\22\n\2RY\5\16\b\2SY\5\20\t\2TY\5\24\13\2UY\5\26\f\2VY\5\30"+
		"\r\2WY\5\32\16\2XQ\3\2\2\2XR\3\2\2\2XS\3\2\2\2XT\3\2\2\2XU\3\2\2\2XV\3"+
		"\2\2\2XW\3\2\2\2Y\r\3\2\2\2Z[\7\22\2\2[\\\7!\2\2\\]\7\33\2\2]^\b\b\1\2"+
		"^_\7\"\2\2_`\7 \2\2`a\b\b\1\2a\17\3\2\2\2bc\7\13\2\2ci\7!\2\2de\7&\2\2"+
		"ej\b\t\1\2fg\5\36\20\2gh\b\t\1\2hj\3\2\2\2id\3\2\2\2if\3\2\2\2jk\3\2\2"+
		"\2kl\7\"\2\2lm\7 \2\2mn\b\t\1\2n\21\3\2\2\2op\7\33\2\2pr\b\n\1\2qs\t\2"+
		"\2\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\b\n\1\2u}\7\31\2\2vw\5\34\17\2wx"+
		"\7 \2\2xy\b\n\1\2y~\3\2\2\2z{\7\'\2\2{|\b\n\1\2|~\7 \2\2}v\3\2\2\2}z\3"+
		"\2\2\2~\23\3\2\2\2\177\u0080\7\3\2\2\u0080\u0081\7!\2\2\u0081\u0082\b"+
		"\13\1\2\u0082\u0083\5\34\17\2\u0083\u0084\b\13\1\2\u0084\u0085\7\32\2"+
		"\2\u0085\u0086\b\13\1\2\u0086\u0087\5\34\17\2\u0087\u0088\b\13\1\2\u0088"+
		"\u0089\7\"\2\2\u0089\u009e\b\13\1\2\u008a\u008b\7\20\2\2\u008b\u008c\7"+
		"#\2\2\u008c\u008e\b\13\1\2\u008d\u008f\5\f\7\2\u008e\u008d\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0093\7$\2\2\u0093\u0094\b\13\1\2\u0094\u0095\7#\2\2\u0095"+
		"\u0097\b\13\1\2\u0096\u0098\5\f\7\2\u0097\u0096\3\2\2\2\u0098\u0099\3"+
		"\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b"+
		"\u009c\7$\2\2\u009c\u009d\b\13\1\2\u009d\u009f\3\2\2\2\u009e\u008a\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00ab\3\2\2\2\u00a0\u00a1\7\4\2\2\u00a1"+
		"\u00a2\7#\2\2\u00a2\u00a4\b\13\1\2\u00a3\u00a5\5\f\7\2\u00a4\u00a3\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8\u00a9\7$\2\2\u00a9\u00aa\b\13\1\2\u00aa\u00ac\3\2"+
		"\2\2\u00ab\u00a0\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\25\3\2\2\2\u00ad\u00ae"+
		"\7\r\2\2\u00ae\u00af\b\f\1\2\u00af\u00b0\7!\2\2\u00b0\u00b1\5\34\17\2"+
		"\u00b1\u00b2\7\32\2\2\u00b2\u00b3\b\f\1\2\u00b3\u00b4\5\34\17\2\u00b4"+
		"\u00b5\7\"\2\2\u00b5\u00b7\7\t\2\2\u00b6\u00b8\5\f\7\2\u00b7\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bc\7\f\2\2\u00bc\u00bd\b\f\1\2\u00bd\27\3\2\2"+
		"\2\u00be\u00bf\7\t\2\2\u00bf\u00c1\b\r\1\2\u00c0\u00c2\5\f\7\2\u00c1\u00c0"+
		"\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c6\7\r\2\2\u00c6\u00c7\7!\2\2\u00c7\u00c8\5\34"+
		"\17\2\u00c8\u00c9\7\32\2\2\u00c9\u00ca\b\r\1\2\u00ca\u00cb\5\34\17\2\u00cb"+
		"\u00cc\7\"\2\2\u00cc\u00cd\7 \2\2\u00cd\u00ce\b\r\1\2\u00ce\31\3\2\2\2"+
		"\u00cf\u00d0\7\17\2\2\u00d0\u00d1\7!\2\2\u00d1\u00d2\7\33\2\2\u00d2\u00d3"+
		"\7\31\2\2\u00d3\u00d4\5\34\17\2\u00d4\u00d5\b\16\1\2\u00d5\u00d6\7 \2"+
		"\2\u00d6\u00d7\5\34\17\2\u00d7\u00d8\7\32\2\2\u00d8\u00d9\5\34\17\2\u00d9"+
		"\u00da\b\16\1\2\u00da\u00db\7 \2\2\u00db\u00dc\7\33\2\2\u00dc\u00dd\t"+
		"\3\2\2\u00dd\u00de\b\16\1\2\u00de\u00df\7\"\2\2\u00df\u00e0\7\t\2\2\u00e0"+
		"\u00e2\b\16\1\2\u00e1\u00e3\5\f\7\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4\3"+
		"\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e7\7\n\2\2\u00e7\u00e8\b\16\1\2\u00e8\33\3\2\2\2\u00e9\u00ea\5\36"+
		"\20\2\u00ea\u00eb\5 \21\2\u00eb\35\3\2\2\2\u00ec\u00ed\5$\23\2\u00ed\u00ee"+
		"\5\"\22\2\u00ee\37\3\2\2\2\u00ef\u00f0\7\26\2\2\u00f0\u00f1\b\21\1\2\u00f1"+
		"\u00f2\5\36\20\2\u00f2\u00f3\5 \21\2\u00f3\u00fa\3\2\2\2\u00f4\u00f5\7"+
		"\25\2\2\u00f5\u00f6\b\21\1\2\u00f6\u00f7\5\36\20\2\u00f7\u00f8\5 \21\2"+
		"\u00f8\u00fa\3\2\2\2\u00f9\u00ef\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00fa"+
		"\3\2\2\2\u00fa!\3\2\2\2\u00fb\u00fc\7\27\2\2\u00fc\u00fd\b\22\1\2\u00fd"+
		"\u00fe\5$\23\2\u00fe\u00ff\5\"\22\2\u00ff\u0106\3\2\2\2\u0100\u0101\7"+
		"\30\2\2\u0101\u0102\b\22\1\2\u0102\u0103\5$\23\2\u0103\u0104\5\"\22\2"+
		"\u0104\u0106\3\2\2\2\u0105\u00fb\3\2\2\2\u0105\u0100\3\2\2\2\u0105\u0106"+
		"\3\2\2\2\u0106#\3\2\2\2\u0107\u0108\7\33\2\2\u0108\u0116\b\23\1\2\u0109"+
		"\u010a\7\34\2\2\u010a\u0116\b\23\1\2\u010b\u010c\7\35\2\2\u010c\u0116"+
		"\b\23\1\2\u010d\u010e\7&\2\2\u010e\u0116\b\23\1\2\u010f\u0110\7!\2\2\u0110"+
		"\u0111\b\23\1\2\u0111\u0112\5\34\17\2\u0112\u0113\7\"\2\2\u0113\u0114"+
		"\b\23\1\2\u0114\u0116\3\2\2\2\u0115\u0107\3\2\2\2\u0115\u0109\3\2\2\2"+
		"\u0115\u010b\3\2\2\2\u0115\u010d\3\2\2\2\u0115\u010f\3\2\2\2\u0116%\3"+
		"\2\2\2\26+\639DOXir}\u0090\u0099\u009e\u00a6\u00ab\u00b9\u00c3\u00e4\u00f9"+
		"\u0105\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}