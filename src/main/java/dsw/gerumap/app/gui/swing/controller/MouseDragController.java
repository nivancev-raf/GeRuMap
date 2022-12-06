package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragController extends MouseAdapter {

    private MindMap map;
    int selected = -1;


    public MouseDragController(MindMap map){
        this.map = map;
    }
/*
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Point position = e.getPoint();
        // u mouse clicked treba staviti da je selectovan true tako sto ce klik da bude na povrsini shape
    }
*/
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        State state = MainFrame.getInstance().getProjectView().getStateManager().getCurrent();
        state.misPovucen(e, this.map);
        // u misPovucen pitamo da li je selektovan

    }


}
