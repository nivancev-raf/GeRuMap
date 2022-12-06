package dsw.gerumap.app.gui.swing.state.controller;

import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class VezaAction extends AbstractGeRuMapAction {

    public VezaAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Veza");
        putValue(SHORT_DESCRIPTION, "Veza");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startVezaState();
    }
}
