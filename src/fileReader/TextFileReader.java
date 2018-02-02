package fileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFileReader {
	String Text;
	
	public TextFileReader(String path)
	{
		Text = new String();
		
		Path directory = Paths.get(path);
		System.out.println("The path readed is -->"+path);
		
		try 
		{
			byte [] file = Files.readAllBytes(directory);
			Text = new String(file);	
		}catch(Exception error)
		{
			System.out.println("Error in the reading of the file");
		}
	}
	
	public String toString()
	{
		if(!Text.isEmpty())
			return this.Text;
		else
			return null;
	}

}
