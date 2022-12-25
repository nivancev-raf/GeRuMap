package dsw.gerumap.app.gui.swing.tree;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;


public interface MapTree {

    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent);
    void removeChild(MapTreeItem parent);
    void refreshTree();
    void deselect();
    MapTreeItem getSelectedNode();
    void loadProject(Project node);
    MapTreeView getTreeView();


}
