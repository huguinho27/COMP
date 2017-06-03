import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TacToC
{
	public static String toCString(String fileName) throws FileNotFoundException, IOException
	{
		ArrayList<String> declaredVariables = new ArrayList<String>();
		String codeC = "int main()\n{\n";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			String newCcodeLine;
			
			while ((line = br.readLine()) != null)
			{
				newCcodeLine = "";
				String[] parts = line.split(" ");
				switch(parts.length)
				{
					case 4:
					{
						if (!declaredVariables.contains(parts[0]))
						{
							newCcodeLine += "double" + " " + parts[0] + " = " + parts[1] + " " + parts[2] + " " + parts[3] + ";\n";
						}
						break;
					}
					case 2:
					{
						if (!declaredVariables.contains(parts[0]))
						{
							newCcodeLine += "double" + " " + parts[0] + " = " + parts[1] + " " + parts[2] + " " + parts[3] + ";\n";
						}
						break;
					}
					case 6:
					{
						
						break;
					}
					case 1:
					{
						
						break;
					}
					default:
					{
						System.out.println("Que linha e esta?!?");
						break;
					}
				}
				codeC += newCcodeLine;
			}
		}

		codeC += "\n}";
		System.out.println(codeC);
		return codeC;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		toCString("input.txt");
	}
}
