package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.mapRepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.ProjectExplorer;
import dsw.gerumap.app.gui.swing.tabbedPane.TabbedPane;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;

@Getter
@Setter
public class ProjectView extends JPanel implements Subscriber{

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

    }

    public void initialiseTabbedPane(TabbedPane tabbedPane){
        this.tabbedPane = tabbedPane;
        this.add(tabbedPane);
    }


    @Override
    public void update(Object notification) {

        if (notification == null){
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



    }
}
