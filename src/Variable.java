import java.util.ArrayList;
import java.util.Collections;

public class Variable {
	private String name;
	private ArrayList<Integer> calls;

	public Variable(String name, int start) {
		this.name = name;
		ArrayList<Integer> calls = new ArrayList<Integer>();
		calls.add(start);
		this.calls = calls;
	}

	public String getName() {
		return name;
	}

	public int getStart() {
		Collections.sort(calls);
		return calls.get(0);
	}

	public ArrayList<Integer> getCalls() {
		return calls;
	}

	public void setCalls(ArrayList<Integer> calls) {
		this.calls = calls;
	}

	public int getEnd() {
		Collections.sort(calls);
		return calls.get(calls.size() - 1);
	}

	public int getLifeRange() {
		int start = this.getStart();
		int end = this.getEnd();
		return end - start + 1;
	}
}
