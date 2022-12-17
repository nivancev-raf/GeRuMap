package dsw.gerumap.app.gui.swing.state.model;

import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

public class SelekcijaState extends State {

    Point startPoint;
    int found = -1;
    Rectangle2D prostokat = new Rectangle2D.Double();
    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        startPoint = generatePoint(e.getPoint());
        prostokat.setFrameFromDiagonal(generatePoint(e.getPoint()).x, generatePoint(e.getPoint()).y,startPoint.x, startPoint.y);
        map.getModel().addRectangle(prostokat);
        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            DevicePainter device = map.getModel().getMapElements().get(i);
            if (map.getModel().getMapElements().get(i).getDiagramDevice().isSelected()){
                //deselect
                map.getModel().getMapElements().get(i).getDiagramDevice().setSelected(false);
            } else if(device.elementAt(generatePoint(e.getPoint()))){
                // select samo jedan
                device.getDiagramDevice().setSelected(true);
                map.getModel().notifySubscribers(null);
                break;
            }
        }


        if(map.getModel().getVeze().size()!=0){
            if (found >= 0){
                //deselect
                LinePainter line = (LinePainter) map.getModel().getVeze().get(found);
                map.getModel().getVeze().get(found).getDiagramDevice().setSelected(false);
                map.getModel().notifySubscribers(null);
                found = -1;
            }
            for(int i = 0; i<map.getModel().getVeze().size(); i++){
                LinePainter line = (LinePainter) map.getModel().getVeze().get(i);
                    if(line.elementAt(generatePoint(e.getPoint()))){
                        found = i;
                        map.getModel().getVeze().get(found).getDiagramDevice().setSelected(true);
                        line.setOldColor(line.getDiagramDevice().getPaint());
                        map.getModel().notifySubscribers(null);
                    }
                }
            }

        map.getModel().getSelectedElements().removeAll(map.getModel().getSelectedElements());
    }

    @Override
    public void misPovucen(MouseEvent e, MindMap map) {
        super.misPovucen(e, map);
        prostokat.setFrameFromDiagonal(generatePoint(e.getPoint()).x, generatePoint(e.getPoint()).y,startPoint.x, startPoint.y);
        map.getModel().addRectangle(prostokat);

        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            if (prostokat.contains(map.getModel().getMapElements().get(i).getDiagramDevice().getPosition())){
                map.getModel().getMapElements().get(i).getDiagramDevice().setSelected(true);
            }else {
                map.getModel().getMapElements().get(i).getDiagramDevice().setSelected(false);
            }
        }
        map.getModel().notifySubscribers(null);
    }

    @Override
    public void misOtpsuten(MouseEvent e, MindMap map) {
        super.misOtpsuten(e, map);
        this.clearSelection(map);

        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            if (map.getModel().getMapElements().get(i).getDiagramDevice().isSelected()){
                // dodajemo selektovane elemente
                map.getModel().addSelectedElement(map.getModel().getMapElements().get(i));
            }
        }
        map.getModel().notifySubscribers(null);
    }

    protected void clearSelection(MindMap map) {
        Iterator var2 = map.getModel().getRectangle().iterator();
        Rectangle2D element;
        while(var2.hasNext()) {
            element = (Rectangle2D) var2.next();
            var2.remove();
        }

    }
}
