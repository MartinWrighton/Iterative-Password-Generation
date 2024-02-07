
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


import javax.swing.UIManager;

public class main_file {
    public static node selected_node;
    public static gui gui;
    public static void main(String[] args) throws IOException {
        try {//TODO do we want windows or java L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
        
        }
        gui = new gui();
        System.out.println("Welcome, please enter base password:");
        Scanner scanner = new Scanner(System.in);
        boolean main_loop = false;
        while (main_loop == true) {// main loop LEGACY
            System.out.println("Your currently selected node is " + selected_node.getWord()
                    + " how would you like to proceed:\n1)View subtree\n2)Move node\n3)Mutate into child");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Viewing");
                    break;
                case "2":
                    System.out.println("Moving\nWhere would you like to go:\n-1)Cancel");
                    for (int i = 0; i < selected_node.getChildren().size(); i++) {
                        System.out.println(Integer.toString(i) + ")" + selected_node.getChildren().get(i).getWord());
                    }
                    if (selected_node.getParent() != null) {
                        System.out.println(Integer.toString(selected_node.getChildren().size()) + ")The parent: "
                                + selected_node.getParent().getWord());
                    }
                    String entry = scanner.nextLine();
                    if (Integer.parseInt(entry) > selected_node.getChildren().size() || Integer.parseInt(entry) < 0) {// doesent
                                                                                                                      // handle
                                                                                                                      // negatives

                    } else if (Integer.parseInt(entry) == selected_node.getChildren().size()) {
                        selected_node = selected_node.getParent();
                    } else {
                        selected_node = selected_node.getChildren().get(Integer.parseInt(entry));
                    }
                    break;
                case "3":

                    break;
            }
        }

        scanner.close();

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
        // printing the output of the program while it runs
        // BufferedReader stdInput = new BufferedReader(new
        // InputStreamReader(process.getInputStream()));
        // String s = null;
        // while ((s = stdInput.readLine()) != null) {
        // System.out.println(s);
        // }
    }

}
