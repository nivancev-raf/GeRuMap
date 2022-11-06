package dsw.gerumap.app.gui.swing.tree;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;
    private JTextField textField;
    private JDialog dialog;
    private MapTreeItem selected;
    private JLabel labela;

    private static int i = 1;


    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem parent) { // parent je My Project Explorer

        if (!(parent.getMapNode() instanceof MapNodeComposite))
            return;

        MapNode child = createChild(parent.getMapNode());
        parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getMapNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public void removeChild(MapTreeItem child) { // ja prosledjujem selektovan root sto je My Project Explorer


        /*
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        MapNodeComposite parent2 = (MapNodeComposite) selected.getMapNode().getParent();
        parent2.removeChild(selected.getMapNode());
        treeView.updateUI();
        SwingUtilities.updateComponentTreeUI(treeView);
        */

        if (!(child.getMapNode() instanceof MapNodeComposite))
            return;
        MapNodeComposite parent = (MapNodeComposite) child.getMapNode().getParent();
        parent.removeChild((MapNode) child.getMapNode());

        treeModel.removeNodeFromParent(child);

    }

    @Override
    public void editChild(MapTreeItem child) {
        if (!(child.getMapNode() instanceof MapNodeComposite))
            return;
/*
        // treba napraviti da kada se klikne na dugme izadje novi dialog koji ima prazan jtextfield
        // gde unosimo izmenu imena childa, i kada kliknemo enter dialog se ugasi i ime childa se promeni


        // label se ne pokazuje, textfield nema border, tekst nije selektovan kad se otvori

        // dodati dugme ok i cancel, na ok se potvrdjuje (enter) a cancel mora da se klikne da se zatvori


        // kada kliknemo enter
        //if ()
*/
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        child = selected;
        String name = child.getMapNode().getName();
        dialog = new JDialog();
        dialog.setTitle("Edit");

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labela = new JLabel("Change project name:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(labela,cs);

        textField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;

        panel.add(textField,cs);



/*
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel("Edit node:  ");
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 10, 0,0 ));
        textField = new JTextField(name);
        //textfield.setBounds(10, 10, 100, 35);
        textField.setSize(50, 25);
        panel1.add(label);
        panel1.add(textField);
        panel1.setAlignmentX(CENTER_ALIGNMENT);
        //dialog.add(panel1);
    */
        dialog.setSize(550, 200); // 250 x 200
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);



        // dugmici
        JButton renameButton = new JButton("Rename");
        JButton cancelButton = new JButton("Cancel");

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(renameButton,cs);

        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(cancelButton,cs);
/*
        JPanel jp = new JPanel(new GridLayout(5,5,10,10));
        jp.add(renameButton);
        jp.add(cancelButton);
*/
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //jp.setAlignmentX(Component.BOTTOM_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        //panel2.add(jp);
        dialog.add(panel2);
        /*


        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel2.add(renameButton);
        panel2.add(cancelButton);

        panel.add(panel1);
        panel.add(panel2);
        dialog.add(panel);
*/
        dialog.setVisible(true);
        
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String renamedText = textField.getText();
                selected.getMapNode().setName(renamedText);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            return  new Project("Project" + i++, parent);
        return null;
    }
}
