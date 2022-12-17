package dsw.gerumap.app.gui.swing.view.painters;

import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import dsw.gerumap.app.gui.swing.elements.SelectedElement;
import lombok.Getter;
import lombok.Setter;
import java.awt.geom.Rectangle2D;

@Setter
@Getter
public class SelectedPainter extends DevicePainter{

    public SelectedPainter(DiagramDevice device) {
        super(device);
        this.updateShape();
    }

    private void updateShape(){
        SelectedElement rectangle = (SelectedElement) this.getDiagramDevice();
        float height = (float)rectangle.getSize().height + getDiagramDevice().getStroke();
        float width = (float)rectangle.getSize().width + getDiagramDevice().getStroke();
        this.shape = new Rectangle2D.Float((float)rectangle.getPosition().x - width / 2.0F - 5.0F , (float)rectangle.getPosition().y - height / 2.0F - 5.0F, width + 10.0F, height + 10.0F);

    }

}
