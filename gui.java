import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class gui {
    private JFrame frame;
    private JTabbedPane tabs;
    private ArrayList<JPanel> panels;
    private ArrayList<JTree> trees;

    protected settings settings;
    public gui() {
        this.frame = new JFrame(); // creating instance of JFrame
        this.tabs = new JTabbedPane();// tabbed pane
        this.panels = new ArrayList<JPanel>();
        this.trees = new ArrayList<JTree>();
        this.frame.add(tabs);
        this.frame.setSize(800, 600); // 400 width and 500 height
        this.frame.setLayout(null); // using no layout managers
        this.frame.setVisible(true); // making the frame visible
        createNewFileTab();
        
        settings = new settings(tabs);
        

    }

    private void createNewFileTab(){
        //TODO fill out this page
        JPanel panel1 = new JPanel();// new page panel
        this.panels.add(panel1);

        JTextField panel1_text1 = new JTextField(20);
        JButton panel1_button1 = new JButton("Create new File");// create tab button

        panel1_button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewWorkingFileTab(panel1_text1.getText());
            }});
        panel1.add(panel1_button1);
        panel1.add(panel1_text1);

        this.tabs.insertTab("New+", null, panel1, "Create a new file", 0);
        this.tabs.setBounds(0, 0, 800, 600);


       
   
    }


    private void createNewWorkingFileTab(String rootPassword){
        //create new panel
        JPanel newpanel = new JPanel();
        newpanel.setBackground(new Color(200,200,255));
        newpanel.setLayout(null);
        panels.add(newpanel);
        tabs.insertTab(rootPassword, null, newpanel, null, tabs.getSelectedIndex());

        

        createLeftPanel(newpanel,rootPassword);
        
    }
        
    private void createLeftPanel(JPanel newpanel,String rootPassword){
        //create left panel
        JPanel newLeftPanel = new JPanel();
        newpanel.add(newLeftPanel);
        newLeftPanel.setBounds(10, 10, 385, 680);
        newLeftPanel.setLayout(null);

        //create tree and place in left panel
        JTree tree = createNewTree(rootPassword,newpanel);
        tree.setBounds(10, 10, 375, 670);
        newLeftPanel.add(tree);
        trees.add(tree);
    }

    
    private JTree createNewTree(String rootPassword,JPanel parentPanel){
        node newNode = new node(rootPassword, null,null,null,null);
        newNode.setRightPanel(new rightPanel(rootPassword,parentPanel,newNode));
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newNode);// configuring tree and combining with our node class
        newNode.setTreeNode(rootNode);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        newNode.setTreeModel(treeModel);
        JTree tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {//when node is clicked
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode clicked_node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();//get the clicked node
                if (clicked_node == null) {
                    return;
                }

                if (main_file.selected_node != null){
                   main_file.selected_node.getRightPanel().setVisible(false);
                }
                main_file.selected_node = (node) clicked_node.getUserObject();//save clicked node (as a node object)
                ((node) clicked_node.getUserObject()).getRightPanel().setVisible(true);

                tree.clearSelection();//lets the node be clicked again
            }

        });

        return tree;
        
   }
   
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<JPanel> panels) {
        this.panels = panels;
    }

}