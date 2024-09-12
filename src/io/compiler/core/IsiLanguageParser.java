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
		T__17=18, T__18=19, OP=20, OP_AT=21, OPREL=22, ID=23, NUMERO=24, VIRG=25, 
		PV=26, PO=27, AP=28, FP=29, DP=30, TEXTO=31, WS=32;
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_cmdPara = 12, RULE_expr = 13, RULE_termo = 14, RULE_exprl = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
			"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
			"cmdPara", "expr", "termo", "exprl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'inteiro'", "'real'", 
			"'texto'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'fimse'", 
			"'enquanto'", "'faca'", "'fimenquanto'", "'para'", "'++'", "'--'", "'fimpara'", 
			null, "':='", null, null, null, "','", "';'", "'.'", "'('", "')'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "OP", "OP_AT", "OPREL", 
			"ID", "NUMERO", "VIRG", "PV", "PO", "AP", "FP", "DP", "TEXTO", "WS"
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
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private IfCommand currentIfCommand;
	    private Stack<ArrayList<Command>> stack = new Stack<>();
	    private Stack<String> stackExprDecision = new Stack<String>();
	    
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8479616L) != 0) );
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
		public TerminalNode DP() { return getToken(IsiLanguageParser.DP, 0); }
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
			 currentDecl.clear(); 
			setState(56);
			match(ID);
			 
			          		Var var = new Var(_input.LT(-1).getText(), currentType);
			          		currentDecl.add(var);
			      		
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(58);
				match(VIRG);
				setState(59);
				match(ID);
				 
				          		Var var = new Var(_input.LT(-1).getText(), currentType);
				          		currentDecl.add(var);
				      		
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(DP);
			 
			          	updateType(); 
			      		
			setState(68);
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
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__3);
				 currentType = Types.NUMBER; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(T__4);
				 currentType = Types.REALNUMBER; 
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				match(T__5);
				 currentType = Types.TEXT; 
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
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				cmdAttrib();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				cmdLeitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				cmdEscrita();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				cmdSe();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(82);
				cmdEnquanto();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(83);
				cmdFacaEnquanto();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 7);
				{
				setState(84);
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
			setState(87);
			match(T__6);
			setState(88);
			match(AP);
			setState(89);
			match(ID);
			 
			    			String id = _input.LT(-1).getText();
			    			if (!isDeclared(id)) {
			        			throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			    			}
			    			symbolTable.setHasValue(id); // Marca a variável como inicializada
			    			symbolTable.markAsUsed(id); // Marca a variável como usada
			    
			    			Command cmdLeitura = new ReadCommand(symbolTable.get(id));
			   	 			stack.peek().add(cmdLeitura);
							
			setState(91);
			match(FP);
			setState(92);
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
			setState(94);
			match(T__7);
			setState(95);
			match(AP);
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(96);
				match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(98);
				termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText); // Variável
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(103);
			match(FP);
			setState(104);
			match(PO);
			 
			    		rightType = null;
						
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PO() { return getToken(IsiLanguageParser.PO, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(ID);
			 
			                   String id = _input.LT(-1).getText();
			                   if (!isDeclared(id)) {
			                       throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			                   }
			                   symbolTable.get(id).setInitialized(true);
			                   leftType = symbolTable.get(id).getType();
			                 
			setState(109);
			match(OP_AT);
			setState(110);
			expr();
			setState(111);
			match(PO);

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
			setState(114);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                     strExpr = "";
			                      currentIfCommand = new IfCommand();
			                  
			setState(116);
			match(AP);
			setState(117);
			expr();
			setState(118);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(120);
			expr();
			setState(121);
			match(FP);
			 currentIfCommand.setExpression(strExpr); 
					      	
			setState(123);
			match(T__9);
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				comando();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8479616L) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			            	
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(130);
				match(T__10);
				 stack.push(new ArrayList<Command>()); 
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132);
					comando();
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8479616L) != 0) );

				                     currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(141);
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
			setState(144);
			match(T__12);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(146);
			match(AP);
			setState(147);
			expr();
			setState(148);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(150);
			expr();
			setState(151);
			match(FP);
			setState(152);
			match(T__13);
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153);
				comando();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8479616L) != 0) );
			setState(158);
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
			setState(161);
			match(T__13);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(164); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(163);
					comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(166); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(168);
			match(T__12);
			setState(169);
			match(AP);
			setState(170);
			expr();
			setState(171);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(173);
			expr();
			setState(174);
			match(FP);
			setState(175);
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
			setState(178);
			match(T__15);
			setState(179);
			match(AP);
			setState(180);
			match(ID);
			setState(181);
			match(OP_AT);
			setState(182);
			expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(184);
			match(PO);
			setState(185);
			expr();
			setState(186);
			match(OPREL);
			setState(187);
			expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(189);
			match(PO);
			setState(190);
			match(ID);
			setState(191);
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
					      
			setState(193);
			match(FP);
			setState(194);
			match(T__13);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(196);
				comando();
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8479616L) != 0) );
			setState(201);
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
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
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
			setState(204);
			termo();
			 strExpr += _input.LT(-1).getText(); 
			setState(206);
			exprl();
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
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode NUMERO() { return getToken(IsiLanguageParser.NUMERO, 0); }
		public TerminalNode TEXTO() { return getToken(IsiLanguageParser.TEXTO, 0); }
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
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(208);
				match(ID);
				 if (!isDeclared(_input.LT(-1).getText())) {
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
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(NUMERO);
				  if (rightType == null) {
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
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				match(TEXTO);
				  if (rightType == null) {
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
	public static class ExprlContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(IsiLanguageParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLanguageParser.OP, i);
		}
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(216);
				match(OP);
				 strExpr += _input.LT(-1).getText(); 
				setState(218);
				termo();
				 strExpr += _input.LT(-1).getText(); 
				}
				}
				setState(225);
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

	public static final String _serializedATN =
		"\u0004\u0001 \u00e3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000"+
		"&\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001"+
		",\b\u0001\u000b\u0001\f\u0001-\u0001\u0002\u0001\u0002\u0004\u00022\b"+
		"\u0002\u000b\u0002\f\u00023\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003>\b"+
		"\u0003\n\u0003\f\u0003A\t\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004M\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007f\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0004\t~\b\t\u000b\t\f\t\u007f\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0004\t\u0086\b\t\u000b\t\f\t\u0087\u0001\t\u0001\t\u0003\t"+
		"\u008c\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u009b\b\n\u000b\n\f"+
		"\n\u009c\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0004"+
		"\u000b\u00a5\b\u000b\u000b\u000b\f\u000b\u00a6\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0004\f\u00c6\b\f\u000b\f\f\f\u00c7"+
		"\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00d7"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00de\b\u000f\n\u000f\f\u000f\u00e1\t\u000f\u0001\u000f\u0000\u0000"+
		"\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e\u0000\u0001\u0001\u0000\u0011\u0012\u00e9\u0000 \u0001"+
		"\u0000\u0000\u0000\u0002+\u0001\u0000\u0000\u0000\u0004/\u0001\u0000\u0000"+
		"\u0000\u00065\u0001\u0000\u0000\u0000\bL\u0001\u0000\u0000\u0000\nU\u0001"+
		"\u0000\u0000\u0000\fW\u0001\u0000\u0000\u0000\u000e^\u0001\u0000\u0000"+
		"\u0000\u0010k\u0001\u0000\u0000\u0000\u0012r\u0001\u0000\u0000\u0000\u0014"+
		"\u0090\u0001\u0000\u0000\u0000\u0016\u00a1\u0001\u0000\u0000\u0000\u0018"+
		"\u00b2\u0001\u0000\u0000\u0000\u001a\u00cc\u0001\u0000\u0000\u0000\u001c"+
		"\u00d6\u0001\u0000\u0000\u0000\u001e\u00df\u0001\u0000\u0000\u0000 %\u0005"+
		"\u0001\u0000\u0000!\"\u0003\u0002\u0001\u0000\"#\u0003\u0004\u0002\u0000"+
		"#&\u0001\u0000\u0000\u0000$&\u0003\u0004\u0002\u0000%!\u0001\u0000\u0000"+
		"\u0000%$\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000"+
		"\u0000\u0000\'(\u0005\u0002\u0000\u0000()\u0006\u0000\uffff\uffff\u0000"+
		")\u0001\u0001\u0000\u0000\u0000*,\u0003\u0006\u0003\u0000+*\u0001\u0000"+
		"\u0000\u0000,-\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001"+
		"\u0000\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/1\u0006\u0002\uffff"+
		"\uffff\u000002\u0003\n\u0005\u000010\u0001\u0000\u0000\u000023\u0001\u0000"+
		"\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000\u0000\u00004\u0005"+
		"\u0001\u0000\u0000\u000056\u0005\u0003\u0000\u000067\u0003\b\u0004\u0000"+
		"78\u0006\u0003\uffff\uffff\u000089\u0005\u0017\u0000\u00009?\u0006\u0003"+
		"\uffff\uffff\u0000:;\u0005\u0019\u0000\u0000;<\u0005\u0017\u0000\u0000"+
		"<>\u0006\u0003\uffff\uffff\u0000=:\u0001\u0000\u0000\u0000>A\u0001\u0000"+
		"\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@B\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BC\u0005\u001e\u0000\u0000"+
		"CD\u0006\u0003\uffff\uffff\u0000DE\u0005\u001b\u0000\u0000E\u0007\u0001"+
		"\u0000\u0000\u0000FG\u0005\u0004\u0000\u0000GM\u0006\u0004\uffff\uffff"+
		"\u0000HI\u0005\u0005\u0000\u0000IM\u0006\u0004\uffff\uffff\u0000JK\u0005"+
		"\u0006\u0000\u0000KM\u0006\u0004\uffff\uffff\u0000LF\u0001\u0000\u0000"+
		"\u0000LH\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000M\t\u0001\u0000"+
		"\u0000\u0000NV\u0003\u0010\b\u0000OV\u0003\f\u0006\u0000PV\u0003\u000e"+
		"\u0007\u0000QV\u0003\u0012\t\u0000RV\u0003\u0014\n\u0000SV\u0003\u0016"+
		"\u000b\u0000TV\u0003\u0018\f\u0000UN\u0001\u0000\u0000\u0000UO\u0001\u0000"+
		"\u0000\u0000UP\u0001\u0000\u0000\u0000UQ\u0001\u0000\u0000\u0000UR\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UT\u0001\u0000\u0000\u0000"+
		"V\u000b\u0001\u0000\u0000\u0000WX\u0005\u0007\u0000\u0000XY\u0005\u001c"+
		"\u0000\u0000YZ\u0005\u0017\u0000\u0000Z[\u0006\u0006\uffff\uffff\u0000"+
		"[\\\u0005\u001d\u0000\u0000\\]\u0005\u001b\u0000\u0000]\r\u0001\u0000"+
		"\u0000\u0000^_\u0005\b\u0000\u0000_e\u0005\u001c\u0000\u0000`a\u0005\u001f"+
		"\u0000\u0000af\u0006\u0007\uffff\uffff\u0000bc\u0003\u001c\u000e\u0000"+
		"cd\u0006\u0007\uffff\uffff\u0000df\u0001\u0000\u0000\u0000e`\u0001\u0000"+
		"\u0000\u0000eb\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005"+
		"\u001d\u0000\u0000hi\u0005\u001b\u0000\u0000ij\u0006\u0007\uffff\uffff"+
		"\u0000j\u000f\u0001\u0000\u0000\u0000kl\u0005\u0017\u0000\u0000lm\u0006"+
		"\b\uffff\uffff\u0000mn\u0005\u0015\u0000\u0000no\u0003\u001a\r\u0000o"+
		"p\u0005\u001b\u0000\u0000pq\u0006\b\uffff\uffff\u0000q\u0011\u0001\u0000"+
		"\u0000\u0000rs\u0005\t\u0000\u0000st\u0006\t\uffff\uffff\u0000tu\u0005"+
		"\u001c\u0000\u0000uv\u0003\u001a\r\u0000vw\u0005\u0016\u0000\u0000wx\u0006"+
		"\t\uffff\uffff\u0000xy\u0003\u001a\r\u0000yz\u0005\u001d\u0000\u0000z"+
		"{\u0006\t\uffff\uffff\u0000{}\u0005\n\u0000\u0000|~\u0003\n\u0005\u0000"+
		"}|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
		"\u0000\u0000\u0000\u0081\u008b\u0006\t\uffff\uffff\u0000\u0082\u0083\u0005"+
		"\u000b\u0000\u0000\u0083\u0085\u0006\t\uffff\uffff\u0000\u0084\u0086\u0003"+
		"\n\u0005\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000"+
		"\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a\u0006\t\uffff"+
		"\uffff\u0000\u008a\u008c\u0001\u0000\u0000\u0000\u008b\u0082\u0001\u0000"+
		"\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0005\f\u0000\u0000\u008e\u008f\u0006\t\uffff"+
		"\uffff\u0000\u008f\u0013\u0001\u0000\u0000\u0000\u0090\u0091\u0005\r\u0000"+
		"\u0000\u0091\u0092\u0006\n\uffff\uffff\u0000\u0092\u0093\u0005\u001c\u0000"+
		"\u0000\u0093\u0094\u0003\u001a\r\u0000\u0094\u0095\u0005\u0016\u0000\u0000"+
		"\u0095\u0096\u0006\n\uffff\uffff\u0000\u0096\u0097\u0003\u001a\r\u0000"+
		"\u0097\u0098\u0005\u001d\u0000\u0000\u0098\u009a\u0005\u000e\u0000\u0000"+
		"\u0099\u009b\u0003\n\u0005\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b"+
		"\u009c\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\u000f\u0000\u0000\u009f\u00a0\u0006\n\uffff\uffff\u0000\u00a0"+
		"\u0015\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005\u000e\u0000\u0000\u00a2"+
		"\u00a4\u0006\u000b\uffff\uffff\u0000\u00a3\u00a5\u0003\n\u0005\u0000\u00a4"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\r\u0000\u0000\u00a9\u00aa"+
		"\u0005\u001c\u0000\u0000\u00aa\u00ab\u0003\u001a\r\u0000\u00ab\u00ac\u0005"+
		"\u0016\u0000\u0000\u00ac\u00ad\u0006\u000b\uffff\uffff\u0000\u00ad\u00ae"+
		"\u0003\u001a\r\u0000\u00ae\u00af\u0005\u001d\u0000\u0000\u00af\u00b0\u0005"+
		"\u001b\u0000\u0000\u00b0\u00b1\u0006\u000b\uffff\uffff\u0000\u00b1\u0017"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0010\u0000\u0000\u00b3\u00b4"+
		"\u0005\u001c\u0000\u0000\u00b4\u00b5\u0005\u0017\u0000\u0000\u00b5\u00b6"+
		"\u0005\u0015\u0000\u0000\u00b6\u00b7\u0003\u001a\r\u0000\u00b7\u00b8\u0006"+
		"\f\uffff\uffff\u0000\u00b8\u00b9\u0005\u001b\u0000\u0000\u00b9\u00ba\u0003"+
		"\u001a\r\u0000\u00ba\u00bb\u0005\u0016\u0000\u0000\u00bb\u00bc\u0003\u001a"+
		"\r\u0000\u00bc\u00bd\u0006\f\uffff\uffff\u0000\u00bd\u00be\u0005\u001b"+
		"\u0000\u0000\u00be\u00bf\u0005\u0017\u0000\u0000\u00bf\u00c0\u0007\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0006\f\uffff\uffff\u0000\u00c1\u00c2\u0005\u001d"+
		"\u0000\u0000\u00c2\u00c3\u0005\u000e\u0000\u0000\u00c3\u00c5\u0006\f\uffff"+
		"\uffff\u0000\u00c4\u00c6\u0003\n\u0005\u0000\u00c5\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0005\u0013\u0000\u0000\u00ca\u00cb\u0006\f\uffff\uffff"+
		"\u0000\u00cb\u0019\u0001\u0000\u0000\u0000\u00cc\u00cd\u0003\u001c\u000e"+
		"\u0000\u00cd\u00ce\u0006\r\uffff\uffff\u0000\u00ce\u00cf\u0003\u001e\u000f"+
		"\u0000\u00cf\u001b\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u0017\u0000"+
		"\u0000\u00d1\u00d7\u0006\u000e\uffff\uffff\u0000\u00d2\u00d3\u0005\u0018"+
		"\u0000\u0000\u00d3\u00d7\u0006\u000e\uffff\uffff\u0000\u00d4\u00d5\u0005"+
		"\u001f\u0000\u0000\u00d5\u00d7\u0006\u000e\uffff\uffff\u0000\u00d6\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d2\u0001\u0000\u0000\u0000\u00d6\u00d4"+
		"\u0001\u0000\u0000\u0000\u00d7\u001d\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0005\u0014\u0000\u0000\u00d9\u00da\u0006\u000f\uffff\uffff\u0000\u00da"+
		"\u00db\u0003\u001c\u000e\u0000\u00db\u00dc\u0006\u000f\uffff\uffff\u0000"+
		"\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00d8\u0001\u0000\u0000\u0000"+
		"\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000"+
		"\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u001f\u0001\u0000\u0000\u0000"+
		"\u00e1\u00df\u0001\u0000\u0000\u0000\u000f%-3?LUe\u007f\u0087\u008b\u009c"+
		"\u00a6\u00c7\u00d6\u00df";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}