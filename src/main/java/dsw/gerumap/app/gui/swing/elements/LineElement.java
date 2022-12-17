package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Setter
@Getter
public class LineElement extends DiagramDevice{

    DevicePainter device1;
    DevicePainter device2;

    public LineElement(Point position, Dimension size, Paint paint, float stroke) {
        super(position, size, paint, stroke);
    }
}
