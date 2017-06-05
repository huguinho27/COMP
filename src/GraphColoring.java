import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.graph.implementations.Graphs;

public class GraphColoring extends JFrame {
	private static final long serialVersionUID = 1L;
	private LifetimeAnalysis lf;
	private Graph graph;
	private Graph tempGraph;

	private String DIR_PATH;
	private Viewer viewer;
	private View view;

	public GraphColoring(int registers) throws FileNotFoundException, ParseException {
		DIR_PATH = System.getProperty("user.dir");
		SimpleNode node = TacParser.parseFile(new File("C:\\Users\\João\\workspaceJava\\COMP P1\\bin\\input1.txt"));
		LifetimeAnalysis lf = new LifetimeAnalysis(8, node);

		this.lf = lf;

		init();
		kColoring(registers);
		lf.printLifetimeGraph(node);

	}

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		new GraphColoring(4);
	}

	public void init() {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		this.graph = new SingleGraph("Register Interference Graph");
		this.tempGraph = new SingleGraph("Register Interference Graph");

		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.setAttribute("ui.stylesheet", getPath());

		ArrayList<Variable> variables = lf.getVariables();

		for (int i = 0; i < variables.size(); i++) {
			Variable v1 = variables.get(i);
			addVertex(v1.getName());
		}

		for (int i = 0; i < variables.size(); i++) {
			Variable v1 = variables.get(i);

			for (int j = 0; j < variables.size(); j++) {
				if (j != i) {
					Variable v2 = variables.get(j);

					if (v1.getStart() <= v2.getStart() && v1.getEnd() >= v2.getEnd()) {
						addEdge(v1.getName(), v2.getName());
					}
				}
			}
		}
	}

	public void addVertex(String name) {
		graph.addNode(name);

		graph.getNode(name).addAttribute("ui.label", name);
		graph.getNode(name).addAttribute("ui.size", 30);
		graph.getNode(name).setAttribute("ui.class", "black");
		graph.getNode(name).setAttribute("color", -1);

		tempGraph.addNode(name);
		tempGraph.getNode(name).addAttribute("ui.label", name);
	}

	public void addEdge(String v1, String v2) {
		graph.addEdge(v1 + " - " + v2, v1, v2);
		tempGraph.addEdge(v1 + " - " + v2, v1, v2);
	}

	private void kColoring(int k) {
		Stack<org.graphstream.graph.Node> stack = new Stack<org.graphstream.graph.Node>();

		for (int i = 0; i < tempGraph.getNodeCount(); i++) {
			org.graphstream.graph.Node n = tempGraph.getNode(i);
			int degree = n.getDegree();

			System.out.println(n.getAttribute("ui.label") + " - " + degree);
			if (degree < k) {
				stack.push(n);
				tempGraph.removeNode(n);
				i--;
			}
		}

		while (!stack.isEmpty()) {
			org.graphstream.graph.Node temp = stack.pop();
			org.graphstream.graph.Node n = graph.getNode(temp.getId());

			System.out.println(n.getAttribute("ui.label")+ ":");
			int numColored = 0;
			Iterator<Edge> it2 = n.getEachEdge().iterator();
			while (it2.hasNext()) {
				Edge e = it2.next();

				org.graphstream.graph.Node n2 = e.getNode0();
				if (n2.getId() == n.getId())
					n2 = e.getNode1();
				
				if ((int) n2.getAttribute("color") != -1)
					numColored++;
			}
			n.setAttribute("color", numColored);
		}
		setColors();
		graph.display();
	}

	public void setColors() {
		for (int i = 0; i < graph.getNodeCount(); i++) {
			org.graphstream.graph.Node n = graph.getNode(i);
			int color = (int) n.getAttribute("color");
			System.out.println(n.getAttribute("ui.label") + " - " + color);
			
			
			switch (color) {
			case 0:
				n.setAttribute("ui.class", "orange");
				break;
			case 1:
				n.setAttribute("ui.class", "lightBlue");
				break;
			case 2:
				n.setAttribute("ui.class", "purple");
				break;
			case 3:
				n.setAttribute("ui.class", "green");
				break;
			case 4:
				n.setAttribute("ui.class", "red");
				break;
			case 5:
				n.setAttribute("ui.class", "blue");
				break;
			case 6:
				n.setAttribute("ui.class", "grey");
				break;
			case 7:
				n.setAttribute("ui.class", "pink");
				break;
			case 8:
				n.setAttribute("ui.class", "brown");
				break;
			case 9:
				n.setAttribute("ui.class", "forestGreen");
				break;
			}
		}
	}

	public String getPath() {
		String cssFile;

		cssFile = DIR_PATH + "\\bin\\graph.css";

		return fileToString(cssFile);
	}

	public String fileToString(String file) {
		BufferedReader br = null;
		String line = "";
		StringBuilder sb = new StringBuilder();

		try {

			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				sb.append(line.replace("path", DIR_PATH));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

}
