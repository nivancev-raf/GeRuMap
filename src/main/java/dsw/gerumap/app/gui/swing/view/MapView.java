package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.controller.MouseController;
import dsw.gerumap.app.gui.swing.controller.MouseDragController;
import dsw.gerumap.app.gui.swing.elements.LineElement;
import dsw.gerumap.app.gui.swing.elements.SelectedElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import dsw.gerumap.app.gui.swing.view.painters.SelectedPainter;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.*;
import java.util.Iterator;

@Getter
@Setter
public class MapView extends JPanel implements Subscriber {

    private MindMap map;
    final public static double scalingFactor = 1.2;
    double scaling = 1;
    double translateX = 0;
    double translateY = 0;
    Paint oldColor;


    public MapView(MindMap map){
        this.map = map;
        Border redline = BorderFactory.createLineBorder(Color.red);
        this.setBorder(redline);
        setBackground(Color.WHITE);
        map.getModel().addSubscriber(this);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseController(map));
        addMouseMotionListener(new MouseDragController(map)); //mouse motion event is generated when the mouse is moved or dragged
        this.setPreferredSize(new Dimension(500, 400));

    }





    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        g2.transform(affineTransform);


        //omogucava providnost elemenata prilikom njihovog preklapanja
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        // smooth display
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Iterator<Rectangle2D> it2 = this.map.getModel().getRectangleIterator();
        while(it2.hasNext()){
            Rectangle2D rectangle2D = it2.next();
            ((Graphics2D) g).draw(rectangle2D);
            g.setColor(new Color(147, 152, 217, 131));
            ((Graphics2D) g).fill(rectangle2D);
            ((Graphics2D) g).setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{10}, 0));
        }



    Iterator<DevicePainter> it1 = this.map.getModel().getVezeIterator();
        while(it1.hasNext()){
            LinePainter line = (LinePainter) it1.next();
            if (((LineElement)line.getDiagramDevice()).getDevice1() != null && ((LineElement)line.getDiagramDevice()).getDevice2() != null){
                line.setOdPojma(((LineElement)line.getDiagramDevice()).getDevice1().getDiagramDevice().getPosition());
                line.setDoPojma(((LineElement)line.getDiagramDevice()).getDevice2().getDiagramDevice().getPosition());
                oldColor = line.getOldColor();
                if (line.getDiagramDevice().isSelected()){
                    line.getDiagramDevice().setPaint(Color.CYAN); // kada je selektovana
                }else{
                    line.getDiagramDevice().setPaint(oldColor);
                }
            }
            line.paint(g2, line);

        }

        Iterator<DevicePainter> it = this.map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();
            if (d.getDiagramDevice().isSelected()){
                SelectedElement selectedElement = new SelectedElement(d.getDiagramDevice().getPosition(), d.getDiagramDevice().getSize(), new Color(1f,0f,0f,.5f ), 2.0F);
                SelectedPainter selectedPainter = new SelectedPainter(selectedElement);
                selectedPainter.paint(g2, selectedPainter);
            }
            d.paint(g2, d);
        }

    }


    @Override
    public void update(Object notification) {
        repaint();
    }


}




