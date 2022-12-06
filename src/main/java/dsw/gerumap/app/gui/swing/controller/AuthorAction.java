package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;
import dsw.gerumap.app.logger.MessageGeneratorImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGeRuMapAction{

    private JDialog dialog;

    private JLabel labela;

    private JTextField textField;
    private EventType type;
    private MessageGeneratorImplementation msg;

    public AuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION, "Add author ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
        }
        if (selected.getMapNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ADD_AUTHOR_ERROR);
            return;
        }
        String name = selected.getMapNode().getName();
        dialog = new JDialog();
        dialog.setTitle("Add author");

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labela = new JLabel("Add author name of project:");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(labela,cs);


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
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(okButton,cs);

        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(cancelButton,cs);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        dialog.add(panel2);

        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
                String authorName = textField.getText();
                if (selected.getMapNode() instanceof Project){
                    ((Project) selected.getMapNode()).setAutor(authorName);
                }

                if(selected.getMapNode() instanceof MindMap){
                    ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ADD_AUTHOR_ERROR);
                    return;
                }

                if (authorName.isEmpty()){
                    ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.FIELD_CANNOT_BE_EMPTY);
                    return;
                }



                MainFrame.getInstance().getProjectView().getLabel2().setText(((Project) selected.getMapNode()).getAutor());



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
