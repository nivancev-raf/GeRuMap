package dsw.gerumap.app.gui.swing.state.model;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.AddElement;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class PojamState extends State {

    ElipseElement elipse;
    ElipseElement prviElement;
    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        elipse = new ElipseElement(generatePoint(e.getPoint()), new Dimension(120, 50),new float[]{188, 246, 121}, 2.0F);
        Point p = new Point(MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getWidth()/2,MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getHeight()/2);
        prviElement = new ElipseElement(p,new Dimension(170,70),new float[]{121, 161, 246},2.0F);
        if(map.getModel().getMapElements().size()==0 && postavljanjeText(map)){
            //map.getModel().addDiagramElements(new ElipsePainter(prviElement));
            AbstractCommand komanda = new AddElement(map,prviElement);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda);
        } else if(map.getModel().getMapElements().size()!=0 && postavljanjeText(map)) {
            //map.getModel().addDiagramElements(new ElipsePainter(elipse));
            AbstractCommand komanda = new AddElement(map,elipse);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda);
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

        if(map.getModel().getMapElements().size()==0){
            prviElement.setName((String) result);
        } else {
            elipse.setName((String) result);
        }


        if(result == null || (result != null && ("".equals(result)))) {
            return false;
        } else if(((String) result).length()>14){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.OUT_OF_BOUNDS);
            return false;
        }
        return true;
    }

}
