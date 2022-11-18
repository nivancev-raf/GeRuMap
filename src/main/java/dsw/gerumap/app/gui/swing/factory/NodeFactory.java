package dsw.gerumap.app.gui.swing.factory;

import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;

import java.util.Random;

public abstract class NodeFactory  {

    public MapNode getNode(MapNode mapNode){

        MapNode n = createNode(mapNode.getName(),mapNode);
        n.setParent(mapNode.getParent());
        if (mapNode instanceof ProjectExplorer){
            n.setName("Project" + new Random().nextInt(100));
        }
        if (mapNode instanceof Project){
            n.setName("MindMap" + new Random().nextInt(100));
        }

        return n;
    }

   public abstract MapNode createNode(String name,MapNode mapNode);
}
