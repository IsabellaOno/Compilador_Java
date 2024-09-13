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
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, SOMA=18, SUB=19, DIV=20, MULT=21, OP_AT=22, OPREL=23, ID=24, 
		NUMERO=25, NUMERO_REAL=26, VIRG=27, PV=28, PO=29, AP=30, FP=31, AC=32, 
		FC=33, DP=34, TEXTO=35, STRING=36, WS=37;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'senao'", "'inteiro'", "'--'", "'++'", "'programa'", 
		"'faca'", "'fimpara'", "'escreva'", "'enquanto'", "'fimprog.'", "'para'", 
		"'entao'", "'declare'", "'leia'", "'texto'", "'real'", "'+'", "'-'", "'/'", 
		"'*'", "':='", "OPREL", "ID", "NUMERO", "NUMERO_REAL", "','", "';'", "'.'", 
		"'('", "')'", "'{'", "'}'", "':'", "TEXTO", "STRING", "WS"
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
			setState(36); match(T__11);
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
			case T__16:
			case T__10:
			case T__8:
			case T__7:
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
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
			case T__14:
				enterOuterAlt(_localctx, 1);
				{
				setState(71); match(T__14);
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
				setState(79); cmdAttrib();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); cmdLeitura();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(81); cmdEscrita();
				}
				break;
			case T__16:
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
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(84); cmdFacaEnquanto();
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

								checkInitialized(_input.LT(-1).getText());
			    				String ident = _input.LT(-1).getText();
			    			
			setState(91); match(FP);
			setState(92); match(PO);

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
			setState(95); match(T__8);
			setState(96); match(AP);
			setState(102);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(97); match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(99); termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText, false); 
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(104); match(FP);
			setState(105); match(PO);
			 
						
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
			setState(108); match(ID);
			 
			                   		String id = _input.LT(-1).getText();
			                   		checkInitialized(id);
			                   		leftType = getTypeById(id);
			                   		String id_dois = id;
			                   		exprReset();
			                 	
			setState(111);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOMA) | (1L << SUB) | (1L << DIV) | (1L << MULT))) != 0)) {
				{
				setState(110);
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
			setState(114); match(OP_AT);
			setState(122);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case NUMERO_REAL:
			case AP:
			case TEXTO:
				{
				{
				setState(115); expr();
				setState(116); match(PO);

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
				setState(119); match(STRING);
					
										String str = _input.LT(-1).getText();
										stringType(id_dois);
										AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
										stack.peek().add(cmdAttrib);
								
				setState(121); match(PO);
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
			setState(124); match(T__16);
			setState(125); match(AP);

									exprReset();
								
			setState(127); expr();

									exprDecision.push(contExpr);
									leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(129); match(OPREL);
			 
									operacao = _input.LT(-1).getText();
									op_atual = exprDecision.pop();
									op_nova = op_atual + operacao;
									exprDecision.push(op_nova);
									exprReset();
								
			setState(131); expr();

									op_atual = exprDecision.pop();
									op_nova = op_atual + contExpr;
									exprDecision.push(op_nova);
									rightType = getTypeIfValid(exTypeList, "direito", op_nova);
								
			setState(133); match(FP);

									if (rightType != leftType) { 
										throw new IsiLanguageSemanticException("Não é possível compará-los");
									}
								
			setState(155);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(135); match(T__4);
				setState(136); match(AC);
				 
				            			comList = new ArrayList<Command>(); 
				            			stack.push(comList);
				        			
				setState(139); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(138); comando();
					}
					}
					setState(141); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
				setState(143); match(FC);

				            				listT = stack.pop();
				            				String expreDecision = exprDecision.pop();
				            				listaVazia = new ArrayList<Command>();
				            				IfCommand ifCommand = new IfCommand(expreDecision, listT, listaVazia);
				            				stack.peek().add(ifCommand);
				        			
				setState(145); match(AC);
				 
										comList = new ArrayList<Command>(); 
				                      	stack.push(comList);
				                    
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(147); comando();
					}
					}
					setState(150); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
				setState(152); match(FC);

				                       listT = stack.pop();	
									   String expDeci = exprDecision.pop();
									   listaVazia = new ArrayList<Command>();
									   IfCommand cmdEntao = new IfCommand(expreDecision, listT, listaVazia);
				                   	   stack.peek().add(cmdEntao);
				                    
				}
			}

			setState(168);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(157); match(T__15);
				setState(158); match(AC);

				                   	 	comList = new ArrayList<Command>();
				                   	 	stack.push(comList);
				                   	 
				{
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(160); comando();
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
				}
				setState(165); match(FC);

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
		public TerminalNode FC() { return getToken(IsiLanguageParser.FC, 0); }
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode AC() { return getToken(IsiLanguageParser.AC, 0); }
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
			setState(170); match(T__7);
			setState(171); match(AP);

										exprReset();
								
			setState(173); expr();

										exprDecision.push(contExpr);
										leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(175); match(OPREL);
			 
										String op = _input.LT(-1).getText();
										String atual = stackExprDecision.pop();
										String novo = atual + op;
										stackExprDecision.push(novo);
										resetExpr();
								
			setState(177); expr();

										atual = stackExprDecision.pop();
										novo = atual + _exprContent;
										stackExprDecision.push(novo);
										_rightType = verifyTypesAndGetTypeIfValid(expressionTypeList, "direito", novo);
								
			setState(179); match(FP);

									if (_rightType != _leftType) { 
										throw new IsiSemanticException("Tipos não comparáveis");
									}
									_rightType = "";
									_leftType ="";
								
			setState(181); match(AC);
			 
										curThread = new ArrayList<AbstractCommand>(); 
										stack.push(curThread);
			                    
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183); comando();
				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
			setState(188); match(FC);

			                       innerCommands = stack.pop();	
								   CommandRepita cmdRepita = new CommandRepita(stackExprDecision.pop(), innerCommands);
			                   	   stack.peek().add(cmdRepita);
			                    
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
			setState(191); match(T__10);
			 
			                     	stack.push(new ArrayList<Command>());
			                  	
			setState(194); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(193); comando();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(196); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(198); match(T__7);
			setState(199); match(AP);
			setState(200); expr();
			setState(201); match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(203); expr();
			setState(204); match(FP);
			setState(205); match(PO);
			 
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
			setState(208); match(T__5);
			setState(209); match(AP);
			setState(210); match(ID);
			setState(211); match(OP_AT);
			setState(212); expr();
			 String initialization = _input.LT(-3).getText() + ":=" + _input.LT(-1).getText(); 
					      
			setState(214); match(PO);
			setState(215); expr();
			setState(216); match(OPREL);
			setState(217); expr();
			 String condition = _input.LT(-3).getText() + _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(219); match(PO);
			setState(220); match(ID);
			setState(221);
			_la = _input.LA(1);
			if ( !(_la==T__13 || _la==T__12) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			 String increment = _input.LT(-2).getText() + _input.LT(-1).getText(); 
					      
			setState(223); match(FP);
			setState(224); match(T__10);
			 
			               stack.push(new ArrayList<Command>());
			              
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(226); comando();
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__10) | (1L << T__8) | (1L << T__7) | (1L << T__2) | (1L << ID))) != 0) );
			setState(231); match(T__9);

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
			setState(234); termo();
			setState(235); expr_ad();
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
			setState(237); fator();
			setState(238); termo_ad();
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
			setState(250);
			switch (_input.LA(1)) {
			case SUB:
				{
				setState(240); match(SUB);
				 
								contExpr += '-';
								String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(242); termo();
				setState(243); expr_ad();
				}
				break;
			case SOMA:
				{
				setState(245); match(SOMA);
				 
				           		contExpr += '+'; String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para subtração.");
				        		}
				        		exTypeList.add(tipo);
				        	
				setState(247); termo();
				setState(248); expr_ad();
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
			setState(262);
			switch (_input.LA(1)) {
			case DIV:
				{
				setState(252); match(DIV);
				 
				        		contExpr += '/'; 
				        		String tipo = getTypeById(_input.LT(-1).getText());
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) { 
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para divisão.");
				        		}
				        		exTypeList.add(tipo);
				    		
				setState(254); fator();
				setState(255); termo_ad();
				}
				break;
			case MULT:
				{
				setState(257); match(MULT);
				 
				        		contExpr += '*'; 
				        		String tipo = getTypeById(_input.LT(-1).getText()); 
				        		if (!tipo.equals("NUMBER") && !tipo.equals("REALNUMBER")) {  
				            		throw new IsiLanguageSemanticException("Tipos incompatíveis: " + tipo + " não é válido para multiplicação.");
				        		}
				        		exTypeList.add(tipo); 
				    		
				setState(259); fator();
				setState(260); termo_ad();
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
			setState(278);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(264); match(ID);
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
				setState(266); match(NUMERO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("NUMBER");
							
				}
				break;
			case NUMERO_REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(268); match(NUMERO_REAL);
				contExpr += _input.LT(-1).getText();
									exTypeList.add("REALNUMBER");
							
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(270); match(TEXTO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("TEXT");
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(272); match(AP);
				 contExpr += _input.LT(-1).getText();
							
				setState(274); expr();
				setState(275); match(FP);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u011b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\3\6\3\62\n\3\r\3"+
		"\16\3\63\3\4\3\4\6\48\n\4\r\4\16\49\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5C\n"+
		"\5\f\5\16\5F\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6P\n\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7X\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\ti\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\nr\n\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u008e\n\13\r\13\16\13\u008f"+
		"\3\13\3\13\3\13\3\13\3\13\6\13\u0097\n\13\r\13\16\13\u0098\3\13\3\13\3"+
		"\13\5\13\u009e\n\13\3\13\3\13\3\13\3\13\6\13\u00a4\n\13\r\13\16\13\u00a5"+
		"\3\13\3\13\3\13\5\13\u00ab\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\6\f\u00bb\n\f\r\f\16\f\u00bc\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\6\r\u00c5\n\r\r\r\16\r\u00c6\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\6\16\u00e6\n\16\r\16\16\16\u00e7\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u00fd\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u0109\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u0119\n\23\3\23\2\2\24\2\4\6\b\n\f\16\20"+
		"\22\24\26\30\32\34\36 \"$\2\4\3\2\24\27\3\2\6\7\u0127\2&\3\2\2\2\4\61"+
		"\3\2\2\2\6\65\3\2\2\2\b;\3\2\2\2\nO\3\2\2\2\fW\3\2\2\2\16Y\3\2\2\2\20"+
		"a\3\2\2\2\22n\3\2\2\2\24~\3\2\2\2\26\u00ac\3\2\2\2\30\u00c1\3\2\2\2\32"+
		"\u00d2\3\2\2\2\34\u00ec\3\2\2\2\36\u00ef\3\2\2\2 \u00fc\3\2\2\2\"\u0108"+
		"\3\2\2\2$\u0118\3\2\2\2&+\7\b\2\2\'(\5\4\3\2()\5\6\4\2),\3\2\2\2*,\5\6"+
		"\4\2+\'\3\2\2\2+*\3\2\2\2+,\3\2\2\2,-\3\2\2\2-.\7\r\2\2./\b\2\1\2/\3\3"+
		"\2\2\2\60\62\5\b\5\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3"+
		"\2\2\2\64\5\3\2\2\2\65\67\b\4\1\2\668\5\f\7\2\67\66\3\2\2\289\3\2\2\2"+
		"9\67\3\2\2\29:\3\2\2\2:\7\3\2\2\2;<\7\20\2\2<=\5\n\6\2=>\7\32\2\2>D\b"+
		"\5\1\2?@\7\35\2\2@A\7\32\2\2AC\b\5\1\2B?\3\2\2\2CF\3\2\2\2DB\3\2\2\2D"+
		"E\3\2\2\2EG\3\2\2\2FD\3\2\2\2GH\7\37\2\2H\t\3\2\2\2IJ\7\5\2\2JP\b\6\1"+
		"\2KL\7\23\2\2LP\b\6\1\2MN\7\22\2\2NP\b\6\1\2OI\3\2\2\2OK\3\2\2\2OM\3\2"+
		"\2\2P\13\3\2\2\2QX\5\22\n\2RX\5\16\b\2SX\5\20\t\2TX\5\24\13\2UX\5\26\f"+
		"\2VX\5\30\r\2WQ\3\2\2\2WR\3\2\2\2WS\3\2\2\2WT\3\2\2\2WU\3\2\2\2WV\3\2"+
		"\2\2X\r\3\2\2\2YZ\7\21\2\2Z[\7 \2\2[\\\7\32\2\2\\]\b\b\1\2]^\7!\2\2^_"+
		"\7\37\2\2_`\b\b\1\2`\17\3\2\2\2ab\7\13\2\2bh\7 \2\2cd\7%\2\2di\b\t\1\2"+
		"ef\5\36\20\2fg\b\t\1\2gi\3\2\2\2hc\3\2\2\2he\3\2\2\2ij\3\2\2\2jk\7!\2"+
		"\2kl\7\37\2\2lm\b\t\1\2m\21\3\2\2\2no\7\32\2\2oq\b\n\1\2pr\t\2\2\2qp\3"+
		"\2\2\2qr\3\2\2\2rs\3\2\2\2st\b\n\1\2t|\7\30\2\2uv\5\34\17\2vw\7\37\2\2"+
		"wx\b\n\1\2x}\3\2\2\2yz\7&\2\2z{\b\n\1\2{}\7\37\2\2|u\3\2\2\2|y\3\2\2\2"+
		"}\23\3\2\2\2~\177\7\3\2\2\177\u0080\7 \2\2\u0080\u0081\b\13\1\2\u0081"+
		"\u0082\5\34\17\2\u0082\u0083\b\13\1\2\u0083\u0084\7\31\2\2\u0084\u0085"+
		"\b\13\1\2\u0085\u0086\5\34\17\2\u0086\u0087\b\13\1\2\u0087\u0088\7!\2"+
		"\2\u0088\u009d\b\13\1\2\u0089\u008a\7\17\2\2\u008a\u008b\7\"\2\2\u008b"+
		"\u008d\b\13\1\2\u008c\u008e\5\f\7\2\u008d\u008c\3\2\2\2\u008e\u008f\3"+
		"\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\7#\2\2\u0092\u0093\b\13\1\2\u0093\u0094\7\"\2\2\u0094\u0096\b\13"+
		"\1\2\u0095\u0097\5\f\7\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7#"+
		"\2\2\u009b\u009c\b\13\1\2\u009c\u009e\3\2\2\2\u009d\u0089\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00aa\3\2\2\2\u009f\u00a0\7\4\2\2\u00a0\u00a1\7\""+
		"\2\2\u00a1\u00a3\b\13\1\2\u00a2\u00a4\5\f\7\2\u00a3\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\7#\2\2\u00a8\u00a9\b\13\1\2\u00a9\u00ab\3\2\2\2\u00aa"+
		"\u009f\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\25\3\2\2\2\u00ac\u00ad\7\f\2"+
		"\2\u00ad\u00ae\7 \2\2\u00ae\u00af\b\f\1\2\u00af\u00b0\5\34\17\2\u00b0"+
		"\u00b1\b\f\1\2\u00b1\u00b2\7\31\2\2\u00b2\u00b3\b\f\1\2\u00b3\u00b4\5"+
		"\34\17\2\u00b4\u00b5\b\f\1\2\u00b5\u00b6\7!\2\2\u00b6\u00b7\b\f\1\2\u00b7"+
		"\u00b8\7\"\2\2\u00b8\u00ba\b\f\1\2\u00b9\u00bb\5\f\7\2\u00ba\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\7#\2\2\u00bf\u00c0\b\f\1\2\u00c0\27\3\2\2\2"+
		"\u00c1\u00c2\7\t\2\2\u00c2\u00c4\b\r\1\2\u00c3\u00c5\5\f\7\2\u00c4\u00c3"+
		"\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00c9\7\f\2\2\u00c9\u00ca\7 \2\2\u00ca\u00cb\5\34"+
		"\17\2\u00cb\u00cc\7\31\2\2\u00cc\u00cd\b\r\1\2\u00cd\u00ce\5\34\17\2\u00ce"+
		"\u00cf\7!\2\2\u00cf\u00d0\7\37\2\2\u00d0\u00d1\b\r\1\2\u00d1\31\3\2\2"+
		"\2\u00d2\u00d3\7\16\2\2\u00d3\u00d4\7 \2\2\u00d4\u00d5\7\32\2\2\u00d5"+
		"\u00d6\7\30\2\2\u00d6\u00d7\5\34\17\2\u00d7\u00d8\b\16\1\2\u00d8\u00d9"+
		"\7\37\2\2\u00d9\u00da\5\34\17\2\u00da\u00db\7\31\2\2\u00db\u00dc\5\34"+
		"\17\2\u00dc\u00dd\b\16\1\2\u00dd\u00de\7\37\2\2\u00de\u00df\7\32\2\2\u00df"+
		"\u00e0\t\3\2\2\u00e0\u00e1\b\16\1\2\u00e1\u00e2\7!\2\2\u00e2\u00e3\7\t"+
		"\2\2\u00e3\u00e5\b\16\1\2\u00e4\u00e6\5\f\7\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9\3\2"+
		"\2\2\u00e9\u00ea\7\n\2\2\u00ea\u00eb\b\16\1\2\u00eb\33\3\2\2\2\u00ec\u00ed"+
		"\5\36\20\2\u00ed\u00ee\5 \21\2\u00ee\35\3\2\2\2\u00ef\u00f0\5$\23\2\u00f0"+
		"\u00f1\5\"\22\2\u00f1\37\3\2\2\2\u00f2\u00f3\7\25\2\2\u00f3\u00f4\b\21"+
		"\1\2\u00f4\u00f5\5\36\20\2\u00f5\u00f6\5 \21\2\u00f6\u00fd\3\2\2\2\u00f7"+
		"\u00f8\7\24\2\2\u00f8\u00f9\b\21\1\2\u00f9\u00fa\5\36\20\2\u00fa\u00fb"+
		"\5 \21\2\u00fb\u00fd\3\2\2\2\u00fc\u00f2\3\2\2\2\u00fc\u00f7\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd!\3\2\2\2\u00fe\u00ff\7\26\2\2\u00ff\u0100\b\22\1"+
		"\2\u0100\u0101\5$\23\2\u0101\u0102\5\"\22\2\u0102\u0109\3\2\2\2\u0103"+
		"\u0104\7\27\2\2\u0104\u0105\b\22\1\2\u0105\u0106\5$\23\2\u0106\u0107\5"+
		"\"\22\2\u0107\u0109\3\2\2\2\u0108\u00fe\3\2\2\2\u0108\u0103\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109#\3\2\2\2\u010a\u010b\7\32\2\2\u010b\u0119\b\23\1"+
		"\2\u010c\u010d\7\33\2\2\u010d\u0119\b\23\1\2\u010e\u010f\7\34\2\2\u010f"+
		"\u0119\b\23\1\2\u0110\u0111\7%\2\2\u0111\u0119\b\23\1\2\u0112\u0113\7"+
		" \2\2\u0113\u0114\b\23\1\2\u0114\u0115\5\34\17\2\u0115\u0116\7!\2\2\u0116"+
		"\u0117\b\23\1\2\u0117\u0119\3\2\2\2\u0118\u010a\3\2\2\2\u0118\u010c\3"+
		"\2\2\2\u0118\u010e\3\2\2\2\u0118\u0110\3\2\2\2\u0118\u0112\3\2\2\2\u0119"+
		"%\3\2\2\2\26+\639DOWhq|\u008f\u0098\u009d\u00a5\u00aa\u00bc\u00c6\u00e7"+
		"\u00fc\u0108\u0118";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}