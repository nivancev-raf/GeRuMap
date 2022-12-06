package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.gui.swing.controller.listeners.UpdateEvent;
import dsw.gerumap.app.gui.swing.controller.listeners.UpdateListener;
import lombok.Getter;
import lombok.Setter;

import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.Iterator;
@Setter
@Getter
public class MindMapModel { // klasa koja cuva sve elemente sa mape

    protected ArrayList<DiagramDevice> mapElements = new ArrayList();
    EventListenerList listenerList;
    UpdateEvent updateEvent;

    public MindMapModel(){
        this.listenerList = new EventListenerList();
        this.updateEvent = null;
    }
    public void addUpdateListener(UpdateListener l) {
        this.listenerList.add(UpdateListener.class, l);
    }
    public void addDiagramElements(DiagramDevice device) {
        device.setStrokeWidth(1.0F);
        this.mapElements.add(device);
        this.fireUpdatePerformed(); // pozivamo repaint
    }

    public int getElementIndex(){
        for (DiagramDevice e : mapElements){
            // ako je e selektovan mora da vrati njegov index
        }

        return 0;
    }


    public Iterator<DiagramDevice> getDeviceIterator() {
        return this.mapElements.iterator();
    }


    /**
     * Javljamo svim listenerima da se dogadjaj desio
     */
    public void fireUpdatePerformed() {
        Object[] listeners = this.listenerList.getListenerList();

        for(int i = listeners.length - 1; i >= 0; --i) {
            if (listeners[i] == UpdateListener.class) {
                if (this.updateEvent == null) {
                    this.updateEvent = new UpdateEvent(this);
                }

                ((UpdateListener)listeners[i + 1]).updatePerformed(this.updateEvent);
            }
        }

    }


}
