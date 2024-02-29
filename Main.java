
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.UIManager;

public class Main {
    public static Node selected_node;
    public static Gui Gui;

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e){
        
        }
        Gui = new Gui();
    }




    public static void createPersonalList() throws IOException {
        // THIS PART IS HOW WE USE HASHCAT TO CREATE NEW WORDLISTS
        //https://www.blackhillsinfosec.com/wp-content/uploads/2020/09/HashcatCheatSheet.v2018.1b.pdf  is goated
        ArrayList<String> command = new ArrayList<String>();// command is a cmd command and a list of
                                                            // arguments
        command.add("hashcat.exe");// the command to run hashcat, for some reason this uses the hashcat in
                                   // vscode_test directory
        // command.add("--force"); hashcat says this is a bad idea
        command.add("CustomWordlists/words.txt");// the file we are using as input
        command.add("-r");// tell it we want to use a rule
        command.add("rules/best64.rule");// the rule we are using
        command.add("--stdout");// i think this is what makes it create a list rather than trying to crack
        command.add("-o");// tells it we want to output somewhere
        command.add("CustomWordlists/personalList.txt");// the file we output to
        ProcessBuilder build = new ProcessBuilder(command);// build the process
        build.directory(new File("../hashcat"));// set its directory to the one with hashcat in it

        // System.out.println("command: " + build.command());//test printing
        // System.out.println("directory: " + build.directory());
        // build.redirectErrorStream(true);
        Process process = build.start();
        process.getErrorStream();//added so that the "this isnt being used warning would go away"
        // printing the output of the program while it runs
        // BufferedReader stdInput = new BufferedReader(new
        // InputStreamReader(process.getInputStream()));
        // String s = null;
        // while ((s = stdInput.readLine()) != null) {
        // System.out.println(s);
        // }
    }

}
