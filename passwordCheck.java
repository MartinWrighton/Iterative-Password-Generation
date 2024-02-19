import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class passwordCheck extends SwingWorker<String,Object> {
    private String pattern;
    private String text;
    public  passwordCheck(String pattern, String text){
        this.pattern = pattern;
        this.text = text;
    }

    @Override
    protected String doInBackground() throws Exception {
        //TODO this is the background process, where the password check can be done
        return Integer.toString(approxStringMatch(this.pattern, this.text));
    }
    protected void done(){
        //TODO this is where the gui gets updated at the end
        JLabel current_page_title = (JLabel) main_file.gui.tabs.getSelectedComponent().getComponentAt(405,10).getComponentAt(10,0);
        try {
            
            current_page_title.setText(get());

            
        } catch (InterruptedException e) {
            // Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }


    private int approxStringMatch(String pattern,String text){
        String[] patternArray = pattern.split("");
        String[] textArray = text.split("");
        int[][] matrix = new int[pattern.length()+1][text.length()+1];
        
        //prime the matrix
       
        for (int i = 0 ; i < matrix.length ; i++  ){
            matrix[i][0] = i;
        }

        //compute matrix
        for (int j = 0 ; j< textArray.length ;j++){
            for (int i = 0 ; i< patternArray.length ;i++){
                
                if (patternArray[i].equals(textArray[j])){
                    matrix[i+1][j+1] = matrix[i][j];
                } else {
                    matrix[i+1][j+1] = Math.min(Math.min(matrix[i][j],matrix[i+1][j]),matrix[i][j+1])+1;
                }
                System.out.println("");
        }
        }
        printMatrix(matrix);
        System.out.println(getMatch(matrix, textArray));
        Arrays.sort(matrix[matrix.length-1]);
        return matrix[matrix.length-1][0];
    }



    private void printMatrix(int[][] matrix){
        for (int[] i : matrix){
            for (int j : i){
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private String getMatch(int[][] matrix,String[] textArray){
        //TODO something here is array index erroring
        int lowestJ = 0;
        int lowest = 999;
        String match = "";
        //find lowest one in last row
        
        for (int j = 0 ; j < matrix[matrix.length-1].length ; j++){
            if (matrix[matrix.length-1][j]<lowest){
                lowest = matrix[matrix.length-1][j];
                lowestJ = j;
            }
        }
        //match = match + textArray[lowestJ-1];

        System.out.println(lowestJ);
        for (int i= matrix.length-2 ; i>=0 ;i--){
            if (matrix[i][lowestJ]<matrix[i][lowestJ-1]){
                
            } else {
                match = textArray[lowestJ-1] + match;
                lowestJ = lowestJ-1;
                
            }
            System.out.print(lowestJ);
            System.out.print(" : ");
            System.out.println(match);
        }
        return match;
            
    }
    
}
