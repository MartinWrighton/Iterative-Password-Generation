import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
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

    private JPanel createRightPanel(String title,JPanel parent,node newNode){
        //TODO add all different mutation types
        JPanel newRightPanel = new JPanel();//setting up right panel
        parent.add(newRightPanel);
        newRightPanel.setVisible(false);
        newRightPanel.setBounds(405, 10, 385, 600);
        newRightPanel.setLayout(null);
        

        //adding titles to right panel
        JLabel currentNodeLabel = new JLabel(title); 
        currentNodeLabel.setBounds(10, 0, 300, 20);
        currentNodeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        newRightPanel.add(currentNodeLabel);

        //labels
        JLabel rightPanelMutateLabel = new JLabel("Create New Children:");
        rightPanelMutateLabel.setBounds(10, 15, 200, 40);
        newRightPanel.add(rightPanelMutateLabel);

        JLabel rightPanelTestLabel = new JLabel("Test this Node:");
        rightPanelTestLabel.setBounds(10, 250, 200, 40);
        newRightPanel.add(rightPanelTestLabel);

        JLabel rightPanelResultsLabel = new JLabel("Results & Issues:");
        rightPanelResultsLabel.setBounds(10, 350, 200, 40);
        newRightPanel.add(rightPanelResultsLabel);

        JButton goMutate = new JButton("Go (test)"); // setting up mutate button
        goMutate.setBackground(new Color(255,105,97));
        goMutate.setBounds(200, 200, 150, 20);
        goMutate.setToolTipText("This is a test button to create test children");
        newRightPanel.add(goMutate);
        goMutate.addActionListener( new ActionListener(){// process for creating a new child

            @Override
            public void actionPerformed(ActionEvent e) {
                createChildNode(e);
            }});// this actionlistener creates children


        JRadioButton mutateRadioButtonT1 = new JRadioButton("Pad Numbers");
        mutateRadioButtonT1.setBounds(10, 80, 150, 20);
        mutateRadioButtonT1.setToolTipText("Create a child by padding the parent with numbers");
        mutateRadioButtonT1.setSelected(true);
        newRightPanel.add(mutateRadioButtonT1);
        JRadioButton mutateRadioButtonT2 = new JRadioButton("Homoglyph");
        mutateRadioButtonT2.setBounds(10, 110, 150, 20);
        mutateRadioButtonT2.setToolTipText("Create a child by exchanging characters with homoglyphs");
        newRightPanel.add(mutateRadioButtonT2);

        JRadioButton mutateRadioButtonT3 = new JRadioButton("Capitalize");
        mutateRadioButtonT3.setBounds(10, 140, 150, 20);
        mutateRadioButtonT3.setToolTipText("Create a child by capitalizing random characters");
        newRightPanel.add(mutateRadioButtonT3);

        ButtonGroup mutateRadioGroup = new ButtonGroup();
        mutateRadioGroup.add(mutateRadioButtonT1);
        mutateRadioGroup.add(mutateRadioButtonT2);
        mutateRadioGroup.add(mutateRadioButtonT3);

        JLabel howManyLabel = new JLabel("How many?");
        howManyLabel.setBounds(200, 150, 200, 20);
        newRightPanel.add(howManyLabel);

        
        JSpinner howManySpin = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        howManySpin.setBounds(200, 170, 40, 20);
        newRightPanel.add(howManySpin);
        
        JProgressBar progressBar = new JProgressBar(0,100);
        progressBar.setBounds(10, 340, 350, 20);
        newRightPanel.add(progressBar);

        JButton goTest = new JButton("Test");
        goTest.setBounds(10, 300, 80, 20);
        newRightPanel.add(goTest);
        goTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (main_file.selected_node != null && settings.getSelectedWordlistString() != null){
                    passwordCheck checker = new passwordCheck(main_file.selected_node,progressBar);
                    checker.execute();
                    goTest.setEnabled(false);
                } else {
                    JDialog dialog = new JDialog(main_file.gui.frame,"ERROR: No wordlist selected!",true);
                    dialog.setSize(230,20);
                    dialog.setLocationRelativeTo(main_file.gui.frame);
                    dialog.setVisible(true);
                }
            }});
        
        JTextArea resultBox = new JTextArea();
        resultBox.setFont(resultBox.getFont().deriveFont(11f));
        resultBox.setEditable(false);
        for (String i : newNode.getIssues()){
            resultBox.append(i);
        }
        JScrollPane scroll = new JScrollPane(resultBox);
        scroll.setBounds(10, 375, 350, 150);
        newRightPanel.add(scroll);

        JButton clearButton = new JButton("Reset");
        clearButton.setMargin(new Insets(0,0,0,0));
        clearButton.setFont(clearButton.getFont().deriveFont(10f));
        clearButton.setBounds(310, 360, 35, 15);
        newRightPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newNode.setIssues(new ArrayList<String>());
                newNode.policyCheck();
                updateIssues(newNode);
            }
            
        });


        return newRightPanel;
    }

    private void createChildNode(ActionEvent e){
        if(main_file.selected_node != null){
            //newly created node stored here
            node newNode = new node(null, null, null, null, null);
            //we will need a randomizer
            Random random = new Random();
            //source of event is the button
            JButton gButton = (JButton) e.getSource();
            //Right panel is the parent
            JPanel rPanel = (JPanel) gButton.getParent();
            //content of howManySpin while we are here
            int howManyNum = (int) ((JSpinner) rPanel.getComponentAt(200, 170)).getValue();
            //radio button is a child of parent
            if (((AbstractButton) rPanel.getComponentAt(10, 80)).isSelected()){//Pixel locator
                //pad numbers
                boolean isValid = ((AbstractButton) main_file.gui.tabs.getComponentAt(0).getComponentAt(10,10).getComponentAt(10,100)).isSelected();
                if (isValid == true){
                    String frontPad = "";
                    String backPad = "";
                    for (int i = 0 ; i < howManyNum ; i++){
                        frontPad = frontPad +(Integer.toString(random.nextInt(10)));
                        backPad = backPad +(Integer.toString(random.nextInt(10)));
                    }
                    newNode = main_file.selected_node.addChild(frontPad+main_file.selected_node.getWord()+backPad);
                } else {//dialogue box
                    JDialog dialog = new JDialog(main_file.gui.frame,"POLICY: Your policy does not allow numbers!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(main_file.gui.frame);
                    dialog.setVisible(true);
                }
            } else if (((AbstractButton) rPanel.getComponentAt(10, 110)).isSelected()){
                //homoglyph
            } else if (((AbstractButton) rPanel.getComponentAt(10, 140)).isSelected()){
                //capitalize
            } else {
                //else
                newNode = main_file.selected_node.addChild(new Date().toString());//date is the test name
            }

            if (newNode.getWord() != null){
                
                newNode.setRightPanel(createRightPanel(newNode.getWord(),(JPanel) main_file.gui.tabs.getSelectedComponent(),newNode));
            }
        }
        
    }

    public static void updateIssues(node newNode){
        JTextArea area = (JTextArea) ((JScrollPane) newNode.getRightPanel().getComponentAt(10,390)).getViewport().getView();
        area.setText("");
        for (String i : newNode.getIssues()){
            area.append(i);
        }

    }

    private JTree createNewTree(String rootPassword,JPanel parentPanel){
         node newNode = new node(rootPassword, null,null,null,null);
         newNode.setRightPanel(createRightPanel(rootPassword,parentPanel,newNode));
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