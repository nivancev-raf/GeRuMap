package dsw.gerumap.app.gui.swing.tree.view;


import dsw.gerumap.app.gui.swing.tree.controller.MapTreeCellEditor;
import dsw.gerumap.app.gui.swing.tree.controller.MapTreeSelectionListener;
import dsw.gerumap.app.gui.swing.tree.controller.MouseTreeListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
@Getter
@Setter
public class MapTreeView extends JTree {


    public MapTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        MapTreeCellRenderer ruTreeCellRenderer = new MapTreeCellRenderer();
        addTreeSelectionListener(new MapTreeSelectionListener());
        setCellEditor(new MapTreeCellEditor(this, ruTreeCellRenderer));
        this.addMouseListener(new MouseTreeListener());
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }
}
