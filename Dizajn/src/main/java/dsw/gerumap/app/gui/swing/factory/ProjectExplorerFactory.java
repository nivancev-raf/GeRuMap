package dsw.gerumap.app.gui.swing.factory;

import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;

public class ProjectExplorerFactory extends NodeFactory{

    @Override
    public MapNode createNode(String name,MapNode parent) {
        return new ProjectExplorer(name);
    }
}
