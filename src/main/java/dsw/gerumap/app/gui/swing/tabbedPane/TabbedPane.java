package dsw.gerumap.app.gui.swing.tabbedPane;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter


public class TabbedPane extends JTabbedPane {

    private MapView mapView;
    private List<MapView> mapViewList;

    public TabbedPane() {
        mapViewList = new ArrayList<>();
    }
    public void setTabs(List<MapNode> nodes){
        removeAll();

        for (MapNode node : nodes){
            mapView = new MapView((MindMap) node);
            mapViewList.add(mapView);
            addTab(node.getName(), mapView);

        }

        MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();
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
