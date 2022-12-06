package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import dsw.gerumap.app.gui.swing.elements.DiagramElement;
import dsw.gerumap.app.gui.swing.elements.ElipseElement;
import dsw.gerumap.app.gui.swing.elements.SelectedElement;
import dsw.gerumap.app.gui.swing.state.model.SelekcijaState;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.font.FontRenderContext;

@Setter
@Getter
public class DevicePainter extends ElementPainter{

    protected Shape shape;
    SelectedElement rectangle;

    public DevicePainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {

        DiagramDevice device = (DiagramDevice)element;

        if (this instanceof ElipsePainter) {
            ((ElipsePainter)this).reposition(device.getPosition());
        }
            g.setPaint(Color.RED);
            g.setStroke(element.getStroke());

        if (element instanceof ElipseElement) {
            g.setStroke(new BasicStroke(element.getStrokeWidth()));
        }

        if (this instanceof SelectedPainter){
            g.setPaint(new Color(0,0, 255));
            g.setStroke(new BasicStroke(1.0F, 0, 2, 0.0F, new float[]{2.0F}, 0.0F));
        }

            g.draw(getShape()); // crtamo objekat
            g.setPaint(element.getPaint());
            g.fill(getShape());

            if (element instanceof ElipseElement){ // ovo je za tekst unutra
            g.setPaint(Color.BLACK);
            //DiagramDevice device=(DiagramDevice )element;
            g.drawString(device.getName(), (int)device.getPosition().getX() - 14, // ovde treba namestiti da tekst bude na centru
                    (int)device.getPosition().getY() + 1);
        }

            /*
            if (element instanceof DiagramDevice){ // ovo je za tekst unutra

                g.setPaint(Color.BLACK);
                //DiagramDevice device=(DiagramDevice )element;
                g.drawString(device.getName(), (int)device.getPosition().getX() - 14, // ovde treba namestiti da tekst bude na centru
                        (int)device.getPosition().getY() + 1);
            }
            */

    }

    @Override
    public boolean elementAt(Point pos){
        return getShape().contains(pos);
    }


}
