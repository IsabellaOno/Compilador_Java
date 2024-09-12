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
		T__18=1, T__17=2, T__16=3, T__15=4, T__14=5, T__13=6, T__12=7, T__11=8, 
		T__10=9, T__9=10, T__8=11, T__7=12, T__6=13, T__5=14, T__4=15, T__3=16, 
		T__2=17, T__1=18, T__0=19, OP=20, OP_AT=21, OPREL=22, ID=23, NUMERO=24, 
		VIRG=25, PV=26, PO=27, AP=28, FP=29, DP=30, TEXTO=31, WS=32;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'senao'", "'inteiro'", "'--'", "'++'", "'programa'", 
		"'faca'", "'fimpara'", "'fimse'", "'escreva'", "'fimenquanto'", "'enquanto'", 
		"'fimprog.'", "'para'", "'entao'", "'declare'", "'leia'", "'texto'", "'real'", 
		"OP", "':='", "OPREL", "ID", "NUMERO", "','", "';'", "'.'", "'('", "')'", 
		"':'", "TEXTO", "WS"
	};
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_cmdPara = 12, RULE_expr = 13, RULE_termo = 14, RULE_exprl = 15;
	public static final String[] ruleNames = {
		"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
		"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
		"cmdPara", "expr", "termo", "exprl"
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
			setState(32); match(T__13);
			setState(37);
			switch (_input.LA(1)) {
			case T__3:
				{
				{
				setState(33); declara();
				setState(34); bloco();
				}
				}
				break;
			case T__18:
			case T__12:
			case T__9:
			case T__7:
			case T__5:
			case T__2:
			case ID:
				{
				setState(36); bloco();
				}
				break;
			case T__6:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(39); match(T__6);

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
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42); declaravar();
				}
				}
				setState(45); 
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
						  
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48); comando();
				}
				}
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__12) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
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
		public TerminalNode DP() { return getToken(IsiLanguageParser.DP, 0); }
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
			setState(53); match(T__3);
			setState(54); tipo();
			 currentDecl.clear(); 
			setState(56); match(ID);
			 
			          		Var var = new Var(_input.LT(-1).getText(), currentType);
			          		currentDecl.add(var);
			      		
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(58); match(VIRG);
				setState(59); match(ID);
				 
				          		Var var = new Var(_input.LT(-1).getText(), currentType);
				          		currentDecl.add(var);
				      		
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66); match(DP);
			 
			          	updateType(); 
			      		
			setState(68); match(PO);
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
			setState(76);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(70); match(T__16);
				 currentType = Types.NUMBER; 
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); match(T__0);
				 currentType = Types.REALNUMBER; 
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(74); match(T__1);
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
			setState(85);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(78); cmdAttrib();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79); cmdLeitura();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(80); cmdEscrita();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 4);
				{
				setState(81); cmdSe();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(82); cmdEnquanto();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 6);
				{
				setState(83); cmdFacaEnquanto();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 7);
				{
				setState(84); cmdPara();
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
			setState(87); match(T__2);
			setState(88); match(AP);
			setState(89); match(ID);
			 
			    			String id = _input.LT(-1).getText();
			    			if (!isDeclared(id)) {
			        			throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			    			}
			    			symbolTable.setHasValue(id); // Marca a variável como inicializada
			    			symbolTable.markAsUsed(id); // Marca a variável como usada
			    
			    			Command cmdLeitura = new ReadCommand(symbolTable.get(id));
			   	 			stack.peek().add(cmdLeitura);
							
			setState(91); match(FP);
			setState(92); match(PO);
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
			setState(94); match(T__9);
			setState(95); match(AP);
			setState(101);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(96); match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(98); termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText); // Variável
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(103); match(FP);
			setState(104); match(PO);
			 
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

	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OP_AT() { return getToken(IsiLanguageParser.OP_AT, 0); }
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
			setState(107); match(ID);
			 
			                   String id = _input.LT(-1).getText();
			                   if (!isDeclared(id)) {
			                       throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			                   }
			                   symbolTable.get(id).setInitialized(true);
			                   leftType = symbolTable.get(id).getType();
			                 
			setState(109); match(OP_AT);
			setState(110); expr();
			setState(111); match(PO);

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

	public static class CmdSeContext extends ParserRuleContext {
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
			setState(114); match(T__18);
			 stack.push(new ArrayList<Command>());
			                     strExpr = "";
			                      currentIfCommand = new IfCommand();
			                  
			setState(116); match(AP);
			setState(117); expr();
			setState(118); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(120); expr();
			setState(121); match(FP);
			 currentIfCommand.setExpression(strExpr); 
					      	
			setState(123); match(T__4);
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124); comando();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__12) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			            	
			setState(139);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(130); match(T__17);
				 stack.push(new ArrayList<Command>()); 
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132); comando();
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__12) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );

				                     currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(141); match(T__10);

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
			setState(144); match(T__7);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(146); match(AP);
			setState(147); expr();
			setState(148); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(150); expr();
			setState(151); match(FP);
			setState(152); match(T__12);
			setState(154); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(153); comando();
				}
				}
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__12) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			setState(158); match(T__8);
			 
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
			setState(161); match(T__12);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(164); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(163); comando();
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
			setState(168); match(T__7);
			setState(169); match(AP);
			setState(170); expr();
			setState(171); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(173); expr();
			setState(174); match(FP);
			setState(175); match(PO);
			 
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
			setState(178); match(T__5);
			setState(179); match(AP);
			setState(180); match(ID);
			setState(181); match(OP_AT);
			setState(182); expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(184); match(PO);
			setState(185); expr();
			setState(186); match(OPREL);
			setState(187); expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(189); match(PO);
			setState(190); match(ID);
			setState(191);
			_la = _input.LA(1);
			if ( !(_la==T__15 || _la==T__14) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(193); match(FP);
			setState(194); match(T__12);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(196); comando();
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__12) | (1L << T__9) | (1L << T__7) | (1L << T__5) | (1L << T__2) | (1L << ID))) != 0) );
			setState(201); match(T__11);

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
			setState(204); termo();
			 strExpr += _input.LT(-1).getText(); 
			setState(206); exprl();
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
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(208); match(ID);
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
				setState(210); match(NUMERO);
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
				setState(212); match(TEXTO);
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

	public static class ExprlContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(IsiLanguageParser.OP); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public TerminalNode OP(int i) {
			return getToken(IsiLanguageParser.OP, i);
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
				setState(216); match(OP);
				 strExpr += _input.LT(-1).getText(); 
				setState(218); termo();
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u00e5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\5\2(\n\2\3\2\3\2\3\2\3\3\6\3.\n\3\r\3\16\3/\3\4\3\4\6\4\64"+
		"\n\4\r\4\16\4\65\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5@\n\5\f\5\16\5C\13"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7X\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\th\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u0080\n\13\r\13\16"+
		"\13\u0081\3\13\3\13\3\13\3\13\6\13\u0088\n\13\r\13\16\13\u0089\3\13\3"+
		"\13\5\13\u008e\n\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\6\f\u009d\n\f\r\f\16\f\u009e\3\f\3\f\3\f\3\r\3\r\3\r\6\r\u00a7\n"+
		"\r\r\r\16\r\u00a8\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\6\16\u00c8\n\16\r\16\16\16\u00c9\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d9\n\20\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u00e0\n\21\f\21\16\21\u00e3\13\21\3\21\2\2\22\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\6\7\u00eb\2\"\3\2\2\2\4-\3\2"+
		"\2\2\6\61\3\2\2\2\b\67\3\2\2\2\nN\3\2\2\2\fW\3\2\2\2\16Y\3\2\2\2\20`\3"+
		"\2\2\2\22m\3\2\2\2\24t\3\2\2\2\26\u0092\3\2\2\2\30\u00a3\3\2\2\2\32\u00b4"+
		"\3\2\2\2\34\u00ce\3\2\2\2\36\u00d8\3\2\2\2 \u00e1\3\2\2\2\"\'\7\b\2\2"+
		"#$\5\4\3\2$%\5\6\4\2%(\3\2\2\2&(\5\6\4\2\'#\3\2\2\2\'&\3\2\2\2\'(\3\2"+
		"\2\2()\3\2\2\2)*\7\17\2\2*+\b\2\1\2+\3\3\2\2\2,.\5\b\5\2-,\3\2\2\2./\3"+
		"\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\5\3\2\2\2\61\63\b\4\1\2\62\64\5\f\7\2"+
		"\63\62\3\2\2\2\64\65\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\7\3\2\2\2"+
		"\678\7\22\2\289\5\n\6\29:\b\5\1\2:;\7\31\2\2;A\b\5\1\2<=\7\33\2\2=>\7"+
		"\31\2\2>@\b\5\1\2?<\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA"+
		"\3\2\2\2DE\7 \2\2EF\b\5\1\2FG\7\35\2\2G\t\3\2\2\2HI\7\5\2\2IO\b\6\1\2"+
		"JK\7\25\2\2KO\b\6\1\2LM\7\24\2\2MO\b\6\1\2NH\3\2\2\2NJ\3\2\2\2NL\3\2\2"+
		"\2O\13\3\2\2\2PX\5\22\n\2QX\5\16\b\2RX\5\20\t\2SX\5\24\13\2TX\5\26\f\2"+
		"UX\5\30\r\2VX\5\32\16\2WP\3\2\2\2WQ\3\2\2\2WR\3\2\2\2WS\3\2\2\2WT\3\2"+
		"\2\2WU\3\2\2\2WV\3\2\2\2X\r\3\2\2\2YZ\7\23\2\2Z[\7\36\2\2[\\\7\31\2\2"+
		"\\]\b\b\1\2]^\7\37\2\2^_\7\35\2\2_\17\3\2\2\2`a\7\f\2\2ag\7\36\2\2bc\7"+
		"!\2\2ch\b\t\1\2de\5\36\20\2ef\b\t\1\2fh\3\2\2\2gb\3\2\2\2gd\3\2\2\2hi"+
		"\3\2\2\2ij\7\37\2\2jk\7\35\2\2kl\b\t\1\2l\21\3\2\2\2mn\7\31\2\2no\b\n"+
		"\1\2op\7\27\2\2pq\5\34\17\2qr\7\35\2\2rs\b\n\1\2s\23\3\2\2\2tu\7\3\2\2"+
		"uv\b\13\1\2vw\7\36\2\2wx\5\34\17\2xy\7\30\2\2yz\b\13\1\2z{\5\34\17\2{"+
		"|\7\37\2\2|}\b\13\1\2}\177\7\21\2\2~\u0080\5\f\7\2\177~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2"+
		"\2\u0083\u008d\b\13\1\2\u0084\u0085\7\4\2\2\u0085\u0087\b\13\1\2\u0086"+
		"\u0088\5\f\7\2\u0087\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\b\13\1\2\u008c"+
		"\u008e\3\2\2\2\u008d\u0084\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0090\7\13\2\2\u0090\u0091\b\13\1\2\u0091\25\3\2\2\2\u0092"+
		"\u0093\7\16\2\2\u0093\u0094\b\f\1\2\u0094\u0095\7\36\2\2\u0095\u0096\5"+
		"\34\17\2\u0096\u0097\7\30\2\2\u0097\u0098\b\f\1\2\u0098\u0099\5\34\17"+
		"\2\u0099\u009a\7\37\2\2\u009a\u009c\7\t\2\2\u009b\u009d\5\f\7\2\u009c"+
		"\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2"+
		"\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\7\r\2\2\u00a1\u00a2\b\f\1\2\u00a2"+
		"\27\3\2\2\2\u00a3\u00a4\7\t\2\2\u00a4\u00a6\b\r\1\2\u00a5\u00a7\5\f\7"+
		"\2\u00a6\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7\16\2\2\u00ab\u00ac\7\36\2\2"+
		"\u00ac\u00ad\5\34\17\2\u00ad\u00ae\7\30\2\2\u00ae\u00af\b\r\1\2\u00af"+
		"\u00b0\5\34\17\2\u00b0\u00b1\7\37\2\2\u00b1\u00b2\7\35\2\2\u00b2\u00b3"+
		"\b\r\1\2\u00b3\31\3\2\2\2\u00b4\u00b5\7\20\2\2\u00b5\u00b6\7\36\2\2\u00b6"+
		"\u00b7\7\31\2\2\u00b7\u00b8\7\27\2\2\u00b8\u00b9\5\34\17\2\u00b9\u00ba"+
		"\b\16\1\2\u00ba\u00bb\7\35\2\2\u00bb\u00bc\5\34\17\2\u00bc\u00bd\7\30"+
		"\2\2\u00bd\u00be\5\34\17\2\u00be\u00bf\b\16\1\2\u00bf\u00c0\7\35\2\2\u00c0"+
		"\u00c1\7\31\2\2\u00c1\u00c2\t\2\2\2\u00c2\u00c3\b\16\1\2\u00c3\u00c4\7"+
		"\37\2\2\u00c4\u00c5\7\t\2\2\u00c5\u00c7\b\16\1\2\u00c6\u00c8\5\f\7\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2"+
		"\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\n\2\2\u00cc\u00cd\b\16\1\2\u00cd"+
		"\33\3\2\2\2\u00ce\u00cf\5\36\20\2\u00cf\u00d0\b\17\1\2\u00d0\u00d1\5 "+
		"\21\2\u00d1\35\3\2\2\2\u00d2\u00d3\7\31\2\2\u00d3\u00d9\b\20\1\2\u00d4"+
		"\u00d5\7\32\2\2\u00d5\u00d9\b\20\1\2\u00d6\u00d7\7!\2\2\u00d7\u00d9\b"+
		"\20\1\2\u00d8\u00d2\3\2\2\2\u00d8\u00d4\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\37\3\2\2\2\u00da\u00db\7\26\2\2\u00db\u00dc\b\21\1\2\u00dc\u00dd\5\36"+
		"\20\2\u00dd\u00de\b\21\1\2\u00de\u00e0\3\2\2\2\u00df\u00da\3\2\2\2\u00e0"+
		"\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2!\3\2\2\2"+
		"\u00e3\u00e1\3\2\2\2\21\'/\65ANWg\u0081\u0089\u008d\u009e\u00a8\u00c9"+
		"\u00d8\u00e1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}