package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;

public class AddElement extends AbstractCommand {


    private int size;
    private ElipseElement elipse;
    private MindMap map;
    int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();


    public AddElement (MindMap map, ElipseElement elipse){
        this.map = map;
        this.elipse = elipse;
        size = map.getModel().getMapElements().size();
    }

    @Override
    public void undoCommand() {
        if (MainFrame.getInstance().getActionManager().getRedoAction().isEnabled()
                && MainFrame.getInstance().getActionManager().getUndoAction().isEnabled()
                && MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().size() > 1){
            size = map.getModel().getMapElements().size() - 1;
        }
        map.getModel().getMapElements().remove(size--);
        map.getModel().notifySubscribers(null);

    }

    @Override
    public void redoCommand() {

        map.getModel().addDiagramElements(new ElipsePainter(elipse));

        if (MainFrame.getInstance().getActionManager().getRedoAction().isEnabled()){
            size++;
        }

    }

}
