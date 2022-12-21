package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.factory.NodeFactory;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPane;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectView extends JPanel implements Subscriber{

    private StateManager stateManager = new StateManager();
    private JLabel label1;
    private JLabel label2;
    private TabbedPane tabbedPane;


    public ProjectView(){
        initialise();
        // u konstruktoru view treba doci do metode addSubcriber u Projectu
//        List<MapNode> mapNodes = ApplicationFramework.getInstance().getMapRepository().getProjectExplorer().getChildren();
//        for (MapNode mapNode : mapNodes){
//            if (mapNode instanceof Project){
//                mapNode.addSubscriber(this);
//                break;
//            }
//        }
    }


    private void initialise(){

        BoxLayout box= new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(box);
        this.add(label1 = new JLabel()); // labela za otovreni projekat
        this.add(label2 = new JLabel()); // labela za autora
        this.tabbedPane = new TabbedPane();
        this.add(tabbedPane);

    }

    public void startPojamState(){
        this.stateManager.setPojamState();
    }
    public void startVezaState(){
        this.stateManager.setVezaState();
    }
    public void startBrisanjeState(){
        this.stateManager.setBrisanjeState();
    }
    public void startPomeranjeState(){
        this.stateManager.setPomeranjeState();
    }
    public void startSelekcijaState(){
        this.stateManager.setSelekcijaState();
    }
    public void startZoomInState(){
        this.stateManager.setZoomInState();
    }
    public void startZoomOutState(){
        this.stateManager.setZoomOutState();
    }



    @Override
    public void update(Object notification) {
        tabbedPane.getMapViewList().clear();
        if (notification == null){
            if (this.tabbedPane == null)
                return;
            tabbedPane.setTabs(new ArrayList<>());
            label1.setText("");
            label2.setText("");
            return;
        }

        Project p = (Project) notification;
            if (tabbedPane != null){
                tabbedPane.setTabs(p.getChildren());
            }
            label1.setText(p.getName());
            label2.setText(p.getAutor());
    }
}
