package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;

import javax.swing.*;
import java.util.List;


public class TabbedPane extends JTabbedPane {

    private JPanel jPanel;

    public TabbedPane() {
        //initialiseTabbedView(parent);
    }

    public void initialiseTabbedView(MapTreeItem parent){

        removeAll();
        for (MapNode child : ((MapNodeComposite) parent.getMapNode()).getChildren()){
            jPanel = new JPanel();
            this.setBorder(BorderFactory.createEmptyBorder(30,5,5,5));
            this.setBounds(150, 30, 500, 200);
            this.setSize(2000,2000);
            this.addTab(child.getName(), jPanel);
        }
    }

    public void setTabs(List<MapNode> nodes){
        removeAll();
        int k = 0;
        for (MapNode node : nodes){
            //addTab(node.getName(), new JPanel());
            addTab(node.getName(), new MapView((MindMap) node));

        }
        MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
        if (selected != null && selected.getMapNode() instanceof MindMap) {
            for (int i = 0; i < getTabCount(); i++) {
                if (getTitleAt(i).equals(selected.getMapNode().getName())) {
                    setSelectedIndex(i);
                    return;
                }
            }
        }
    }


}
