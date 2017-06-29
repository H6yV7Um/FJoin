// Generated from com/ahuazhu/fjoin/sql/Sql.g4 by ANTLR 4.5.3
package com.ahuazhu.fjoin.sql;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SqlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FIELDS=1, FROM_STATEMENT=2, FIELD=3, SELECT=4, FROM=5, AS=6, DB_NAME=7, 
		TABLE_NAME=8, FIELD_NAME=9, ALIAS=10, DOT=11, IDENTIFY=12, WS=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"FIELDS", "FROM_STATEMENT", "FIELD", "SELECT", "FROM", "AS", "DB_NAME", 
		"TABLE_NAME", "FIELD_NAME", "ALIAS", "DOT", "IDENTIFY", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'select'", "'from'", "'as'", null, null, null, 
		null, "'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FIELDS", "FROM_STATEMENT", "FIELD", "SELECT", "FROM", "AS", "DB_NAME", 
		"TABLE_NAME", "FIELD_NAME", "ALIAS", "DOT", "IDENTIFY", "WS"
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


	public SqlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Sql.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17^\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\5\2#\n\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\48"+
		"\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\6\rT\n\r\r\r\16\rU\3\16\6\16"+
		"Y\n\16\r\16\16\16Z\3\16\3\16\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\3\2\4\3\2\62;\5\2\13\f\17\17\"\"a\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\3\"\3\2\2\2\5$\3\2\2\2\7\67\3\2\2\2\t9\3\2\2\2\13@\3"+
		"\2\2\2\rE\3\2\2\2\17H\3\2\2\2\21J\3\2\2\2\23L\3\2\2\2\25N\3\2\2\2\27P"+
		"\3\2\2\2\31S\3\2\2\2\33X\3\2\2\2\35#\5\7\4\2\36\37\5\7\4\2\37 \7.\2\2"+
		" !\5\3\2\2!#\3\2\2\2\"\35\3\2\2\2\"\36\3\2\2\2#\4\3\2\2\2$%\5\13\6\2%"+
		"&\5\17\b\2&\'\5\27\f\2\'(\5\21\t\2(\6\3\2\2\2)*\5\17\b\2*+\5\27\f\2+,"+
		"\5\21\t\2,-\5\27\f\2-.\5\23\n\2.8\3\2\2\2/\60\5\17\b\2\60\61\5\27\f\2"+
		"\61\62\5\21\t\2\62\63\5\27\f\2\63\64\5\23\n\2\64\65\5\r\7\2\65\66\5\25"+
		"\13\2\668\3\2\2\2\67)\3\2\2\2\67/\3\2\2\28\b\3\2\2\29:\7u\2\2:;\7g\2\2"+
		";<\7n\2\2<=\7g\2\2=>\7e\2\2>?\7v\2\2?\n\3\2\2\2@A\7h\2\2AB\7t\2\2BC\7"+
		"q\2\2CD\7o\2\2D\f\3\2\2\2EF\7c\2\2FG\7u\2\2G\16\3\2\2\2HI\5\31\r\2I\20"+
		"\3\2\2\2JK\5\31\r\2K\22\3\2\2\2LM\5\31\r\2M\24\3\2\2\2NO\5\31\r\2O\26"+
		"\3\2\2\2PQ\7\60\2\2Q\30\3\2\2\2RT\t\2\2\2SR\3\2\2\2TU\3\2\2\2US\3\2\2"+
		"\2UV\3\2\2\2V\32\3\2\2\2WY\t\3\2\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2"+
		"\2\2[\\\3\2\2\2\\]\b\16\2\2]\34\3\2\2\2\7\2\"\67UZ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}