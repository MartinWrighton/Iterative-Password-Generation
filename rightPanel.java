
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;


public class RightPanel extends JPanel{
    private Node newNode;

    private JRadioButton padNumber;
    private JRadioButton homoglyph;
    private JRadioButton capitalize;
    private JRadioButton reverse;
    private JRadioButton insert;
    private JRadioButton padSymbol;

    private JSpinner howMany;
    private JButton goMutate;
    private JButton goTest;
    private JButton goSubstring;

    private JProgressBar progressBar;
    private JTextArea resultBox;
    
    public RightPanel(String title,JPanel parent,Node newNode){
        this.newNode = newNode;
        //TODO add option to edit manually
        parent.add(this);
        setVisible(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx =0.66;
        c.weighty = 0.1;
        parent.add(this,c);
        setLayout(new GridBagLayout());
        

        //adding titles to right panel
        JLabel currentNodeLabel = new JLabel(title); 
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weightx = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        currentNodeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(currentNodeLabel,c);

        //labels
        JLabel rightPanelMutateLabel = new JLabel("Create New Children:");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        add(rightPanelMutateLabel,c);

        JLabel rightPanelTestLabel = new JLabel("Test this Node:");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 7;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        add(rightPanelTestLabel,c);

        JLabel rightPanelResultsLabel = new JLabel("Results & Issues:");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 12;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        add(rightPanelResultsLabel,c);

        goMutate = new JButton("Go"); // setting up mutate button
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        goMutate.setToolTipText("This is a test button to create test children");
        add(goMutate,c);
        goMutate.addActionListener( new ActionListener(){// process for creating a new child

            @Override
            public void actionPerformed(ActionEvent e) {
                createChildNode(e);
            }});// this actionlistener creates children


        padNumber = new JRadioButton("Pad Numbers");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        padNumber.setToolTipText("Create a child by padding the parent with numbers");
        padNumber.setSelected(true);
        add(padNumber,c);

        homoglyph = new JRadioButton("Homoglyph");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        homoglyph.setToolTipText("Create a child by exchanging characters with homoglyphs");
        add(homoglyph,c);

        capitalize = new JRadioButton("Capitalize");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        capitalize.setToolTipText("Create a child by capitalizing random characters");
        add(capitalize,c);

        reverse = new JRadioButton("Reverse");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        capitalize.setToolTipText("Create a child by reversing the password");
        add(reverse,c);

        insert = new JRadioButton("Random Insert");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        capitalize.setToolTipText("Create a child by inserting random characters");
        add(insert,c);

        padSymbol = new JRadioButton("Pad Symbols");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        capitalize.setToolTipText("Create a child by padding the parent with symbols");
        add(padSymbol,c);

        

        ButtonGroup mutateRadioGroup = new ButtonGroup();
        mutateRadioGroup.add(padNumber);
        mutateRadioGroup.add(homoglyph);
        mutateRadioGroup.add(capitalize);
        mutateRadioGroup.add(reverse);
        mutateRadioGroup.add(insert);
        mutateRadioGroup.add(padSymbol);

        JLabel howManyLabel = new JLabel("How many?");
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(howManyLabel,c);

        
        howMany = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.CENTER;
        add(howMany,c);
        
        progressBar = new JProgressBar(0,100);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 11;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.weighty = 0.1;
        c.insets = new Insets(0, 10, 0, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        add(progressBar,c);

        goTest = new JButton("Test Approximate");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 10;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        add(goTest,c);
        goTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (newNode != null && Main.Gui.Settings.getSelectedWordlistString() != null){
                    ApproximateCheck checker = new ApproximateCheck(Main.selected_node,progressBar);
                    checker.execute();
                    for (JButton i : getButtons()){
                        i.setEnabled(false);
                    }
                } else {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: No wordlist selected!",true);
                    dialog.setSize(230,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }
            }});

            goSubstring = new JButton("Test Substrings");
            c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 10;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.weighty = 0.1;
            c.anchor = GridBagConstraints.LINE_START;
            add(goSubstring,c);
            goSubstring.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    if (Main.selected_node != null && Main.Gui.Settings.getSelectedWordlistString() != null){
                        SubstringCheck checker = new SubstringCheck(Main.selected_node,progressBar);
                        checker.execute();
                        for (JButton i : getButtons()){
                            i.setEnabled(false);
                        }
                    } else {
                        JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: No wordlist selected!",true);
                        dialog.setSize(230,20);
                        dialog.setLocationRelativeTo(Main.Gui.getFrame());
                        dialog.setVisible(true);
                    }
                }});
        
        resultBox = new JTextArea();
        resultBox.setFont(resultBox.getFont().deriveFont(11f));
        resultBox.setEditable(false);
        for (String i : newNode.getIssues()){
            resultBox.append(i);
        }

        JScrollPane scroll = new JScrollPane(resultBox);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 13;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.weighty = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        add(scroll,c);

        JButton clearButton = new JButton("Reset");
        clearButton.setMargin(new Insets(0,0,0,0));
        clearButton.setFont(clearButton.getFont().deriveFont(10f));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 12;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.anchor = GridBagConstraints.LINE_END;
        add(clearButton,c);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newNode.setIssues(new ArrayList<String>());
                newNode.policyCheck();
                updateIssues();
            }
            
        });
        
    }

    private void createChildNode(ActionEvent e){
        if(Main.selected_node != null){
            //newly created Node stored here
            Node newNode = new Node(null, null, null, null, null);
            //we will need a randomizer
            Random random = new Random();

            
            int howManyNum = (int) howMany.getValue();
            //radio button is a child of parent
            if (padNumber.isSelected()){
                //pad numbers
                if (Main.Gui.Settings.canNumber()){
                    String frontPad = "";
                    String backPad = "";
                    for (int i = 0 ; i < howManyNum ; i++){
                        frontPad = frontPad +(Integer.toString(random.nextInt(10)));
                        backPad = backPad +(Integer.toString(random.nextInt(10)));
                    }
                    newNode = this.newNode.addChild(frontPad+Main.selected_node.getWord()+backPad);
                } else {//dialogue box
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"POLICY: Your policy does not allow numbers!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }
            } else if (homoglyph.isSelected()){
                //homoglyph
                Dictionary<String,String> glyphs = new Hashtable<>();
                if (Main.Gui.Settings.canNumber()) {
                    glyphs.put("i", "1");
                    glyphs.put("t", "7");
                    glyphs.put("E", "3");
                    glyphs.put("G","6");
                    glyphs.put("I", "1");
                    glyphs.put("O", "0");
                    glyphs.put("S","5");
                    glyphs.put("T","7");
                }
                if (Main.Gui.Settings.canSymbol()){
                    glyphs.put("a","@");
                    glyphs.put("J", "!");
                    glyphs.put("l","[");
                    glyphs.put("m","n");
                    glyphs.put("n","m" );
                    glyphs.put("p","q");
                    glyphs.put("q", "p");
                } 
                
                String[] charList = this.newNode.getWord().split("");
                int pass = 0;
                int fail = 0;
                
                while (pass < howManyNum && fail < this.newNode.getWord().length()*20){
                    int randIndex = random.nextInt(this.newNode.getWord().length());
                    if (glyphs.get(charList[randIndex]) != null){
                        charList[randIndex] = glyphs.get(charList[randIndex]);
                        pass += 1;
                    } else {
                        fail += 1;
                    }
                }
                if (glyphs.isEmpty()) {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"POLICY: Your policy does not allow numbers or symbols!",true);
                    dialog.setSize(400,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }
                if (pass >= 1){
                    newNode = this.newNode.addChild(String.join("",charList));
                } else {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: Not enough homoglyphs found",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }

            } else if (capitalize.isSelected()){
                //capitalize
                //find a letter and replace it with the capital version
                if (Main.Gui.Settings.canUppercase()){
                    String[] charList = this.newNode.getWord().split("");
                    int pass = 0;
                    int fail = 0;
                    while (pass < howManyNum && fail < this.newNode.getWord().length()*10){
                        int randIndex = random.nextInt(this.newNode.getWord().length());
                        if (Character.isLowerCase(charList[randIndex].charAt(0))){
                            charList[randIndex] = charList[randIndex].toUpperCase();
                            pass += 1;
                        } else {
                            fail +=1;
                        }
                    }
                    newNode = this.newNode.addChild(String.join("",charList));
                } else {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"POLICY: Your policy does not allow uppercase!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }

            } else if (padSymbol.isSelected()) {
                //pad symbol
                //pad numbers
                if (Main.Gui.Settings.canSymbol()){
                    String frontPad = "";
                    String backPad = "";
                    String[] symbols = "()-.?[]_`~;:!@#$%^&*+=".split("");
                    for (int i = 0 ; i < howManyNum ; i++){
                        frontPad = frontPad +(symbols[random.nextInt(symbols.length)]);
                        backPad = backPad +(symbols[random.nextInt(symbols.length)]);
                    }
                    newNode = this.newNode.addChild(frontPad+Main.selected_node.getWord()+backPad);
                } else {//dialogue box
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"POLICY: Your policy does not allow symbols!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }
            } else if (insert.isSelected()) {
                //insert
                String characterString = "";
                if (Main.Gui.Settings.canLowercase()){
                    characterString = characterString+"abcdefghijklmnopqrstuvwxyz";
                }
                if (Main.Gui.Settings.canUppercase()){
                    characterString = characterString+"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                }
                if (Main.Gui.Settings.canNumber()){
                    characterString = characterString+"1234567890";
                }
                if (Main.Gui.Settings.canSymbol()){
                    characterString = characterString+"()-.?[]_`~;:!@#$%^&*+=";
                }
                if (!characterString.equals("")){
                    String[] characters = characterString.split("");
                    ArrayList<String> charlist =  new ArrayList<String>(Arrays.asList(this.newNode.getWord().split("")));
                    for (int i = 0 ; i < howManyNum ; i++){
                        charlist.add(random.nextInt(this.newNode.getWord().length()),characters[random.nextInt(characters.length)]);
                    }
                    newNode = this.newNode.addChild(String.join("",charlist));
                } else {
                    JDialog dialog = new JDialog(Main.Gui.getFrame(),"POLICY: Your policy does not allow any characters!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
                }

            }else if (reverse.isSelected()){
                //reverse
                String[] charList = this.newNode.getWord().split("");
                String newpass = "";
                for (String i : charList){
                    newpass = i+newpass;
                }
                newNode = this.newNode.addChild(newpass);
            
            
            
            
            } else {
                //else
                JDialog dialog = new JDialog(Main.Gui.getFrame(),"ERROR: No mutation selected",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(Main.Gui.getFrame());
                    dialog.setVisible(true);
            }

            if (newNode.getWord() != null){
                newNode.setRightPanel(new RightPanel(newNode.getWord(),(JPanel) Main.Gui.getTabs().getSelectedComponent(),newNode));
            }
        }
    }

    public void updateIssues(){
        JTextArea area = resultBox;
        area.setText("");
        for (String i : newNode.getIssues()){
            area.append(i);
        }
    }

    public JButton[] getButtons(){
        JButton[] buttonList = {goTest,goSubstring};
        return buttonList;
    }
}
