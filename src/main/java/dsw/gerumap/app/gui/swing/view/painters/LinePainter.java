package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
public class LinePainter extends DevicePainter{
    Point odPojma;
    Point doPojma;
    float lineStroke;
    float[] oldColor;

    public LinePainter(DiagramDevice device, Point from, Point to,float lineStroke, float[] oldColor) {
        super(device);
        this.odPojma = from;
        this.doPojma = to;
        this.lineStroke = lineStroke;
        this.oldColor = oldColor;
        this.updateShape();
    }

    private void updateShape() {
        this.shape = new Line2D.Double(odPojma, doPojma);
    }

    public void repositionLine(Point position2) { // position2 je doPojma
        (this.getDiagramDevice()).setPosition(position2);
        this.updateShape();
    }
}
