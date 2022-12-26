package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;

public class AddElement extends AbstractCommand {


    private static int size;
    private ElipseElement elipse;
    private MindMap map;

    public AddElement (MindMap map, ElipseElement elipse){
        this.map = map;
        this.elipse = elipse;
        size = map.getModel().getMapElements().size();
    }

    @Override
    public void undoCommand() {
        map.getModel().getMapElements().remove(size--);
        map.getModel().notifySubscribers(null);
        System.out.println(map.getModel().getMapElements().size());
        //System.out.println("size u undo " + (size+1));
    }

    @Override
    public void redoCommand() {

        map.getModel().addDiagramElements(new ElipsePainter(elipse));

        if (MainFrame.getInstance().getActionManager().getRedoAction().isEnabled()){
            size++;
        }
        //System.out.println("size u redo " + (size + 1));
    }

}
