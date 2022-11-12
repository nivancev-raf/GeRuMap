package dsw.gerumap.app.gui.swing.tree.controller;

import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPane;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseTreeListener implements MouseListener {
    private static int i = 0;
    @Override
    public void mouseClicked(MouseEvent e) {

        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected.getMapNode() instanceof Project) {
            if (e.getClickCount() == 2) {


                if (i != 0) MainFrame.getInstance().getProjectView().remove(2); // problem je sto je na pocetku null
                i++;

                MainFrame.getInstance().getProjectView().getLabel1().setText((selected.getMapNode().getName()));
                MainFrame.getInstance().getProjectView().add(new TabbedPane(selected));
                MainFrame.getInstance().getProjectView().updateUI();
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
