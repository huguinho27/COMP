public class Register
{
	private String varName;
	private int lifetime = 1;
	
	public Register(String varName)
	{
		this.varName = varName;
	}

	/**
	 * @return the varName
	 */
	public String getVarName()
	{
		return varName;
	}

	/**
	 * @param varName the varName to set
	 */
	public void setVarName(String varName)
	{
		this.varName = varName;
	}

	/**
	 * @return the lifetime
	 */
	public int getLifetime()
	{
		return lifetime;
	}

	/**
	 * @param lifetime the lifetime to set
	 */
	public void setLifetime(int lifetime)
	{
		this.lifetime = lifetime;
	}
}