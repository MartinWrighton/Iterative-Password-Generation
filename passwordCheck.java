
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class passwordCheck extends SwingWorker<String,Object> {
    private String pattern;
    public  passwordCheck(String pattern){
        this.pattern = pattern;
    }

    @Override
    protected String doInBackground() throws Exception {
        //this is the background process, where the password check can be done
        // The matrix is filled with bytes, this means that we cannot use passwords longer than 126 characters. I doubt that will be an issue
        //TODO we are going to break this down, because it gets up to like 5gb of array, just split the list into blocks of idk 3,000,000 characters and run them each seperately
        //TODO add a break if a perfect match is found 
        System.out.print("Wordlist characters :");
        System.out.println(main_file.selected_wordlist_string.length());
        System.out.println((10/11)+1);
        int bestMatch = 999;
        int newMatch;
        for (int i = 1; i <= (main_file.selected_wordlist_string.length()/3000000)+1 ; i++){
            //System.out.print("Block : ");
            //System.out.println(i);
            
            try {
                newMatch = approxStringMatch(this.pattern,main_file.selected_wordlist_string.substring(3000000*(i-1),3000000*i));
            } catch (Exception e) {
                newMatch = approxStringMatch(this.pattern,main_file.selected_wordlist_string.substring(3000000*(i-1)));
            }
            if (newMatch<bestMatch){
                bestMatch = newMatch;
            }
        }

        return Integer.toString(bestMatch);
    }

    protected void done(){
        //this is where the gui gets updated at the end
        JLabel current_page_title = (JLabel) main_file.gui.getTabs().getSelectedComponent().getComponentAt(405,10).getComponentAt(10,0);
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
            
        }
        //printMatrix(matrix);
        //System.out.println(getMatch(matrix, textArray));
        Arrays.sort(matrix[matrix.length-1]);
        return matrix[matrix.length-1][0];
    }



    @SuppressWarnings("unused")
    private void printMatrix(int[][] matrix){
        for (int[] i : matrix){
            for (int j : i){
                System.out.print(j);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    private String getMatch(byte[][] matrix,String[] textArray){
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

}
