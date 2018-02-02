package fileReader;
import java.io.BufferedReader;
import java.io.FileReader;


public class TextFileReader {
	BufferedReader text;
	
	public TextFileReader(String path)
	{
		System.out.println("The path readed is -->"+path);
		try  {
			text = new BufferedReader(new FileReader(path));	
		}
		catch(Exception error) {
			System.out.println("Error reading the file.");
		}
		
		try
		{
			text.mark(1000000);//marca a posição inicial da stream
		}
		catch(Exception E)
		{
			System.out.println("Error at setting initial mark");;
		}
	}
	public char getNextChar()
	{
		char current = (char) -1;
		try
		{
			current = (char) text.read();
		}
		catch (Exception E)
		{
			System.out.println("Error on character read from file");
			
		}
		return current;
	}
	
	public String toString()
	{
		String str = new String();
		String aux = new String();
		do
		{
			try
			{
				aux = text.readLine();
			}
			catch(Exception E)
			{
				System.out.println("Error on line read from file");
			}
			if (aux != null)
				str = str+aux+"\n";
		}while(aux != null);
		
		try
		{
			text.reset();
		}
		catch(Exception E)
		{
			System.out.println("Error while reseting file stream position.");
		}
		
		return str;
	}

}
