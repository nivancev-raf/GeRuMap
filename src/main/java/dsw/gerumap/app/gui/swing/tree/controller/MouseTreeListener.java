package dsw.gerumap.app.gui.swing.tree.controller;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPane;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MouseTreeListener implements MouseListener {
    private static int i = 0;
    @Override
    public void mouseClicked(MouseEvent e) {

        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
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
