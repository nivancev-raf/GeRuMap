package dsw.gerumap.app.gui.swing.controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;


import static dsw.gerumap.app.gui.swing.view.MyMenuBar.CENTER_ALIGNMENT;
import static java.awt.Component.BOTTOM_ALIGNMENT;
import static java.awt.Component.TOP_ALIGNMENT;


public class InfoAction extends AbstractGeRuMapAction{

    static JFrame f;
    InfoAction infoAction;
    private static final String IMAGE_URL = "/images/slika1.jpg";
    private static final String IMAGE = "/images/slika2.jpg";
    public InfoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info-icon.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    public void actionPerformed(ActionEvent arg0) {
        JDialog d = new JDialog(f, "Info");
        JLabel l = new JLabel("Nikola Ivancev 53/21 RN");
        JLabel l3 = new JLabel("Luka Vukadinovic 29/21 RN");
        JLabel l2 = null;
        JLabel l4 = null;
        try {
            l2 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(IMAGE_URL))));
            l4 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(IMAGE))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel panel = new JPanel(new GridLayout(2,2));

        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;
        
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(l,cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;

        panel.add(l2,cs);

        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(l3,cs);

        cs.gridx = 2;
        cs.gridy = 2;
        cs.gridwidth = 2;

        panel.add(l4,cs);

        d.add(panel);
        d.setSize(350, 200);
        d.setVisible(true);
        d.setLocationRelativeTo(f);

    }
}
