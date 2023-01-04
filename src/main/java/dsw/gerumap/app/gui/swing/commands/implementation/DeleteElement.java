package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;

import java.util.Iterator;

public class DeleteElement extends AbstractCommand {

    private DevicePainter elipse;
    private MindMap map;

    public DeleteElement(MindMap map, DevicePainter elipse){
        this.map = map;
        this.elipse = elipse;
    }

    @Override
    public void undoCommand() {

        map.getModel().addDiagramElements(elipse);
        map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {

        Iterator<DevicePainter> it = map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();
            if (d.equals(elipse)){
                it.remove();
                break;
            }

        }
        map.getModel().notifySubscribers(null);

    }

}
