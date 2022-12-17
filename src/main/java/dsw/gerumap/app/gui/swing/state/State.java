package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class State {

    public void misKliknut(MouseEvent e, MindMap map) {}
    public void misOtpsuten(MouseEvent e, MindMap map){}
    public void misPovucen(MouseEvent e, MindMap map){}

    public static Point generatePoint(Point p){
        MapView model = MainFrame.getInstance().getProjectView().getTabbedPane().getMapView();
        return new Point((int) ((p.x + Math.abs(model.getTranslateX()))/model.getScaling()), (int) ((p.y + Math.abs(model.getTranslateY()))/model.getScaling()));
    }




}
