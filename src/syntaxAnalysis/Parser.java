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
		while(!currentToken.getSpelling().equals("<eot>"))
		{
			currentToken = scanner.scan();
			System.out.println(currentToken.toString());
		}
	}
}
