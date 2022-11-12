package dsw.gerumap.app.gui.swing.tabbedPane;

import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JTabbedPane{

    private JPanel jPanel;
    public TabbedPane(MapTreeItem parent) {
        initialiseTabbedView(parent);
    }

    public void initialiseTabbedView(MapTreeItem parent){


        for (MapNode child : ((MapNodeComposite) parent.getMapNode()).getChildren()){
            jPanel = new JPanel();
            this.setBorder(BorderFactory.createEmptyBorder(30,5,5,5));
            this.setBounds(150, 30, 500, 200);
            this.setSize(2000,2000);
            this.addTab(child.getName(), jPanel);
        }
    }
}
