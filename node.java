import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Node {
    private String word;
    private int strength;
    private Node parent;
    private ArrayList<Node> children;
    private DefaultMutableTreeNode treeNode;
    private DefaultTreeModel treeModel;
    private RightPanel RightPanel;
    private ArrayList<String> issues;

    public Node(String word,Node parent, DefaultMutableTreeNode treeNode, DefaultTreeModel treeModel,RightPanel RightPanel){
        this.word = word;
        this.children = new ArrayList<Node>();
        this.parent = parent;
        this.treeNode = treeNode;
        this.treeModel = treeModel;
        this.RightPanel = RightPanel;
        this.issues = new ArrayList<String>();
        if (this.word != null){
            policyCheck();
        }
    }



    @Override
    public String toString(){
        return this.word;
    }

    public Node addChild(String name){
        //System.out.print("addChild ");
        Node newNode = new Node(name, this, null,this.treeModel,null);//create the new Node
        //System.out.print("newNode ");
        DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(newNode);// create the new treeNode holding the Node
        //System.out.print("newTreeNode ");
        newNode.setTreeNode(newTreeNode);// tell the Node who its treeNode is
        //System.out.print("setTreeNode ");
        this.children.add(newNode);// adds new Node to is nodes children
        //System.out.print("addNode ");
        this.treeNode.add(newTreeNode);// adds treeNode to the tree under this Node
        //System.out.println("addTreeNode");
        this.treeModel.reload();
        return newNode;
    };

    public void policyCheck(){
        //TODO find out how to change the icons for swing trees
        //get policy requirements
        boolean minCharacters = Main.Gui.Settings.needLength();
        boolean needCapital = Main.Gui.Settings.needCapital();;
        boolean needNumber = Main.Gui.Settings.needNumber();;
        boolean needSymbol = Main.Gui.Settings.needSymbol();
        
        int minNum = Main.Gui.Settings.targetLength();

        //Analysis of passwords
        boolean hasCapital,hasNumber,hasSymbol;
        Pattern Cap = Pattern.compile("\\p{Upper}");
        Pattern Num = Pattern.compile("\\d");
        Pattern Sym = Pattern.compile ("\\p{Punct}");
        hasCapital = Cap.matcher(this.word).find();
        hasNumber = Num.matcher(this.word).find();
        hasSymbol = Sym.matcher(this.word).find();


        if (minCharacters && this.word.length()<minNum){
            this.issues.add("POLICY: Your policy requires passwords of at least "+minNum+" characters\n");
        }
        if (needCapital && !hasCapital){
            this.issues.add("POLICY: Your policy requires at least one capital letter\n");
        }
        if (needNumber && !hasNumber){
            this.issues.add("POLICY: Your policy requires at least one number\n");
        }
        if (needSymbol && !hasSymbol){
            this.issues.add("POLICY: Your policy requires at least one symbol\n");
        }

    }















    public String getWord(){
        return this.word;
    }

    public int getStrength(){
        return this.strength;
    }

    public Node getParent(){
        return this.parent;
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    public DefaultMutableTreeNode getTreeNode(){
        return this.treeNode;
    }

    public DefaultTreeModel getDefaultTreeModel(){
        return this.treeModel;
    }

    public RightPanel getRightPanel(){
        return this.RightPanel;
    }

    public ArrayList<String> getIssues(){
        return this.issues;
    }

    public void setWord(String word) {
        this.word = word;
        policyCheck();
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void setTreeNode(DefaultMutableTreeNode treeNode){
        this.treeNode = treeNode;
    };

    public void setTreeModel(DefaultTreeModel treeModel){
        this.treeModel = treeModel;
    }

    public void setRightPanel(RightPanel RightPanel){
        this.RightPanel = RightPanel;
    }

    public void setIssues(ArrayList<String> newList){
        this.issues = newList;
    }
    
}
