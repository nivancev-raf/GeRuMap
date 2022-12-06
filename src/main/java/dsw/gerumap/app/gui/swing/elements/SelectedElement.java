package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;
import dsw.gerumap.app.gui.swing.view.painters.SelectedPainter;

import java.awt.*;

public class SelectedElement extends DiagramDevice{
    public SelectedElement(Point position, Dimension size, Paint paint, float stroke) {
        super(position, size, paint, stroke);
        this.elementPainter = new SelectedPainter(this);
    }

}
