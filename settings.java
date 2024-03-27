import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Date;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

public class Settings extends JPanel {
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

    public Settings(JTabbedPane tabs){

        setBackground(new Color(200,200,255));
        setLayout(new GridBagLayout());// let setBounds work

        tabs.insertTab("Settings", null, this, "Personalise your experience", 0);

        createCharacterListPanel();
        
        createPasswordPolicyPanel();

        createWordlistPanel();
    }

    
    public void createCharacterListPanel(){
        

        //TODO implement character lists
        JPanel characterPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 0, 0);
        c.ipadx = 10;
        c.ipady = 10;
        c.weightx = 1;
        c.weighty = 1;
        characterPanel.setLayout(new GridBagLayout());
        add(characterPanel,c);

        JLabel allowedCharactersText = new JLabel("Valid Characters:");// character lists
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        characterPanel.add(allowedCharactersText,c);

        canLowercase = new JCheckBox("Lower case letters: a-z");//these are all allowed characters by the IBM Business Automation Workflow
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        canLowercase.setSelected(true);
        characterPanel.add(canLowercase,c);

        canUppercase = new JCheckBox("Upper case letters: A-Z");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        canUppercase.setSelected(true);
        characterPanel.add(canUppercase,c);

        canNumber = new JCheckBox("Numbers: 0-9");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        canNumber.setSelected(true);
        characterPanel.add(canNumber,c);

        canSymbol = new JCheckBox("Symbols: ()-.?[]_`~;:!@#$%^&*+=");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        canSymbol.setSelected(true);
        characterPanel.add(canSymbol,c);
    }

    public void createPasswordPolicyPanel(){
        //TODO more password policy options?
        JPanel policyPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.ipady = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 0);
        policyPanel.setLayout(new GridBagLayout());
        add(policyPanel,c);

        JLabel policyText = new JLabel("Required Policy:");// policy
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        policyPanel.add(policyText,c);

        needLength = new JCheckBox("At least");//these are all allowed characters by the IBM Business Automation Workflow
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        policyPanel.add(needLength,c);

        targetLength = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        //c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        policyPanel.add(targetLength,c);

        JLabel lengthEndLabel = new JLabel("characters");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        //c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_END;
        policyPanel.add(lengthEndLabel,c);

        needCapital = new JCheckBox("Must include a capital letter");//these are all allowed characters by the IBM Business Automation Workflow
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        policyPanel.add(needCapital,c);

        needNumber = new JCheckBox("Must include a number");//these are all allowed characters by the IBM Business Automation Workflow
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        policyPanel.add(needNumber,c);

        needSymbol = new JCheckBox("Must include a symbol");//these are all allowed characters by the IBM Business Automation Workflow
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.LINE_START;
        policyPanel.add(needSymbol,c);

    }

    public void createWordlistPanel(){
        //TODO add wordlist handling and clean up the logic into functions, might need to redo a lot of it
        //TODO ability to view list (as a popup or just open the file?)
        JPanel wordlistPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 10;
        c.ipady = 10;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(10, 10, 10, 10);
        wordlistPanel.setLayout(new GridBagLayout());
        add(wordlistPanel,c);

        JLabel wordListsText = new JLabel("Wordlists:");// word lists
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        wordlistPanel.add(wordListsText,c);

        // getting list of wordlists
        wordBox = new JComboBox<String>();
        try {
            File wordFile = new File("WordLists");
            for (File i : wordFile.listFiles()) {
                wordBox.addItem(i.getName());
            }
        } catch (Exception e) {
            System.out.println("Hashcat folder not found");
        }
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        wordlistPanel.add(wordBox,c);
        
        selectList = new JButton("Select List");
        //selectList.setMargin(new Insets(0, 0, 0, 0));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.CENTER;
        wordlistPanel.add(selectList,c);
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
       

        JButton editList = new JButton("Edit List");

        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        editList.setToolTipText("<html>Edit selected list<html/>");

        wordlistPanel.add(editList,c);
        editList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "WordLists/"+selectedWordlistName);
                try {
                    pb.start();
                } catch (IOException e1) {

                }
            }
            
        });


        JButton createList = new JButton("Create new list");
        //uploadList.setMargin(new Insets(0, 0, 0, 0));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        createList.setToolTipText("<html>Create a new list.<html/>");

        wordlistPanel.add(createList,c);
        createList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String[] time =  new Date().toString().split(" ");
                time = time[3].split(":");
                String fileName = String.join("",time);
                File newFile = new File("WordLists/"+fileName+".txt");
                try {
                    newFile.createNewFile();
                    ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "WordLists/"+fileName);
                    pb.start();
                    //update the combobox
                    wordBox.removeAllItems();
                    try {
                        File wordFile = new File("WordLists");
                        for (File i : wordFile.listFiles()) {
                            wordBox.addItem(i.getName());
                        }
                    } catch (Exception e2) {
                        System.out.println("Hashcat folder not found");
                    }
                } catch (IOException e1) {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: Could not create file!",true);
                    dialog.setSize(200,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                    
                }
            }
            
        });

        JButton personalList = new JButton("Apply best 64");
        //personalList.setMargin(new Insets(0, 0, 0, 0));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(3, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
        personalList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //TODO where is this outputting / what is it doing. Main file has the code to print the output
                    ArrayList<String> command = new ArrayList<String>();
                    command.add("hashcat.exe");
                    command.add("WordLists/"+selectedWordlistName+".txt");
                    command.add("-r");
                    command.add("rules/best64.rule");
                    command.add("--stdout");
                    command.add("-o");
                    String path =  "CustomWordlists/"+selectedWordlistName+"64.txt";
                    command.add(path);
                    ProcessBuilder build = new ProcessBuilder(command);
                    build.directory(new File("hashcat"));
                    Process process = build.start();
                } catch (IOException e1) {
                    System.out.println("createPersonalList() failed!!!");
                    e1.printStackTrace();
                }
                wordBox.addItem("personalList.txt");
                

                
            }
        });
        personalList.setToolTipText("<html>Use hashcat to create a personalised wordlist <br> based on your publicly available data.<html/>");
        personalList.setBackground(new Color(255,179,71));
        wordlistPanel.add(personalList,c);
    }

    private String wordlistToString(String filename){
        try {
            return String.join("",Files.readAllLines(FileSystems.getDefault().getPath("WordLists/"+filename)));
        } catch (IOException e) {
            JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: File not found!",true);
            dialog.setSize(200,20);
            dialog.setLocationRelativeTo(Main.Gui.getFrame());
            dialog.setVisible(true);
        }
        return "";
    }


    public boolean canLowercase(){
        return canLowercase.isSelected();
    }
    public boolean canUppercase(){
        return canUppercase.isSelected();
    }
    public boolean canNumber(){
        return canNumber.isSelected();
    }
    public boolean canSymbol(){
        return canSymbol.isSelected();
    }

    public boolean needLength(){
        return needLength.isSelected();
    }
    public boolean needCapital(){
        return needCapital.isSelected();
    }
    public boolean needNumber(){
        return needNumber.isSelected();
    }
    public boolean needSymbol(){
        return needSymbol.isSelected();
    }

    public int targetLength(){
        return (int) targetLength.getValue();
    }

    public String getSelectedWordlistName(){
        return selectedWordlistName;
    }

    public String getSelectedWordlistString(){
        return selectedWordlistString;
    }
    

    
}
