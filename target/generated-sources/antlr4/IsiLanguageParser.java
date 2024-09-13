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
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, SOMA=14, SUB=15, DIV=16, MULT=17, 
		OP_AT=18, OPREL=19, ID=20, NUMERO=21, NUMERO_REAL=22, VIRG=23, PV=24, 
		PO=25, AP=26, FP=27, AC=28, FC=29, DP=30, TEXTO=31, STRING=32, WS=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'se'", "'senao'", "'inteiro'", "'programa'", "'faca'", "'escreva'", 
		"'enquanto'", "'fimprog.'", "'entao'", "'declare'", "'leia'", "'texto'", 
		"'real'", "'+'", "'-'", "'/'", "'*'", "':='", "OPREL", "ID", "NUMERO", 
		"NUMERO_REAL", "','", "';'", "'.'", "'('", "')'", "'{'", "'}'", "':'", 
		"TEXTO", "STRING", "WS"
	};
	public static final int
		RULE_programa = 0, RULE_declara = 1, RULE_bloco = 2, RULE_declaravar = 3, 
		RULE_tipo = 4, RULE_comando = 5, RULE_cmdLeitura = 6, RULE_cmdEscrita = 7, 
		RULE_cmdAttrib = 8, RULE_cmdSe = 9, RULE_cmdEnquanto = 10, RULE_cmdFacaEnquanto = 11, 
		RULE_expr = 12, RULE_termo = 13, RULE_expr_ad = 14, RULE_termo_ad = 15, 
		RULE_fator = 16;
	public static final String[] ruleNames = {
		"programa", "declara", "bloco", "declaravar", "tipo", "comando", "cmdLeitura", 
		"cmdEscrita", "cmdAttrib", "cmdSe", "cmdEnquanto", "cmdFacaEnquanto", 
		"expr", "termo", "expr_ad", "termo_ad", "fator"
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
	    private ArrayList<Command> doWhileCommands;
	    private ArrayList<Command> whileCommands;
	    private ArrayList<Command> listaVazia;
	    private ArrayList<Command> listT;
	    private ArrayList<Command> listQ;
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
			setState(34); match(T__9);
			setState(39);
			switch (_input.LA(1)) {
			case T__3:
				{
				{
				setState(35); declara();
				setState(36); bloco();
				}
				}
				break;
			case T__12:
			case T__8:
			case T__7:
			case T__6:
			case T__2:
			case ID:
				{
				setState(38); bloco();
				}
				break;
			case T__5:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(41); match(T__5);

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
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44); declaravar();
				}
				}
				setState(47); 
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
						  
			setState(51); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(50); comando();
				}
				}
				setState(53); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
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
			setState(55); match(T__3);
			setState(56); tipo();
			setState(57); match(ID);

			      			String id_var = _input.LT(-1).getText();
			          		Symbol sym = new Var(id_var, null, currentType);
			          		if (!symbolTable.exists(id_var)){
				            	symbolTable.add(sym);	
				            }
				            else{
				                throw new IsiLanguageSemanticException("Variável"+id_var+" já declarada.");
				            } 
			      		
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(59); match(VIRG);
				setState(60); match(ID);
				 
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
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67); match(PO);
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
			setState(75);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(69); match(T__10);
				 currentType = Var.NUMBER;
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(71); match(T__0);
				 currentType = Var.REALNUMBER;
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(73); match(T__1);
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
			setState(83);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(77); cmdAttrib();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78); cmdLeitura();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(79); cmdEscrita();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(80); cmdSe();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(81); cmdEnquanto();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(82); cmdFacaEnquanto();
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
			setState(85); match(T__2);
			setState(86); match(AP);
			setState(87); match(ID);

								checkInitialized(_input.LT(-1).getText());
			    				String ident = _input.LT(-1).getText();
			    			
			setState(89); match(FP);
			setState(90); match(PO);

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
			setState(93); match(T__7);
			setState(94); match(AP);
			setState(100);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(95); match(TEXTO);
				 
				        		String text = _input.LT(-1).getText();
				        		Command cmdEscrita = new WriteCommand(text, true); // Literal
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			case 2:
				{
				setState(97); termo();
				 
				       			String termoText = _input.LT(-1).getText();
				        		if (!isDeclared(termoText)) {
				            		throw new IsiLanguageSemanticException("Symbol " + termoText + " not declared");
				        		}
				        		Command cmdEscrita = new WriteCommand(termoText, false); 
				        		stack.peek().add(cmdEscrita);
				    		
				}
				break;
			}
			setState(102); match(FP);
			setState(103); match(PO);
			 
						
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
			setState(106); match(ID);
			 
			                   		String id = _input.LT(-1).getText();
			                   		checkInitialized(id);
			                   		leftType = getTypeById(id);
			                   		String id_dois = id;
			                   		exprReset();
			                 	
			setState(109);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SOMA) | (1L << SUB) | (1L << DIV) | (1L << MULT))) != 0)) {
				{
				setState(108);
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
			setState(112); match(OP_AT);
			setState(120);
			switch (_input.LA(1)) {
			case ID:
			case NUMERO:
			case NUMERO_REAL:
			case AP:
			case TEXTO:
				{
				{
				setState(113); expr();
				setState(114); match(PO);

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
				setState(117); match(STRING);
					
										String str = _input.LT(-1).getText();
										stringType(id_dois);
										AttribCommand cmdAttrib = new AttribCommand(id_dois, str);
										stack.peek().add(cmdAttrib);
								
				setState(119); match(PO);
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
		public TerminalNode FP(int i) {
			return getToken(IsiLanguageParser.FP, i);
		}
		public TerminalNode AC(int i) {
			return getToken(IsiLanguageParser.AC, i);
		}
		public TerminalNode FC(int i) {
			return getToken(IsiLanguageParser.FC, i);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL(int i) {
			return getToken(IsiLanguageParser.OPREL, i);
		}
		public List<TerminalNode> OPREL() { return getTokens(IsiLanguageParser.OPREL); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public List<TerminalNode> FP() { return getTokens(IsiLanguageParser.FP); }
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public List<TerminalNode> AP() { return getTokens(IsiLanguageParser.AP); }
		public List<TerminalNode> FC() { return getTokens(IsiLanguageParser.FC); }
		public List<TerminalNode> AC() { return getTokens(IsiLanguageParser.AC); }
		public TerminalNode AP(int i) {
			return getToken(IsiLanguageParser.AP, i);
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
			setState(122); match(T__12);
			setState(123); match(AP);

									exprReset();
								
			setState(125); expr();

									exprDecision.push(contExpr);
									leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(127); match(OPREL);
			 
									operacao = _input.LT(-1).getText();
									op_atual = exprDecision.pop();
									op_nova = op_atual + operacao;
									exprDecision.push(op_nova);
									exprReset();
								
			setState(129); expr();

									op_atual = exprDecision.pop();
									op_nova = op_atual + contExpr;
									exprDecision.push(op_nova);
									rightType = getTypeIfValid(exTypeList, "direito", op_nova);
								
			setState(131); match(FP);

									if (rightType != leftType) { 
										throw new IsiLanguageSemanticException("Não é possível compará-los");
									}
								
			setState(133); match(AC);

									comList = new ArrayList<Command>(); 
			            			stack.push(comList); 
			            		
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135); comando();
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
			setState(140); match(FC);

									listT = stack.pop();
									String Dec = exprDecision.pop();
			            			IfCommand cmdSe = new IfCommand("se", Dec, listT, listaVazia);
			                   	   	stack.peek().add(cmdSe);
			setState(163);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(142); match(T__4);
				setState(143); match(AP);
				 			  			
				            			exprReset();
										
				setState(145); expr();

											exprDecision.push(contExpr);
											leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
										
				setState(147); match(OPREL);
				 
											operacao = _input.LT(-1).getText();
											op_atual = exprDecision.pop();
											op_nova = op_atual + operacao;
											exprDecision.push(op_nova);
											exprReset();
										
				setState(149); expr();

											op_atual = exprDecision.pop();
											op_nova = op_atual + contExpr;
											exprDecision.push(op_nova);
											rightType = getTypeIfValid(exTypeList, "direito", op_nova);
										
				setState(151); match(FP);

											if (rightType != leftType) { 
												throw new IsiLanguageSemanticException("Não é possível compará-los");
											}
										
				setState(153); match(AC);

											comList = new ArrayList<Command>(); 
				            				stack.push(comList); 
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(155); comando();
					}
					}
					setState(158); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
				setState(160); match(FC);

				            				listQ = stack.pop();
											String Deca = exprDecision.pop();
				            				IfCommand cmdEntao = new IfCommand("entao", Deca, listT, listQ);
				                   	   		stack.peek().add(cmdEntao);
				}
			}

			setState(176);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(165); match(T__11);
				setState(166); match(AC);

				                   	 	comList = new ArrayList<Command>();
				                   	 	stack.push(comList);
				                   	 
				{
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(168); comando();
					}
					}
					setState(171); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
				}
				setState(173); match(FC);

				                   		listF = stack.pop();
										stack.peek().remove(stack.peek().size() - 1); 
				                   		IfCommand cmdSeNao = new IfCommand("senao", Dec, listT, listF);
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
			setState(178); match(T__6);
			setState(179); match(AP);

										exprReset();
								
			setState(181); expr();

										exprDecision.push(contExpr);
										leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr);
								
			setState(183); match(OPREL);
			 
										operacao = _input.LT(-1).getText();
										op_atual = exprDecision.pop();
										op_nova = op_atual + operacao;
										exprDecision.push(op_nova);
										exprReset();
								
			setState(185); expr();

										op_atual = exprDecision.pop();
										System.out.println(op_atual);
										System.out.println(contExpr);
										op_nova = op_atual + contExpr;
										System.out.println(op_nova);
										exprDecision.push(op_nova);
										rightType = getTypeIfValid(exTypeList, "direito", op_nova);
								
			setState(187); match(FP);

									if (rightType != leftType) { 
										throw new IsiLanguageSemanticException("Não é possível compará-los");
									}
								
								
			setState(189); match(AC);

									comList = new ArrayList<Command>(); 
			            			stack.push(comList); 
			                    
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
			setState(196); match(FC);

			                       whileCommands = stack.pop();	
								   WhileCommand cmdEnquanto = new WhileCommand(exprDecision.pop(), whileCommands);
			                   	   stack.peek().add(cmdEnquanto);
			                    
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
		public TerminalNode FC() { return getToken(IsiLanguageParser.FC, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public TerminalNode AC() { return getToken(IsiLanguageParser.AC, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199); match(T__8);
			setState(200); match(AC);
			 
			            			comList = new ArrayList<Command>(); 
			            			stack.push(comList); 
			        			
			setState(203); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(202); comando();
				}
				}
				setState(205); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__8) | (1L << T__7) | (1L << T__6) | (1L << T__2) | (1L << ID))) != 0) );
			setState(207); match(FC);
			setState(208); match(T__6);
			setState(209); match(AP);
			 
			            			exprReset(); 
			       			 	
			setState(211); expr();
			 
			            			exprDecision.push(contExpr); 
			            			leftType = getTypeIfValid(exTypeList, "esquerdo", contExpr); 
			        			
			setState(213); match(OPREL);
			 
			            			operacao = _input.LT(-1).getText(); 
			            			op_atual = exprDecision.pop(); 
			            			op_nova = op_atual + operacao; 
			            			exprDecision.push(op_nova); 
			            			exprReset(); 
			        			
			setState(215); expr();
			 
			            			op_atual = exprDecision.pop(); 
			            			op_nova = op_atual + contExpr; 
			            			exprDecision.push(op_nova); 
			            			rightType = getTypeIfValid(exTypeList, "direito", op_nova); 
			        			
			setState(217); match(FP);
			 
			            			if (rightType != leftType) { 
			                			throw new IsiLanguageSemanticException("Não é possível compará-los"); 
			            			}
			        			
			 doWhileCommands = stack.pop(); 
			            		  DoWhileCommand cmdFacaEnquanto = new DoWhileCommand(exprDecision.pop(), doWhileCommands);
			            		  stack.peek().add(cmdFacaEnquanto);
				        		
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
		enterRule(_localctx, 24, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221); termo();
			setState(222); expr_ad();
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
		enterRule(_localctx, 26, RULE_termo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); fator();
			setState(225); termo_ad();
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
		enterRule(_localctx, 28, RULE_expr_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			switch (_input.LA(1)) {
			case SUB:
				{
				setState(227); match(SUB);
				 
								contExpr += '-'; exTypeList.add("NUMBER");
				setState(229); termo();
				setState(230); expr_ad();
				}
				break;
			case SOMA:
				{
				setState(232); match(SOMA);
				 
				           		contExpr += '+'; exTypeList.add("NUMBER");
				setState(234); termo();
				setState(235); expr_ad();
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
		enterRule(_localctx, 30, RULE_termo_ad);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			switch (_input.LA(1)) {
			case DIV:
				{
				setState(239); match(DIV);
				 contExpr += '/'; exTypeList.add("NUMBER");
							
				setState(241); fator();
				setState(242); termo_ad();
				}
				break;
			case MULT:
				{
				setState(244); match(MULT);
				 contExpr += '*'; exTypeList.add("NUMBER"); 
				setState(246); fator();
				setState(247); termo_ad();
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
		enterRule(_localctx, 32, RULE_fator);
		try {
			setState(265);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(251); match(ID);
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
				setState(253); match(NUMERO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("NUMBER");
							
				}
				break;
			case NUMERO_REAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(255); match(NUMERO_REAL);
				contExpr += _input.LT(-1).getText();
									exTypeList.add("REALNUMBER");
							
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(257); match(TEXTO);
				  contExpr += _input.LT(-1).getText();
									exTypeList.add("TEXT");
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 5);
				{
				setState(259); match(AP);
				 contExpr += _input.LT(-1).getText();
							
				setState(261); expr();
				setState(262); match(FP);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u010e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\5\2*\n\2\3\2\3\2\3\2\3\3\6\3\60\n\3\r\3\16\3\61\3"+
		"\4\3\4\6\4\66\n\4\r\4\16\4\67\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5A\n\5\f\5"+
		"\16\5D\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7V\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\tg\n\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\5\np\n\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\n{\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\6\13\u008b\n\13\r\13\16\13\u008c\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\6\13\u009f\n\13\r\13\16\13\u00a0\3\13\3\13\3\13\5\13\u00a6\n\13\3\13"+
		"\3\13\3\13\3\13\6\13\u00ac\n\13\r\13\16\13\u00ad\3\13\3\13\3\13\5\13\u00b3"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00c3"+
		"\n\f\r\f\16\f\u00c4\3\f\3\f\3\f\3\r\3\r\3\r\3\r\6\r\u00ce\n\r\r\r\16\r"+
		"\u00cf\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u00f0\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\5\21\u00fc\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u010c\n\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\36 \"\2\3\3\2\20\23\u011a\2$\3\2\2\2\4/\3\2\2\2\6\63\3\2\2\2"+
		"\b9\3\2\2\2\nM\3\2\2\2\fU\3\2\2\2\16W\3\2\2\2\20_\3\2\2\2\22l\3\2\2\2"+
		"\24|\3\2\2\2\26\u00b4\3\2\2\2\30\u00c9\3\2\2\2\32\u00df\3\2\2\2\34\u00e2"+
		"\3\2\2\2\36\u00ef\3\2\2\2 \u00fb\3\2\2\2\"\u010b\3\2\2\2$)\7\6\2\2%&\5"+
		"\4\3\2&\'\5\6\4\2\'*\3\2\2\2(*\5\6\4\2)%\3\2\2\2)(\3\2\2\2)*\3\2\2\2*"+
		"+\3\2\2\2+,\7\n\2\2,-\b\2\1\2-\3\3\2\2\2.\60\5\b\5\2/.\3\2\2\2\60\61\3"+
		"\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\5\3\2\2\2\63\65\b\4\1\2\64\66\5\f"+
		"\7\2\65\64\3\2\2\2\66\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\7\3\2\2\2"+
		"9:\7\f\2\2:;\5\n\6\2;<\7\26\2\2<B\b\5\1\2=>\7\31\2\2>?\7\26\2\2?A\b\5"+
		"\1\2@=\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\7\33"+
		"\2\2F\t\3\2\2\2GH\7\5\2\2HN\b\6\1\2IJ\7\17\2\2JN\b\6\1\2KL\7\16\2\2LN"+
		"\b\6\1\2MG\3\2\2\2MI\3\2\2\2MK\3\2\2\2N\13\3\2\2\2OV\5\22\n\2PV\5\16\b"+
		"\2QV\5\20\t\2RV\5\24\13\2SV\5\26\f\2TV\5\30\r\2UO\3\2\2\2UP\3\2\2\2UQ"+
		"\3\2\2\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\r\3\2\2\2WX\7\r\2\2XY\7\34\2\2"+
		"YZ\7\26\2\2Z[\b\b\1\2[\\\7\35\2\2\\]\7\33\2\2]^\b\b\1\2^\17\3\2\2\2_`"+
		"\7\b\2\2`f\7\34\2\2ab\7!\2\2bg\b\t\1\2cd\5\34\17\2de\b\t\1\2eg\3\2\2\2"+
		"fa\3\2\2\2fc\3\2\2\2gh\3\2\2\2hi\7\35\2\2ij\7\33\2\2jk\b\t\1\2k\21\3\2"+
		"\2\2lm\7\26\2\2mo\b\n\1\2np\t\2\2\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\b"+
		"\n\1\2rz\7\24\2\2st\5\32\16\2tu\7\33\2\2uv\b\n\1\2v{\3\2\2\2wx\7\"\2\2"+
		"xy\b\n\1\2y{\7\33\2\2zs\3\2\2\2zw\3\2\2\2{\23\3\2\2\2|}\7\3\2\2}~\7\34"+
		"\2\2~\177\b\13\1\2\177\u0080\5\32\16\2\u0080\u0081\b\13\1\2\u0081\u0082"+
		"\7\25\2\2\u0082\u0083\b\13\1\2\u0083\u0084\5\32\16\2\u0084\u0085\b\13"+
		"\1\2\u0085\u0086\7\35\2\2\u0086\u0087\b\13\1\2\u0087\u0088\7\36\2\2\u0088"+
		"\u008a\b\13\1\2\u0089\u008b\5\f\7\2\u008a\u0089\3\2\2\2\u008b\u008c\3"+
		"\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\7\37\2\2\u008f\u00a5\b\13\1\2\u0090\u0091\7\13\2\2\u0091\u0092"+
		"\7\34\2\2\u0092\u0093\b\13\1\2\u0093\u0094\5\32\16\2\u0094\u0095\b\13"+
		"\1\2\u0095\u0096\7\25\2\2\u0096\u0097\b\13\1\2\u0097\u0098\5\32\16\2\u0098"+
		"\u0099\b\13\1\2\u0099\u009a\7\35\2\2\u009a\u009b\b\13\1\2\u009b\u009c"+
		"\7\36\2\2\u009c\u009e\b\13\1\2\u009d\u009f\5\f\7\2\u009e\u009d\3\2\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2"+
		"\3\2\2\2\u00a2\u00a3\7\37\2\2\u00a3\u00a4\b\13\1\2\u00a4\u00a6\3\2\2\2"+
		"\u00a5\u0090\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00b2\3\2\2\2\u00a7\u00a8"+
		"\7\4\2\2\u00a8\u00a9\7\36\2\2\u00a9\u00ab\b\13\1\2\u00aa\u00ac\5\f\7\2"+
		"\u00ab\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\37\2\2\u00b0\u00b1\b\13\1\2"+
		"\u00b1\u00b3\3\2\2\2\u00b2\u00a7\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\25"+
		"\3\2\2\2\u00b4\u00b5\7\t\2\2\u00b5\u00b6\7\34\2\2\u00b6\u00b7\b\f\1\2"+
		"\u00b7\u00b8\5\32\16\2\u00b8\u00b9\b\f\1\2\u00b9\u00ba\7\25\2\2\u00ba"+
		"\u00bb\b\f\1\2\u00bb\u00bc\5\32\16\2\u00bc\u00bd\b\f\1\2\u00bd\u00be\7"+
		"\35\2\2\u00be\u00bf\b\f\1\2\u00bf\u00c0\7\36\2\2\u00c0\u00c2\b\f\1\2\u00c1"+
		"\u00c3\5\f\7\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2"+
		"\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\7\37\2\2\u00c7"+
		"\u00c8\b\f\1\2\u00c8\27\3\2\2\2\u00c9\u00ca\7\7\2\2\u00ca\u00cb\7\36\2"+
		"\2\u00cb\u00cd\b\r\1\2\u00cc\u00ce\5\f\7\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\7\37\2\2\u00d2\u00d3\7\t\2\2\u00d3\u00d4\7\34\2\2\u00d4\u00d5\b"+
		"\r\1\2\u00d5\u00d6\5\32\16\2\u00d6\u00d7\b\r\1\2\u00d7\u00d8\7\25\2\2"+
		"\u00d8\u00d9\b\r\1\2\u00d9\u00da\5\32\16\2\u00da\u00db\b\r\1\2\u00db\u00dc"+
		"\7\35\2\2\u00dc\u00dd\b\r\1\2\u00dd\u00de\b\r\1\2\u00de\31\3\2\2\2\u00df"+
		"\u00e0\5\34\17\2\u00e0\u00e1\5\36\20\2\u00e1\33\3\2\2\2\u00e2\u00e3\5"+
		"\"\22\2\u00e3\u00e4\5 \21\2\u00e4\35\3\2\2\2\u00e5\u00e6\7\21\2\2\u00e6"+
		"\u00e7\b\20\1\2\u00e7\u00e8\5\34\17\2\u00e8\u00e9\5\36\20\2\u00e9\u00f0"+
		"\3\2\2\2\u00ea\u00eb\7\20\2\2\u00eb\u00ec\b\20\1\2\u00ec\u00ed\5\34\17"+
		"\2\u00ed\u00ee\5\36\20\2\u00ee\u00f0\3\2\2\2\u00ef\u00e5\3\2\2\2\u00ef"+
		"\u00ea\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\37\3\2\2\2\u00f1\u00f2\7\22\2"+
		"\2\u00f2\u00f3\b\21\1\2\u00f3\u00f4\5\"\22\2\u00f4\u00f5\5 \21\2\u00f5"+
		"\u00fc\3\2\2\2\u00f6\u00f7\7\23\2\2\u00f7\u00f8\b\21\1\2\u00f8\u00f9\5"+
		"\"\22\2\u00f9\u00fa\5 \21\2\u00fa\u00fc\3\2\2\2\u00fb\u00f1\3\2\2\2\u00fb"+
		"\u00f6\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc!\3\2\2\2\u00fd\u00fe\7\26\2\2"+
		"\u00fe\u010c\b\22\1\2\u00ff\u0100\7\27\2\2\u0100\u010c\b\22\1\2\u0101"+
		"\u0102\7\30\2\2\u0102\u010c\b\22\1\2\u0103\u0104\7!\2\2\u0104\u010c\b"+
		"\22\1\2\u0105\u0106\7\34\2\2\u0106\u0107\b\22\1\2\u0107\u0108\5\32\16"+
		"\2\u0108\u0109\7\35\2\2\u0109\u010a\b\22\1\2\u010a\u010c\3\2\2\2\u010b"+
		"\u00fd\3\2\2\2\u010b\u00ff\3\2\2\2\u010b\u0101\3\2\2\2\u010b\u0103\3\2"+
		"\2\2\u010b\u0105\3\2\2\2\u010c#\3\2\2\2\25)\61\67BMUfoz\u008c\u00a0\u00a5"+
		"\u00ad\u00b2\u00c4\u00cf\u00ef\u00fb\u010b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}