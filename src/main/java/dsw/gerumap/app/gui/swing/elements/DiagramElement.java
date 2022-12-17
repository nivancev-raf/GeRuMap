package dsw.gerumap.app.gui.swing.elements;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class DiagramElement { // model

    protected Paint paint;
    protected float stroke;
    protected boolean selected;


    public DiagramElement(Paint paint, float stroke) {
        this.stroke = stroke;
        this.paint = paint;
    }


}
