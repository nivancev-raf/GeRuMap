package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPane;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Getter
@Setter
public class ProjectView extends JPanel implements Subscriber{

    private StateManager stateManager = new StateManager();
    private Project project;

    private JLabel label1;
    private JLabel label2;
    private TabbedPane tabbedPane;



    public ProjectView(){
        initialise();

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

    public void startZoomState(){
        this.stateManager.setZoomState();
    }

    public void misKliknut(MouseEvent e, MindMap m){
        this.stateManager.getCurrent().misKliknut(e, m);
        // sa getCurrent uzimamo trenutno stanje
    }





    @Override
    public void update(Object notification) {
        //System.out.println("update izvrsen");
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
