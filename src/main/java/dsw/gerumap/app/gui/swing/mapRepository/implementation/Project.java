package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Project  extends MapNodeComposite {
    private String ime;
    private String autor;
    private String putanja;
    public Project(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(MapNode child) {
        if(child!=null && child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            if(!this.getChildren().contains(mindMap)){
                this.getChildren().add(mindMap);
                child.setParent(this);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if (child != null &&  child instanceof MindMap){
            MindMap mindMap = (MindMap) child;
            if (this.getChildren().contains(mindMap)){
                this.getChildren().remove(mindMap);
                child.setParent(null);
            }
        }
    }
}
