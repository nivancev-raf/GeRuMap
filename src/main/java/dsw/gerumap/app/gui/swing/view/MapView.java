package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.controller.MouseController;
import dsw.gerumap.app.gui.swing.controller.MouseDragController;
import dsw.gerumap.app.gui.swing.controller.listeners.UpdateEvent;
import dsw.gerumap.app.gui.swing.controller.listeners.UpdateListener;
import dsw.gerumap.app.gui.swing.elements.DiagramDevice;
import dsw.gerumap.app.gui.swing.elements.SelectedElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.view.painters.SelectedPainter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Getter
public class MapView extends JPanel implements UpdateListener {

    private MindMap map;
    public MapView(MindMap map){
        this.map = map;
        setBackground(Color.WHITE);
        map.getModel().addUpdateListener(this);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseController(map));
        addMouseMotionListener(new MouseDragController(map)); //mouse motion event is generated when the mouse is moved or dragged


    }

    @Override
    public void updatePerformed(UpdateEvent var1) {
        this.repaint(); // trigeruje paintComponent
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //omogucava providnost elemenata prilikom njihovog preklapanja
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        // smooth display
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<DiagramDevice> it = this.map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DiagramDevice d = (DiagramDevice) it.next();
            ElementPainter painter =  d.getPainter();
            if (d.isSelected()){
                //System.out.println("selektovan");
                //painter.paint(g2, new SelectedElement(d.getPosition(), d.getSize(), new Color(0, 0, 0, 0), 2.0F));
                painter.paint(g2, new SelectedElement(d.getPosition(), d.getSize(), new Color(1f,0f,0f,.5f ), 2.0F));
            }
            else {
               //System.out.println("nije selektovan");
                painter.paint(g2, d);
            }


            /*

            if (d.isSelected()){
                g.setColor(Color.GRAY);
                System.out.println("tu sam");
                g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0));
                g.draw((Shape) new SelectedElement(device.getPosition(), new Dimension(110, 60), new Color(152,251,152, 50), 3.0F));

            }*/

        }

        //System.out.println("Izvršena paintComponent metoda view-a");
        // problem : sout se printa na svaki klik
    }



}




