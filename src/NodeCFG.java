import java.util.Vector;

/**
 * Created by Ruben on 6/4/2017.
 */
public class NodeCFG {

    public String name;
    public String content;
    public String to;
    public String to_else;


    public NodeCFG(String name) {
        this.name = name;
        this.to = null;
        this.to_else = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo_else() {
        return to_else;
    }

    public void setTo_else(String to_else) {
        this.to_else = to_else;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
       String str = "";
        str += "Name: " + this.name + "\n" + "To: " + this.to + "\n";
        if(this.to_else != null)
            str += "Else_to: " + to_else + "\n";

        return str;
    }

    public  void substituteLabels()
    {
    	if(GeneratorCFG.labels.contains(name))
        {
            name = Integer.toString( GeneratorCFG.labelsInt.get(GeneratorCFG.labels.indexOf(name)));
        }
        if(GeneratorCFG.labels.contains(to))
        {
            to = Integer.toString( GeneratorCFG.labelsInt.get(GeneratorCFG.labels.indexOf(to)));
        }
        if(GeneratorCFG.labels.contains(to_else))
        {
            to_else = Integer.toString( GeneratorCFG.labelsInt.get(GeneratorCFG.labels.indexOf(to_else)));
        }
    }

    public String elseEdge(){
        if(to_else != null)
            return to_else;
        return null;
    }

    public String toEdge(){
        if(to != null)
            return to;
        return null;
    }


}
