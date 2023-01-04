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
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private SettingsAction settingsAction;
    private OpenAction openAction;
    private SaveAsAction saveAsAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveAction saveAction;
    private ExportAction exportAction;
    private GalleryAction galleryAction;
    private AllocationAction allocationAction;
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
        zoomOutAction = new ZoomOutAction();
        zoomInAction = new ZoomInAction();
        settingsAction = new SettingsAction();
        openAction = new OpenAction();
        saveAsAction = new SaveAsAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveAction = new SaveAction();
        exportAction = new ExportAction();
        galleryAction = new GalleryAction();
        allocationAction = new AllocationAction();
    }
}
