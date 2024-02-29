
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class ApproximateCheck extends SwingWorker<String,Integer> {
    //TODO make a new longest substring test?
    //TODO make hashing time test?
    private String pattern;
    private JProgressBar progressBar;
    private Node Node;
    public  ApproximateCheck(Node Node,JProgressBar progressBar){
        this.pattern = Node.getWord();
        this.Node = Node;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground() throws Exception {
        //this is the background process, where the password check can be done
        // The matrix is filled with bytes, this means that we cannot use passwords longer than 126 characters. I doubt that will be an issue
        int bestMatch = 999;
        int newMatch;
        for (int i = 1; i <= (Main.Gui.Settings.getSelectedWordlistString().length()/3000000)+1 ; i++){
            
            
            try {
                newMatch = approxStringMatch(this.pattern,Main.Gui.Settings.getSelectedWordlistString().substring(3000000*(i-1),3000000*i));
            } catch (Exception e) {
                newMatch = approxStringMatch(this.pattern,Main.Gui.Settings.getSelectedWordlistString().substring(3000000*(i-1)));
            }
            if (newMatch == 0){// early break on perfect match
                bestMatch = 0;
                break;
            } else if (newMatch<bestMatch){
                bestMatch = newMatch;
            }

            publish((i*100) / ((Main.Gui.Settings.getSelectedWordlistString().length()/3000000)+1));
        }
        return Integer.toString(bestMatch);
    }

    protected void done(){
        //this is where the Gui.Settings gets updated at the end
        String result;
        ArrayList<String> issues = this.Node.getIssues();
        try {
            result = get();
            //TODO actually calculate how bad this is and display it somehow
            if (result.equals("0")){
                issues.add("TEST: "+Main.Gui.Settings.getSelectedWordlistName()+" cracked this password\n");
            } else {
                issues.add("TEST: "+Main.Gui.Settings.getSelectedWordlistName()+" was within "+result+" character(s) of this password\n");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Node.getRightPanel().getGoTest().setEnabled(true);
        Node.setIssues(issues);
        this.Node.getRightPanel().updateIssues();
    }


    private int approxStringMatch(String pattern,String text){
        String[] patternArray = pattern.split("");
        String[] textArray = text.split("");
        byte[][] matrix = new byte[pattern.length()+1][text.length()+1];
        
        //prime the matrix
       
        for (byte i = 0 ; i < matrix.length ; i++  ){
            matrix[i][0] = i;
        }
        //compute matrix
        for (int j = 0 ; j< textArray.length ;j++){
            for (int i = 0 ; i< patternArray.length ;i++){
                
                if (patternArray[i].equals(textArray[j])){
                    matrix[i+1][j+1] = matrix[i][j];
                } else {
                    matrix[i+1][j+1] = (byte) (Math.min(Math.min(matrix[i][j],matrix[i+1][j]),matrix[i][j+1])+1);
                }
            }
            if (matrix[patternArray.length][j+1] == 0){//break early on perfect match
                return 0;
            }

            
        }

        
        Arrays.sort(matrix[matrix.length-1]);
        return matrix[matrix.length-1][0];
    }



    @SuppressWarnings("unused")
    private void printMatrix(byte[][] matrix){
        for (byte[] i : matrix){
            for (int j : i){
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    @SuppressWarnings("unused")
    private String getMatch(byte[][] matrix,String[] textArray){
        int lowestJ = 0;
        int lowest = 126;
        String match = "";
        //find lowest one in last row
        
        for (int j = 0 ; j < matrix[matrix.length-1].length ; j++){
            if (matrix[matrix.length-1][j]<lowest){
                lowest = matrix[matrix.length-1][j];
                lowestJ = j;
            }
        }
        //match = match + textArray[lowestJ-1];
        
        if (lowestJ > 1){
        for (int i= matrix.length-2 ; i>=0 ;i--){
            if (matrix[i][lowestJ]<matrix[i][lowestJ-1]){
                
            } else {
                match = textArray[lowestJ-1] + match;
                lowestJ = lowestJ-1;
                if (lowestJ == 0){
                    break;
                }
            }
            System.out.print(lowestJ);
            System.out.print(" : ");
            System.out.println(match);
        }
        } else {
            match = match + textArray[0];
        }
        return match;
            
    }

    @Override
    protected void process(List<Integer> chunks){
        for (int number : chunks) {
            this.progressBar.setValue(number);
        }
    }
}
