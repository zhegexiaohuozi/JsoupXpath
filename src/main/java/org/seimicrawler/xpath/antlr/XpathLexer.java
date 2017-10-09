// Generated from D:/CODE/OSS/JsoupXpath/src/main/resources/dsl\Xpath.g4 by ANTLR 4.7
package org.seimicrawler.xpath.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XpathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NodeType=9, 
		Number=10, AxisName=11, PATHSEP=12, ABRPATH=13, LPAR=14, RPAR=15, LBRAC=16, 
		RBRAC=17, MINUS=18, PLUS=19, DOT=20, MUL=21, DOTDOT=22, AT=23, COMMA=24, 
		PIPE=25, LESS=26, MORE_=27, LE=28, GE=29, START_WITH=30, END_WITH=31, 
		CONTAIN_WITH=32, REGEXP_WITH=33, REGEXP_NOT_WITH=34, COLON=35, CC=36, 
		APOS=37, QUOT=38, Literal=39, Whitespace=40, NCName=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "NodeType", 
		"Number", "Digits", "AxisName", "PATHSEP", "ABRPATH", "LPAR", "RPAR", 
		"LBRAC", "RBRAC", "MINUS", "PLUS", "DOT", "MUL", "DOTDOT", "AT", "COMMA", 
		"PIPE", "LESS", "MORE_", "LE", "GE", "START_WITH", "END_WITH", "CONTAIN_WITH", 
		"REGEXP_WITH", "REGEXP_NOT_WITH", "COLON", "CC", "APOS", "QUOT", "Literal", 
		"Whitespace", "NCName", "NCNameStartChar", "NCNameChar"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'processing-instruction'", "'or'", "'and'", "'='", "'!='", "'`div`'", 
		"'`mod`'", "'$'", null, null, null, "'/'", "'//'", "'('", "')'", "'['", 
		"']'", "'-'", "'+'", "'.'", "'*'", "'..'", "'@'", "','", "'|'", "'<'", 
		"'>'", "'<='", "'>='", "'^='", "'$='", "'*='", "'~='", "'!~'", "':'", 
		"'::'", "'''", "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "NodeType", "Number", 
		"AxisName", "PATHSEP", "ABRPATH", "LPAR", "RPAR", "LBRAC", "RBRAC", "MINUS", 
		"PLUS", "DOT", "MUL", "DOTDOT", "AT", "COMMA", "PIPE", "LESS", "MORE_", 
		"LE", "GE", "START_WITH", "END_WITH", "CONTAIN_WITH", "REGEXP_WITH", "REGEXP_NOT_WITH", 
		"COLON", "CC", "APOS", "QUOT", "Literal", "Whitespace", "NCName"
	};
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


	public XpathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Xpath.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u01b7\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\5\n\u00b5\n\n\3\13\3\13\3\13\5\13\u00ba\n\13\5\13\u00bc"+
		"\n\13\3\13\3\13\5\13\u00c0\n\13\3\f\6\f\u00c3\n\f\r\f\16\f\u00c4\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0150\n\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3"+
		"!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3)"+
		"\7)\u0194\n)\f)\16)\u0197\13)\3)\3)\3)\7)\u019c\n)\f)\16)\u019f\13)\3"+
		")\5)\u01a2\n)\3*\6*\u01a5\n*\r*\16*\u01a6\3*\3*\3+\3+\7+\u01ad\n+\f+\16"+
		"+\u01b0\13+\3,\3,\3-\3-\5-\u01b6\n-\2\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\2\31\r\33\16\35\17\37\20!\21#\22%\23\'\24)\25+\26"+
		"-\27/\30\61\31\63\32\65\33\67\349\35;\36=\37? A!C\"E#G$I%K&M\'O(Q)S*U"+
		"+W\2Y\2\3\2\7\3\2$$\3\2))\5\2\13\f\17\17\"\"\20\2C\\aac|\u00c2\u00d8\u00da"+
		"\u00f8\u00fa\u0301\u0372\u037f\u0381\u2001\u200e\u200f\u2072\u2191\u2c02"+
		"\u2ff1\u3003\ud801\uf902\ufdd1\ufdf2\uffff\7\2/\60\62;\u00b9\u00b9\u0302"+
		"\u0371\u2041\u2042\2\u01cd\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\3[\3\2\2\2\5r\3\2\2\2\7u\3\2\2\2\ty\3\2\2\2\13{\3"+
		"\2\2\2\r~\3\2\2\2\17\u0084\3\2\2\2\21\u008a\3\2\2\2\23\u00b4\3\2\2\2\25"+
		"\u00bf\3\2\2\2\27\u00c2\3\2\2\2\31\u014f\3\2\2\2\33\u0151\3\2\2\2\35\u0153"+
		"\3\2\2\2\37\u0156\3\2\2\2!\u0158\3\2\2\2#\u015a\3\2\2\2%\u015c\3\2\2\2"+
		"\'\u015e\3\2\2\2)\u0160\3\2\2\2+\u0162\3\2\2\2-\u0164\3\2\2\2/\u0166\3"+
		"\2\2\2\61\u0169\3\2\2\2\63\u016b\3\2\2\2\65\u016d\3\2\2\2\67\u016f\3\2"+
		"\2\29\u0171\3\2\2\2;\u0173\3\2\2\2=\u0176\3\2\2\2?\u0179\3\2\2\2A\u017c"+
		"\3\2\2\2C\u017f\3\2\2\2E\u0182\3\2\2\2G\u0185\3\2\2\2I\u0188\3\2\2\2K"+
		"\u018a\3\2\2\2M\u018d\3\2\2\2O\u018f\3\2\2\2Q\u01a1\3\2\2\2S\u01a4\3\2"+
		"\2\2U\u01aa\3\2\2\2W\u01b1\3\2\2\2Y\u01b5\3\2\2\2[\\\7r\2\2\\]\7t\2\2"+
		"]^\7q\2\2^_\7e\2\2_`\7g\2\2`a\7u\2\2ab\7u\2\2bc\7k\2\2cd\7p\2\2de\7i\2"+
		"\2ef\7/\2\2fg\7k\2\2gh\7p\2\2hi\7u\2\2ij\7v\2\2jk\7t\2\2kl\7w\2\2lm\7"+
		"e\2\2mn\7v\2\2no\7k\2\2op\7q\2\2pq\7p\2\2q\4\3\2\2\2rs\7q\2\2st\7t\2\2"+
		"t\6\3\2\2\2uv\7c\2\2vw\7p\2\2wx\7f\2\2x\b\3\2\2\2yz\7?\2\2z\n\3\2\2\2"+
		"{|\7#\2\2|}\7?\2\2}\f\3\2\2\2~\177\7b\2\2\177\u0080\7f\2\2\u0080\u0081"+
		"\7k\2\2\u0081\u0082\7x\2\2\u0082\u0083\7b\2\2\u0083\16\3\2\2\2\u0084\u0085"+
		"\7b\2\2\u0085\u0086\7o\2\2\u0086\u0087\7q\2\2\u0087\u0088\7f\2\2\u0088"+
		"\u0089\7b\2\2\u0089\20\3\2\2\2\u008a\u008b\7&\2\2\u008b\22\3\2\2\2\u008c"+
		"\u008d\7e\2\2\u008d\u008e\7q\2\2\u008e\u008f\7o\2\2\u008f\u0090\7o\2\2"+
		"\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2\u0092\u00b5\7v\2\2\u0093\u0094"+
		"\7v\2\2\u0094\u0095\7g\2\2\u0095\u0096\7z\2\2\u0096\u00b5\7v\2\2\u0097"+
		"\u0098\7r\2\2\u0098\u0099\7t\2\2\u0099\u009a\7q\2\2\u009a\u009b\7e\2\2"+
		"\u009b\u009c\7g\2\2\u009c\u009d\7u\2\2\u009d\u009e\7u\2\2\u009e\u009f"+
		"\7k\2\2\u009f\u00a0\7p\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7/\2\2\u00a2"+
		"\u00a3\7k\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7v\2\2"+
		"\u00a6\u00a7\7t\2\2\u00a7\u00a8\7w\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa"+
		"\7v\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7q\2\2\u00ac\u00b5\7p\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7f\2\2\u00b0\u00b5\7g\2\2"+
		"\u00b1\u00b2\7p\2\2\u00b2\u00b3\7w\2\2\u00b3\u00b5\7o\2\2\u00b4\u008c"+
		"\3\2\2\2\u00b4\u0093\3\2\2\2\u00b4\u0097\3\2\2\2\u00b4\u00ad\3\2\2\2\u00b4"+
		"\u00b1\3\2\2\2\u00b5\24\3\2\2\2\u00b6\u00bb\5\27\f\2\u00b7\u00b9\7\60"+
		"\2\2\u00b8\u00ba\5\27\f\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\u00bc\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00c0\3\2"+
		"\2\2\u00bd\u00be\7\60\2\2\u00be\u00c0\5\27\f\2\u00bf\u00b6\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00c0\26\3\2\2\2\u00c1\u00c3\4\62;\2\u00c2\u00c1\3\2\2"+
		"\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\30"+
		"\3\2\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7e\2\2\u00c9"+
		"\u00ca\7g\2\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7q\2\2"+
		"\u00cd\u0150\7t\2\2\u00ce\u00cf\7c\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1"+
		"\7e\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7u\2\2\u00d3\u00d4\7v\2\2\u00d4"+
		"\u00d5\7q\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7/\2\2\u00d7\u00d8\7q\2\2"+
		"\u00d8\u00d9\7t\2\2\u00d9\u00da\7/\2\2\u00da\u00db\7u\2\2\u00db\u00dc"+
		"\7g\2\2\u00dc\u00dd\7n\2\2\u00dd\u0150\7h\2\2\u00de\u00df\7c\2\2\u00df"+
		"\u00e0\7v\2\2\u00e0\u00e1\7v\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7k\2\2"+
		"\u00e3\u00e4\7d\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6\7v\2\2\u00e6\u0150"+
		"\7g\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7j\2\2\u00e9\u00ea\7k\2\2\u00ea"+
		"\u00eb\7n\2\2\u00eb\u0150\7f\2\2\u00ec\u00ed\7f\2\2\u00ed\u00ee\7g\2\2"+
		"\u00ee\u00ef\7u\2\2\u00ef\u00f0\7e\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2"+
		"\7p\2\2\u00f2\u00f3\7f\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7p\2\2\u00f5"+
		"\u0150\7v\2\2\u00f6\u00f7\7f\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7u\2\2"+
		"\u00f9\u00fa\7e\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7p\2\2\u00fc\u00fd"+
		"\7f\2\2\u00fd\u00fe\7c\2\2\u00fe\u00ff\7p\2\2\u00ff\u0100\7v\2\2\u0100"+
		"\u0101\7/\2\2\u0101\u0102\7q\2\2\u0102\u0103\7t\2\2\u0103\u0104\7/\2\2"+
		"\u0104\u0105\7u\2\2\u0105\u0106\7g\2\2\u0106\u0107\7n\2\2\u0107\u0150"+
		"\7h\2\2\u0108\u0109\7h\2\2\u0109\u010a\7q\2\2\u010a\u010b\7n\2\2\u010b"+
		"\u010c\7n\2\2\u010c\u010d\7q\2\2\u010d\u010e\7y\2\2\u010e\u010f\7k\2\2"+
		"\u010f\u0110\7p\2\2\u0110\u0150\7i\2\2\u0111\u0112\7h\2\2\u0112\u0113"+
		"\7q\2\2\u0113\u0114\7n\2\2\u0114\u0115\7n\2\2\u0115\u0116\7q\2\2\u0116"+
		"\u0117\7y\2\2\u0117\u0118\7k\2\2\u0118\u0119\7p\2\2\u0119\u011a\7i\2\2"+
		"\u011a\u011b\7/\2\2\u011b\u011c\7u\2\2\u011c\u011d\7k\2\2\u011d\u011e"+
		"\7d\2\2\u011e\u011f\7n\2\2\u011f\u0120\7k\2\2\u0120\u0121\7p\2\2\u0121"+
		"\u0150\7i\2\2\u0122\u0123\7p\2\2\u0123\u0124\7c\2\2\u0124\u0125\7o\2\2"+
		"\u0125\u0126\7g\2\2\u0126\u0127\7u\2\2\u0127\u0128\7r\2\2\u0128\u0129"+
		"\7c\2\2\u0129\u012a\7e\2\2\u012a\u0150\7g\2\2\u012b\u012c\7r\2\2\u012c"+
		"\u012d\7c\2\2\u012d\u012e\7t\2\2\u012e\u012f\7g\2\2\u012f\u0130\7p\2\2"+
		"\u0130\u0150\7v\2\2\u0131\u0132\7r\2\2\u0132\u0133\7t\2\2\u0133\u0134"+
		"\7g\2\2\u0134\u0135\7e\2\2\u0135\u0136\7g\2\2\u0136\u0137\7f\2\2\u0137"+
		"\u0138\7k\2\2\u0138\u0139\7p\2\2\u0139\u0150\7i\2\2\u013a\u013b\7r\2\2"+
		"\u013b\u013c\7t\2\2\u013c\u013d\7g\2\2\u013d\u013e\7e\2\2\u013e\u013f"+
		"\7g\2\2\u013f\u0140\7f\2\2\u0140\u0141\7k\2\2\u0141\u0142\7p\2\2\u0142"+
		"\u0143\7i\2\2\u0143\u0144\7/\2\2\u0144\u0145\7u\2\2\u0145\u0146\7k\2\2"+
		"\u0146\u0147\7d\2\2\u0147\u0148\7n\2\2\u0148\u0149\7k\2\2\u0149\u014a"+
		"\7p\2\2\u014a\u0150\7i\2\2\u014b\u014c\7u\2\2\u014c\u014d\7g\2\2\u014d"+
		"\u014e\7n\2\2\u014e\u0150\7h\2\2\u014f\u00c6\3\2\2\2\u014f\u00ce\3\2\2"+
		"\2\u014f\u00de\3\2\2\2\u014f\u00e7\3\2\2\2\u014f\u00ec\3\2\2\2\u014f\u00f6"+
		"\3\2\2\2\u014f\u0108\3\2\2\2\u014f\u0111\3\2\2\2\u014f\u0122\3\2\2\2\u014f"+
		"\u012b\3\2\2\2\u014f\u0131\3\2\2\2\u014f\u013a\3\2\2\2\u014f\u014b\3\2"+
		"\2\2\u0150\32\3\2\2\2\u0151\u0152\7\61\2\2\u0152\34\3\2\2\2\u0153\u0154"+
		"\7\61\2\2\u0154\u0155\7\61\2\2\u0155\36\3\2\2\2\u0156\u0157\7*\2\2\u0157"+
		" \3\2\2\2\u0158\u0159\7+\2\2\u0159\"\3\2\2\2\u015a\u015b\7]\2\2\u015b"+
		"$\3\2\2\2\u015c\u015d\7_\2\2\u015d&\3\2\2\2\u015e\u015f\7/\2\2\u015f("+
		"\3\2\2\2\u0160\u0161\7-\2\2\u0161*\3\2\2\2\u0162\u0163\7\60\2\2\u0163"+
		",\3\2\2\2\u0164\u0165\7,\2\2\u0165.\3\2\2\2\u0166\u0167\7\60\2\2\u0167"+
		"\u0168\7\60\2\2\u0168\60\3\2\2\2\u0169\u016a\7B\2\2\u016a\62\3\2\2\2\u016b"+
		"\u016c\7.\2\2\u016c\64\3\2\2\2\u016d\u016e\7~\2\2\u016e\66\3\2\2\2\u016f"+
		"\u0170\7>\2\2\u01708\3\2\2\2\u0171\u0172\7@\2\2\u0172:\3\2\2\2\u0173\u0174"+
		"\7>\2\2\u0174\u0175\7?\2\2\u0175<\3\2\2\2\u0176\u0177\7@\2\2\u0177\u0178"+
		"\7?\2\2\u0178>\3\2\2\2\u0179\u017a\7`\2\2\u017a\u017b\7?\2\2\u017b@\3"+
		"\2\2\2\u017c\u017d\7&\2\2\u017d\u017e\7?\2\2\u017eB\3\2\2\2\u017f\u0180"+
		"\7,\2\2\u0180\u0181\7?\2\2\u0181D\3\2\2\2\u0182\u0183\7\u0080\2\2\u0183"+
		"\u0184\7?\2\2\u0184F\3\2\2\2\u0185\u0186\7#\2\2\u0186\u0187\7\u0080\2"+
		"\2\u0187H\3\2\2\2\u0188\u0189\7<\2\2\u0189J\3\2\2\2\u018a\u018b\7<\2\2"+
		"\u018b\u018c\7<\2\2\u018cL\3\2\2\2\u018d\u018e\7)\2\2\u018eN\3\2\2\2\u018f"+
		"\u0190\7$\2\2\u0190P\3\2\2\2\u0191\u0195\7$\2\2\u0192\u0194\n\2\2\2\u0193"+
		"\u0192\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2"+
		"\2\2\u0196\u0198\3\2\2\2\u0197\u0195\3\2\2\2\u0198\u01a2\7$\2\2\u0199"+
		"\u019d\7)\2\2\u019a\u019c\n\3\2\2\u019b\u019a\3\2\2\2\u019c\u019f\3\2"+
		"\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2\2\u019f"+
		"\u019d\3\2\2\2\u01a0\u01a2\7)\2\2\u01a1\u0191\3\2\2\2\u01a1\u0199\3\2"+
		"\2\2\u01a2R\3\2\2\2\u01a3\u01a5\t\4\2\2\u01a4\u01a3\3\2\2\2\u01a5\u01a6"+
		"\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8"+
		"\u01a9\b*\2\2\u01a9T\3\2\2\2\u01aa\u01ae\5W,\2\u01ab\u01ad\5Y-\2\u01ac"+
		"\u01ab\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2"+
		"\2\2\u01afV\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\t\5\2\2\u01b2X\3\2"+
		"\2\2\u01b3\u01b6\5W,\2\u01b4\u01b6\t\6\2\2\u01b5\u01b3\3\2\2\2\u01b5\u01b4"+
		"\3\2\2\2\u01b6Z\3\2\2\2\17\2\u00b4\u00b9\u00bb\u00bf\u00c4\u014f\u0195"+
		"\u019d\u01a1\u01a6\u01ae\u01b5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}