import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.Insets;

public class Gui {
    private JFrame frame;
    private JTabbedPane tabs;

    protected Settings Settings;

    public Gui() {
        //TODO suggestions, if characters are missing, its too short or long substrings are matched
        this.frame = new JFrame(); // creating instance of JFrame
        this.tabs = new JTabbedPane();// tabbed pane

        this.frame.setLayout(new GridBagLayout());
        this.frame.setMinimumSize(new Dimension(550,400));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        this.frame.add(tabs,c);
        

        this.frame.setVisible(true); // making the frame visible
        createNewFileTab();
        
        Settings = new Settings(tabs);
        

    }

    private void createNewFileTab(){

        JPanel panel1 = new JPanel(new GridBagLayout());// new page panel
        

        JTextField panel1_text1 = new JTextField(20);
        JButton panel1_button1 = new JButton("Create new File");// create tab button
        JTextArea panel1Text = new JTextArea(10,61);
        JPanel textpanel = new JPanel();
        panel1Text.append("Welcome!\nTo create a new password: enter your simple root password inthe box above and press 'Create New File'.\nThis will create a new tab for you to navigate to.\nThere you will be able to select your password or any       existing child to open its panel. Choose from the list of   mutations, use the spinner to set how many times it should  be applied and hit go!\nClick either testing button to test the password against thecurrently selected wordlist. You will be free to continue   working in the meantime.\nRemember to check the settings tab to set your password     policy, disalow certain character types and to select a     wordlist!");
        panel1Text.setEditable(false);
        panel1Text.setLineWrap(true);
        panel1_button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panel1_text1.getText().equals("")){
                    createNewWorkingFileTab(panel1_text1.getText());
                } 
                panel1_text1.setText("");
            }});
        

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        panel1.add(panel1_button1,c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        panel1.add(panel1_text1,c);

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 0;
        c.gridwidth = 4;
        c.weightx = 0;
        c.weighty = 1;
        c.anchor = GridBagConstraints.PAGE_START;
        panel1.add(textpanel,c);
        textpanel.add(panel1Text);
        this.tabs.insertTab("New+", null, panel1, "Create a new file", 0);


       
   
    }


    private void createNewWorkingFileTab(String rootPassword){
        //create new panel
        JPanel newpanel = new JPanel();
        newpanel.setBackground(new Color(200,200,255));
        newpanel.setLayout(new GridBagLayout());
        tabs.insertTab(rootPassword, null, newpanel, null, tabs.getSelectedIndex());

        

        createLeftPanel(newpanel,rootPassword);
        
    }
        
    private void createLeftPanel(JPanel newpanel,String rootPassword){
        //create left panel
        JPanel newLeftPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 0);
        c.ipadx = 10;
        c.ipady = 10;
        c.weightx = 1;
        c.weighty = 1;
        newLeftPanel.setLayout(new GridBagLayout());
        newpanel.add(newLeftPanel,c);
        //create tree and place in left panel
        JTree tree = createNewTree(rootPassword,newpanel);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        newLeftPanel.add(tree,c);
    }

    
    private JTree createNewTree(String rootPassword,JPanel parentPanel){
        Node newNode = new Node(rootPassword, null,null,null,null);
        newNode.setRightPanel(new RightPanel(rootPassword,parentPanel,newNode));
        newNode.getRightPanel().setVisible(false);
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newNode);// configuring tree and combining with our Node class
        newNode.setTreeNode(rootNode);
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        newNode.setTreeModel(treeModel);
        JTree tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {//when Node is clicked
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode clicked_node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();//get the clicked Node
                if (clicked_node == null) {
                    return;
                }

                if (Main.selected_node != null){
                   Main.selected_node.getRightPanel().setVisible(false);
                }
                Main.selected_node = (Node) clicked_node.getUserObject();//save clicked Node (as a Node object)
                ((Node) clicked_node.getUserObject()).getRightPanel().setVisible(true);

                tree.clearSelection();//lets the Node be clicked again
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

}