package dsw.gerumap.app.gui.swing.tree;


import dsw.gerumap.app.AppCore;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.gui.swing.factory.NodeFactory;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Element;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.ConsoleLogger;
import dsw.gerumap.app.logger.EventType;
import dsw.gerumap.app.logger.MessageGeneratorImplementation;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class MapTreeImplementation implements MapTree {

    private MapTreeView treeView;
    private DefaultTreeModel treeModel;
    private JTextField textField;
    private JDialog dialog;
    private MapTreeItem selected;
    private JLabel labela;
    private MessageGeneratorImplementation msg;
    private EventType eventType;
    private ConsoleLogger cl;

    private static int i = 1;
    private static int k = 1;


    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        return treeView;

    }

    @Override
    public void addChild(MapTreeItem parent) {

        if (parent.getMapNode() instanceof ProjectExplorer) {
            MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
            Project child = (Project)createChild(parent.getMapNode());
            parent.add(new MapTreeItem(child));
            ((MapNodeComposite) parent.getMapNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }

        if (parent.getMapNode() instanceof Project) {
            ProjectExplorer pe = (ProjectExplorer) parent.getMapNode().getParent();
            if (pe == null) return;
            MapTreeItem selected = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
            MindMap child = (MindMap) createChild(parent.getMapNode());
            parent.add(new MapTreeItem(child));
            ((MapNodeComposite) parent.getMapNode()).addChild(child);
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
        }
        if(parent.getMapNode() instanceof MindMap){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ADDING_ELEMENT);
            return;
        }
    }

    @Override
    public void removeChild(MapTreeItem parent) {


        if (parent.getMapNode() instanceof Project) {
            parent.removeFromParent();
            treeView.expandPath(treeView.getSelectionPath());
            SwingUtilities.updateComponentTreeUI(treeView);
            ((ProjectExplorer) parent.getMapNode().getParent()).removeChild((Project) parent.getMapNode());
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
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }

    private MapNode createChild(MapNode parent) {
        return ApplicationFramework.getInstance().getMapRepository().getInstance(parent).getNode(parent);

    }
}
