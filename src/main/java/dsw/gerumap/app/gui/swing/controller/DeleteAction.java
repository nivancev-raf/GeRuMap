package dsw.gerumap.app.gui.swing.controller;



import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractGeRuMapAction implements Publisher {
    Subscriber mainFrame;
    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete_4219.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
        }
        if (selected.getMapNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.REMOVE_PROJECT_EXPLORER);
            return;
        }


        MainFrame.getInstance().getMapTree().removeChild(selected);

    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        mainFrame = subscriber;
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        if (mainFrame == null) return;
        mainFrame.update(notification);
    }
}
