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
        // na undo treba da dodajem te obrisane
        map.getModel().addDiagramElements(elipse);
        map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {
        // na redo treba da se brisu
        Iterator<DevicePainter> it = map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();
            if (d.equals(elipse)){
                it.remove();
                //System.out.println("uklonio sam ga");
                break;
            }

        }
        map.getModel().notifySubscribers(null);

    }

}
