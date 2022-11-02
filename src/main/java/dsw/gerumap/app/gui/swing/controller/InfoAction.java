package dsw.gerumap.app.gui.swing.controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


import static dsw.gerumap.app.gui.swing.view.MyMenuBar.CENTER_ALIGNMENT;


public class InfoAction extends AbstractGeRuMapAction{

    static JFrame f;
    InfoAction infoAction;
    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.jpg"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    public void actionPerformed(ActionEvent arg0) {
        JDialog d = new JDialog(f, "Info");
        JLabel l = new JLabel("Nikola Ivancev 53/21 RN; " +
                "Luka Vukadinovic 29/21 RN");
        l.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        d.add(l);
        d.setSize(350, 200);
        d.setVisible(true);
        d.setLocationRelativeTo(f);

    }
}
