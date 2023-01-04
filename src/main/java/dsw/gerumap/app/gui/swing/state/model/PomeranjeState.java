package dsw.gerumap.app.gui.swing.state.model;


import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.MoveMultipleElements;
import dsw.gerumap.app.gui.swing.commands.implementation.MoveSingleElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Setter
@Getter
public class PomeranjeState extends State {

    private DevicePainter selectedElement = null;
    Point startPoint;
    Point krajnjaPozicija;
    Point pocetnaPozicija;

    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        startPoint = generatePoint(e.getPoint());
        pocetnaPozicija = generatePoint(e.getPoint());
        int found = -1;
        int size = map.getModel().getSelectedElements().size();
        for (int i = map.getModel().getMapElements().size() - 1; i >= 0; i--){
            DevicePainter device = map.getModel().getMapElements().get(i);
            if (device.elementAt(generatePoint(e.getPoint()))){
                selectedElement = device;
                device.getDiagramDevice().setSelected(true);
                map.getModel().addSelectedElement(device);
                map.getModel().notifySubscribers(null);
                found = i;
                break;
            }
        }

            if (map.getModel().getSelectedElements().size() == 2 && size == 1){
                for (int i = map.getModel().getMapElements().size() - 1; i >= 0; i--){
                    DevicePainter device = map.getModel().getMapElements().get(i);
                    if (i != found) {
                        device.getDiagramDevice().setSelected(false);
                        //remove
                        map.getModel().getSelectedElements().remove(map.getModel().getMapElements().get(i));
                    }
                }
            }

            if (found == -1){
                this.clearSelection(map);
            }

            map.getModel().notifySubscribers(null);
    }


    @Override
    public void misPovucen(MouseEvent e, MindMap map) {
        super.misPovucen(e, map);
        Point point = generatePoint(e.getPoint());
        int offsetX = (int) - (startPoint.x - point.getX());
        int offsetY = (int) - (startPoint.y - point.getY());

        for (DevicePainter d : map.getModel().getSelectedElements()){
            d.getDiagramDevice().setPosition(new Point(d.getDiagramDevice().getPosition().x + offsetX, d.getDiagramDevice().getPosition().y + offsetY));
        }

        if (MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getScaling() != 1 && map.getModel().getSelectedElements().size() == 0){

            double x = MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getTranslateX()+offsetX*MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getScaling();
            double y = MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getTranslateY()+offsetY*MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().getScaling();

            if (x <= 0) {
                MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().setTranslateX(x);
            }
            if (y <= 0) {
                MainFrame.getInstance().getProjectView().getTabbedPane().getMapView().setTranslateY(y);
            }
        }
            startPoint = generatePoint(e.getPoint());


        map.getModel().notifySubscribers(null); // repaint
        }


    @Override
    public void misOtpsuten(MouseEvent e, MindMap map) {
        super.misOtpsuten(e, map);
        krajnjaPozicija = generatePoint(e.getPoint());
        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            if (map.getModel().getKrajnjeKoordinateHashMap().containsKey(i)){
                List<Point> krajnjiPos = map.getModel().getKrajnjeKoordinateHashMap().get(i);
                krajnjiPos.add(map.getModel().getMapElements().get(i).getDiagramDevice().getPosition());
            }
        }


        if (map.getModel().getSelectedElements().size() == 1){
            if(selectedElement!=null && selectedElement.elementAt(generatePoint(e.getPoint()))) {
                AbstractCommand komanda = new MoveSingleElement(map, pocetnaPozicija, krajnjaPozicija, selectedElement);
                int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
            }
        }
        if (map.getModel().getSelectedElements().size() > 1){
            AbstractCommand komanda = new MoveMultipleElements(map);
            int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
            MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
        }
        selectedElement = null;
    }

    protected void clearSelection(MindMap map) {
        Iterator var2 = map.getModel().getMapElements().iterator();
        map.getModel().getSelectedElements().clear();
        DevicePainter element;
        while(var2.hasNext()) {
            element = (DevicePainter) var2.next();
            element.getDiagramDevice().setSelected(false);
        }

    }

}
