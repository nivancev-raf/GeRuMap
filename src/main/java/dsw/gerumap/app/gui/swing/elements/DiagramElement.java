package dsw.gerumap.app.gui.swing.elements;

import dsw.gerumap.app.gui.swing.view.painters.ElementPainter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class DiagramElement { // model

    protected Paint paint;
    protected Stroke stroke;
    protected float strokeWidth;
    protected boolean selected;
    /**
     * Instanciranje ElementPainter-a obavljaju konkretni  elementi
     * prilikom svoje konstrukcije
     */
    protected ElementPainter elementPainter;

    public DiagramElement(Paint paint, float stroke) {
        this.stroke = new BasicStroke(stroke);
        this.paint = paint;
    }
    public ElementPainter getPainter() {
        return elementPainter;
    }

}
