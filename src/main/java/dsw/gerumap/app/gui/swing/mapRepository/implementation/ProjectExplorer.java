package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;


public class ProjectExplorer extends MapNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(MapNode child){
        if(child!=null && child instanceof Project){
            Project project = (Project) child;
            if(!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(MapNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (this.getChildren().contains(project)){
                this.getChildren().remove(project);
            }
        }
    }
}
