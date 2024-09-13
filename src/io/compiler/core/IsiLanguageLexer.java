// Generated from IsiLanguage.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class IsiLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, SOMA=19, SUB=20, DIV=21, MULT=22, OP_AT=23, OPREL=24, ID=25, 
		NUMERO=26, NUMERO_REAL=27, VIRG=28, PV=29, PO=30, AP=31, FP=32, AC=33, 
		FC=34, DP=35, TEXTO=36, STRING=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "SOMA", "SUB", "DIV", "MULT", "OP_AT", "OPREL", "ID", "NUMERO", 
			"NUMERO_REAL", "VIRG", "PV", "PO", "AP", "FP", "AC", "FC", "DP", "TEXTO", 
			"STRING", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'declare'", "'inteiro'", "'real'", 
			"'texto'", "'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", 
			"'faca'", "'fimenquanto'", "'para'", "'++'", "'--'", "'fimpara'", "'+'", 
			"'-'", "'/'", "'*'", "':='", null, null, null, null, "','", "';'", "'.'", 
			"'('", "')'", "'{'", "'}'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "SOMA", "SUB", "DIV", "MULT", 
			"OP_AT", "OPREL", "ID", "NUMERO", "NUMERO_REAL", "VIRG", "PV", "PO", 
			"AP", "FP", "AC", "FC", "DP", "TEXTO", "STRING", "WS"
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


	public IsiLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000&\u011a\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u00d8\b\u0017\u0001\u0018\u0001\u0018\u0005\u0018\u00dc"+
		"\b\u0018\n\u0018\f\u0018\u00df\t\u0018\u0001\u0019\u0004\u0019\u00e2\b"+
		"\u0019\u000b\u0019\f\u0019\u00e3\u0001\u001a\u0004\u001a\u00e7\b\u001a"+
		"\u000b\u001a\f\u001a\u00e8\u0001\u001a\u0001\u001a\u0004\u001a\u00ed\b"+
		"\u001a\u000b\u001a\f\u001a\u00ee\u0003\u001a\u00f1\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001!\u0001!\u0001"+
		"\"\u0001\"\u0001#\u0001#\u0005#\u0105\b#\n#\f#\u0108\t#\u0001#\u0001#"+
		"\u0001$\u0001$\u0001$\u0001$\u0005$\u0110\b$\n$\f$\u0113\t$\u0001$\u0001"+
		"$\u0001%\u0001%\u0001%\u0001%\u0001\u0111\u0000&\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&\u0001\u0000\u0006\u0002\u0000"+
		"<<>>\u0001\u0000az\u0003\u000009AZaz\u0001\u000009\u0005\u0000  ,.09A"+
		"Zaz\u0003\u0000\t\n\r\r  \u0125\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;"+
		"\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000"+
		"\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000"+
		"\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I"+
		"\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0001M\u0001\u0000"+
		"\u0000\u0000\u0003V\u0001\u0000\u0000\u0000\u0005_\u0001\u0000\u0000\u0000"+
		"\u0007g\u0001\u0000\u0000\u0000\to\u0001\u0000\u0000\u0000\u000bt\u0001"+
		"\u0000\u0000\u0000\rz\u0001\u0000\u0000\u0000\u000f\u007f\u0001\u0000"+
		"\u0000\u0000\u0011\u0087\u0001\u0000\u0000\u0000\u0013\u008a\u0001\u0000"+
		"\u0000\u0000\u0015\u0090\u0001\u0000\u0000\u0000\u0017\u0096\u0001\u0000"+
		"\u0000\u0000\u0019\u009f\u0001\u0000\u0000\u0000\u001b\u00a4\u0001\u0000"+
		"\u0000\u0000\u001d\u00b0\u0001\u0000\u0000\u0000\u001f\u00b5\u0001\u0000"+
		"\u0000\u0000!\u00b8\u0001\u0000\u0000\u0000#\u00bb\u0001\u0000\u0000\u0000"+
		"%\u00c3\u0001\u0000\u0000\u0000\'\u00c5\u0001\u0000\u0000\u0000)\u00c7"+
		"\u0001\u0000\u0000\u0000+\u00c9\u0001\u0000\u0000\u0000-\u00cb\u0001\u0000"+
		"\u0000\u0000/\u00d7\u0001\u0000\u0000\u00001\u00d9\u0001\u0000\u0000\u0000"+
		"3\u00e1\u0001\u0000\u0000\u00005\u00e6\u0001\u0000\u0000\u00007\u00f2"+
		"\u0001\u0000\u0000\u00009\u00f4\u0001\u0000\u0000\u0000;\u00f6\u0001\u0000"+
		"\u0000\u0000=\u00f8\u0001\u0000\u0000\u0000?\u00fa\u0001\u0000\u0000\u0000"+
		"A\u00fc\u0001\u0000\u0000\u0000C\u00fe\u0001\u0000\u0000\u0000E\u0100"+
		"\u0001\u0000\u0000\u0000G\u0102\u0001\u0000\u0000\u0000I\u010b\u0001\u0000"+
		"\u0000\u0000K\u0116\u0001\u0000\u0000\u0000MN\u0005p\u0000\u0000NO\u0005"+
		"r\u0000\u0000OP\u0005o\u0000\u0000PQ\u0005g\u0000\u0000QR\u0005r\u0000"+
		"\u0000RS\u0005a\u0000\u0000ST\u0005m\u0000\u0000TU\u0005a\u0000\u0000"+
		"U\u0002\u0001\u0000\u0000\u0000VW\u0005f\u0000\u0000WX\u0005i\u0000\u0000"+
		"XY\u0005m\u0000\u0000YZ\u0005p\u0000\u0000Z[\u0005r\u0000\u0000[\\\u0005"+
		"o\u0000\u0000\\]\u0005g\u0000\u0000]^\u0005.\u0000\u0000^\u0004\u0001"+
		"\u0000\u0000\u0000_`\u0005d\u0000\u0000`a\u0005e\u0000\u0000ab\u0005c"+
		"\u0000\u0000bc\u0005l\u0000\u0000cd\u0005a\u0000\u0000de\u0005r\u0000"+
		"\u0000ef\u0005e\u0000\u0000f\u0006\u0001\u0000\u0000\u0000gh\u0005i\u0000"+
		"\u0000hi\u0005n\u0000\u0000ij\u0005t\u0000\u0000jk\u0005e\u0000\u0000"+
		"kl\u0005i\u0000\u0000lm\u0005r\u0000\u0000mn\u0005o\u0000\u0000n\b\u0001"+
		"\u0000\u0000\u0000op\u0005r\u0000\u0000pq\u0005e\u0000\u0000qr\u0005a"+
		"\u0000\u0000rs\u0005l\u0000\u0000s\n\u0001\u0000\u0000\u0000tu\u0005t"+
		"\u0000\u0000uv\u0005e\u0000\u0000vw\u0005x\u0000\u0000wx\u0005t\u0000"+
		"\u0000xy\u0005o\u0000\u0000y\f\u0001\u0000\u0000\u0000z{\u0005l\u0000"+
		"\u0000{|\u0005e\u0000\u0000|}\u0005i\u0000\u0000}~\u0005a\u0000\u0000"+
		"~\u000e\u0001\u0000\u0000\u0000\u007f\u0080\u0005e\u0000\u0000\u0080\u0081"+
		"\u0005s\u0000\u0000\u0081\u0082\u0005c\u0000\u0000\u0082\u0083\u0005r"+
		"\u0000\u0000\u0083\u0084\u0005e\u0000\u0000\u0084\u0085\u0005v\u0000\u0000"+
		"\u0085\u0086\u0005a\u0000\u0000\u0086\u0010\u0001\u0000\u0000\u0000\u0087"+
		"\u0088\u0005s\u0000\u0000\u0088\u0089\u0005e\u0000\u0000\u0089\u0012\u0001"+
		"\u0000\u0000\u0000\u008a\u008b\u0005e\u0000\u0000\u008b\u008c\u0005n\u0000"+
		"\u0000\u008c\u008d\u0005t\u0000\u0000\u008d\u008e\u0005a\u0000\u0000\u008e"+
		"\u008f\u0005o\u0000\u0000\u008f\u0014\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0005s\u0000\u0000\u0091\u0092\u0005e\u0000\u0000\u0092\u0093\u0005n"+
		"\u0000\u0000\u0093\u0094\u0005a\u0000\u0000\u0094\u0095\u0005o\u0000\u0000"+
		"\u0095\u0016\u0001\u0000\u0000\u0000\u0096\u0097\u0005e\u0000\u0000\u0097"+
		"\u0098\u0005n\u0000\u0000\u0098\u0099\u0005q\u0000\u0000\u0099\u009a\u0005"+
		"u\u0000\u0000\u009a\u009b\u0005a\u0000\u0000\u009b\u009c\u0005n\u0000"+
		"\u0000\u009c\u009d\u0005t\u0000\u0000\u009d\u009e\u0005o\u0000\u0000\u009e"+
		"\u0018\u0001\u0000\u0000\u0000\u009f\u00a0\u0005f\u0000\u0000\u00a0\u00a1"+
		"\u0005a\u0000\u0000\u00a1\u00a2\u0005c\u0000\u0000\u00a2\u00a3\u0005a"+
		"\u0000\u0000\u00a3\u001a\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005f\u0000"+
		"\u0000\u00a5\u00a6\u0005i\u0000\u0000\u00a6\u00a7\u0005m\u0000\u0000\u00a7"+
		"\u00a8\u0005e\u0000\u0000\u00a8\u00a9\u0005n\u0000\u0000\u00a9\u00aa\u0005"+
		"q\u0000\u0000\u00aa\u00ab\u0005u\u0000\u0000\u00ab\u00ac\u0005a\u0000"+
		"\u0000\u00ac\u00ad\u0005n\u0000\u0000\u00ad\u00ae\u0005t\u0000\u0000\u00ae"+
		"\u00af\u0005o\u0000\u0000\u00af\u001c\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0005p\u0000\u0000\u00b1\u00b2\u0005a\u0000\u0000\u00b2\u00b3\u0005r"+
		"\u0000\u0000\u00b3\u00b4\u0005a\u0000\u0000\u00b4\u001e\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0005+\u0000\u0000\u00b6\u00b7\u0005+\u0000\u0000\u00b7"+
		" \u0001\u0000\u0000\u0000\u00b8\u00b9\u0005-\u0000\u0000\u00b9\u00ba\u0005"+
		"-\u0000\u0000\u00ba\"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005f\u0000"+
		"\u0000\u00bc\u00bd\u0005i\u0000\u0000\u00bd\u00be\u0005m\u0000\u0000\u00be"+
		"\u00bf\u0005p\u0000\u0000\u00bf\u00c0\u0005a\u0000\u0000\u00c0\u00c1\u0005"+
		"r\u0000\u0000\u00c1\u00c2\u0005a\u0000\u0000\u00c2$\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0005+\u0000\u0000\u00c4&\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0005-\u0000\u0000\u00c6(\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005"+
		"/\u0000\u0000\u00c8*\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005*\u0000"+
		"\u0000\u00ca,\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005:\u0000\u0000\u00cc"+
		"\u00cd\u0005=\u0000\u0000\u00cd.\u0001\u0000\u0000\u0000\u00ce\u00d8\u0007"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0005>\u0000\u0000\u00d0\u00d8\u0005=\u0000"+
		"\u0000\u00d1\u00d2\u0005<\u0000\u0000\u00d2\u00d8\u0005=\u0000\u0000\u00d3"+
		"\u00d4\u0005<\u0000\u0000\u00d4\u00d8\u0005>\u0000\u0000\u00d5\u00d6\u0005"+
		"=\u0000\u0000\u00d6\u00d8\u0005=\u0000\u0000\u00d7\u00ce\u0001\u0000\u0000"+
		"\u0000\u00d7\u00cf\u0001\u0000\u0000\u0000\u00d7\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d3\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d80\u0001\u0000\u0000\u0000\u00d9\u00dd\u0007\u0001\u0000\u0000"+
		"\u00da\u00dc\u0007\u0002\u0000\u0000\u00db\u00da\u0001\u0000\u0000\u0000"+
		"\u00dc\u00df\u0001\u0000\u0000\u0000\u00dd\u00db\u0001\u0000\u0000\u0000"+
		"\u00dd\u00de\u0001\u0000\u0000\u0000\u00de2\u0001\u0000\u0000\u0000\u00df"+
		"\u00dd\u0001\u0000\u0000\u0000\u00e0\u00e2\u0007\u0003\u0000\u0000\u00e1"+
		"\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4"+
		"4\u0001\u0000\u0000\u0000\u00e5\u00e7\u0007\u0003\u0000\u0000\u00e6\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e6"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00f0"+
		"\u0001\u0000\u0000\u0000\u00ea\u00ec\u0005.\u0000\u0000\u00eb\u00ed\u0007"+
		"\u0003\u0000\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001"+
		"\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001"+
		"\u0000\u0000\u0000\u00ef\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ea\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f16\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0005,\u0000\u0000\u00f38\u0001\u0000\u0000\u0000"+
		"\u00f4\u00f5\u0005;\u0000\u0000\u00f5:\u0001\u0000\u0000\u0000\u00f6\u00f7"+
		"\u0005.\u0000\u0000\u00f7<\u0001\u0000\u0000\u0000\u00f8\u00f9\u0005("+
		"\u0000\u0000\u00f9>\u0001\u0000\u0000\u0000\u00fa\u00fb\u0005)\u0000\u0000"+
		"\u00fb@\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005{\u0000\u0000\u00fdB"+
		"\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005}\u0000\u0000\u00ffD\u0001\u0000"+
		"\u0000\u0000\u0100\u0101\u0005:\u0000\u0000\u0101F\u0001\u0000\u0000\u0000"+
		"\u0102\u0106\u0005\"\u0000\u0000\u0103\u0105\u0007\u0004\u0000\u0000\u0104"+
		"\u0103\u0001\u0000\u0000\u0000\u0105\u0108\u0001\u0000\u0000\u0000\u0106"+
		"\u0104\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107"+
		"\u0109\u0001\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0109"+
		"\u010a\u0005\"\u0000\u0000\u010aH\u0001\u0000\u0000\u0000\u010b\u0111"+
		"\u0005\"\u0000\u0000\u010c\u010d\u0005\\\u0000\u0000\u010d\u0110\u0005"+
		"\"\u0000\u0000\u010e\u0110\t\u0000\u0000\u0000\u010f\u010c\u0001\u0000"+
		"\u0000\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u0110\u0113\u0001\u0000"+
		"\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0111\u010f\u0001\u0000"+
		"\u0000\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u0111\u0001\u0000"+
		"\u0000\u0000\u0114\u0115\u0005\"\u0000\u0000\u0115J\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0007\u0005\u0000\u0000\u0117\u0118\u0001\u0000\u0000"+
		"\u0000\u0118\u0119\u0006%\u0000\u0000\u0119L\u0001\u0000\u0000\u0000\f"+
		"\u0000\u00d7\u00db\u00dd\u00e3\u00e8\u00ee\u00f0\u0104\u0106\u010f\u0111"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}