package fileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;

public class TextFileReader {
	BufferedReader text;
	
	public TextFileReader(String path)
	{
		Path directory = Paths.get(path);
		System.out.println("The path readed is -->"+path);
		
		try  {
			text = new BufferedReader(new FileReader(path));	
		}
		catch(Exception error) {
			System.out.println("Error reading the file.");
		}
	}
	
	public String toString() {
		return  "STRING"; //text.toString();
	}

}
