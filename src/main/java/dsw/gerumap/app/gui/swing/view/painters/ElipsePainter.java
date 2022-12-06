package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramElement;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ElipsePainter extends DevicePainter{
    private DiagramElement device;
    public ElipsePainter(DiagramElement device) {
        super(device);
        this.device = device;
        this.updateShape();
    }

    private void updateShape() {
        ElipseElement ellipse = (ElipseElement)this.device;
        this.shape = new Ellipse2D.Float((float)(ellipse.getPosition().x - ellipse.getSize().width / 2), (float)(ellipse.getPosition().y - ellipse.getSize().height / 2), (float)ellipse.getSize().width, (float)ellipse.getSize().height);

    }

    public void reposition(Point position) {
        ((ElipseElement)this.device).setPosition(position);
        this.updateShape();
    }
}
