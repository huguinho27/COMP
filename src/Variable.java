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
		return calls.get(0);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Variable))
			return false;

		Variable v = (Variable) obj;
		return getName().equals(v.getName());
	}

	public ArrayList<Integer> getCalls() {
		return calls;
	}

	public void addCall(int call) {
		this.calls.add(call);
		Collections.sort(calls);
	}

	public void setCalls(ArrayList<Integer> calls) {
		Collections.sort(calls);
		this.calls = calls;
	}

	public int getEnd() {
		return calls.get(calls.size() - 1);
	}

	public int getLifeRange() {
		int start = this.getStart();
		int end = this.getEnd();
		return end - start + 1;
	}
}
