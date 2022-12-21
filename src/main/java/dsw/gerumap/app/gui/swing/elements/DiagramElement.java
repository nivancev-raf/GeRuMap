package dsw.gerumap.app.gui.swing.elements;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class DiagramElement { // model

    protected float[] paint;
    protected float stroke;
    protected boolean selected;



    public DiagramElement(float[] paint, float stroke) {
        this.stroke = stroke;
        this.paint = paint;
    }


}
