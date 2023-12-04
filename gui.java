import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu.Separator;
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

    public gui() {
        this.frame = new JFrame(); // creating instance of JFrame
        this.tabs = new JTabbedPane();// tabbed pane
        this.panels = new ArrayList<JPanel>();
        this.trees = new ArrayList<JTree>();
        

        // ACTION LISTENERS FOR BUTTONS
        ActionListener mutateChild = new ActionListener() {// process for creating a new child
            @Override
            public void actionPerformed(ActionEvent e) {// TODO learn how to add nodes to trees

            }

        };
        //#region New File Tab
        JPanel panel1 = new JPanel();// new page panel
        this.panels.add(panel1);
        JTextField panel1_text1 = new JTextField(20);
        JButton panel1_button1 = new JButton("Create new File");// create tab button
        //#region Process for creating a file tab
        ActionListener createNewTab = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel newpanel = new JPanel();
                panels.add(newpanel);
                tabs.addTab(panel1_text1.getText(), newpanel);
                // creating tree
                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new node(panel1_text1.getText(), null));// configuring
                                                                                                                     // tree

                DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
                JTree tree = new JTree(treeModel);
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);// Tree selection
                                                                                                    // listener

                tree.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        DefaultMutableTreeNode clicked_node = (DefaultMutableTreeNode) tree
                                .getLastSelectedPathComponent();
                        if (clicked_node == null) {
                            return;
                        }
                        main_file.selected_node = (node) clicked_node.getUserObject();

                    }

                });
                // adding tree to storage
                newpanel.add(tree);
                trees.add(tree);

                // Buttons for panel
                JButton mutateButton = new JButton("Mutate New Child");
                mutateButton.addActionListener(mutateChild);// this actionlistener creates children
                mutateButton.setBackground(new Color(255,105,97));
                newpanel.add(mutateButton);

            }
        };
        //#endregion Process for creating a file tab
        panel1_button1.addActionListener(createNewTab);// this actionlistener creates new tabs
        panel1.add(panel1_button1);
        panel1.add(panel1_text1);
        this.tabs.addTab("New+", panel1);
        this.tabs.setBounds(0, 0, 800, 600);
        //#endregion New File Tab
        
        //#region Settings Tab
        JPanel settings = new JPanel();
        this.tabs.addTab("Settings", settings);

        //#region Valid Character Lists
        JLabel allowedCharactersText = new JLabel("Valid Characters:");// character lists
        allowedCharactersText.setBounds(100, 20, 100, 40);
        settings.add(allowedCharactersText);
        JCheckBox lowerCaseCheckBox = new JCheckBox("Lower case letters: a-z");//these are all allowed characters by the IBM Business Automation Workflow
        lowerCaseCheckBox.setBounds(100, 50, 180, 40);
        settings.add(lowerCaseCheckBox);
        JCheckBox upperCaseCheckBox = new JCheckBox("Upper case letters: A-Z");
        upperCaseCheckBox.setBounds(100, 80, 180, 40);
        settings.add(upperCaseCheckBox);
        JCheckBox numberCheckBox = new JCheckBox("Numbers: 0-9");
        numberCheckBox.setBounds(100, 110, 180, 40);
        settings.add(numberCheckBox);
        JCheckBox symbolCheckBox = new JCheckBox("Symbols: ()-.?[]_`~;:!@#$%^&*+=");
        symbolCheckBox.setBounds(100, 140, 220, 40);
        settings.add(symbolCheckBox);
        //#endregion Valid Character Lists

        //#region Password Policies
        JLabel policyText = new JLabel("Required Policy:");// policy
        policyText.setBounds(100, 250, 100, 40);
        settings.add(policyText);
        //#endregion Password Policies
        
        //#region Wordlists
        JLabel wordListsText = new JLabel("Wordlists:");// word lists
        wordListsText.setBounds(500, 20, 100, 40);
        settings.add(wordListsText);
        // getting list of wordlists
        JComboBox wordBox = new JComboBox(new String[0]);
        File wordFile = new File("../hashcat/CustomWordlists");
        for (File i : wordFile.listFiles()) {
            wordBox.addItem(i.getName());
        }
        wordBox.setBounds(500, 70, 150, 20);
        settings.add(wordBox);
        JButton selectList = new JButton("Use this list");
        selectList.setMargin(new Insets(0, 0, 0, 0));
        selectList.setBounds(670, 70, 75, 20);
        selectList.setBackground(new Color(255,105,97));
        settings.add(selectList);
        JLabel createListTitle = new JLabel("Create new Lists:");// subtitle
        createListTitle.setBounds(500, 100, 100, 40);// sets position and size
        createListTitle.setBorder(BorderFactory.createEmptyBorder());// removes border
        settings.add(createListTitle);// adds it to page
        JButton combineList = new JButton("Combine Lists");
        combineList.setMargin(new Insets(0, 0, 0, 0));
        combineList.setBounds(500, 150, 90, 20);
        combineList.setToolTipText("<html>Create a new Wordlist by combining parts <br> of one or more lists.<html/>");
        combineList.setBackground(new Color(255,105,97));
        settings.add(combineList);
        JButton uploadList = new JButton("Upload Custom Lists");
        uploadList.setMargin(new Insets(0, 0, 0, 0));
        uploadList.setBounds(600, 150, 130, 20);
        uploadList.setToolTipText("<html>Upload your own list from elsewhere <br> on your computer.<html/>");
        uploadList.setBackground(new Color(255,105,97));
        settings.add(uploadList);
        JButton personalList = new JButton("Create Personal List");
        personalList.setMargin(new Insets(0, 0, 0, 0));
        personalList.setBounds(500, 200, 130, 20);
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
        settings.add(personalList);
        settings.setLayout(null);// let setBounds work
        this.panels.add(settings);
        //#endregion Settings Tab

        this.frame.add(tabs);
        this.frame.setSize(800, 600); // 400 width and 500 height
        this.frame.setLayout(null); // using no layout managers
        this.frame.setVisible(true); // making the frame visible

    }
    //#region Getters and Setters
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
    //#endregion Getters and Setters

}
