package dsw.gerumap.app.gui.swing.mapRepository;


import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.factory.FactoryUtils;
import dsw.gerumap.app.gui.swing.factory.NodeFactory;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;

public class MapRepositoryImplementation implements MapRepository {
    private ProjectExplorer projectExplorer;
    private CommandManager commandManager;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
        commandManager = new CommandManager();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {

    }

    @Override
    public NodeFactory getInstance(MapNode mapNode) {
        return FactoryUtils.getInstance().returnNodeFactory(mapNode);
    }



}
