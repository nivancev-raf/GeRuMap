package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGeRuMapAction{

    static JFrame f;

    public AuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION, "Add author ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog(f, "Add Author for the project");
        JLabel l = new JLabel("Enter name: ");
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        JTextField textField = new JTextField(selected.getMapNode().getName());
        JPanel jPanel = new JPanel();
        jPanel.add(l);
        jPanel.add(textField);
        //float centerAlignment = jPanel.CENTER_ALIGNMENT;


        dialog.add(jPanel);
        dialog.setSize(350, 200);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(f);


    }
}
