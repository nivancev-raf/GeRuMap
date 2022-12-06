package dsw.gerumap.app.gui.swing.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import dsw.gerumap.app.gui.swing.elements.DiagramElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.logger.EventType;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;


public class PomeranjeState extends State {


    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        this.clearSelection(map);
        for (DiagramDevice element : map.getModel().getMapElements()){
            if (element.getPainter().elementAt(e.getPoint())){ // da li je na tom elementu mis
                element.setSelected(true); // element je selektovan
                //System.out.println("element je selektovan");
                map.getModel().fireUpdatePerformed();
                //element.getPainter().
                /*
                Point pos = new Point();
                pos.x = (int) (element.getPosition().getX() - 5);
                pos.y = (int) (element.getPosition().getY() - 5);
                rectangle = new SelectedElement(pos, new Dimension(110, 60), new Color(152,251,152, 50), 3.0F);
                map.getModel().fireUpdatePerformed();
                */
            }else{
                element.setSelected(false);
            }
        }
    }

    @Override
    public void misPovucen(MouseEvent e, MindMap map) {
        super.misPovucen(e, map);
        // umesto get(0) mora selektovan item
        //if(e.getPoint().)
        //map.getModel().getMapElements().get(0).setPosition(e.getPoint());
        Iterator<DiagramDevice> it = map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DiagramDevice d = (DiagramDevice) it.next();
            if (d.isSelected()){
                d.setPosition(e.getPoint());
            }

            /*

            if (!d.isSelected()){
                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED_STATE);
                return;
            }
        */
        map.getModel().fireUpdatePerformed(); // repaint
        }

        // error logger -> NIJE SELECTOVAN ITEM


    }


    protected void clearSelection(MindMap map) {
        Iterator var2 = map.getModel().getMapElements().iterator();

        DiagramElement element;
        while(var2.hasNext()) {
            element = (DiagramElement)var2.next();
            ((DiagramDevice)element).setSelected(false);
        }

    }

}
