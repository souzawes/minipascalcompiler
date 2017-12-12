package analyzeLexical;

public class Token{
	
	private int 	type;
	private String	value;
	private int		currentLine,
					currentColumn;
	
	public Token(int type, String value, int currentLine, int currentColumn){
		setType(type);
		setValue(value);
		setCurrentLine(currentLine);
		setCurrentColumn(currentColumn);
	}

	private void setType(int newType){
		this.type	= newType;
	}
	
	private void setValue(String newValue){
		this.value	= newValue;
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
	
	public String getValue() {
		return this.value;
	}
	
	public int getCurrentLine() {
		return this.currentLine;
	}
	
	public int getCurrentColumn() {
		return this.currentColumn;
	}
	
	public final static int
	
		IF				= 0,
		THEN			= 1,
		ELSE			= 2,
		WHILE			= 3,
		DO				= 4,
		BEGIN			= 5,
		END				= 6,
		LET				= 7,
		IN				= 8,
		VAR				= 9,
		CONST			= 10,
		IDENTIFIER		= 11,
		INTLITERAL		= 12,
		BECOMES			= 13,	// :=
		OPERATORSOM		= 14,	// +
		OPERATORSUB		= 15,	// -
		OPERATORDIV		= 16,	// /
		OPERATORMULT	= 17,	// *
		LEQ				= 18,	// <
		GEQ				= 19,	// >
		EQUALS			= 20,	// =
		LPAREN			= 21,	// (
		RPAREN			= 22,	// )
		SEMICOLON		= 23,	// ;
		IS				= 24,	// ~
		COLON			= 25;	// :
	
	private final static String[] spellings = {
			
			"<identifier>",
			"<interger-literal>",
			"<operator>",
			"begin",
			"const",
			"do",
			"else",
			"end",
			"if",
			"in",
			"let",
			"then",
			"var",
			"while",
			";",
			":",
			":=",
			"~",
			"(",
			")",
			"<eot>"
	};
	
}
