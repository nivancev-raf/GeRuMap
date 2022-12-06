package dsw.gerumap.app.gui.swing.state.model;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PojamState extends State {

    ElipseElement elipse;

    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        int elementNumber = map.getModel().getMapElements().size();
        String name = "Test " + elementNumber;
        elipse = new ElipseElement(e.getPoint(), new Dimension(100, 50),new Color(194, 246, 121), 2.0F);
        elipse.setName(name);

        map.getModel().addDiagramElements(elipse);
    }


}
