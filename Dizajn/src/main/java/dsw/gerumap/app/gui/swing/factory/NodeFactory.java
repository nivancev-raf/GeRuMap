package dsw.gerumap.app.gui.swing.factory;

import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;

public abstract class NodeFactory {

    public MapNode getNode(MapNode mapNode){

        MapNode n = createNode(mapNode.getName(),mapNode.getParent());
        n.setParent(mapNode.getParent());
        n.setName(mapNode.getName());
        return n;
    }

   public abstract MapNode createNode(String name,MapNode mapNode);
}
