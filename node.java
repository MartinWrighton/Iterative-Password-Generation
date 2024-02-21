import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class node {
    private String word;
    private int strength;
    private node parent;
    private ArrayList<node> children;
    private DefaultMutableTreeNode treeNode;
    private DefaultTreeModel treeModel;
    private JPanel rightPanel;
    public node(String word,node parent, DefaultMutableTreeNode treeNode, DefaultTreeModel treeModel,JPanel rightPanel){
        this.word = word;
        this.children = new ArrayList<node>();
        this.parent = parent;
        this.treeNode = treeNode;
        this.treeModel = treeModel;
        this.rightPanel = rightPanel;
    }



    @Override
    public String toString(){
        return this.word;
    }

    public node addChild(String name){
        //System.out.print("addChild ");
        node newNode = new node(name, this, null,this.treeModel,null);//create the new node
        //System.out.print("newNode ");
        DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(newNode);// create the new treeNode holding the node
        //System.out.print("newTreeNode ");
        newNode.setTreeNode(newTreeNode);// tell the node who its treeNode is
        //System.out.print("setTreeNode ");
        this.children.add(newNode);// adds new node to is nodes children
        //System.out.print("addNode ");
        this.treeNode.add(newTreeNode);// adds treeNode to the tree under this node
        //System.out.println("addTreeNode");
        this.treeModel.reload();
        return newNode;
    };

















    public String getWord(){
        return this.word;
    }

    public int getStrength(){
        return this.strength;
    }

    public node getParent(){
        return this.parent;
    }

    public ArrayList<node> getChildren(){
        return this.children;
    }

    public DefaultMutableTreeNode getTreeNode(){
        return this.treeNode;
    }

    public DefaultTreeModel getDefaultTreeModel(){
        return this.treeModel;
    }

    public JPanel getRightPanel(){
        return this.rightPanel;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setParent(node parent) {
        this.parent = parent;
    }

    public void setChildren(ArrayList<node> children) {
        this.children = children;
    }

    public void setTreeNode(DefaultMutableTreeNode treeNode){
        this.treeNode = treeNode;
    };

    public void setTreeModel(DefaultTreeModel treeModel){
        this.treeModel = treeModel;
    }

    public void setRightPanel(JPanel rightpanel){
        this.rightPanel = rightpanel;
    }
    
}
