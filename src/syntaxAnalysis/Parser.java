package syntaxAnalysis;
import fileReader.TextFileReader;
import lexicalAnalysis.Scanner;
import lexicalAnalysis.Token;

public class Parser {

	Scanner scanner;
	Token currentToken;
	
	public Parser(TextFileReader file)
	{
		scanner = new Scanner(file);
	}
	public void parse()
	{
		for (int c = 0; c < 40 ; c++)
		{
			currentToken = scanner.scan();
			System.out.println(currentToken.toString());
			currentToken = scanner.scan();
			System.out.println(currentToken.toString());
			currentToken = scanner.scan();
			System.out.println(currentToken.toString());
		}
	}
}
