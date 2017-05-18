import java.util.HashMap;
import java.util.Vector;

public class lifetimeAnalysis
{
	public Vector<register> registers;

	public lifetimeAnalysis()
	{
		registers = new Vector<register>();
	}

	/**
	 * @return the registers
	 */
	public Vector<register> getRegisters()
	{
		return registers;
	}

	/**
	 * @param registers
	 *            the registers to set
	 */
	public void setRegisters(Vector<register> registers)
	{
		this.registers = registers;
	}

	public void addRegister(register r)
	{
		registers.addElement(r);
	}

	public void parseNode(SimpleNode s)
	{
		// System.out.println(toString(prefix));
		if (s.children != null)
		{
			for (int i = 0; i < s.children.length; ++i)
			{
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null)
				{
					if (n.value.equals(":="))
					{
						parseNewRegister(n);
						parseNode(n);
					}
					
				}
			}
		}
	}

	public void parseNewRegister(SimpleNode s)
	{
		SimpleNode n = (SimpleNode) s.children[0];
		registers.addElement(new register((String) n.value));
	}
	
	public void printLifetime(SimpleNode s)
	{
		for (int i = 0; i < registers.size();i++)
		{
			System.out.println(lifetimePerRegister(s,registers.get(i).varName));
		}
	}

	public int lifetimePerRegister(SimpleNode s, String regName)
	{
		int last = -1;
		if (s.children != null)
		{
			for (int i = 0; i < s.children.length; ++i)
			{
				SimpleNode n = (SimpleNode) s.children[i];
				//System.out.println("node"+i);
				if(parseNodes(n,regName)){
					last =i;
				}
				
			}
		
		}
		return last;
	}
	
	public boolean parseNodes(SimpleNode s,String regName){
		
		
		if (s.children != null)
		{
			for (int i = 0; i < s.children.length; ++i)
			{
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null)
				{
				//	System.out.println(i + ": " + n.value + " " + regName);
					if (n.value.equals("*") || n.value.equals("+") ||  n.value.equals("-") || n.value.equals("/"))
					{
						 if(parseExpr(n,regName))
							 return true;
					}
					
				}
			}
		}
		
		
		return false;
		
	}
	
	public boolean parseExpr(SimpleNode s,String regName){
		
		if (s.children != null)
		{
			for (int i = 0; i < s.children.length; ++i)
			{
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null)
				{
				//	System.out.println(i + ": " + n.value + " " + regName);
					if (n.value.equals(regName))
					{
						 return true;
					}
					
				}
			}
		}
		
		
		return false;
		
	}
	
}