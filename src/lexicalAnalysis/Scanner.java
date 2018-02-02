package lexicalAnalysis;
import fileReader.TextFileReader;

public class Scanner {
	
	private char 			currentChar;
	private int 			currentType;
	private StringBuffer 	currentSpelling;
	private TextFileReader 	fileText;
	int						currentLine,currentColumn;
	
	
	public Scanner(TextFileReader fileText){
	
		this.fileText = fileText;
		setCurrentLine(0);
		setCurrentColumn(0);
		setCurrentChar((char)0);
	}
	
	public void setCurrentLine(int currentLine) 
	{
		this.currentLine = currentLine;
	}
	public void setCurrentColumn(int currentColumn) 
	{
		this.currentColumn = currentColumn;
	}
	public int getCurrentLine() 
	{
		return this.currentLine;
	}
	public int getCurrentColumn() 
	{
		return this.currentColumn;
	}	
	public char getCurrentChar() {
		return currentChar;
	}

	public void setCurrentChar(char currentChar) {
		this.currentChar = currentChar;
	}

	public int getCurrentType() {
		return currentType;
	}

	public void setCurrentType(int currentType) {
		this.currentType = currentType;
	}

	public StringBuffer getCurrentSpelling() {
		return currentSpelling;
	}

	public void setCurrentSpelling(StringBuffer currentSpelling) {
		this.currentSpelling = currentSpelling;
	}
	
	private void take(char expectedChar) {
		if(currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			setCurrentChar(fileText.getNextChar());
		}
		else { 	//	Erro de caracter não experado
			System.out.println("ERROR\n Expected: " + expectedChar 
					+ " in line " + getCurrentLine() + 
					" column "+ getCurrentColumn());
		}
	}
	
	private void take() { 	//	takeIt()
		currentSpelling.append(currentChar);
		setCurrentChar(fileText.getNextChar());
	}
	
	private boolean isDigit (char c) {	//	Verifica se é um digito
		if ((c >= '0' && c <'9'))
			return true;
		else
			return false;
	}
	
	private boolean isLetter (char c) {
		if ((c >= 'a' && c <'z') || (c >= 'A' && c <'Z'))
			return true;
		else
			return false;
	}
	private boolean isGraphic (char c) { 	//	Verifica se é qualquer caracter gráfico
		if (c < ' ')
			return false;
		else
			return true;
	}
	private boolean isEOF(char c)
	{
		return (c == (char) -1);
	}
	
	private void scanSeparator () { 	//	Tratamento de comentários
		switch (currentChar) {
			case '!':
				take();
				while (isGraphic(currentChar))	// Ignorar caracter gráfico
					take();
			break;
			case ' ': case '\r': case '\n' :
				take();
			break;
		}
	}
	
	private int scanToken () {
		return 0;
	}
	
	public Token scan () 
	{
		if(isEOF(currentChar))
			return null;
		else
		{
			while(	currentChar == '!' 
					|| currentChar == ' '
					|| currentChar == '\r'
					|| currentChar == '\n')
				scanSeparator();
			
			currentSpelling = new StringBuffer(" ");
			currentType = scanToken(); 
						
			return new Token(currentType, currentSpelling.toString(), getCurrentLine(), getCurrentColumn());
		}	
	}		
}
