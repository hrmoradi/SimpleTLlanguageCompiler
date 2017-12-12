import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 3/31/2017.
 */
public class Node {
    public String data;
    public Node parent;
    public List<Node> children;

    public Node(String s){
        System.out.println("Creating Class.Node: "+s);
        //System.out.println(          "parent: "+this.parent.getData());

        this.data = s;
        //this.children = null;
        this.parent = null ;
        this.children = new LinkedList<Node>();
    }
    public void setData(String S) {
        this.data=S;
    }
    public String getData() {
        return data;
    }

//    public void setData(String data) {
//        this.data = data;
//    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    public int childrenSize(){
        return this.children.size();
    }
    public Node getChildren(int index) {
        if (index >= this.children.size()){return null;}
        return children.get(index);
    }

    public Node setChildren(String childData) {
        System.out.println(this.data+"::Node.setChildren:: "+childData);
        Node childNode = new Node(childData);
        childNode.setParent(this);
        this.children.add(childNode);
        return childNode;
    }
    public void setNodeChild(Node n_factor){
//        System.out.println(this.data+"::Node.setNodeChild::c "+n_factor.getData());
        n_factor.setParent(this);
 //       System.out.println(this.data+"::Node.setNodeChild::p "+n_factor.getParent().getData());
        this.children.add(n_factor);
//        System.out.println(this.data+"::Node.setNodeChild::pcd "+this.getChildren(children.size()-1).getData());
    }


}
