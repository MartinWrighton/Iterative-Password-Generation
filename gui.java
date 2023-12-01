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

    public gui() {
        this.frame = new JFrame(); // creating instance of JFrame
        this.tabs = new JTabbedPane();// tabbed pane
        this.panels = new ArrayList<JPanel>();
        this.trees = new ArrayList<JTree>();
        JPanel panel1 = new JPanel();// new page panel
        this.panels.add(panel1);
        JTextField panel1_text1 = new JTextField(20);

        // ACTION LISTENERS FOR BUTTONS
        ActionListener mutateChild = new ActionListener() {// process for creating a new child
            @Override
            public void actionPerformed(ActionEvent e) {//TODO learn how to add nodes to trees

            }

        };

        ActionListener createNewTab = new ActionListener() {// Process for creating a new tab
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel newpanel = new JPanel();
                panels.add(newpanel);
                tabs.addTab(panel1_text1.getText(), newpanel);
                // creating tree
                DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new node(panel1_text1.getText(), null));// configuring tree

                DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
                JTree tree = new JTree(treeModel);
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);// Tree selection listener

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
                newpanel.add(mutateButton);

            }
        };

        JButton panel1_button1 = new JButton("Create new File");
        panel1_button1.addActionListener(createNewTab);// this actionlistener creates new tabs
        panel1.add(panel1_button1);
        panel1.add(panel1_text1);
        this.tabs.addTab("New+", panel1);
        this.tabs.setBounds(0, 0, 800, 600);

        this.frame.add(tabs);
        this.frame.setSize(800, 600); // 400 width and 500 height
        this.frame.setLayout(null); // using no layout managers
        this.frame.setVisible(true); // making the frame visible
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
