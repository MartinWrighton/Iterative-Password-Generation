

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class SubstringCheck extends SwingWorker<String,Integer> {
    private String pattern;
    private JProgressBar progressBar;
    private Node Node;
    public  SubstringCheck(Node Node,JProgressBar progressBar){
        this.pattern = Node.getWord();
        this.Node = Node;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground() throws Exception {
        //this is the background process, where the password check can be done
        String subMatch = "";

        String[] subStrings = toSubstrings(pattern);
        for (int i = subStrings.length-1 ; i >=0 ; i-- ){
            if (Main.Gui.Settings.getSelectedWordlistString().contains(subStrings[i])){
                return subStrings[i];
            }
        }
        return subMatch;
    }

    private String[] toSubstrings(String word){
        ArrayList<String> subStrings = new ArrayList<String>();
        for (int i = 0; i < word.length() ; i++){
            for (int j = i+1 ; j < word.length()+1 ; j++){
                subStrings.add(word.substring(i, j));
            }
        }
        String[] subStringArr =  new String[subStrings.size()];
        subStringArr = subStrings.toArray(subStringArr);
        Arrays.sort(subStringArr,Comparator.comparing(String::length));
        return subStringArr;
    }


    protected void done(){
        //this is where the Gui.Settings gets updated at the end
        String result;
        ArrayList<String> issues = this.Node.getIssues();
        try {
            result = get();

            
            
            issues.add("TEST: "+Main.Gui.Settings.getSelectedWordlistName()+" matched a continuous "+ (result.length()*100)/this.Node.getWord().length()+"% of this password: "+result+"\n");
            
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
       for (JButton i : Node.getRightPanel().getButtons()){
            i.setEnabled(true);
        }
        Node.setIssues(issues);
        this.Node.getRightPanel().updateIssues();
    }


    @Override
    protected void process(List<Integer> chunks){
        for (int number : chunks) {
            this.progressBar.setValue(number);
        }
    }
}
