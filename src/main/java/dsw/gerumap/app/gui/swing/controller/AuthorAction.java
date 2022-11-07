package dsw.gerumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AuthorAction extends AbstractGeRuMapAction{

    public AuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/log-out.png"));
        putValue(NAME, "Add author");
        putValue(SHORT_DESCRIPTION, "Add author ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
