package frontEnd.lexicalAnalysis;

public class Token{
	
	private int 	type;
	private String	spelling;
	private int		line,
					column;
	
	public Token(int type, String spelling, int line, int column){
		setType(type);
		setSpelling(spelling);
		setLine(line);
		setColumn(column);
	}

	private void setType(int newType){
		this.type	= newType;
	}
	
	private void setSpelling(String newValue){
		this.spelling	= newValue;
	}
	
	private void setLine(int newLine){
		this.line	= newLine; 
	}
	
	private void setColumn(int newColumn){
		this.column	= newColumn;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getSpelling() {
		return this.spelling;
	}
	
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
	public String toString()
	{
		return new String("Type = "+getType()+
				"\nSpelling = "+getSpelling()+
				"\nCurrent Line = "+getLine()+
				"\nCurrent Column = "+getColumn()
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
		OPSUM			= 30, 	// +
		OPSUB			= 31, 	// -
		OPMULT			= 32,	// *
		OPDIV			= 33, 	// /
		OPLOWERTHN		= 34, 	// <
		OPGREATTHN		= 35,	// >
		OPLOWOREQ		= 36, 	// <=
		OPGREOREQ		= 37, 	// >=
		OPEQUAL			= 38, 	// =
		OPDIFF			= 39, 	// <>
		OPATTRIB		= 40, 	// :=
		INTLITERAL		= 41,	// 
		FLOATLITERAL	= 42;
		
	
	public final static String[] spellings = {
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
