package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;



public class NewProjectAction extends AbstractGeRuMapAction implements Publisher {

    Subscriber mainFrame;
    public NewProjectAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("/images/Add.png"));
        putValue(NAME,"New Project");
        putValue(SHORT_DESCRIPTION,"New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
        }
        MainFrame.getInstance().getMapTree().addChild(selected);
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
