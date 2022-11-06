package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;

public class Project  extends MapNodeComposite {
    private String ime;
    private String autor;
    private String putanja;
    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {

    }

    @Override
    public void removeChild(MapNode child) {

    }
}
