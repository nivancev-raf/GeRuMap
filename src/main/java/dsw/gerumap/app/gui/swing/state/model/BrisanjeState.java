package dsw.gerumap.app.gui.swing.state.model;


import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.DeleteElement;
import dsw.gerumap.app.gui.swing.commands.implementation.DeleteVeza;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class BrisanjeState extends State {

    private DevicePainter removedElement = null;
    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        Iterator<DevicePainter> it = map.getModel().getDeviceIterator();
        while(it.hasNext()){
            DevicePainter d = it.next();

            if(map.getModel().getMapElements().size()!=0 && map.getModel().getMapElements().size()!=1 && d==map.getModel().getMapElements().get(0)){
                continue;
            }

            if(map.getModel().getMapElements().get(0).elementAt(generatePoint(e.getPoint())) && map.getModel().getMapElements().size()!=0 && map.getModel().getMapElements().size()!=1){
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Must delete other elements before the main element");
                break;
            }

            if(d.elementAt(generatePoint(e.getPoint()))){
                removedElement = d;
                AbstractCommand komanda = new DeleteElement(map,removedElement);
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda);
                //it.remove(); // remove za elipsu
                it = map.getModel().getDeviceIterator(); // it mora da se redefinise jer se promenio u redoAction

                // brisanje elementa i veze ukoliko su spojeni
                if (map.getModel().getVeze() != null) {
                    Iterator<DevicePainter> it1 = map.getModel().getVezeIterator();
                    while (it1.hasNext()) {
                        LinePainter line = (LinePainter) it1.next();
                        if (line.getOdPojma() == removedElement.getDiagramDevice().getPosition()){
                            //AbstractCommand komanda2 = new DeleteVeza(map,d, e);
                            //ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda2);
                            it1.remove();
                            //it1 = map.getModel().getVezeIterator(); // it mora da se redefinise jer se promenio u redoAction
                        }
                        if (line.getDoPojma() == removedElement.getDiagramDevice().getPosition()){
                            //AbstractCommand komanda3 = new DeleteVeza(map,d, e);
                            //ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda3);
                            it1.remove();
                            //it1 = map.getModel().getVezeIterator(); // it mora da se redefinise jer se promenio u redoAction
                        }
                    }
                }
            }

        }

/*
            if(map.getModel().getMapElements().size()!=0){
                ApplicationFramework.getInstance().getGui().enableRedoAction();
            }

            else{
                ApplicationFramework.getInstance().getGui().disableUndoAction();
            }
*/
        if (map.getModel().getVeze() != null){
            Iterator<DevicePainter> it2 = map.getModel().getVezeIterator();
            while (it2.hasNext()){
                DevicePainter d = it2.next();
                if (d.elementAt(generatePoint(e.getPoint()))){
                    AbstractCommand komanda = new DeleteVeza(map,d, e);
                    ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(komanda);
                    //it2.remove(); // remove za vezu
                    it2 = map.getModel().getVezeIterator(); // it mora da se redefinise jer se promenio u redoAction
                }
            }
        }

        map.getModel().notifySubscribers(null);
    }
}
