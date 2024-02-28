import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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


public class rightPanel extends JPanel{
    private node newNode;
    private JRadioButton padNumber;
    private JRadioButton homoglyph;
    private JRadioButton capitalize;
    private JSpinner howMany;
    private JButton goMutate;
    private JButton goTest;
    private JProgressBar progressBar;
    private JTextArea resultBox;

    public rightPanel(String title,JPanel parent,node newNode){
        this.newNode = newNode;

        //TODO add all different mutation types
        parent.add(this);
        setVisible(false);
        setBounds(405, 10, 385, 600);
        setLayout(null);
        

        //adding titles to right panel
        JLabel currentNodeLabel = new JLabel(title); 
        currentNodeLabel.setBounds(10, 0, 300, 20);
        currentNodeLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(currentNodeLabel);

        //labels
        JLabel rightPanelMutateLabel = new JLabel("Create New Children:");
        rightPanelMutateLabel.setBounds(10, 15, 200, 40);
        add(rightPanelMutateLabel);

        JLabel rightPanelTestLabel = new JLabel("Test this Node:");
        rightPanelTestLabel.setBounds(10, 250, 200, 40);
        add(rightPanelTestLabel);

        JLabel rightPanelResultsLabel = new JLabel("Results & Issues:");
        rightPanelResultsLabel.setBounds(10, 350, 200, 40);
        add(rightPanelResultsLabel);

        goMutate = new JButton("Go (test)"); // setting up mutate button
        goMutate.setBackground(new Color(255,105,97));
        goMutate.setBounds(200, 200, 150, 20);
        goMutate.setToolTipText("This is a test button to create test children");
        add(goMutate);
        goMutate.addActionListener( new ActionListener(){// process for creating a new child

            @Override
            public void actionPerformed(ActionEvent e) {
                createChildNode(e);
            }});// this actionlistener creates children


        padNumber = new JRadioButton("Pad Numbers");
        padNumber.setBounds(10, 80, 150, 20);
        padNumber.setToolTipText("Create a child by padding the parent with numbers");
        padNumber.setSelected(true);
        add(padNumber);
        homoglyph = new JRadioButton("Homoglyph");
        homoglyph.setBounds(10, 110, 150, 20);
        homoglyph.setToolTipText("Create a child by exchanging characters with homoglyphs");
        add(homoglyph);

        capitalize = new JRadioButton("Capitalize");
        capitalize.setBounds(10, 140, 150, 20);
        capitalize.setToolTipText("Create a child by capitalizing random characters");
        add(capitalize);

        ButtonGroup mutateRadioGroup = new ButtonGroup();
        mutateRadioGroup.add(padNumber);
        mutateRadioGroup.add(homoglyph);
        mutateRadioGroup.add(capitalize);

        JLabel howManyLabel = new JLabel("How many?");
        howManyLabel.setBounds(200, 150, 200, 20);
        add(howManyLabel);

        
        howMany = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        howMany.setBounds(200, 170, 40, 20);
        add(howMany);
        
        progressBar = new JProgressBar(0,100);
        progressBar.setBounds(10, 340, 350, 20);
        add(progressBar);

        goTest = new JButton("Test");
        goTest.setBounds(10, 300, 80, 20);
        add(goTest);
        goTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (main_file.selected_node != null && main_file.gui.settings.getSelectedWordlistString() != null){
                    passwordCheck checker = new passwordCheck(main_file.selected_node,progressBar);
                    checker.execute();
                    goTest.setEnabled(false);
                } else {
                    JDialog dialog = new JDialog(main_file.gui.getFrame(),"ERROR: No wordlist selected!",true);
                    dialog.setSize(230,20);
                    dialog.setLocationRelativeTo(main_file.gui.getFrame());
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
        scroll.setBounds(10, 375, 350, 150);
        add(scroll);

        JButton clearButton = new JButton("Reset");
        clearButton.setMargin(new Insets(0,0,0,0));
        clearButton.setFont(clearButton.getFont().deriveFont(10f));
        clearButton.setBounds(310, 360, 35, 15);
        add(clearButton);
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
        if(main_file.selected_node != null){
            //newly created node stored here
            node newNode = new node(null, null, null, null, null);
            //we will need a randomizer
            Random random = new Random();

            
            int howManyNum = (int) howMany.getValue();
            //radio button is a child of parent
            if (padNumber.isSelected()){
                //pad numbers
                boolean isValid = main_file.gui.settings.canNumber();
                if (isValid == true){
                    String frontPad = "";
                    String backPad = "";
                    for (int i = 0 ; i < howManyNum ; i++){
                        frontPad = frontPad +(Integer.toString(random.nextInt(10)));
                        backPad = backPad +(Integer.toString(random.nextInt(10)));
                    }
                    newNode = main_file.selected_node.addChild(frontPad+main_file.selected_node.getWord()+backPad);
                } else {//dialogue box
                    JDialog dialog = new JDialog(main_file.gui.getFrame(),"POLICY: Your policy does not allow numbers!",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(main_file.gui.getFrame());
                    dialog.setVisible(true);
                }
            } else if (homoglyph.isSelected()){
                //homoglyph
            } else if (capitalize.isSelected()){
                //capitalize
            } else {
                //else
                JDialog dialog = new JDialog(main_file.gui.getFrame(),"ERROR: No mutation selected",true);
                    dialog.setSize(330,20);
                    dialog.setLocationRelativeTo(main_file.gui.getFrame());
                    dialog.setVisible(true);
            }

            if (newNode.getWord() != null){
                newNode.setRightPanel(new rightPanel(newNode.getWord(),(JPanel) main_file.gui.getTabs().getSelectedComponent(),newNode));
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

    public JButton getGoTest(){
        return this.goTest;
    }
}
