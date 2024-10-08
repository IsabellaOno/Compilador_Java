// Generated from IsiLanguage.g4 by ANTLR 4.4

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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, SOMA=14, SUB=15, DIV=16, MULT=17, 
		OP_AT=18, OPREL=19, ID=20, NUMERO=21, NUMERO_REAL=22, VIRG=23, PV=24, 
		PO=25, AP=26, FP=27, AC=28, FC=29, DP=30, TEXTO=31, STRING=32, WS=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'"
	};
	public static final String[] ruleNames = {
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "SOMA", "SUB", "DIV", "MULT", "OP_AT", 
		"OPREL", "ID", "NUMERO", "NUMERO_REAL", "VIRG", "PV", "PO", "AP", "FP", 
		"AC", "FC", "DP", "TEXTO", "STRING", "WS"
	};


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
		       	System.out.println("Warning - Variável " + sym.getId() + " foi declarada, mas não foi utilizada."); 
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
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00f3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00b1\n\24"+
		"\3\25\3\25\7\25\u00b5\n\25\f\25\16\25\u00b8\13\25\3\26\6\26\u00bb\n\26"+
		"\r\26\16\26\u00bc\3\27\6\27\u00c0\n\27\r\27\16\27\u00c1\3\27\3\27\6\27"+
		"\u00c6\n\27\r\27\16\27\u00c7\5\27\u00ca\n\27\3\30\3\30\3\31\3\31\3\32"+
		"\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \7 \u00de"+
		"\n \f \16 \u00e1\13 \3 \3 \3!\3!\3!\3!\7!\u00e9\n!\f!\16!\u00ec\13!\3"+
		"!\3!\3\"\3\"\3\"\3\"\3\u00ea\2#\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#\3\2\b\4\2>>@@\3\2c|\5\2\62;"+
		"C\\c|\3\2\62;\7\2\"\".\60\62;C\\c|\5\2\13\f\17\17\"\"\u00fe\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5H\3\2\2\2\7N\3\2\2"+
		"\2\tV\3\2\2\2\13_\3\2\2\2\rd\3\2\2\2\17l\3\2\2\2\21u\3\2\2\2\23~\3\2\2"+
		"\2\25\u0084\3\2\2\2\27\u008c\3\2\2\2\31\u0091\3\2\2\2\33\u0097\3\2\2\2"+
		"\35\u009c\3\2\2\2\37\u009e\3\2\2\2!\u00a0\3\2\2\2#\u00a2\3\2\2\2%\u00a4"+
		"\3\2\2\2\'\u00b0\3\2\2\2)\u00b2\3\2\2\2+\u00ba\3\2\2\2-\u00bf\3\2\2\2"+
		"/\u00cb\3\2\2\2\61\u00cd\3\2\2\2\63\u00cf\3\2\2\2\65\u00d1\3\2\2\2\67"+
		"\u00d3\3\2\2\29\u00d5\3\2\2\2;\u00d7\3\2\2\2=\u00d9\3\2\2\2?\u00db\3\2"+
		"\2\2A\u00e4\3\2\2\2C\u00ef\3\2\2\2EF\7u\2\2FG\7g\2\2G\4\3\2\2\2HI\7u\2"+
		"\2IJ\7g\2\2JK\7p\2\2KL\7c\2\2LM\7q\2\2M\6\3\2\2\2NO\7k\2\2OP\7p\2\2PQ"+
		"\7v\2\2QR\7g\2\2RS\7k\2\2ST\7t\2\2TU\7q\2\2U\b\3\2\2\2VW\7r\2\2WX\7t\2"+
		"\2XY\7q\2\2YZ\7i\2\2Z[\7t\2\2[\\\7c\2\2\\]\7o\2\2]^\7c\2\2^\n\3\2\2\2"+
		"_`\7h\2\2`a\7c\2\2ab\7e\2\2bc\7c\2\2c\f\3\2\2\2de\7g\2\2ef\7u\2\2fg\7"+
		"e\2\2gh\7t\2\2hi\7g\2\2ij\7x\2\2jk\7c\2\2k\16\3\2\2\2lm\7g\2\2mn\7p\2"+
		"\2no\7s\2\2op\7w\2\2pq\7c\2\2qr\7p\2\2rs\7v\2\2st\7q\2\2t\20\3\2\2\2u"+
		"v\7h\2\2vw\7k\2\2wx\7o\2\2xy\7r\2\2yz\7t\2\2z{\7q\2\2{|\7i\2\2|}\7\60"+
		"\2\2}\22\3\2\2\2~\177\7g\2\2\177\u0080\7p\2\2\u0080\u0081\7v\2\2\u0081"+
		"\u0082\7c\2\2\u0082\u0083\7q\2\2\u0083\24\3\2\2\2\u0084\u0085\7f\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7e\2\2\u0087\u0088\7n\2\2\u0088\u0089\7c\2\2"+
		"\u0089\u008a\7t\2\2\u008a\u008b\7g\2\2\u008b\26\3\2\2\2\u008c\u008d\7"+
		"n\2\2\u008d\u008e\7g\2\2\u008e\u008f\7k\2\2\u008f\u0090\7c\2\2\u0090\30"+
		"\3\2\2\2\u0091\u0092\7v\2\2\u0092\u0093\7g\2\2\u0093\u0094\7z\2\2\u0094"+
		"\u0095\7v\2\2\u0095\u0096\7q\2\2\u0096\32\3\2\2\2\u0097\u0098\7t\2\2\u0098"+
		"\u0099\7g\2\2\u0099\u009a\7c\2\2\u009a\u009b\7n\2\2\u009b\34\3\2\2\2\u009c"+
		"\u009d\7-\2\2\u009d\36\3\2\2\2\u009e\u009f\7/\2\2\u009f \3\2\2\2\u00a0"+
		"\u00a1\7\61\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\7,\2\2\u00a3$\3\2\2\2\u00a4"+
		"\u00a5\7<\2\2\u00a5\u00a6\7?\2\2\u00a6&\3\2\2\2\u00a7\u00b1\t\2\2\2\u00a8"+
		"\u00a9\7@\2\2\u00a9\u00b1\7?\2\2\u00aa\u00ab\7>\2\2\u00ab\u00b1\7?\2\2"+
		"\u00ac\u00ad\7>\2\2\u00ad\u00b1\7@\2\2\u00ae\u00af\7?\2\2\u00af\u00b1"+
		"\7?\2\2\u00b0\u00a7\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0"+
		"\u00ac\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1(\3\2\2\2\u00b2\u00b6\t\3\2\2"+
		"\u00b3\u00b5\t\4\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4"+
		"\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7*\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00bb\t\5\2\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2"+
		"\2\2\u00bc\u00bd\3\2\2\2\u00bd,\3\2\2\2\u00be\u00c0\t\5\2\2\u00bf\u00be"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c9\3\2\2\2\u00c3\u00c5\7\60\2\2\u00c4\u00c6\t\5\2\2\u00c5\u00c4\3"+
		"\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00ca\3\2\2\2\u00c9\u00c3\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca.\3\2\2\2"+
		"\u00cb\u00cc\7.\2\2\u00cc\60\3\2\2\2\u00cd\u00ce\7=\2\2\u00ce\62\3\2\2"+
		"\2\u00cf\u00d0\7\60\2\2\u00d0\64\3\2\2\2\u00d1\u00d2\7*\2\2\u00d2\66\3"+
		"\2\2\2\u00d3\u00d4\7+\2\2\u00d48\3\2\2\2\u00d5\u00d6\7}\2\2\u00d6:\3\2"+
		"\2\2\u00d7\u00d8\7\177\2\2\u00d8<\3\2\2\2\u00d9\u00da\7<\2\2\u00da>\3"+
		"\2\2\2\u00db\u00df\7$\2\2\u00dc\u00de\t\6\2\2\u00dd\u00dc\3\2\2\2\u00de"+
		"\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2"+
		"\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e3\7$\2\2\u00e3@\3\2\2\2\u00e4\u00ea"+
		"\7$\2\2\u00e5\u00e6\7^\2\2\u00e6\u00e9\7$\2\2\u00e7\u00e9\13\2\2\2\u00e8"+
		"\u00e5\3\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed"+
		"\u00ee\7$\2\2\u00eeB\3\2\2\2\u00ef\u00f0\t\7\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\b\"\2\2\u00f2D\3\2\2\2\16\2\u00b0\u00b4\u00b6\u00bc\u00c1\u00c7"+
		"\u00c9\u00dd\u00df\u00e8\u00ea\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}