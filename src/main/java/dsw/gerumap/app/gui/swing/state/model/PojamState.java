package dsw.gerumap.app.gui.swing.state.model;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class PojamState extends State {

    ElipseElement elipse;

    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        elipse = new ElipseElement(generatePoint(e.getPoint()), new Dimension(120, 50),new Color(188, 246, 121), 2.0F);
        if(postavljanjeText(map)){
            map.getModel().addDiagramElements(new ElipsePainter(elipse));
        }
    }

    @Override
    public void misPovucen(MouseEvent e, MindMap map) {
        super.misPovucen(e, map);
        elipse.setPosition(generatePoint(e.getPoint()));
        map.getModel().notifySubscribers(null);
    }

    private boolean postavljanjeText(MindMap map){

        JFrame frame = new JFrame();
        Object result = JOptionPane.showInputDialog(frame, "Unesite text:");
        elipse.setName((String) result);

        if(result == null || (result != null && ("".equals(result)))) {
            return false;
        } else if(((String) result).length()>14){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.OUT_OF_BOUNDS);
            return false;
        }
        return true;
    }

}
