package dsw.gerumap.app.gui.swing.controller;


import dsw.gerumap.app.gui.swing.state.controller.*;
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
    private AuthorAction authorAction;
    private BrisanjeAction brisanjeAction;
    private PojamAction pojamAction;
    private PomeranjeAction pomeranjeAction;
    private SelekcijaAction selekcijaAction;
    private VezaAction vezaAction;
    private ZoomAction zoomAction;

    public ActionManager(){

        initialiseActions();

    }


    private void initialiseActions(){

        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        infoAction = new InfoAction();
        deleteAction = new DeleteAction();
        editAction = new EditAction();
        authorAction = new AuthorAction();

        brisanjeAction = new BrisanjeAction();
        pojamAction = new PojamAction();
        pomeranjeAction = new PomeranjeAction();
        selekcijaAction = new SelekcijaAction();
        vezaAction = new VezaAction();
        zoomAction = new ZoomAction();

    }
}
