package dsw.gerumap.app.gui.swing.view.painters;

import java.awt.*;

public abstract class ElementPainter {

    public ElementPainter() {}
    public abstract void paint(Graphics2D g, DevicePainter element);
    public abstract boolean elementAt(Point pos);

}
