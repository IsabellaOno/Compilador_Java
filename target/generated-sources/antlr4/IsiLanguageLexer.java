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
		T__16=1, T__15=2, T__14=3, T__13=4, T__12=5, T__11=6, T__10=7, T__9=8, 
		T__8=9, T__7=10, T__6=11, T__5=12, T__4=13, T__3=14, T__2=15, T__1=16, 
		T__0=17, SOMA=18, SUB=19, DIV=20, MULT=21, OP_AT=22, OPREL=23, ID=24, 
		NUMERO=25, NUMERO_REAL=26, VIRG=27, PV=28, PO=29, AP=30, FP=31, AC=32, 
		FC=33, DP=34, TEXTO=35, STRING=36, WS=37;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'"
	};
	public static final String[] ruleNames = {
		"T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", 
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"SOMA", "SUB", "DIV", "MULT", "OP_AT", "OPREL", "ID", "NUMERO", "NUMERO_REAL", 
		"VIRG", "PV", "PO", "AP", "FP", "AC", "FC", "DP", "TEXTO", "STRING", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\'\u010e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00cc\n\30\3\31\3\31\7\31"+
		"\u00d0\n\31\f\31\16\31\u00d3\13\31\3\32\6\32\u00d6\n\32\r\32\16\32\u00d7"+
		"\3\33\6\33\u00db\n\33\r\33\16\33\u00dc\3\33\3\33\6\33\u00e1\n\33\r\33"+
		"\16\33\u00e2\5\33\u00e5\n\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\7$\u00f9\n$\f$\16$\u00fc\13$\3$\3$\3%\3"+
		"%\3%\3%\7%\u0104\n%\f%\16%\u0107\13%\3%\3%\3&\3&\3&\3&\3\u0105\2\'\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'\3\2\b\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\7\2\"\".\60\62"+
		";C\\c|\5\2\13\f\17\17\"\"\u0119\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3\2\2\2\5P"+
		"\3\2\2\2\7V\3\2\2\2\t^\3\2\2\2\13a\3\2\2\2\rd\3\2\2\2\17m\3\2\2\2\21r"+
		"\3\2\2\2\23z\3\2\2\2\25\u0082\3\2\2\2\27\u008b\3\2\2\2\31\u0094\3\2\2"+
		"\2\33\u0099\3\2\2\2\35\u009f\3\2\2\2\37\u00a7\3\2\2\2!\u00ac\3\2\2\2#"+
		"\u00b2\3\2\2\2%\u00b7\3\2\2\2\'\u00b9\3\2\2\2)\u00bb\3\2\2\2+\u00bd\3"+
		"\2\2\2-\u00bf\3\2\2\2/\u00cb\3\2\2\2\61\u00cd\3\2\2\2\63\u00d5\3\2\2\2"+
		"\65\u00da\3\2\2\2\67\u00e6\3\2\2\29\u00e8\3\2\2\2;\u00ea\3\2\2\2=\u00ec"+
		"\3\2\2\2?\u00ee\3\2\2\2A\u00f0\3\2\2\2C\u00f2\3\2\2\2E\u00f4\3\2\2\2G"+
		"\u00f6\3\2\2\2I\u00ff\3\2\2\2K\u010a\3\2\2\2MN\7u\2\2NO\7g\2\2O\4\3\2"+
		"\2\2PQ\7u\2\2QR\7g\2\2RS\7p\2\2ST\7c\2\2TU\7q\2\2U\6\3\2\2\2VW\7k\2\2"+
		"WX\7p\2\2XY\7v\2\2YZ\7g\2\2Z[\7k\2\2[\\\7t\2\2\\]\7q\2\2]\b\3\2\2\2^_"+
		"\7/\2\2_`\7/\2\2`\n\3\2\2\2ab\7-\2\2bc\7-\2\2c\f\3\2\2\2de\7r\2\2ef\7"+
		"t\2\2fg\7q\2\2gh\7i\2\2hi\7t\2\2ij\7c\2\2jk\7o\2\2kl\7c\2\2l\16\3\2\2"+
		"\2mn\7h\2\2no\7c\2\2op\7e\2\2pq\7c\2\2q\20\3\2\2\2rs\7h\2\2st\7k\2\2t"+
		"u\7o\2\2uv\7r\2\2vw\7c\2\2wx\7t\2\2xy\7c\2\2y\22\3\2\2\2z{\7g\2\2{|\7"+
		"u\2\2|}\7e\2\2}~\7t\2\2~\177\7g\2\2\177\u0080\7x\2\2\u0080\u0081\7c\2"+
		"\2\u0081\24\3\2\2\2\u0082\u0083\7g\2\2\u0083\u0084\7p\2\2\u0084\u0085"+
		"\7s\2\2\u0085\u0086\7w\2\2\u0086\u0087\7c\2\2\u0087\u0088\7p\2\2\u0088"+
		"\u0089\7v\2\2\u0089\u008a\7q\2\2\u008a\26\3\2\2\2\u008b\u008c\7h\2\2\u008c"+
		"\u008d\7k\2\2\u008d\u008e\7o\2\2\u008e\u008f\7r\2\2\u008f\u0090\7t\2\2"+
		"\u0090\u0091\7q\2\2\u0091\u0092\7i\2\2\u0092\u0093\7\60\2\2\u0093\30\3"+
		"\2\2\2\u0094\u0095\7r\2\2\u0095\u0096\7c\2\2\u0096\u0097\7t\2\2\u0097"+
		"\u0098\7c\2\2\u0098\32\3\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2\u009b"+
		"\u009c\7v\2\2\u009c\u009d\7c\2\2\u009d\u009e\7q\2\2\u009e\34\3\2\2\2\u009f"+
		"\u00a0\7f\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7n\2\2"+
		"\u00a3\u00a4\7c\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7g\2\2\u00a6\36\3\2"+
		"\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab"+
		"\7c\2\2\u00ab \3\2\2\2\u00ac\u00ad\7v\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af"+
		"\7z\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7q\2\2\u00b1\"\3\2\2\2\u00b2\u00b3"+
		"\7t\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7n\2\2\u00b6"+
		"$\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8&\3\2\2\2\u00b9\u00ba\7/\2\2\u00ba("+
		"\3\2\2\2\u00bb\u00bc\7\61\2\2\u00bc*\3\2\2\2\u00bd\u00be\7,\2\2\u00be"+
		",\3\2\2\2\u00bf\u00c0\7<\2\2\u00c0\u00c1\7?\2\2\u00c1.\3\2\2\2\u00c2\u00cc"+
		"\t\2\2\2\u00c3\u00c4\7@\2\2\u00c4\u00cc\7?\2\2\u00c5\u00c6\7>\2\2\u00c6"+
		"\u00cc\7?\2\2\u00c7\u00c8\7>\2\2\u00c8\u00cc\7@\2\2\u00c9\u00ca\7?\2\2"+
		"\u00ca\u00cc\7?\2\2\u00cb\u00c2\3\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c5"+
		"\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc\60\3\2\2\2\u00cd"+
		"\u00d1\t\3\2\2\u00ce\u00d0\t\4\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\62\3\2\2\2\u00d3\u00d1"+
		"\3\2\2\2\u00d4\u00d6\t\5\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\64\3\2\2\2\u00d9\u00db\t\5\2"+
		"\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd"+
		"\3\2\2\2\u00dd\u00e4\3\2\2\2\u00de\u00e0\7\60\2\2\u00df\u00e1\t\5\2\2"+
		"\u00e0\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00de\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\66\3\2\2\2\u00e6\u00e7\7.\2\2\u00e78\3\2\2\2\u00e8\u00e9\7=\2\2\u00e9"+
		":\3\2\2\2\u00ea\u00eb\7\60\2\2\u00eb<\3\2\2\2\u00ec\u00ed\7*\2\2\u00ed"+
		">\3\2\2\2\u00ee\u00ef\7+\2\2\u00ef@\3\2\2\2\u00f0\u00f1\7}\2\2\u00f1B"+
		"\3\2\2\2\u00f2\u00f3\7\177\2\2\u00f3D\3\2\2\2\u00f4\u00f5\7<\2\2\u00f5"+
		"F\3\2\2\2\u00f6\u00fa\7$\2\2\u00f7\u00f9\t\6\2\2\u00f8\u00f7\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\7$\2\2\u00feH\3\2\2\2\u00ff\u0105"+
		"\7$\2\2\u0100\u0101\7^\2\2\u0101\u0104\7$\2\2\u0102\u0104\13\2\2\2\u0103"+
		"\u0100\3\2\2\2\u0103\u0102\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0105\u0103\3\2\2\2\u0106\u0108\3\2\2\2\u0107\u0105\3\2\2\2\u0108"+
		"\u0109\7$\2\2\u0109J\3\2\2\2\u010a\u010b\t\7\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010d\b&\2\2\u010dL\3\2\2\2\16\2\u00cb\u00cf\u00d1\u00d7\u00dc\u00e2"+
		"\u00e4\u00f8\u00fa\u0103\u0105\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}