import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class gui {
    private JFrame frame;
    JTabbedPane tabs;
    private ArrayList<JPanel> panels;
    private ArrayList<JTree> trees;

    public gui() {
        this.frame = new JFrame(); // creating instance of JFrame
        this.tabs = new JTabbedPane();// tabbed pane
        this.panels = new ArrayList<JPanel>();
        this.trees = new ArrayList<JTree>();
        
        createNewFileTab();
        
        createSettingsTab();
        
        this.frame.add(tabs);
        this.frame.setSize(800, 600); // 400 width and 500 height
        this.frame.setLayout(null); // using no layout managers
        this.frame.setVisible(true); // making the frame visible

    }

    private void createNewFileTab(){
        
        //#region New File Tab
        JPanel panel1 = new JPanel();// new page panel
        this.panels.add(panel1);
        JTextField panel1_text1 = new JTextField(20);
        JButton panel1_button1 = new JButton("Create new File");// create tab button

        //#region newly created tree tabs

        ActionListener createNewTab = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel newpanel = new JPanel();
                panels.add(newpanel);
                tabs.insertTab(panel1_text1.getText(), null, newpanel, null, tabs.getSelectedIndex());
                //#region creating tree
                node newNode = new node(panel1_text1.getText(), null,null,null);
                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newNode);// configuring tree
                newNode.setTreeNode(rootNode);
                DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
                newNode.setTreeModel(treeModel);
                JTree tree = new JTree(treeModel);

                //#region tree selection listener
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                tree.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        DefaultMutableTreeNode clicked_node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                        if (clicked_node == null) {
                            return;
                        }
                        main_file.selected_node = (node) clicked_node.getUserObject();
                        
                        //System.out.println(main_file.selected_node);
                        //System.out.println(main_file.selected_node.getTreeNode().getChildCount());

                        //setting text on right panel THIS WORKS BY PIXEL PLACEMENT AND WIL BREAK IF YOU MOVE THE COMPONENTS
                        JLabel current_page_title = (JLabel) main_file.gui.tabs.getSelectedComponent().getComponentAt(405,10).getComponentAt(10,0);
                        current_page_title.setText(clicked_node.toString());
                    }

                });
                //#endregion tree selection listener

                //#region adding tree to storage and page
                
                
                newpanel.setLayout(null);
                JPanel newLeftPanel = new JPanel();
                newpanel.add(newLeftPanel);
                newLeftPanel.setBounds(10, 10, 385, 680);
                
                newLeftPanel.add(tree);
                newLeftPanel.setLayout(null);
                tree.setBounds(10, 10, 375, 670);
                trees.add(tree);
                //#endregion adding tree to storage and page
            //#endregion creating tree

                //#region right panel
                //TODO add all different mutation types
               
                JPanel newRightPanel = new JPanel();//setting up right panel
                newpanel.add(newRightPanel);
                newRightPanel.setBounds(405, 10, 385, 600);
                newRightPanel.setLayout(null);
                newpanel.setBackground(new Color(200,200,255));

                //adding titles to right panel
                JLabel currentNodeLabel = new JLabel("-"); //MOVING THIS WILL PROVENT IT FROM WORKING (SEE TREE LISTENER) (this is a poor workaround)
                currentNodeLabel.setBounds(10, 0, 300, 20);
                currentNodeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
                newRightPanel.add(currentNodeLabel);

                JLabel rightPanelMutateLabel = new JLabel("Create New Children:");
                rightPanelMutateLabel.setBounds(10, 15, 200, 40);
                newRightPanel.add(rightPanelMutateLabel);

                JLabel rightPanelTestLabel = new JLabel("Test this Node:");
                rightPanelTestLabel.setBounds(10, 250, 200, 40);
                newRightPanel.add(rightPanelTestLabel);

                JButton goMutate = new JButton("Go (test)"); // setting up mutate button
                goMutate.addActionListener( new ActionListener(){// process for creating a new child

                    @Override
                    public void actionPerformed(ActionEvent e) {
                    if(main_file.selected_node != null){
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
                                main_file.selected_node.addChild(frontPad+main_file.selected_node.getWord()+backPad);
                            } else {//dialogue box
                                JDialog dialog = new JDialog(main_file.gui.frame,"Your policy does not allow numbers!",true);
                                dialog.setSize(300,20);
                                dialog.setLocationRelativeTo(main_file.gui.frame);
                                dialog.setVisible(true);
                            }
                        } else if (((AbstractButton) rPanel.getComponentAt(10, 110)).isSelected()){
                            //homoglyph
                        } else if (((AbstractButton) rPanel.getComponentAt(10, 140)).isSelected()){
                            //capitalize
                        } else {
                            //else
                            main_file.selected_node.addChild(new Date().toString());//date is the test name
                        }
                    }
                    
                }
                });// this actionlistener creates children
                goMutate.setBackground(new Color(255,105,97));
                goMutate.setBounds(200, 200, 150, 20);
                goMutate.setToolTipText("This is a test button to create test children");
                newRightPanel.add(goMutate);

                    //#region example mutate options
                JRadioButton mutateRadioButtonT1 = new JRadioButton("Pad Numbers");
                mutateRadioButtonT1.setBounds(10, 80, 150, 20);
                mutateRadioButtonT1.setToolTipText("Create a child by padding the parent with numbers");
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
                    //#endregion

                    //#region how many X
                JLabel howManyLabel = new JLabel("How many?");
                howManyLabel.setBounds(200, 150, 200, 20);
                newRightPanel.add(howManyLabel);

                
                JSpinner howManySpin = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
                howManySpin.setBounds(200, 170, 40, 20);
                newRightPanel.add(howManySpin);
                    //#endregion
                
                    //#region test password
                    JButton goTest = new JButton("Test");
                    goTest.setBounds(10, 300, 80, 20);
                    newRightPanel.add(goTest);
                    goTest.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            passwordCheck checker = new passwordCheck(main_file.selected_node.getWord(),"martin");
                            //TODO add file handling, maybe just concat the whole file (make sure to remove endlines ) nad patternmatch against the whole thing?
                            checker.execute();
                        }});
                    //#endregion test password
                

                //#endregion right panel

            }
        };
        //#endregion Process for creating a new file tab
        
        panel1_button1.addActionListener(createNewTab);// this actionlistener creates new tabs
        panel1.add(panel1_button1);
        panel1.add(panel1_text1);
        this.tabs.insertTab("New+", null, panel1, "Create a new file", 0);
        this.tabs.setBounds(0, 0, 800, 600);
        //#endregion New File Tab
   
    }

    private void createSettingsTab(){
        
        //#region Settings Tab
        JPanel settings = new JPanel();
        settings.setBackground(new Color(200,200,255));
        this.tabs.insertTab("Settings", null, settings, "Personalise your experience", 0);

        //#region Valid Character Lists
        JPanel characterPanel = new JPanel();
        characterPanel.setBounds(10,10,385,255);
        characterPanel.setLayout(null);
        settings.add(characterPanel);
        JLabel allowedCharactersText = new JLabel("Valid Characters:");// character lists
        allowedCharactersText.setBounds(10,10, 100, 10);
        characterPanel.add(allowedCharactersText);
        JCheckBox lowerCaseCheckBox = new JCheckBox("Lower case letters: a-z");//these are all allowed characters by the IBM Business Automation Workflow
        lowerCaseCheckBox.setBounds(10, 40, 180, 20);
        characterPanel.add(lowerCaseCheckBox);
        JCheckBox upperCaseCheckBox = new JCheckBox("Upper case letters: A-Z");
        upperCaseCheckBox.setBounds(10, 70, 180, 20);
        characterPanel.add(upperCaseCheckBox);
        JCheckBox numberCheckBox = new JCheckBox("Numbers: 0-9");
        numberCheckBox.setBounds(10, 100, 180, 20);
        characterPanel.add(numberCheckBox);
        JCheckBox symbolCheckBox = new JCheckBox("Symbols: ()-.?[]_`~;:!@#$%^&*+=");
        symbolCheckBox.setBounds(10, 130, 220, 20);
        characterPanel.add(symbolCheckBox);
        //#endregion Valid Character Lists

        //#region Password Policies

        JPanel policyPanel = new JPanel();
        policyPanel.setBounds(10,275,385,255);
        policyPanel.setLayout(null);
        settings.add(policyPanel);
        JLabel policyText = new JLabel("Required Policy:");// policy
        policyText.setBounds(10, 10, 100, 12);
        policyPanel.add(policyText);
        JCheckBox lengthCheckBox = new JCheckBox("At least ___ characters");//these are all allowed characters by the IBM Business Automation Workflow
        lengthCheckBox.setBounds(10, 40, 180, 40);
        policyPanel.add(lengthCheckBox);
        JCheckBox capitalCheckBox = new JCheckBox("Must include a capital letter");//these are all allowed characters by the IBM Business Automation Workflow
        capitalCheckBox.setBounds(10, 70, 200, 40);
        policyPanel.add(capitalCheckBox);
        JCheckBox numeralCheckBox = new JCheckBox("Must include a number");//these are all allowed characters by the IBM Business Automation Workflow
        numeralCheckBox.setBounds(10, 100, 180, 40);
        policyPanel.add(numeralCheckBox);
        JCheckBox specialCheckBox = new JCheckBox("Must include a symbol");//these are all allowed characters by the IBM Business Automation Workflow
        specialCheckBox.setBounds(10, 130, 180, 40);
        policyPanel.add(specialCheckBox);


        //#endregion Password Policies
        
        //#region Wordlists
        JPanel wordlistPanel = new JPanel();
        wordlistPanel.setBounds(405,10,385,600);
        wordlistPanel.setLayout(null);
        settings.add(wordlistPanel);
        JLabel wordListsText = new JLabel("Wordlists:");// word lists
        wordListsText.setBounds(10, 10, 100, 40);
        wordlistPanel.add(wordListsText);
        // getting list of wordlists
        JComboBox wordBox = new JComboBox(new String[0]);
        try {
            File wordFile = new File("../hashcat/CustomWordlists");
            for (File i : wordFile.listFiles()) {
                wordBox.addItem(i.getName());
            }
        } catch (Exception e) {
            System.out.println("Hashcat folder not found");
        }
        
        wordBox.setBounds(10, 40, 150, 20);
        wordlistPanel.add(wordBox);
        JButton selectList = new JButton("Use this list");
        selectList.setMargin(new Insets(0, 0, 0, 0));
        selectList.setBounds(10, 70, 75, 20);
        selectList.setBackground(new Color(255,105,97));
        wordlistPanel.add(selectList);
        JLabel createListTitle = new JLabel("Create new Lists:");// subtitle
        createListTitle.setBounds(10, 100, 100, 40);// sets position and size
        createListTitle.setBorder(BorderFactory.createEmptyBorder());// removes border
        wordlistPanel.add(createListTitle);// adds it to page
        JButton combineList = new JButton("Combine Lists");
        combineList.setMargin(new Insets(0, 0, 0, 0));
        combineList.setBounds(10, 130, 90, 20);
        combineList.setToolTipText("<html>Create a new Wordlist by combining parts <br> of one or more lists.<html/>");
        combineList.setBackground(new Color(255,105,97));
        wordlistPanel.add(combineList);
        JButton uploadList = new JButton("Upload Custom Lists");
        uploadList.setMargin(new Insets(0, 0, 0, 0));
        uploadList.setBounds(10, 160, 130, 20);
        uploadList.setToolTipText("<html>Upload your own list from elsewhere <br> on your computer.<html/>");
        uploadList.setBackground(new Color(255,105,97));
        wordlistPanel.add(uploadList);
        JButton personalList = new JButton("Create Personal List");
        personalList.setMargin(new Insets(0, 0, 0, 0));
        personalList.setBounds(10, 190, 130, 20);
        personalList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main_file.createPersonalList();
                } catch (IOException e1) {
                    System.out.println("main_file.createPersonalList() failed!!!");
                    e1.printStackTrace();
                }
                wordBox.addItem("personalList.txt");
                

                
            }
        });
        personalList.setToolTipText("<html>Use hashcat to create a personalised wordlist <br> based on your publicly available data.<html/>");
        personalList.setBackground(new Color(255,179,71));


        //#endregion Wordlists
        wordlistPanel.add(personalList);
        settings.setLayout(null);// let setBounds work
        this.panels.add(settings);
        //#endregion Settings Tab

    }
    // #region Getters and Setters
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
    // #endregion Getters and Setters

}
