package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramElement;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.elements.SelectedElement;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class SelectedPainter extends DevicePainter{
    private DiagramElement device;

    public SelectedPainter(DiagramElement device) {
        super(device);
        this.device = device;
        this.updateShape();
    }

    private void updateShape(){
        SelectedElement rectangle = (SelectedElement) this.device;


        float height = (float)rectangle.getSize().height + device.getStrokeWidth();
        float width = (float)rectangle.getSize().width + device.getStrokeWidth();
        this.shape = new Rectangle2D.Float((float)rectangle.getPosition().x - width / 2.0F - 5.0F, (float)rectangle.getPosition().y - height / 2.0F - 5.0F, width + 10.0F, height + 10.0F);


    }


}
