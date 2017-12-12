package analyzeSyntactic;

import analyzeLexical.Token;

public class Scanner {
	
	private char 			currentChar;
	private int 			currentType;
	private StringBuffer 	currentSpelling;
	
	public Scanner(char currentChar, int currentType, StringBuffer currentSpelling) {
		setCurrentChar(currentChar);
		setCurrentType(currentType);
		setCurrentSpelling(currentSpelling);
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
	
	private void take (char expectedChar) {
		if(currentChar == expectedChar) {
			currentSpelling.append(currentChar);
			//currentChar = proximo caracter;
		}
	}
	
	private void takeIt () {
		currentSpelling.append(currentChar);
		//currentChar = proximo caracter
	}
	
	private boolean isDigit () {
		//retorna verdadeiro se é digito, e falso se não
		return true;
	}
	
	private boolean isLetter () {
		// retorna verdadeiro se é letra, falso se não
		return true;
	}
	
	private void scanSeparator () {
		
	}
	
	private int scanToken () {
		return 0;
	}
	
	public Token scan () {
		while(	currentChar == '!' 
				|| currentChar == ' ' 
				|| currentChar == '\n')
			scanSeparator();
		
		currentSpelling = new StringBuffer(" ");
		currentType = scanToken(); 
					
			return new Token(currentType, currentSpelling.toString(), 0, 0);
	}	
}
