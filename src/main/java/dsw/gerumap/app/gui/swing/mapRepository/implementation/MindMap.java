package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.elements.MindMapModel;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MindMap extends MapNodeComposite {
    private transient CommandManager commandManager;
    public MindMap(String name, MapNode parent) {
        super(name, parent);
        commandManager = new CommandManager();
    }
    @Override
    public MapTreeItem getMapTreeItem() {
        setMapTreeItem(new MapTreeItem(this));
        return super.getMapTreeItem();
    }

    private MindMapModel model = new MindMapModel();
    @Override
    public void addChild(MapNode child) {

    }

    @Override
    public void removeChild(MapNode child) {

    }

    @Override
    public void addSubscriber(Subscriber subscriber) {

    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification) {

    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
