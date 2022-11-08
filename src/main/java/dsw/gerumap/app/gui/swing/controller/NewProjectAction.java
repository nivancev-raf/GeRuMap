package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;



public class NewProjectAction extends AbstractGeRuMapAction {

    public NewProjectAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("/images/plus.png"));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        MainFrame.getInstance().getMapTree().addChild(selected);
    }
}
