package dsw.gerumap.app.core;


import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;

public interface MapRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(MapNodeComposite parent, MapNode child);

}
