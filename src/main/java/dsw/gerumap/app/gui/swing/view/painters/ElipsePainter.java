package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ElipsePainter extends DevicePainter{

    public ElipsePainter(DiagramDevice device) {
        super(device);
        this.updateShape();
    }

    private void updateShape() {
        ElipseElement ellipse = (ElipseElement)this.getDiagramDevice();
        this.shape = new Ellipse2D.Float((float)(ellipse.getPosition().x - ellipse.getSize().width / 2), (float)(ellipse.getPosition().y - ellipse.getSize().height / 2), (float)ellipse.getSize().width, (float)ellipse.getSize().height);
    }

    public void reposition(Point position) {
        (this.getDiagramDevice()).setPosition(position);
        this.updateShape();
    }
}
