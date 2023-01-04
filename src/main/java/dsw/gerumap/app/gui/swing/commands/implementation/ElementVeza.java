package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.elements.LineElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ElementVeza extends AbstractCommand {

    private DevicePainter line;
    private MindMap map;
    private DevicePainter removedElement;

    public ElementVeza(MindMap map, DevicePainter line, DevicePainter removedElement){
        this.map = map;
        this.line = line;
        this.removedElement = removedElement;
    }


    @Override
    public void undoCommand() {

        for (int i = 0; i < map.getModel().getListaObrisanihLinija().size(); i++){
            LinePainter line = (LinePainter) map.getModel().getListaObrisanihLinija().get(i);
            for (int j = 0 ; j < map.getModel().getMapElements().size(); j++){
                if (removedElement.elementAt(line.getDoPojma()) && line.getOdPojma().equals(map.getModel().getMapElements().get(j).getDiagramDevice().getPosition())){
                    map.getModel().addVeza(line);
                }
                if (removedElement.elementAt(line.getOdPojma()) && line.getDoPojma().equals(map.getModel().getMapElements().get(j).getDiagramDevice().getPosition())){
                    map.getModel().addVeza(line);
                }
            }
        }


        if (map.getModel().getListaObrisanihLinija().size() != 0){
            for (int i = 0; i < map.getModel().getVeze().size(); i++){
                for (int j = 0; j < map.getModel().getListaObrisanihLinija().size(); j++){
                    if (map.getModel().getVeze().get(i).equals(map.getModel().getListaObrisanihLinija().get(j)))
                        map.getModel().getListaObrisanihLinija().remove(line);
                }
            }
        }

        map.getModel().addDiagramElements(removedElement);


    }

    @Override
    public void redoCommand() {
        for (int i = 0; i < map.getModel().getVeze().size(); i++){
            LinePainter line = (LinePainter) map.getModel().getVeze().get(i);
            if (removedElement.getDiagramDevice().getPosition() == line.getDoPojma() || removedElement.getDiagramDevice().getPosition() == line.getOdPojma()){
                map.getModel().getListaObrisanihLinija().add(line);
            }
        }

        if (map.getModel().getListaObrisanihLinija().size() != 0){
            for (int i = 0; i < map.getModel().getVeze().size(); i++){
                for (int j = 0; j < map.getModel().getListaObrisanihLinija().size(); j++){
                    if (map.getModel().getVeze().get(i).equals(map.getModel().getListaObrisanihLinija().get(j)))
                            map.getModel().getVeze().remove(i);
                }
            }
        }

        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            if (map.getModel().getMapElements().get(i).equals(removedElement))
                map.getModel().getMapElements().remove(i);
        }

        map.getModel().notifySubscribers(null);
    }
}
