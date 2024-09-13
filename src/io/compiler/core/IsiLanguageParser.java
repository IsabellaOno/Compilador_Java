// Generated from IsiLanguage.g4 by ANTLR 4.13.2
package io.compiler.core;

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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class IsiLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, SOMA=20, SUB=21, DIV=22, MULT=23, OP_AT=24, OPREL=25, 
		ID=26, NUMERO=27, NUMERO_REAL=28, VIRG=29, PV=30, PO=31, AP=32, FP=33, 
		DP=34, TEXTO=35, STRING=36, WS=37;
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_cmdPara = 12, RULE_expr = 13, RULE_termo = 14, RULE_fator = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
			"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
			"cmdPara", "expr", "termo", "fator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'inteiro'", "'real'", 
			"'texto'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'fimse'", 
			"'enquanto'", "'faca'", "'fimenquanto'", "'para'", "'++'", "'--'", "'fimpara'", 
			"'+'", "'-'", "'/'", "'*'", "':='", null, null, null, null, "','", "';'", 
			"'.'", "'('", "')'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "SOMA", "SUB", "DIV", 
			"MULT", "OP_AT", "OPREL", "ID", "NUMERO", "NUMERO_REAL", "VIRG", "PV", 
			"PO", "AP", "FP", "DP", "TEXTO", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

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
					throw new IsiLanguageSemanticException("Tipos incompatíveis");
				}
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public DeclaraContext declara() {
			return getRuleContext(DeclaraContext.class,0);
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
			setState(32);
			match(T__0);
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				{
				setState(33);
				declara();
				setState(34);
				bloco();
				}
				}
				break;
			case T__6:
			case T__7:
			case T__8:
			case T__12:
			case T__13:
			case T__15:
			case ID:
				{
				setState(36);
				bloco();
				}
				break;
			case T__1:
				break;
			default:
				break;
			}
			setState(39);
			match(T__1);

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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaraContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
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
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				declaravar();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
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
						  
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				comando();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67199872L) != 0) );
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public List<TerminalNode> VIRG() { return getTokens(IsiLanguageParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(IsiLanguageParser.VIRG, i);
		}
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
			setState(53);
			match(T__2);
			setState(54);
			tipo();
			setState(55);
			match(ID);

			      			String id_var = _input.LT(-1).getText();
			          		Symbol sym = new Var(id_var, null, currentType);
			          		if (!symbolTable.exists(id_var)){
				            	symbolTable.add(sym);	
				            }
				            else{
				                throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
				            } 
			      		
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(57);
				match(VIRG);
				setState(58);
				match(ID);
				 
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
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(PO);
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

	@SuppressWarnings("CheckReturnValue")
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
			setState(73);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				match(T__3);
				 currentType = Var.NUMBER;
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__4);
				 currentType = Var.REALNUMBER;
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdSeContext cmdSe() {
			return getRuleContext(CmdSeContext.class,0);
		}
		public CmdEnquantoContext cmdEnquanto() {
			return getRuleContext(CmdEnquantoContext.class,0);
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
			setState(82);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				cmdAttrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				cmdLeitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(77);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				cmdSe();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
				cmdEnquanto();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(80);
				cmdFacaEnquanto();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 7);
				{
				setState(81);
				cmdPara();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
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
			setState(84);
			match(T__6);
			setState(85);
			match(AP);
			setState(86);
			match(ID);

								checkInitialized(_input.LT(-1).getText());
			    				String ident = _input.LT(-1).getText();
			    			
			setState(88);
			match(FP);
			setState(89);
			match(PO);

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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
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
			setState(92);
			match(T__7);
			setState(93);
			match(AP);
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(94);
				match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(96);
				termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText, false); 
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(101);
			match(FP);
			setState(102);
			match(PO);
			 
						
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public TerminalNode SOMA() { return getToken(IsiLanguageParser.SOMA, 0); }
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TerminalNode MULT() { return getToken(IsiLanguageParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public TerminalNode STRING() { return getToken(IsiLanguageParser.STRING, 0); }
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
			setState(105);
			match(ID);
			 
			                   		String id = _input.LT(-1).getText();
			                   		checkInitialized(id);
			                   		leftType = getTypeById(id);
			                   		String id_dois = id;
			                   		exprReset();
			                 	
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) {
				{
				setState(107);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}


			                 		String op = _input.LT(-1).getText();
			                 		if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
			                 			contExpr = id_dois + op;
			                 	}
			setState(111);
			match(OP_AT);
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case NUMERO_REAL:
			case AP:
			case TEXTO:
				{
				{
				setState(112);
				expr();
				setState(113);
				match(PO);

				                 			AttribCommand cmdAttrib = new AttribCommand(id_dois, contExpr);
											checkTypeAttrib(leftType, id_dois, contExpr);
											setHasValue(id_dois);
											stack.peek().add(cmdAttrib);
									
				}
				}
				break;
			case STRING:
				{
				{
				setState(116);
				match(STRING);
					
										String str = _input.LT(-1).getText();
										stringType(id_dois);
										AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
										stack.peek().add(cmdAttrib);
								
				setState(118);
				match(PO);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdSeContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
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
			setState(121);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                     strExpr = "";
			                      currentIfCommand = new IfCommand();
			                  
			setState(123);
			match(AP);
			setState(124);
			expr();
			setState(125);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(127);
			expr();
			setState(128);
			match(FP);
			 currentIfCommand.setExpression(strExpr); 
					      	
			setState(130);
			match(T__9);
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131);
				comando();
				}
				}
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67199872L) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			            	
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(137);
				match(T__10);
				 stack.push(new ArrayList<Command>()); 
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(139);
					comando();
					}
					}
					setState(142); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67199872L) != 0) );

				                     currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(148);
			match(T__11);

			               	stack.peek().add(currentIfCommand);
			               
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
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
			setState(151);
			match(T__12);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(153);
			match(AP);
			setState(154);
			expr();
			setState(155);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(157);
			expr();
			setState(158);
			match(FP);
			setState(159);
			match(T__13);
			setState(161); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160);
				comando();
				}
				}
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67199872L) != 0) );
			setState(165);
			match(T__14);
			 
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdFacaEnquantoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
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
			setState(168);
			match(T__13);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(171); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(170);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(173); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(175);
			match(T__12);
			setState(176);
			match(AP);
			setState(177);
			expr();
			setState(178);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(180);
			expr();
			setState(181);
			match(FP);
			setState(182);
			match(PO);
			 
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

	@SuppressWarnings("CheckReturnValue")
	public static class CmdParaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> PO() { return getTokens(IsiLanguageParser.PO); }
		public TerminalNode PO(int i) {
			return getToken(IsiLanguageParser.PO, i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
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
			setState(185);
			match(T__15);
			setState(186);
			match(AP);
			setState(187);
			match(ID);
			setState(188);
			match(OP_AT);
			setState(189);
			expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(191);
			match(PO);
			setState(192);
			expr();
			setState(193);
			match(OPREL);
			setState(194);
			expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(196);
			match(PO);
			setState(197);
			match(ID);
			setState(198);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(200);
			match(FP);
			setState(201);
			match(T__13);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(204); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(203);
				comando();
				}
				}
				setState(206); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 67199872L) != 0) );
			setState(208);
			match(T__18);

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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> SOMA() { return getTokens(IsiLanguageParser.SOMA); }
		public TerminalNode SOMA(int i) {
			return getToken(IsiLanguageParser.SOMA, i);
		}
		public List<TerminalNode> SUB() { return getTokens(IsiLanguageParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(IsiLanguageParser.SUB, i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			termo();
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SOMA || _la==SUB) {
				{
				setState(220);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SOMA:
					{
					setState(212);
					match(SOMA);
					setState(213);
					termo();
					 contExpr += '+'; 
					}
					break;
				case SUB:
					{
					setState(216);
					match(SUB);
					setState(217);
					termo();
					 contExpr += '-'; exTypeList.add("NUMBER"); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public List<FatorContext> fator() {
			return getRuleContexts(FatorContext.class);
		}
		public FatorContext fator(int i) {
			return getRuleContext(FatorContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(IsiLanguageParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(IsiLanguageParser.MULT, i);
		}
		public List<TerminalNode> DIV() { return getTokens(IsiLanguageParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(IsiLanguageParser.DIV, i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			fator();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV || _la==MULT) {
				{
				setState(234);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case MULT:
					{
					setState(226);
					match(MULT);
					setState(227);
					fator();
					 contExpr += '*'; exTypeList.add("NUMBER"); 
					}
					break;
				case DIV:
					{
					setState(230);
					match(DIV);
					setState(231);
					fator();
					 contExpr += '/'; exTypeList.add("NUMBER"); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode NUMERO() { return getToken(IsiLanguageParser.NUMERO, 0); }
		public TerminalNode NUMERO_REAL() { return getToken(IsiLanguageParser.NUMERO_REAL, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
		enterRule(_localctx, 30, RULE_fator);
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(ID);
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
				setState(241);
				match(NUMERO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("NUMBER");
				}
				break;
			case NUMERO_REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				match(NUMERO_REAL);
				contExpr += _input.LT(-1).getText();
									exTypeList.add("REALNUMBER");
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				match(TEXTO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("TEXT");
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(247);
				match(AP);
				 contExpr += _input.LT(-1).getText();
				setState(249);
				expr();
				setState(250);
				match(FP);
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
		"\u0004\u0001%\u0100\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"&\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001"+
		",\b\u0001\u000b\u0001\f\u0001-\u0001\u0002\u0001\u0002\u0004\u00022\b"+
		"\u0002\u000b\u0002\f\u00023\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003=\b\u0003\n\u0003"+
		"\f\u0003@\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004J\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005S\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007d\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0003\bm\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bx\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004"+
		"\t\u0085\b\t\u000b\t\f\t\u0086\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t"+
		"\u008d\b\t\u000b\t\f\t\u008e\u0001\t\u0001\t\u0003\t\u0093\b\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0004\n\u00a2\b\n\u000b\n\f\n\u00a3\u0001\n"+
		"\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u00ac"+
		"\b\u000b\u000b\u000b\f\u000b\u00ad\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0004\f\u00cd\b\f\u000b\f\f\f\u00ce\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00dd\b\r\n\r\f\r\u00e0\t\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u00eb\b\u000e\n\u000e\f\u000e\u00ee\t\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u00fe\b\u000f\u0001\u000f\u0000\u0000\u0010"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e\u0000\u0002\u0001\u0000\u0014\u0017\u0001\u0000\u0011\u0012"+
		"\u010d\u0000 \u0001\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u0004"+
		"/\u0001\u0000\u0000\u0000\u00065\u0001\u0000\u0000\u0000\bI\u0001\u0000"+
		"\u0000\u0000\nR\u0001\u0000\u0000\u0000\fT\u0001\u0000\u0000\u0000\u000e"+
		"\\\u0001\u0000\u0000\u0000\u0010i\u0001\u0000\u0000\u0000\u0012y\u0001"+
		"\u0000\u0000\u0000\u0014\u0097\u0001\u0000\u0000\u0000\u0016\u00a8\u0001"+
		"\u0000\u0000\u0000\u0018\u00b9\u0001\u0000\u0000\u0000\u001a\u00d3\u0001"+
		"\u0000\u0000\u0000\u001c\u00e1\u0001\u0000\u0000\u0000\u001e\u00fd\u0001"+
		"\u0000\u0000\u0000 %\u0005\u0001\u0000\u0000!\"\u0003\u0002\u0001\u0000"+
		"\"#\u0003\u0004\u0002\u0000#&\u0001\u0000\u0000\u0000$&\u0003\u0004\u0002"+
		"\u0000%!\u0001\u0000\u0000\u0000%$\u0001\u0000\u0000\u0000%&\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0005\u0002\u0000\u0000()\u0006"+
		"\u0000\uffff\uffff\u0000)\u0001\u0001\u0000\u0000\u0000*,\u0003\u0006"+
		"\u0003\u0000+*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-+\u0001"+
		"\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000.\u0003\u0001\u0000\u0000"+
		"\u0000/1\u0006\u0002\uffff\uffff\u000002\u0003\n\u0005\u000010\u0001\u0000"+
		"\u0000\u000023\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001"+
		"\u0000\u0000\u00004\u0005\u0001\u0000\u0000\u000056\u0005\u0003\u0000"+
		"\u000067\u0003\b\u0004\u000078\u0005\u001a\u0000\u00008>\u0006\u0003\uffff"+
		"\uffff\u00009:\u0005\u001d\u0000\u0000:;\u0005\u001a\u0000\u0000;=\u0006"+
		"\u0003\uffff\uffff\u0000<9\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000AB\u0005\u001f\u0000\u0000B\u0007"+
		"\u0001\u0000\u0000\u0000CD\u0005\u0004\u0000\u0000DJ\u0006\u0004\uffff"+
		"\uffff\u0000EF\u0005\u0005\u0000\u0000FJ\u0006\u0004\uffff\uffff\u0000"+
		"GH\u0005\u0006\u0000\u0000HJ\u0006\u0004\uffff\uffff\u0000IC\u0001\u0000"+
		"\u0000\u0000IE\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000J\t\u0001"+
		"\u0000\u0000\u0000KS\u0003\u0010\b\u0000LS\u0003\f\u0006\u0000MS\u0003"+
		"\u000e\u0007\u0000NS\u0003\u0012\t\u0000OS\u0003\u0014\n\u0000PS\u0003"+
		"\u0016\u000b\u0000QS\u0003\u0018\f\u0000RK\u0001\u0000\u0000\u0000RL\u0001"+
		"\u0000\u0000\u0000RM\u0001\u0000\u0000\u0000RN\u0001\u0000\u0000\u0000"+
		"RO\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RQ\u0001\u0000\u0000"+
		"\u0000S\u000b\u0001\u0000\u0000\u0000TU\u0005\u0007\u0000\u0000UV\u0005"+
		" \u0000\u0000VW\u0005\u001a\u0000\u0000WX\u0006\u0006\uffff\uffff\u0000"+
		"XY\u0005!\u0000\u0000YZ\u0005\u001f\u0000\u0000Z[\u0006\u0006\uffff\uffff"+
		"\u0000[\r\u0001\u0000\u0000\u0000\\]\u0005\b\u0000\u0000]c\u0005 \u0000"+
		"\u0000^_\u0005#\u0000\u0000_d\u0006\u0007\uffff\uffff\u0000`a\u0003\u001c"+
		"\u000e\u0000ab\u0006\u0007\uffff\uffff\u0000bd\u0001\u0000\u0000\u0000"+
		"c^\u0001\u0000\u0000\u0000c`\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000ef\u0005!\u0000\u0000fg\u0005\u001f\u0000\u0000gh\u0006\u0007\uffff"+
		"\uffff\u0000h\u000f\u0001\u0000\u0000\u0000ij\u0005\u001a\u0000\u0000"+
		"jl\u0006\b\uffff\uffff\u0000km\u0007\u0000\u0000\u0000lk\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000no\u0006\b\uffff"+
		"\uffff\u0000ow\u0005\u0018\u0000\u0000pq\u0003\u001a\r\u0000qr\u0005\u001f"+
		"\u0000\u0000rs\u0006\b\uffff\uffff\u0000sx\u0001\u0000\u0000\u0000tu\u0005"+
		"$\u0000\u0000uv\u0006\b\uffff\uffff\u0000vx\u0005\u001f\u0000\u0000wp"+
		"\u0001\u0000\u0000\u0000wt\u0001\u0000\u0000\u0000x\u0011\u0001\u0000"+
		"\u0000\u0000yz\u0005\t\u0000\u0000z{\u0006\t\uffff\uffff\u0000{|\u0005"+
		" \u0000\u0000|}\u0003\u001a\r\u0000}~\u0005\u0019\u0000\u0000~\u007f\u0006"+
		"\t\uffff\uffff\u0000\u007f\u0080\u0003\u001a\r\u0000\u0080\u0081\u0005"+
		"!\u0000\u0000\u0081\u0082\u0006\t\uffff\uffff\u0000\u0082\u0084\u0005"+
		"\n\u0000\u0000\u0083\u0085\u0003\n\u0005\u0000\u0084\u0083\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0092\u0006\t\uffff\uffff\u0000\u0089\u008a\u0005\u000b"+
		"\u0000\u0000\u008a\u008c\u0006\t\uffff\uffff\u0000\u008b\u008d\u0003\n"+
		"\u0005\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000"+
		"\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000"+
		"\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0006\t\uffff"+
		"\uffff\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092\u0089\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0005\f\u0000\u0000\u0095\u0096\u0006\t\uffff"+
		"\uffff\u0000\u0096\u0013\u0001\u0000\u0000\u0000\u0097\u0098\u0005\r\u0000"+
		"\u0000\u0098\u0099\u0006\n\uffff\uffff\u0000\u0099\u009a\u0005 \u0000"+
		"\u0000\u009a\u009b\u0003\u001a\r\u0000\u009b\u009c\u0005\u0019\u0000\u0000"+
		"\u009c\u009d\u0006\n\uffff\uffff\u0000\u009d\u009e\u0003\u001a\r\u0000"+
		"\u009e\u009f\u0005!\u0000\u0000\u009f\u00a1\u0005\u000e\u0000\u0000\u00a0"+
		"\u00a2\u0003\n\u0005\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u0005\u000f\u0000\u0000\u00a6\u00a7\u0006\n\uffff\uffff\u0000\u00a7\u0015"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u000e\u0000\u0000\u00a9\u00ab"+
		"\u0006\u000b\uffff\uffff\u0000\u00aa\u00ac\u0003\n\u0005\u0000\u00ab\u00aa"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0005\r\u0000\u0000\u00b0\u00b1\u0005"+
		" \u0000\u0000\u00b1\u00b2\u0003\u001a\r\u0000\u00b2\u00b3\u0005\u0019"+
		"\u0000\u0000\u00b3\u00b4\u0006\u000b\uffff\uffff\u0000\u00b4\u00b5\u0003"+
		"\u001a\r\u0000\u00b5\u00b6\u0005!\u0000\u0000\u00b6\u00b7\u0005\u001f"+
		"\u0000\u0000\u00b7\u00b8\u0006\u000b\uffff\uffff\u0000\u00b8\u0017\u0001"+
		"\u0000\u0000\u0000\u00b9\u00ba\u0005\u0010\u0000\u0000\u00ba\u00bb\u0005"+
		" \u0000\u0000\u00bb\u00bc\u0005\u001a\u0000\u0000\u00bc\u00bd\u0005\u0018"+
		"\u0000\u0000\u00bd\u00be\u0003\u001a\r\u0000\u00be\u00bf\u0006\f\uffff"+
		"\uffff\u0000\u00bf\u00c0\u0005\u001f\u0000\u0000\u00c0\u00c1\u0003\u001a"+
		"\r\u0000\u00c1\u00c2\u0005\u0019\u0000\u0000\u00c2\u00c3\u0003\u001a\r"+
		"\u0000\u00c3\u00c4\u0006\f\uffff\uffff\u0000\u00c4\u00c5\u0005\u001f\u0000"+
		"\u0000\u00c5\u00c6\u0005\u001a\u0000\u0000\u00c6\u00c7\u0007\u0001\u0000"+
		"\u0000\u00c7\u00c8\u0006\f\uffff\uffff\u0000\u00c8\u00c9\u0005!\u0000"+
		"\u0000\u00c9\u00ca\u0005\u000e\u0000\u0000\u00ca\u00cc\u0006\f\uffff\uffff"+
		"\u0000\u00cb\u00cd\u0003\n\u0005\u0000\u00cc\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000"+
		"\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000"+
		"\u00d0\u00d1\u0005\u0013\u0000\u0000\u00d1\u00d2\u0006\f\uffff\uffff\u0000"+
		"\u00d2\u0019\u0001\u0000\u0000\u0000\u00d3\u00de\u0003\u001c\u000e\u0000"+
		"\u00d4\u00d5\u0005\u0014\u0000\u0000\u00d5\u00d6\u0003\u001c\u000e\u0000"+
		"\u00d6\u00d7\u0006\r\uffff\uffff\u0000\u00d7\u00dd\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0005\u0015\u0000\u0000\u00d9\u00da\u0003\u001c\u000e\u0000"+
		"\u00da\u00db\u0006\r\uffff\uffff\u0000\u00db\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dc\u00d4\u0001\u0000\u0000\u0000\u00dc\u00d8\u0001\u0000\u0000\u0000"+
		"\u00dd\u00e0\u0001\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u001b\u0001\u0000\u0000\u0000"+
		"\u00e0\u00de\u0001\u0000\u0000\u0000\u00e1\u00ec\u0003\u001e\u000f\u0000"+
		"\u00e2\u00e3\u0005\u0017\u0000\u0000\u00e3\u00e4\u0003\u001e\u000f\u0000"+
		"\u00e4\u00e5\u0006\u000e\uffff\uffff\u0000\u00e5\u00eb\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0005\u0016\u0000\u0000\u00e7\u00e8\u0003\u001e\u000f"+
		"\u0000\u00e8\u00e9\u0006\u000e\uffff\uffff\u0000\u00e9\u00eb\u0001\u0000"+
		"\u0000\u0000\u00ea\u00e2\u0001\u0000\u0000\u0000\u00ea\u00e6\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ee\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u001d\u0001\u0000"+
		"\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u001a"+
		"\u0000\u0000\u00f0\u00fe\u0006\u000f\uffff\uffff\u0000\u00f1\u00f2\u0005"+
		"\u001b\u0000\u0000\u00f2\u00fe\u0006\u000f\uffff\uffff\u0000\u00f3\u00f4"+
		"\u0005\u001c\u0000\u0000\u00f4\u00fe\u0006\u000f\uffff\uffff\u0000\u00f5"+
		"\u00f6\u0005#\u0000\u0000\u00f6\u00fe\u0006\u000f\uffff\uffff\u0000\u00f7"+
		"\u00f8\u0005 \u0000\u0000\u00f8\u00f9\u0006\u000f\uffff\uffff\u0000\u00f9"+
		"\u00fa\u0003\u001a\r\u0000\u00fa\u00fb\u0005!\u0000\u0000\u00fb\u00fc"+
		"\u0006\u000f\uffff\uffff\u0000\u00fc\u00fe\u0001\u0000\u0000\u0000\u00fd"+
		"\u00ef\u0001\u0000\u0000\u0000\u00fd\u00f1\u0001\u0000\u0000\u0000\u00fd"+
		"\u00f3\u0001\u0000\u0000\u0000\u00fd\u00f5\u0001\u0000\u0000\u0000\u00fd"+
		"\u00f7\u0001\u0000\u0000\u0000\u00fe\u001f\u0001\u0000\u0000\u0000\u0014"+
		"%-3>IRclw\u0086\u008e\u0092\u00a3\u00ad\u00ce\u00dc\u00de\u00ea\u00ec"+
		"\u00fd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}