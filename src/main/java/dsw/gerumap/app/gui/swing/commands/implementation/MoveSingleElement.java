package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;

import java.awt.*;

public class MoveSingleElement extends AbstractCommand {

    private MindMap map;
    Point startPoint;
    Point endPoint;
    private DevicePainter elipsa;

    public MoveSingleElement(MindMap map, Point startPoint, Point endPoint, DevicePainter elipsa){
        this.map = map;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.elipsa = elipsa;
    }

    @Override
    public void undoCommand() {
        elipsa.getDiagramDevice().setPosition(startPoint);
        map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {

        elipsa.getDiagramDevice().setPosition(endPoint);
        map.getModel().notifySubscribers(null);
    }
}
