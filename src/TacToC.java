import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TacToC
{
	public static void toCString(String fileName, PrintWriter out) throws FileNotFoundException, IOException
	{
		ArrayList<String> declaredVariables = new ArrayList<String>();
		out.println("int main()\n{\n");

		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String line;
			String newCcodeLine;

			while ((line = br.readLine()) != null)
			{
				newCcodeLine = "";
				String[] parts = line.split(" ");
				switch (parts.length)
				{
				case 1:
				{
					newCcodeLine += parts[0] + " return;";
					break;
				}
				case 5:
				{
					if (!declaredVariables.contains(parts[0]))
					{
						newCcodeLine += "double ";
						declaredVariables.add(parts[0]);
					}
					newCcodeLine += parts[0] + " = " + parts[2] + " " + parts[3] + " " + parts[4] + ";\n";
					break;
				}
				case 3:
				{
					if (!declaredVariables.contains(parts[0]))
					{
						newCcodeLine += "double ";
						declaredVariables.add(parts[0]);
					}
					newCcodeLine += parts[0] + " = " + parts[2] + ";\n";
					break;
				}
				case 7:
				{
					newCcodeLine += parts[0] + " " + parts[1] + " (" + parts[2] + " " + parts[3] + " " + parts[4] + ") "
							+ parts[5] + " " + parts[6] + ";\n";
					break;
				}
				case 2:
				{
					newCcodeLine += parts[0] + " " + parts[1] + ";\n";
					break;
				}
				default:
				{
					System.out.println("Que linha e esta?!?");
					break;
				}
				}
				out.println(newCcodeLine);
			}
		}
		out.println("\n}");
	}
	
	public void toCString(SimpleNode root) throws FileNotFoundException, IOException
	{
		ArrayList<String> declaredVariables = new ArrayList<String>();
		
		String out = root.dump("");
		String[] lines = out.split("\n");
		String parts[];
		String output = "int main()\n{\n";
		
		for (int i = 0; i < lines.length;i++)
		{
			
			if (lines[i].contains("Label"))
			{
				i++;
				parts = lines[i].split(" ");
				output += parts[0] + ": ";
				i++;
				parts = lines[i].split(" ");
				output += parts[0] + " (";
				i+=2;
				parts = lines[i].split(" ");
				output += parts[0] + " ";
				i--;
				parts = lines[i].split(" ");
				output += parts[0] + " ";
				i+=2;
				parts = lines[i].split(" ");
				output += parts[0] + ") ";
				i++;
				parts = lines[i].split(" ");
				output += parts[0] + " ";
				i++;
				parts = lines[i].split(" ");
				output += parts[0] + ";";
				break;
			}
		}
		System.out.println(output);
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		PrintWriter out = new PrintWriter("out.txt");
		toCString("input.txt", out);
		out.close();
	}
}
