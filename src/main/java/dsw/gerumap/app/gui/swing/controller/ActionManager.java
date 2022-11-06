package dsw.gerumap.app.gui.swing.controller;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActionManager {

    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private InfoAction infoAction;
    private DeleteAction deleteAction;
    private EditAction editAction;


    public ActionManager(){

        initialiseActions();

    }


    private void initialiseActions(){

        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();
        deleteAction = new DeleteAction();
        editAction = new EditAction();

    }
}
