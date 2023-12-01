import java.util.ArrayList;
import java.util.Scanner;

public class main_file {
    public static node selected_node;
    public static void main(String[] args) {
         gui gui = new gui();
        System.out.println("Welcome, please enter base password:");
        Scanner scanner = new Scanner(System.in);
        node root = new node(scanner.nextLine(),null);
        boolean main_loop = true;
        selected_node = root;
        while (main_loop = true) {//main loop
            System.out.println("Your currently selected node is "+selected_node.getWord()+" how would you like to proceed:\n1)View subtree\n2)Move node\n3)Mutate into child");
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("Viewing");
                    System.out.println(selected_node.toTree());
                    break;
                case "2":
                    System.out.println("Moving\nWhere would you like to go:\n-1)Cancel");
                    for (int i = 0; i < selected_node.getChildren().size(); i++) {
                        System.out.println(Integer.toString(i)+")"+selected_node.getChildren().get(i).getWord());
                    }
                    if(selected_node.getParent()!=null){
                        System.out.println(Integer.toString(selected_node.getChildren().size())+")The parent: "+selected_node.getParent().getWord());
                    }
                    String entry = scanner.nextLine();
                    if (Integer.parseInt(entry)>selected_node.getChildren().size()||Integer.parseInt(entry)<0){//doesent handle negatives
                        
                    } else if (Integer.parseInt(entry)==selected_node.getChildren().size()){
                        selected_node = selected_node.getParent();
                    } else { 
                        selected_node = selected_node.getChildren().get(Integer.parseInt(entry));
                    }
                    break;
                case "3":
                    System.out.println("Mutating");//just adding children for testing at the moment
                    ArrayList<node> temp = selected_node.getChildren();
                    temp.add(new node("mertin",selected_node));
                    selected_node.setChildren(temp);
                    break;
            }
        }

    




        scanner.close();























    }
    
}
