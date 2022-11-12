package dsw.gerumap.app.gui.swing.mapRepository.composite;

import dsw.gerumap.app.core.observer.Publisher;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MapNode implements Publisher {
    private String name;
    private MapNode parent;

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof MapNode){
            MapNode otherobj = (MapNode) obj;
            return this.getName().equals(otherobj.getName());
        }
        return false;
    }
}
