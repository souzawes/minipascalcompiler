import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lexicalAnalysis.Scanner;
import lexicalAnalysis.Token;
public class MpC 
{

	public static void main(String[] args) 
	{
		String text = new String();
		
		if(args[0].equals("-help"))
		{
			
			System.out.println("To compile type java MpC and the directory of the code file");
			System.out.println("If there's spaces in folders names it's mandatory to put the directory between quotation marks");
			
		}
		else
		{
			Path directory = Paths.get(args[0]);
			System.out.println("The path readed is -->"+args[0]);
			
			try {
				byte [] file = Files.readAllBytes(directory);
				text = new String(file);
				
				
			}catch(Exception error)
			{
				System.out.println("Error in the reading of the file");
			}
			
			if(!text.isEmpty())
			{
				System.out.println("The file readed follows:");
				System.out.println(text);
				StringBuffer str = new StringBuffer(text);
				int l,c,pos=0;
				l=c=0;
				//Implementar aqui um laço que remova caracteres separadores!
				Scanner scan = new Scanner(str,l,c);//acho que tem que chamar aqui o analisador sintático antes e receber a AST pra jogar no analisador de contexto
				
			}
			else
			{
				System.out.println("The file readed was empty");
			}
		}
		
	}

}
