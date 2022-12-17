package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;




public class EditAction extends AbstractGeRuMapAction{


    private JDialog dialog;
    private JTextField textField;
    private JLabel labela;

    public EditAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
        }

        String name = selected.getMapNode().getName();
        dialog = new JDialog();
        dialog.setTitle("Edit");

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labela = new JLabel("Change project name:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(labela, cs);


        textField = new JTextField(20);
        textField.setText(name);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;

        panel.add(textField, cs);

        dialog.setSize(550, 130);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        // dugmici
        JButton renameButton = new JButton("Rename");
        JButton cancelButton = new JButton("Cancel");

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(renameButton, cs);

        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(cancelButton, cs);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        dialog.add(panel2);

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
                String renamedText = textField.getText();
                if (renamedText.isEmpty()){
                    ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.FIELD_CANNOT_BE_EMPTY);
                    return;
                }
                selected.getMapNode().setName(renamedText);
                MainFrame.getInstance().getMapTree().refreshTree();
                dialog.dispose();
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
}
