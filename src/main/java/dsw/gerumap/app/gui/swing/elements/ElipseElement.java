package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.gui.swing.view.painters.ElipsePainter;

import java.awt.*;

public class ElipseElement extends DiagramDevice{
    public ElipseElement(Point position, Dimension size, Paint paint, float stroke) {
        super(position, size, paint, stroke);
        this.elementPainter = new ElipsePainter(this);
    }
}
