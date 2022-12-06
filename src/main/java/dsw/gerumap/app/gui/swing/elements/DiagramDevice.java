package dsw.gerumap.app.gui.swing.elements;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Setter
@Getter
public abstract class DiagramDevice extends DiagramElement {

    protected Dimension size;
    protected Point position;
    protected String name;




    public DiagramDevice(Point position, Dimension size, Paint paint, float stroke) {
        super(paint, stroke);
        this.size = size;
        this.position = position;
    }
}
