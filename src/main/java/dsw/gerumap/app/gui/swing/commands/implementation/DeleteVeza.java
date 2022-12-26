package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;

import java.awt.event.MouseEvent;
import java.util.Iterator;

public class DeleteVeza extends AbstractCommand {

    private DevicePainter line;
    private MindMap map;
    private MouseEvent e;

    public DeleteVeza(MindMap map, DevicePainter line, MouseEvent event){
        this.map = map;
        this.line = line;
        this.e = event;
    }
    @Override
    public void undoCommand() {
        map.getModel().addVeza(line);
        //map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {
        Iterator<DevicePainter> it = map.getModel().getVezeIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();
            if (d.elementAt(e.getPoint())){
                it.remove();
                System.out.println("uklonio sam vezu");
                break;
            }

        }
        map.getModel().notifySubscribers(null);
        //System.out.println("treba da uklonim vezu");

    }

}
