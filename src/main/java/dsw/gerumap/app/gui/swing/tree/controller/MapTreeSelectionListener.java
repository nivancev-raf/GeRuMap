package dsw.gerumap.app.gui.swing.tree.controller;


import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;


public class MapTreeSelectionListener implements TreeSelectionListener, Publisher {

    List<Subscriber> subs;

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        subs = new ArrayList<>();

        addSubscriber(MainFrame.getInstance().getProjectView());
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        if (treeItemSelected.getMapNode() instanceof Project) {
            notifySubscribers(treeItemSelected.getMapNode());
        }

        if (treeItemSelected.getMapNode() instanceof MindMap) {
            notifySubscribers(treeItemSelected.getMapNode().getParent());
        }
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        subs.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        subs.remove(sub);
    }


    @Override
    public void notifySubscribers(Object notification) {
        for (Subscriber sub : subs) {
            sub.update(notification);
        }
    }
}

