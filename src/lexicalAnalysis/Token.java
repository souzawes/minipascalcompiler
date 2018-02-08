package lexicalAnalysis;

public class Token{
	
	private int 	type;
	private String	spelling;
	private int		currentLine,
					currentColumn;
	
	public Token(int type, String spelling, int currentLine, int currentColumn){
		setType(type);
		setSpelling(spelling);
		setCurrentLine(currentLine);
		setCurrentColumn(currentColumn);
	}

	private void setType(int newType){
		this.type	= newType;
	}
	
	private void setSpelling(String newValue){
		this.spelling	= newValue;
	}
	
	private void setCurrentLine(int newCurrentLine){
		this.currentLine	= newCurrentLine; 
	}
	
	private void setCurrentColumn(int newCurrentColumn){
		this.currentColumn	= newCurrentColumn;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getSpelling() {
		return this.spelling;
	}
	
	public int getCurrentLine() {
		return this.currentLine;
	}
	
	public int getCurrentColumn() {
		return this.currentColumn;
	}
	public String toString()
	{
		return new String("Type = "+getType()+
				"\nSpelling = "+getSpelling()+
				"\nCurrent Line = "+getCurrentLine()+
				"\nCurrent Column = "+getCurrentColumn()
				);
	}
	
	public final static int
		ID				= 0,
		TRUE			= 1,
		FALSE			= 2,
		BEGIN			= 3,
		END				= 4,
		IF				= 5,
		THEN			= 6,
		ELSE			= 7,
		FUNCTION		= 8,
		PROCEDURE		= 9,
		VAR				= 10,
		WHILE			= 11,
		DO				= 12,
		OR				= 13,	// OR op
		AND				= 14,	// AND op
		PROGRAM			= 15,	// program
		ARRAY			= 16,	// array
		OF				= 17,	// of
		INTEGER			= 18,	// integer
		REAL			= 19,	// real
		BOOLEAN			= 20,	// boolean
		DOT				= 21,	// .
		TILL			= 22,	// ..
		LBRACKET		= 23,	// [
		RBRACKET		= 24,	// ]
		LPARENTHESIS	= 25,	// (
		RPARENTHESIS	= 26,	// )
		COMMA			= 27, 	// ,
		COLON			= 28, 	// : 
		SEMICOLON		= 29, 	// ;
		SUMOP			= 30, 	// +
		SUBOP			= 31, 	// -
		MULOP			= 32,	// *
		DIVOP			= 33, 	// /
		LOTOP			= 34, 	// <
		HITOP			= 35,	// >
		LOEOP			= 36, 	// <=
		HIEOP			= 37, 	// >=
		EQTOP			= 38, 	// =
		DIFOP			= 39, 	// <>
		ATTOP			= 40, 	// :=
		INTLITERAL		= 41,
		FLOATLITERAL	= 42;
		
		
	
	private final static String[] spellings = {
			"id",
			"true",
			"false",
			"begin",
			"end",
			"if",
			"then",
			"else",
			"function",
			"procedure",
			"var",
			"while",
			"do",
			"or",
			"and",
			"program",
			"array",
			"of",
			"integer",
			"real",
			"boolean",
			".",
			"..",
			"[",
			"]",
			"(",
			")",
			",",
			":",
			";",
			"+",
			"-",
			"*",
			"/",
			"<",
			">",
			"<=",
			">=",
			"=",
			"<>",
			":=",
			"int-lit",
			"float-lit"
			//"<eot>"
	};
	
}
