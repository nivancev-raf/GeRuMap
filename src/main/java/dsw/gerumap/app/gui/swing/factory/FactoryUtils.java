package dsw.gerumap.app.gui.swing.factory;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FactoryUtils {
    private static FactoryUtils instance;

    private static NodeFactory elementFactory;
    private static NodeFactory projectFactory;
    private static NodeFactory projectExplorerFactory;
    private static NodeFactory mindMapFactory;


    private FactoryUtils(){
        elementFactory = new ElementFactory();
        projectFactory = new ProjectFactory();
        projectExplorerFactory = new ProjectExplorerFactory();
        mindMapFactory = new MindMapFactory();
    }

    public static FactoryUtils getInstance(){
        if(instance ==null){
            return new FactoryUtils();
        }
        return instance;
    }



    public NodeFactory returnNodeFactory(MapNode mapNode){
        if(mapNode instanceof ProjectExplorer){
              return new ProjectFactory();
        } else if(mapNode instanceof Project){
            return new MindMapFactory();
        } else if(mapNode==null){
            return new ProjectExplorerFactory();
        }
        return elementFactory;
    }


}
