package dsw.gerumap.app.gui.swing.controller;


import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActionManager {

    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private InfoAction infoAction;


    public ActionManager(){

        initialiseActions();

    }


    private void initialiseActions(){

        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();

    }
}
