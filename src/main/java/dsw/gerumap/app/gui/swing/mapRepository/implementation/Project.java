package dsw.gerumap.app.gui.swing.mapRepository.implementation;


import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true)

public class Project extends MapNodeComposite {
    //private String ime; polje sa imenom vec imamo u mapNode
    private String autor;
    private String filePath;
    protected boolean changed = true;

    //private List<Subscriber> subs;

    public Project(String name, MapNode parent) {
        super(name, parent);
        //subs = new ArrayList<>();
        //addSubscriber(MainFrame.getInstance().getProjectView());
    }


    public void delete(){
        notifySubscribers(null);
    }


    @Override
    public void addChild(MapNode child) {
        if(child!=null && child instanceof MindMap){
            MindMap mindMap = (MindMap) child;

            if(!this.getChildren().contains(mindMap)){
                this.getChildren().add(mindMap);
                child.setParent(this);
                notifySubscribers(this);
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
                notifySubscribers(this);
            }
        }
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        //subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        //subs.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        MainFrame.getInstance().getProjectView().update(notification);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        changed = true;
    }
}
