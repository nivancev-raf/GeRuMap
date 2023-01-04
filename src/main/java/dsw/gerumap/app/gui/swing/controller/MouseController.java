package dsw.gerumap.app.gui.swing.controller;


import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseController implements MouseListener {

    private MindMap map;
    public MouseController(MindMap map){
        this.map = map;

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            State current = MainFrame.getInstance().getProjectView().getStateManager().getCurrent();
            current.misKliknut(e, this.map);
        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {
        State current = MainFrame.getInstance().getProjectView().getStateManager().getCurrent();
        current.misOtpsuten(e, this.map);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
