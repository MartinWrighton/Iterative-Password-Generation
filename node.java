import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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
    private ArrayList<String> issues;
    public node(String word,node parent, DefaultMutableTreeNode treeNode, DefaultTreeModel treeModel,JPanel rightPanel){
        this.word = word;
        this.children = new ArrayList<node>();
        this.parent = parent;
        this.treeNode = treeNode;
        this.treeModel = treeModel;
        this.rightPanel = rightPanel;
        this.issues = new ArrayList<String>();
        if (this.word != null){
            policyCheck();
        }
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

    public void policyCheck(){
        //TODO find out how to change the icons for swing trees
        //get policy requirements
        boolean minCharacters = ((AbstractButton) main_file.gui.getTabs().getComponentAt(0).getComponentAt(10,275).getComponentAt(10,40)).isSelected();
        boolean needCapital = ((AbstractButton) main_file.gui.getTabs().getComponentAt(0).getComponentAt(10,275).getComponentAt(10,70)).isSelected();
        boolean needNumber = ((AbstractButton) main_file.gui.getTabs().getComponentAt(0).getComponentAt(10,275).getComponentAt(10,100)).isSelected();
        boolean needSymbol = ((AbstractButton) main_file.gui.getTabs().getComponentAt(0).getComponentAt(10,275).getComponentAt(10,130)).isSelected();
        
        int minNum = (int) ((JSpinner) main_file.gui.getTabs().getComponentAt(0).getComponentAt(10,275).getComponentAt(73,40)).getValue();

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

    public void setIssues(ArrayList<String> newList){
        this.issues = newList;
    }
    
}
