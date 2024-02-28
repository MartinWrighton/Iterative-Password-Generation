
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
        c.gridx = 2;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
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

        ButtonGroup mutateRadioGroup = new ButtonGroup();
        mutateRadioGroup.add(padNumber);
        mutateRadioGroup.add(homoglyph);
        mutateRadioGroup.add(capitalize);

        JLabel howManyLabel = new JLabel("How many?");
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(howManyLabel,c);

        
        howMany = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 0.1;
        c.weightx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        add(howMany,c);
        
        progressBar = new JProgressBar(0,100);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 11;
        c.gridheight = 1;
        c.gridwidth = 2;
        c.weighty = 0.1;
        c.insets = new Insets(0, 10, 0, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        add(progressBar,c);

        goTest = new JButton("Test");
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
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 13;
        c.gridheight = 1;
        c.gridwidth = 2;
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
