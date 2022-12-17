package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDragController extends MouseAdapter {

    private MindMap map;


    public MouseDragController(MindMap map){
        this.map = map;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        State state = MainFrame.getInstance().getProjectView().getStateManager().getCurrent();
        state.misPovucen(e, this.map);

    }


}
