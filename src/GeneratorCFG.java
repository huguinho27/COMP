

import javax.swing.filechooser.FileSystemView;
import java.io.*;

import java.util.Vector;

/**
 * Created by Ruben on 6/4/2017.
 */
public class GeneratorCFG {

    public SimpleNode root;
    public Vector<NodeCFG> nodes;
    public static Vector<String> labels;
    public static Vector<Integer> labelsInt;


    public GeneratorCFG(SimpleNode root) {
        this.root = root;
        nodes = new Vector<>();
        labels = new Vector<>();
        labelsInt = new Vector<>();
    }


    public void parseNodes(){
        String parsed = null;
        NodeCFG nodeCFG;
        int contador = 0;
        for (int i = 0; i < root.jjtGetNumChildren()  ; ++i) {
            nodeCFG = new NodeCFG(Integer.toString(contador ));

            SimpleNode n = (SimpleNode) root.jjtGetChild(i);
            
            if( parseNode(n,nodeCFG,contador,i == (root.jjtGetNumChildren() -1)))
            {

                if(i != root.jjtGetNumChildren()-1 )
                    nodeCFG.setTo(Integer.toString(contador+1));
                nodes.add(nodeCFG);
                contador++;
             //   System.out.println(nodeCFG.toString());
            }
        }

        for (int i = 1; i < nodes.size();i++)
            nodes.get(i).substituteLabels();




    }

    private boolean parseNode(SimpleNode n, NodeCFG nodeCFG,int i,boolean last) {

        if(n.jjtGetValue().equals(":="))
            nodeCFG.setContent(SimpleNode.equalsNode(n));
        else if(n.jjtGetValue().equals(":")) {
            if(last){
            	 labels.add(SimpleNode.parseLabel(n));
                 labelsInt.add(i );
            	nodeCFG.setContent(SimpleNode.parseLabel(n));
            }
            else{
            	 labels.add(SimpleNode.parseLabel(n));
                 labelsInt.add(i );
            	return false;
            	
            }     
            }
        else if(n.jjtGetValue().equals("if")) {
            nodeCFG.setTo_else((String)((SimpleNode)((SimpleNode) n.jjtGetChild(1)).jjtGetChild(0)).jjtGetValue());
            nodeCFG.setContent( "if " + " " +SimpleNode.parseOperator((SimpleNode) n.jjtGetChild(0)));

        }
        else if(n.jjtGetValue().equals("goto")){
            setToLast(SimpleNode.parseLabel(n));
            return false;
        }
        return true;
    }


    private void setToLast(String to){

        nodes.get(nodes.size() - 1).setTo(to);

    }
    private String getGoto(SimpleNode n) {
        if(n.jjtGetValue().equals("if")) {
            return  (String)((SimpleNode)((SimpleNode) n.jjtGetChild(1)).jjtGetChild(0)).jjtGetValue();
        }else if(n.jjtGetValue().equals("goto")){
            return SimpleNode.parseLabel(n);
        }
        return null;
    }

    public Vector<NodeCFG> getNodes() {
        return nodes;
    }

    public void setNodes(Vector<NodeCFG> nodes) {
        this.nodes = nodes;
    }

    public void saveFile(String name) throws IOException {
        FileWriter out = null;

        try {

            File file = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\" + "dot-files" + "\\");
            if(!file.exists())
            file.mkdir();
            System.out.println(file.getAbsolutePath());
            out = new FileWriter(file.getAbsolutePath() + "\\" +name + ".dot");

            out.write("digraph\n");
            out.write("{\n");
            out.write( "node [margin=0 fontcolor=blue  shape=box style=filled]");
	
			
            String elseTo,to;
            for(int i=0; i < nodes.size() ;i++){
                elseTo = nodes.get(i).elseEdge();
                to = nodes.get(i).toEdge();
                if( elseTo!= null) {
                    if (to != null)
                        out.write("\"" + i + ": " +nodes.get(i).getContent() + "\""  + " -> " + "\"" + to + ": " + nodes.get(Integer.parseInt(to)).getContent() + "\""  + "[label=\"true\"]" + "\n");
                    out.write("\"" + i + ": " +nodes.get(i).getContent() + "\""  + " -> " + "\"" + elseTo + ": " +nodes.get(Integer.parseInt(elseTo)).getContent() + "\""   + "[label=\"false\"]" + "\n");
                }else {
                    if (to != null)
                        out.write("\""+ i + ": " + nodes.get(i).getContent() + "\"" + " -> " + "\""  + to + ": " + nodes.get(Integer.parseInt(to)).getContent()  + "\"" + "\n");
                }
            }
            out.write("}\n");
        } finally {

            if (out != null) {
                out.close();
            }
        }
    }

    public class Label{
        public String label;
        public int node;
        public Label(String label,int node)
        {
            this.label = label;
            this.node = node;
        }
    }
}
