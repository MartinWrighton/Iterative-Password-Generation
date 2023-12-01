import java.util.ArrayList;

public class node {
    private String word;
    private int strength;
    private node parent;
    private ArrayList<node> children;
    public node(String word,node parent){
        this.word = word;
        this.children = new ArrayList<node>();
        this.parent = parent;
    }




    public String toTree(){//WIP might not make it until we go GUI
        
        String branch = "";
        for (int i = 0 ; i < this.getChildren().size() ; i++){
            branch = branch + this.getChildren().get(i).toTree();
        }
        return this.getWord()+"\n"+branch;
        
    }

    @Override
    public String toString(){
        return this.word;
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
    
}
