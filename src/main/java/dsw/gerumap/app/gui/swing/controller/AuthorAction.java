package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGeRuMapAction{

    static JFrame f;
    private JDialog dialog;

    private JLabel labela;

    private JTextField textField;

    public AuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION, "Add author ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       /* JDialog dialog = new JDialog(f, "Add Author for the project");
        JLabel l = new JLabel("Enter name: ");
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        JTextField textField = new JTextField(selected.getMapNode().getName());
        JPanel jPanel = new JPanel();
        jPanel.add(l);
        jPanel.add(textField);

        dialog.add(jPanel);
        dialog.setSize(350, 200);
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(f);
*/
        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
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
                String authorName = textField.getText();
                MainFrame.getInstance().getProjectView().getLabel2().setText("Author name: " + authorName);

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
