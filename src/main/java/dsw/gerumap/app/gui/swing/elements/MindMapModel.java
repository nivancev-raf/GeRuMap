package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import lombok.Getter;
import lombok.Setter;


import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
@Setter
@Getter
public class MindMapModel implements Publisher { // klasa koja cuva sve elemente sa mape

    protected ArrayList<DevicePainter> mapElements;
    protected ArrayList<DevicePainter> veze;
    protected ArrayList<Rectangle2D> rectangle;
    protected ArrayList<DevicePainter> selectedElements;
    protected ArrayList<Subscriber> subscribers;

    public MindMapModel(){
        mapElements = new ArrayList();
        veze = new ArrayList();
        rectangle = new ArrayList<>();
        selectedElements = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public void addSelectedElement(DevicePainter devicePainter){
        if (!selectedElements.contains(devicePainter)){
            this.selectedElements.add(devicePainter);
            this.notifySubscribers(null);
        }
    }

    public void addDiagramElements(DevicePainter device) {
        device.getDiagramDevice().setStroke(1.0F);
        this.mapElements.add(device);
        this.notifySubscribers(null); // pozivamo repaint
    }

    public void addRectangle(Rectangle2D rectangle2D){
        this.rectangle.add(rectangle2D);
        this.notifySubscribers(null);
    }

    public void addVeza(DevicePainter device){
        this.veze.add(device);
        this.notifySubscribers(null);
    }

    public Iterator<DevicePainter> getDeviceIterator() {
        return this.mapElements.iterator();
    }
    public Iterator<DevicePainter> getVezeIterator() {
        return this.veze.iterator();
    }
    public Iterator<Rectangle2D> getRectangleIterator() {
        return this.rectangle.iterator();
    }


    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        for (Subscriber subs : subscribers){
            subs.update(notification);
        }
    }
}
