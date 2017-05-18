public class register
{
	public String varName;
	public int lifetime = 1;
	
	public register(String varName)
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