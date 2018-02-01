import fileReader.TextFileReader;
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
			System.out.println("If there's spaces in the folder's names it's mandatory to put the directory between quotation marks");
			
		}
		else
		{
			TextFileReader reader = new TextFileReader(args[0]);
			text = reader.toString();
			
			if(!text.isEmpty())
			{
				System.out.println("The file read follows:");
				System.out.println(text);
				int l,c,pos=0;
				l=c=0;
				//Implementar aqui um la�o que remova caracteres separadores!
				Scanner scan = new Scanner(reader,l,c);//acho que tem que chamar aqui o analisador sint�tico antes e receber a AST pra jogar no analisador de contexto
				
			}
			else
			{
				System.out.println("The file read was empty.");
			}
		}
		
	}

}
