package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.elements.MindMapModel;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MindMap extends MapNodeComposite {
    public MindMap(String name, MapNode parent) {
        super(name, parent);
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
}
