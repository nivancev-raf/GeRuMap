package dsw.gerumap.app.gui.swing.tree;



import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeModel;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.rmi.MarshalledObject;


@Getter
@Setter

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private MapTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new MapTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;

    }

    @Override
    public void addChild(MapTreeItem parent) {

        if (parent.getMapNode() instanceof ProjectExplorer) {
            Project child = (Project)createChild(parent.getMapNode());
            parent.add(new MapTreeItem(child));
            ((MapNodeComposite) parent.getMapNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }

        if (parent.getMapNode() instanceof Project) {
            ProjectExplorer pe = (ProjectExplorer) parent.getMapNode().getParent();
            if (pe == null) return;
            MindMap child = (MindMap) createChild(parent.getMapNode());
            parent.add(new MapTreeItem(child));
            ((MapNodeComposite) parent.getMapNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        if(parent.getMapNode() instanceof MindMap){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ADDING_ELEMENT);
        }
    }

    @Override
    public void removeChild(MapTreeItem parent) {
        if (parent.getMapNode() instanceof Project) {
            parent.removeFromParent();
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            ((ProjectExplorer) parent.getMapNode().getParent()).removeChild(parent.getMapNode());
            MainFrame.getInstance().getMapTree().deselect();
            ((Project) parent.getMapNode()).delete();
        }

        if (parent.getMapNode() instanceof MindMap) {
            MainFrame.getInstance().getMapTree().deselect();
            parent.removeFromParent();
            ((MapNodeComposite) parent.getMapNode().getParent()).removeChild(parent.getMapNode());
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
    }

    @Override
    public void refreshTree() {
        treeModel.reload();
    }

    @Override
    public void deselect() {
        treeView.clearSelection();
    }

    @Override
    public MapTreeItem getSelectedNode() {
        if (treeView.getLastSelectedPathComponent() == null){
            treeView.setSelectionPath(new TreePath(treeModel.getRoot().getPath()));
            return (MapTreeItem) treeView.getLastSelectedPathComponent();
        }
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public void loadProject(Project node) {
        // dodajemo project na root
        MapTreeItem loadedProject = new MapTreeItem(node);
        treeModel.getRoot().add(loadedProject);
        MapNodeComposite mapNode = (MapNodeComposite) treeModel.getRoot().getMapNode();
        mapNode.addChild(node);
        // posle ovoga treba paint
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

        // dodajemo mindmap na project
        for (int i = 0; i < node.getChildren().size(); i++){
            loadedProject.add(node.getChildren().get(i).getMapTreeItem());
            //((MindMap)node.getChildren().get(i)).getModel().setReady(true);
        }
            treeView.setSelectionPath(new TreePath(loadedProject.getPath()));
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);

    }


    private MapNode createChild(MapNode parent) {
        return ApplicationFramework.getInstance().getMapRepository().getInstance(parent).getNode(parent);
    }

}
