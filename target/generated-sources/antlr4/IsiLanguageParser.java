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
		T__1=17, T__0=18, OP=19, OP_AT=20, OPREL=21, ID=22, NUMERO=23, VIRG=24, 
		PV=25, PO=26, AP=27, FP=28, DP=29, TEXTO=30, WS=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'senao'", "'numero'", "'--'", "'++'", "'programa'", 
		"'faca'", "'fimpara'", "'fimse'", "'escreva'", "'fimenquanto'", "'enquanto'", 
		"'fimprog.'", "'para'", "'entao'", "'declare'", "'leia'", "'texto'", "OP", 
		"':='", "OPREL", "ID", "NUMERO", "','", "';'", "'.'", "'('", "')'", "':'", 
		"TEXTO", "WS"
	};
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_comando = 4, RULE_cmdLeitura = 5, RULE_cmdEscrita = 6, RULE_cmdAttrib = 7, 
		RULE_cmdSe = 8, RULE_cmdEnquanto = 9, RULE_cmdFacaEnquanto = 10, RULE_cmdPara = 11, 
		RULE_expr = 12, RULE_termo = 13, RULE_exprl = 14;
	public static final String[] ruleNames = {
		"programa", "declara", "bloco", "declaravar", "comando", "cmdLeitura", 
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
			setState(30); match(T__12);
			setState(35);
			switch (_input.LA(1)) {
			case T__2:
				{
				{
				setState(31); declara();
				setState(32); bloco();
				}
				}
				break;
			case T__17:
			case T__11:
			case T__8:
			case T__6:
			case T__4:
			case T__1:
			case ID:
				{
				setState(34); bloco();
				}
				break;
			case T__5:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(37); match(T__5);

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
			setState(41); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(40); declaravar();
				}
				}
				setState(43); 
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
						  
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46); comando();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
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
			setState(51); match(T__2);
			 currentDecl.clear(); 
			setState(53); match(ID);
			 currentDecl.add(new Var(_input.LT(-1).getText()));
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(55); match(VIRG);
				setState(56); match(ID);
				 currentDecl.add(new Var(_input.LT(-1).getText()));
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); match(DP);
			setState(68);
			switch (_input.LA(1)) {
			case T__15:
				{
				setState(64); match(T__15);
				currentType = Types.NUMBER;
				}
				break;
			case T__0:
				{
				setState(66); match(T__0);
				currentType = Types.TEXT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(71); match(PO);
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
		enterRule(_localctx, 8, RULE_comando);
		try {
			setState(80);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(73); cmdAttrib();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(74); cmdLeitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(75); cmdEscrita();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 4);
				{
				setState(76); cmdSe();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(77); cmdEnquanto();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 6);
				{
				setState(78); cmdFacaEnquanto();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 7);
				{
				setState(79); cmdPara();
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
		enterRule(_localctx, 10, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(T__1);
			setState(83); match(AP);
			setState(84); match(ID);
			 
			    			String id = _input.LT(-1).getText();
			    			if (!isDeclared(id)) {
			        			throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			    			}
			    			symbolTable.setHasValue(id); // Marca a variável como inicializada
			    			symbolTable.markAsUsed(id); // Marca a variável como usada
			    
			    			Command cmdLeitura = new ReadCommand(symbolTable.get(id));
			   	 			stack.peek().add(cmdLeitura);
							
			setState(86); match(FP);
			setState(87); match(PO);
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
		enterRule(_localctx, 12, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(T__8);
			setState(90); match(AP);
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(91); match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(93); termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText); // Variável
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(98); match(FP);
			setState(99); match(PO);
			 
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
		enterRule(_localctx, 14, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(ID);
			 
			                   String id = _input.LT(-1).getText();
			                   if (!isDeclared(id)) {
			                       throw new IsiLanguageSemanticException("Undeclared Variable: " + id);
			                   }
			                   symbolTable.get(id).setInitialized(true);
			                   leftType = symbolTable.get(id).getType();
			                 
			setState(104); match(OP_AT);
			setState(105); expr();
			setState(106); match(PO);

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
		enterRule(_localctx, 16, RULE_cmdSe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); match(T__17);
			 stack.push(new ArrayList<Command>());
			                     strExpr = "";
			                      currentIfCommand = new IfCommand();
			                  
			setState(111); match(AP);
			setState(112); expr();
			setState(113); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(115); expr();
			setState(116); match(FP);
			 currentIfCommand.setExpression(strExpr); 
					      	
			setState(118); match(T__3);
			setState(120); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(119); comando();
				}
				}
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			 
			                  currentIfCommand.setTrueList(stack.pop());                            
			            	
			setState(134);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(125); match(T__16);
				 stack.push(new ArrayList<Command>()); 
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(127); comando();
					}
					}
					setState(130); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );

				                     currentIfCommand.setFalseList(stack.pop());
				                 
				}
			}

			setState(136); match(T__9);

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
		enterRule(_localctx, 18, RULE_cmdEnquanto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(T__6);
			 
			                  stack.push(new ArrayList<Command>());
			                  strExpr = ""; 
			               	
			setState(141); match(AP);
			setState(142); expr();
			setState(143); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(145); expr();
			setState(146); match(FP);
			setState(147); match(T__11);
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			setState(153); match(T__7);
			 
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
		enterRule(_localctx, 20, RULE_cmdFacaEnquanto);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(T__11);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(159); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(158); comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(161); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(163); match(T__6);
			setState(164); match(AP);
			setState(165); expr();
			setState(166); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(168); expr();
			setState(169); match(FP);
			setState(170); match(PO);
			 
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
		enterRule(_localctx, 22, RULE_cmdPara);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); match(T__4);
			setState(174); match(AP);
			setState(175); match(ID);
			setState(176); match(OP_AT);
			setState(177); expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(179); match(PO);
			setState(180); expr();
			setState(181); match(OPREL);
			setState(182); expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(184); match(PO);
			setState(185); match(ID);
			setState(186);
			_la = _input.LA(1);
			if ( !(_la==T__14 || _la==T__13) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(188); match(FP);
			setState(189); match(T__11);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(192); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(191); comando();
				}
				}
				setState(194); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__11) | (1L << T__8) | (1L << T__6) | (1L << T__4) | (1L << T__1) | (1L << ID))) != 0) );
			setState(196); match(T__10);

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
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); termo();
			 strExpr += _input.LT(-1).getText(); 
			setState(201); exprl();
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
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(209);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(203); match(ID);
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
				setState(205); match(NUMERO);
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
				setState(207); match(TEXTO);
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
		enterRule(_localctx, 28, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(211); match(OP);
				 strExpr += _input.LT(-1).getText(); 
				setState(213); termo();
				 strExpr += _input.LT(-1).getText(); 
				}
				}
				setState(220);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00e0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\5\2&\n\2\3\2\3\2\3\2\3\3\6\3,\n\3\r\3\16\3-\3\4\3\4\6\4\62\n\4\r\4\16"+
		"\4\63\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5=\n\5\f\5\16\5@\13\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5G\n\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6S\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bc\n\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\6\n{\n\n\r\n\16\n|\3\n\3\n\3\n\3\n\6\n\u0083\n\n\r\n\16\n\u0084"+
		"\3\n\3\n\5\n\u0089\n\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\6\13\u0098\n\13\r\13\16\13\u0099\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\6\f\u00a2\n\f\r\f\16\f\u00a3\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\6\r\u00c3\n\r\r\r\16\r\u00c4\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00d4\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u00db\n\20\f\20\16\20\u00de\13\20\3\20\2\2\21\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36\2\3\3\2\6\7\u00e6\2 \3\2\2\2\4+\3\2\2\2\6/\3"+
		"\2\2\2\b\65\3\2\2\2\nR\3\2\2\2\fT\3\2\2\2\16[\3\2\2\2\20h\3\2\2\2\22o"+
		"\3\2\2\2\24\u008d\3\2\2\2\26\u009e\3\2\2\2\30\u00af\3\2\2\2\32\u00c9\3"+
		"\2\2\2\34\u00d3\3\2\2\2\36\u00dc\3\2\2\2 %\7\b\2\2!\"\5\4\3\2\"#\5\6\4"+
		"\2#&\3\2\2\2$&\5\6\4\2%!\3\2\2\2%$\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\7\17"+
		"\2\2()\b\2\1\2)\3\3\2\2\2*,\5\b\5\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3"+
		"\2\2\2.\5\3\2\2\2/\61\b\4\1\2\60\62\5\n\6\2\61\60\3\2\2\2\62\63\3\2\2"+
		"\2\63\61\3\2\2\2\63\64\3\2\2\2\64\7\3\2\2\2\65\66\7\22\2\2\66\67\b\5\1"+
		"\2\678\7\30\2\28>\b\5\1\29:\7\32\2\2:;\7\30\2\2;=\b\5\1\2<9\3\2\2\2=@"+
		"\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AF\7\37\2\2BC\7\5\2\2"+
		"CG\b\5\1\2DE\7\24\2\2EG\b\5\1\2FB\3\2\2\2FD\3\2\2\2GH\3\2\2\2HI\b\5\1"+
		"\2IJ\7\34\2\2J\t\3\2\2\2KS\5\20\t\2LS\5\f\7\2MS\5\16\b\2NS\5\22\n\2OS"+
		"\5\24\13\2PS\5\26\f\2QS\5\30\r\2RK\3\2\2\2RL\3\2\2\2RM\3\2\2\2RN\3\2\2"+
		"\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2S\13\3\2\2\2TU\7\23\2\2UV\7\35\2\2VW\7"+
		"\30\2\2WX\b\7\1\2XY\7\36\2\2YZ\7\34\2\2Z\r\3\2\2\2[\\\7\f\2\2\\b\7\35"+
		"\2\2]^\7 \2\2^c\b\b\1\2_`\5\34\17\2`a\b\b\1\2ac\3\2\2\2b]\3\2\2\2b_\3"+
		"\2\2\2cd\3\2\2\2de\7\36\2\2ef\7\34\2\2fg\b\b\1\2g\17\3\2\2\2hi\7\30\2"+
		"\2ij\b\t\1\2jk\7\26\2\2kl\5\32\16\2lm\7\34\2\2mn\b\t\1\2n\21\3\2\2\2o"+
		"p\7\3\2\2pq\b\n\1\2qr\7\35\2\2rs\5\32\16\2st\7\27\2\2tu\b\n\1\2uv\5\32"+
		"\16\2vw\7\36\2\2wx\b\n\1\2xz\7\21\2\2y{\5\n\6\2zy\3\2\2\2{|\3\2\2\2|z"+
		"\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\u0088\b\n\1\2\177\u0080\7\4\2\2\u0080\u0082"+
		"\b\n\1\2\u0081\u0083\5\n\6\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\n"+
		"\1\2\u0087\u0089\3\2\2\2\u0088\177\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a"+
		"\3\2\2\2\u008a\u008b\7\13\2\2\u008b\u008c\b\n\1\2\u008c\23\3\2\2\2\u008d"+
		"\u008e\7\16\2\2\u008e\u008f\b\13\1\2\u008f\u0090\7\35\2\2\u0090\u0091"+
		"\5\32\16\2\u0091\u0092\7\27\2\2\u0092\u0093\b\13\1\2\u0093\u0094\5\32"+
		"\16\2\u0094\u0095\7\36\2\2\u0095\u0097\7\t\2\2\u0096\u0098\5\n\6\2\u0097"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u009c\7\r\2\2\u009c\u009d\b\13\1\2\u009d"+
		"\25\3\2\2\2\u009e\u009f\7\t\2\2\u009f\u00a1\b\f\1\2\u00a0\u00a2\5\n\6"+
		"\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\7\16\2\2\u00a6\u00a7\7\35\2\2"+
		"\u00a7\u00a8\5\32\16\2\u00a8\u00a9\7\27\2\2\u00a9\u00aa\b\f\1\2\u00aa"+
		"\u00ab\5\32\16\2\u00ab\u00ac\7\36\2\2\u00ac\u00ad\7\34\2\2\u00ad\u00ae"+
		"\b\f\1\2\u00ae\27\3\2\2\2\u00af\u00b0\7\20\2\2\u00b0\u00b1\7\35\2\2\u00b1"+
		"\u00b2\7\30\2\2\u00b2\u00b3\7\26\2\2\u00b3\u00b4\5\32\16\2\u00b4\u00b5"+
		"\b\r\1\2\u00b5\u00b6\7\34\2\2\u00b6\u00b7\5\32\16\2\u00b7\u00b8\7\27\2"+
		"\2\u00b8\u00b9\5\32\16\2\u00b9\u00ba\b\r\1\2\u00ba\u00bb\7\34\2\2\u00bb"+
		"\u00bc\7\30\2\2\u00bc\u00bd\t\2\2\2\u00bd\u00be\b\r\1\2\u00be\u00bf\7"+
		"\36\2\2\u00bf\u00c0\7\t\2\2\u00c0\u00c2\b\r\1\2\u00c1\u00c3\5\n\6\2\u00c2"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\7\n\2\2\u00c7\u00c8\b\r\1\2\u00c8"+
		"\31\3\2\2\2\u00c9\u00ca\5\34\17\2\u00ca\u00cb\b\16\1\2\u00cb\u00cc\5\36"+
		"\20\2\u00cc\33\3\2\2\2\u00cd\u00ce\7\30\2\2\u00ce\u00d4\b\17\1\2\u00cf"+
		"\u00d0\7\31\2\2\u00d0\u00d4\b\17\1\2\u00d1\u00d2\7 \2\2\u00d2\u00d4\b"+
		"\17\1\2\u00d3\u00cd\3\2\2\2\u00d3\u00cf\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4"+
		"\35\3\2\2\2\u00d5\u00d6\7\25\2\2\u00d6\u00d7\b\20\1\2\u00d7\u00d8\5\34"+
		"\17\2\u00d8\u00d9\b\20\1\2\u00d9\u00db\3\2\2\2\u00da\u00d5\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\37\3\2\2"+
		"\2\u00de\u00dc\3\2\2\2\21%-\63>FRb|\u0084\u0088\u0099\u00a3\u00c4\u00d3"+
		"\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}