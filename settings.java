import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class settings extends JPanel {
    private JCheckBox canLowercase;
    private JCheckBox canUppercase;
    private JCheckBox canNumber;
    private JCheckBox canSymbol;

    private JCheckBox needLength;
    private JCheckBox needCapital;
    private JCheckBox needNumber;
    private JCheckBox needSymbol;

    private JSpinner targetLength;

    private JComboBox<String> wordBox;
    private JButton selectList;

    private String selectedWordlistName;
    private String selectedWordlistString;

    public settings(){

        setBackground(new Color(200,200,255));
        setLayout(null);// let setBounds work
        main_file.gui.getPanels().add(this);
        main_file.gui.getTabs().insertTab("Settings", null, this, "Personalise your experience", 0);

        createCharacterListPanel();
        
        createPasswordPolicyPanel();

        createWordlistPanel();
    }

    
    public void createCharacterListPanel(){
        //TODO implement character lists
        JPanel characterPanel = new JPanel();
        characterPanel.setBounds(10,10,385,255);
        characterPanel.setLayout(null);
        add(characterPanel);
        JLabel allowedCharactersText = new JLabel("Valid Characters:");// character lists
        allowedCharactersText.setBounds(10,10, 100, 10);
        characterPanel.add(allowedCharactersText);
        canLowercase = new JCheckBox("Lower case letters: a-z");//these are all allowed characters by the IBM Business Automation Workflow
        canLowercase.setBounds(10, 40, 180, 20);
        canLowercase.setSelected(true);
        characterPanel.add(canLowercase);
        canUppercase = new JCheckBox("Upper case letters: A-Z");
        canUppercase.setBounds(10, 70, 180, 20);
        canUppercase.setSelected(true);
        characterPanel.add(canUppercase);
        canNumber = new JCheckBox("Numbers: 0-9");
        canNumber.setBounds(10, 100, 180, 20);
        canNumber.setSelected(true);
        characterPanel.add(canNumber);
        canSymbol = new JCheckBox("Symbols: ()-.?[]_`~;:!@#$%^&*+=");
        canSymbol.setBounds(10, 130, 220, 20);
        canSymbol.setSelected(true);
        characterPanel.add(canSymbol);
    }

    public void createPasswordPolicyPanel(){
        //TODO more password policy options?
        JPanel policyPanel = new JPanel();
        policyPanel.setBounds(10,275,385,255);
        policyPanel.setLayout(null);
        add(policyPanel);

        JLabel policyText = new JLabel("Required Policy:");// policy
        policyText.setBounds(10, 10, 100, 12);
        policyPanel.add(policyText);

        needLength = new JCheckBox("At least");//these are all allowed characters by the IBM Business Automation Workflow
        needLength.setBounds(10, 40, 62, 15);
        policyPanel.add(needLength);

        targetLength = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        targetLength.setBounds(73, 40, 35, 15);
        policyPanel.add(targetLength);

        JLabel lengthEndLabel = new JLabel("characters");
        lengthEndLabel.setBounds(110, 40, 180, 15);
        policyPanel.add(lengthEndLabel);

        needCapital = new JCheckBox("Must include a capital letter");//these are all allowed characters by the IBM Business Automation Workflow
        needCapital.setBounds(10, 70, 200, 15);
        policyPanel.add(needCapital);

        needNumber = new JCheckBox("Must include a number");//these are all allowed characters by the IBM Business Automation Workflow
        needNumber.setBounds(10, 100, 180, 15);
        policyPanel.add(needNumber);

        needSymbol = new JCheckBox("Must include a symbol");//these are all allowed characters by the IBM Business Automation Workflow
        needSymbol.setBounds(10, 130, 180, 15);
        policyPanel.add(needSymbol);

    }

    public void createWordlistPanel(){
        //TODO add wordlist handling and clean up the logic into functions, might need to redo a lot of it
        //TODO abilirty to view list (as a popup or just open the file?)
        JPanel wordlistPanel = new JPanel();
        wordlistPanel.setBounds(405,10,385,600);
        wordlistPanel.setLayout(null);
        add(wordlistPanel);
        JLabel wordListsText = new JLabel("Wordlists:");// word lists
        wordListsText.setBounds(10, 10, 100, 40);
        wordlistPanel.add(wordListsText);

        // getting list of wordlists
        wordBox = new JComboBox<String>();
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
        
        selectList = new JButton("Use this list");
        selectList.setMargin(new Insets(0, 0, 0, 0));
        selectList.setBounds(10, 70, 75, 20);
        wordlistPanel.add(selectList);
        selectList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectList.setEnabled(false);
                selectedWordlistString = wordlistToString((String)wordBox.getSelectedItem());
                selectedWordlistName = (String)wordBox.getSelectedItem();
            }
            
        });
        wordBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wordBox.getSelectedItem() != selectedWordlistName){
                    selectList.setEnabled(true);
                } else {
                    selectList.setEnabled(false);
                }
            }
        });

        //TODO get these to work
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
            public void actionPerformed(ActionEvent e) {//TODO redo list creation
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


        wordlistPanel.add(personalList);
    }

    private String wordlistToString(String filename){
        try {
            return String.join("",Files.readAllLines(FileSystems.getDefault().getPath(filename)));
        } catch (IOException e) {
            JDialog dialog = new JDialog(main_file.gui.getFrame(),"ERROR: File not found!",true);
            dialog.setSize(200,20);
            dialog.setLocationRelativeTo(main_file.gui.getFrame());
            dialog.setVisible(true);
        }
        return "";
    }

}
