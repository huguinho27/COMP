
public class Variable {
	private String name;
	private int start;
	private int end;

	public Variable(String name, int start) {
		this.name = name;
		this.start = start;
	}

	public String getName() {
		return name;
	}
	
	public int getStart(){
		return start;
	}
	
	public void setEnd(int end) {
		if (end == -1)
			this.end = start;
		else
			this.end = end;
	}

	public int getEnd() {
		return end;
	}
	
	public int getLifeRange(){
		return end - start + 1;
	}
}
