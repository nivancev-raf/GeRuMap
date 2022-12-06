package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

    public ElementPainter(DiagramElement element) {	}

    public abstract void paint(Graphics2D g, DiagramElement element);

    public abstract boolean elementAt(Point pos); // za vezu, da li smo krenuli da vezujemo od elementa
    // vraca false ako se pravi veza koja nije krenula od elementa
    // bilo je za elementAt prva var DiagramElement element
}
