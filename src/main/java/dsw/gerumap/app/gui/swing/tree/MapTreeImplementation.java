package dsw.gerumap.app.gui.swing.tree;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Element;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
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
    private static int k = 1;


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

        //tekst nije selektovan kad se otvori
        textField = new JTextField(20);
        textField.setText(name);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;

        panel.add(textField,cs);

        dialog.setSize(550, 130);
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

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        dialog.add(panel2);

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
                String renamedText = getTextField().getText();
                selected.getMapNode().setName(renamedText);
                treeModel.reload();
                getDialog().dispose();
                //na enter se potvrdjuje rename
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });


        dialog.setVisible(true);
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent) {
        if (parent instanceof ProjectExplorer)
            return  new Project("Project" + i++, parent);
        if (parent instanceof Project) {
            return new MindMap("MindMap" + k++, parent);
        }
        return null;
    }

}
