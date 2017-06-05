import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TacToC
{
	public static String toCCode(String input) throws FileNotFoundException, IOException
	{
		ArrayList<String> declaredVariables = new ArrayList<String>();
		
		String newCcodeLine = "#include <stdio.h>\n" + "int main()\n{\n";

		String line[] = input.split("\n");

		for (int i = 0; i < line.length; i++)
		{
			String[] parts = line[i].split(" ");
			
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
			case 4:
			{
				newCcodeLine += parts[0] + " " + parts[1] + " " + "=" + " " + parts[3] + ";\n";
				break;
			}
			case 6:
			{
				newCcodeLine += parts[0] + " " + parts[1] + " " + "=" + " " + parts[3] + " " + parts[4] + " " + parts[5] + ";\n";
				break;
			}
			default:
			{
				break;
			}
			}
		}
		newCcodeLine += "\n}";
		return newCcodeLine;
	}

	public String toCString(SimpleNode root) throws FileNotFoundException, IOException
	{
		String output = "";
		String line = "";

		for (int i = 0; i < root.jjtGetNumChildren(); i++)
		{
			line = parseNode((SimpleNode) root.jjtGetChild(i));
			if (line.contains("thisisalabel"))
			{
				String[] parts = line.split("thisisalabel");
				output += parts[0] + ": ";
				continue;
			}
			output += line + "\n";
		}
		return toCCode(output);
	}

	private String parseNode(SimpleNode n)
	{

		if (n.jjtGetValue().equals(":="))
			return SimpleNode.equalsNode(n);
		else if (n.jjtGetValue().equals(":"))
		{
			return SimpleNode.parseLabel(n) + "thisisalabel";

		} else if (n.jjtGetValue().equals("if"))
		{
			return "if" + " " + SimpleNode.parseOperator((SimpleNode) n.jjtGetChild(0)) + " goto "
					+ (String) ((SimpleNode) ((SimpleNode) n.jjtGetChild(1)).jjtGetChild(0)).jjtGetValue();

		} else if (n.jjtGetValue().equals("goto"))
		{
			return "goto " + SimpleNode.parseLabel(n);

		}
		return "";
	}

	public void saveFIle(SimpleNode root) throws IOException
	{
		FileWriter out = null;
		out = new FileWriter("output.c");
		out.write(toCString(root));
		out.close();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException
	{

	}
}
