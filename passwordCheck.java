import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class passwordCheck extends SwingWorker<String,Object> {

    @Override
    protected String doInBackground() throws Exception {
        //TODO this is the background process, where the password check can be done
        approxStringMatch("happy", "Have a hsppyday");

        return "Done";
    }
    protected void done(){
        JLabel current_page_title = (JLabel) main_file.gui.tabs.getSelectedComponent().getComponentAt(405,10).getComponentAt(10,0);
        try {
            //TODO this is where the gui gets updated at the end
            current_page_title.setText(get());

            
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
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
        
        //TODO return smallest difference
        return 1;
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
    
}
