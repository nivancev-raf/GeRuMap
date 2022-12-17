package dsw.gerumap.app.gui.swing.state.model;

import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import java.awt.event.MouseEvent;


import static dsw.gerumap.app.gui.swing.view.MapView.scalingFactor;

public class ZoomInState extends State {
    double scaling;
    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        scaling = MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getScaling();
        scaling *= scalingFactor;
        if(scaling > 5)
            scaling = 5;

        MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().setScaling(scaling);


        map.getModel().notifySubscribers(null);
    }


}