package dsw.gerumap.app.gui.swing.state.model;


import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class BrisanjeState extends State {

    private DevicePainter removedElement = null;
    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        Iterator<DevicePainter> it = map.getModel().getDeviceIterator();
            while(it.hasNext()){
                DevicePainter d = it.next();
                if(d.elementAt(generatePoint(e.getPoint()))){
                    removedElement = d;
                    it.remove();
                    if (map.getModel().getVeze() != null) {
                        Iterator<DevicePainter> it1 = map.getModel().getVezeIterator();
                        while (it1.hasNext()) {
                            LinePainter line = (LinePainter) it1.next();
                            if (line.getOdPojma() == removedElement.getDiagramDevice().getPosition()){
                                it1.remove();
                            }
                            if (line.getDoPojma() == removedElement.getDiagramDevice().getPosition()){
                                it1.remove();
                            }
                        }
                    }
                }
            }

            if (map.getModel().getVeze() != null){
                Iterator<DevicePainter> it2 = map.getModel().getVezeIterator();
                while (it2.hasNext()){
                    DevicePainter d = it2.next();
                    if (d.elementAt(generatePoint(e.getPoint()))){
                        it2.remove();
                    }
                }
            }

            map.getModel().notifySubscribers(null);
    }
}
