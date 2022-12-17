package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.*;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import java.awt.geom.Line2D;


@Setter
@Getter
public class DevicePainter extends ElementPainter{

    protected Shape shape;
    protected DiagramDevice diagramDevice; // painter ima referencu na diagramDevice

    public DevicePainter(DiagramDevice device) {
        super();
        this.diagramDevice = device;
    }

    @Override
    public void paint(Graphics2D g, DevicePainter device) {

        if (this instanceof ElipsePainter) {
            ((ElipsePainter)this).reposition(device.getDiagramDevice().getPosition());
        }
        if (this instanceof LinePainter){
            ((LinePainter)this).repositionLine(device.getDiagramDevice().getPosition());
            g.setPaint(device.getDiagramDevice().getPaint());
            g.setStroke(new BasicStroke((((LinePainter) this).getLineStroke())));
        }

        if (device.getDiagramDevice() instanceof ElipseElement) {
            g.setPaint(Color.RED);
            g.setStroke(new BasicStroke(device.getDiagramDevice().getStroke()));
        }

        if (device.getDiagramDevice() instanceof SelectedElement){
            g.setStroke(new BasicStroke());
        }

            g.draw(getShape()); // crtamo objekat
            g.setPaint(device.getDiagramDevice().getPaint());
            g.fill(getShape());

            if (device.getDiagramDevice() instanceof ElipseElement){ // ovo je za tekst unutra
                g.setPaint(Color.BLACK);
                int x = (int)getDiagramDevice().getPosition().getX();
                x -= g.getFontMetrics().stringWidth(getDiagramDevice().getName()) / 2;
                int y = (int)getDiagramDevice().getPosition().getY();
                y += 5;
                g.drawString(getDiagramDevice().getName(), x, y);
            }
    }

    @Override
    public boolean elementAt(Point pos){
        if (shape instanceof Line2D){
            boolean b = ((Line2D) shape).ptSegDist(pos) < 8;
            return b;
        }
        return getShape().getBounds().contains(pos);
    }

    public DiagramDevice getDiagramDevice() {
        return diagramDevice;
    }
}
