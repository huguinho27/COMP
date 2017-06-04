import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class LifetimeAnalysis {
	private ArrayList<Variable> variables;
	private ArrayList<SimpleNode> instructions;
	private Register[] registers;
	private SimpleNode root;

	public LifetimeAnalysis(int registers, SimpleNode root) {
		this.registers = new Register[registers];
		this.root = root;
		variables = new ArrayList<Variable>();
		instructions = new ArrayList<SimpleNode>();

		parseNode(root);
		calculateLifetime();
	}

	/**
	 * @return the registers
	 */
	public Register[] getregisters() {
		return registers;
	}

	public void addVariable(Variable v) {
		variables.add(v);
	}

	public Variable getVariableByName(String name) {
		for (int i = 0; i < variables.size(); i++) {
			Variable v = variables.get(i);
			if (v.getName().equals(name))
				return v;
		}
		return null;
	}

	public int getVariableIndex(String name) {
		for (int i = 0; i < variables.size(); i++) {
			Variable v = variables.get(i);
			if (v.getName().equals(name))
				return i;
		}
		return -1;
	}

	public void parseNode(SimpleNode s) {
		if (s.children != null) {
			for (int i = 0; i < s.children.length; ++i) {
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null) {
					if (n.value.equals(":=") | n.value.equals("if")) {
						instructions.add(n);
						int start = instructions.size();
						if (n.value.equals(":=")) {
							addVariable(n, start);
							parseNode(n);
						}
					}
				}
			}
		}
	}

	public void calculateLifetime() {
		for (int i = 0; i < variables.size(); i++) {
			Variable v = variables.get(i);
			v.setCalls(calculateCalls(instructions, v.getName()));
		}
	}

	public void addVariable(SimpleNode s, int start) {
		SimpleNode n = (SimpleNode) s.children[0];
		Variable v = new Variable((String) n.value, start);

		if (!variables.contains(v))
			variables.add(v);
		else {
			Variable var = getVariableByName(v.getName());
			var.addCall(start);
		}
	}

	public void printLifetime(SimpleNode s) {
		for (int i = 0; i < variables.size(); i++) {
			Variable v = variables.get(i);

			System.out.println(v.getName() + " -> Start = " + v.getStart() + ", End = " + v.getEnd() + ", Range = "
					+ v.getLifeRange());
			System.out.println(v.getCalls().toString());
		}
	}

	public void printLifetimeGraph(SimpleNode s) {
		System.out.print("\nLine    ");
		for (int a = 0; a < instructions.size(); a++) {
			String padded = String.format("%1$-3s", (a + 1));
			System.out.print(padded);
		}
		System.out.print("\n");
		for (int i = 0; i < variables.size(); i++) {
			Variable v = variables.get(i);

			String padded = String.format("%1$-3s", variables.get(i).getName());
			System.out.print("\n" + padded + " -> ");
			for (int j = 1; j <= v.getEnd(); j++) {
				if (j < v.getStart())
					System.out.print("   ");
				else {
					if (v.getCalls().contains(j))
						System.out.print("-o-");
					else
						System.out.print("---");
				}
			}
		}
		System.out.print("\n");
	}

	public ArrayList<Integer> calculateCalls(ArrayList<SimpleNode> instructions, String varName) {
		Variable v = getVariableByName(varName);
		ArrayList<Integer> calls = v.getCalls();

		for (int i = 0; i < instructions.size(); i++) {
			SimpleNode n = (SimpleNode) instructions.get(i);
			if (parseNodes(n, varName)) {
				calls.add(i + 1);
			}
		}
		return calls;

	}

	public boolean parseNodes(SimpleNode s, String varName) {
		if (s.children != null) {
			for (int i = 0; i < s.children.length; ++i) {
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null) {
					if (n.value.equals("*") || n.value.equals("+") || n.value.equals("-") || n.value.equals("/")
							|| n.value.equals("<") || n.value.equals(">")) {
						if (parseExpr(n, varName))
							return true;
					} else if (n.value.equals(varName))
						return true;
				}
			}
		}
		return false;
	}

	public boolean parseExpr(SimpleNode s, String varName) {
		if (s.children != null) {
			for (int i = 0; i < s.children.length; ++i) {
				SimpleNode n = (SimpleNode) s.children[i];
				if (n != null) {
					if (n.value.equals(varName)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void leftEdgeAllocation() {

	}
}