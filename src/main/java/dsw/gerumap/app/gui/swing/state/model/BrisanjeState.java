package dsw.gerumap.app.gui.swing.state.model;


import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.DeleteElement;
import dsw.gerumap.app.gui.swing.commands.implementation.DeleteVeza;
import dsw.gerumap.app.gui.swing.commands.implementation.ElementVeza;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class BrisanjeState extends State {

    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        Iterator<DevicePainter> it = map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();
            if(d.elementAt(generatePoint(e.getPoint()))){
                // za pojam i vezu zajedno
                if (map.getModel().getVeze() != null){
                    Iterator<DevicePainter> it2 = map.getModel().getVezeIterator();
                    while (it2.hasNext()){
                        LinePainter line = (LinePainter) it2.next();
                        if (line.getDoPojma() == d.getDiagramDevice().getPosition()){
                            AbstractCommand komanda = new ElementVeza(map, line, d);
                            int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                            MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                            it = map.getModel().getDeviceIterator();
                            break;
                        }
                        if (line.getOdPojma() == d.getDiagramDevice().getPosition()){
                            AbstractCommand komanda = new ElementVeza(map, line, d);
                            int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                            MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                            it = map.getModel().getDeviceIterator();
                            break;
                        }
                    }
                }
            }
        }

        // slucaj kada uopste nema veza
        if (map.getModel().getVeze().size() == 0){
            Iterator<DevicePainter> it3 = map.getModel().getDeviceIterator();
            while(it3.hasNext()){
                DevicePainter d = it3.next();
                if(d.elementAt(generatePoint(e.getPoint()))){
                    AbstractCommand komanda = new DeleteElement(map,d);
                    int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                    MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                    it3 = map.getModel().getDeviceIterator(); // it mora da se redefinise jer se promenio u redoAction
                }
            }
            // slucaj kada ima veza ali element nije povezan
        } else if(map.getModel().getVeze().size() != 0) {
            Iterator<DevicePainter> it3 = map.getModel().getDeviceIterator();
            while (it3.hasNext()) {
                DevicePainter d = it3.next();
                for (int i = 0; i < map.getModel().getVeze().size(); i++) {
                    if (d.elementAt(generatePoint(e.getPoint())) && !d.elementAt(map.getModel().getVeze().get(i).getDiagramDevice().getPosition())) {
                        AbstractCommand komanda = new DeleteElement(map, d);
                        int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                        MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                        it3 = map.getModel().getDeviceIterator(); // it mora da se redefinise jer se promenio u redoAction
                    }
                }
            }
        }

        // samo za veze
        if (map.getModel().getVeze() != null){
            Iterator<DevicePainter> it2 = map.getModel().getVezeIterator();
            while (it2.hasNext()){
                DevicePainter d = it2.next();
                if (d.elementAt(generatePoint(e.getPoint()))){
                    AbstractCommand komanda = new DeleteVeza(map,d, e);
                    int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                    MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                    it2 = map.getModel().getVezeIterator(); // it mora da se redefinise jer se promenio u redoAction
                }
            }
        }


        map.getModel().notifySubscribers(null);
    }
}
