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
        initialise(parent);
    }

    public void initialise(MapTreeItem parent){

        for (MapNode child : ((MapNodeComposite) parent.getMapNode()).getChildren()){
            // problem : lose se prave tabovi i treba promeniti velicinu panela
            jPanel = new JPanel();
            jPanel.add(new Label("Pozor 1"));
            //this.setBounds(50, 50, 250, 200);
            this.add(child.getName(), jPanel);
        }

    }
}
