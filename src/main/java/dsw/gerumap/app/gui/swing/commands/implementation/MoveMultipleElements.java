package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveMultipleElements extends AbstractCommand {

    private MindMap map;
    private int index = 0;
    private List<Point> pos = new ArrayList<>();
    private static int k = 0;


    public MoveMultipleElements(MindMap map){
        this.map = map;


    }

    @Override
    public void undoCommand() {

        if (k < 0) k = 0;
        for (int i = 0; i < map.getModel().getMapElements().size(); i++){
            if (map.getModel().getKrajnjeKoordinateHashMap().containsKey(i)){
                pos = map.getModel().getKrajnjeKoordinateHashMap().get(i);
                index = pos.size() - 2 - k;
                map.getModel().getMapElements().get(i).getDiagramDevice().setPosition(pos.get(index));
            }
        }
        k++;

        map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {

        for (int i = 0; i < map.getModel().getMapElements().size(); i++) {
            if (map.getModel().getKrajnjeKoordinateHashMap().containsKey(i)) {
                pos = map.getModel().getKrajnjeKoordinateHashMap().get(i);
                if (pos.size() != 0 && k <= 0) {
                    index = pos.size() - 1;
                    map.getModel().getMapElements().get(i).getDiagramDevice().setPosition(pos.get(index));
                }else if (pos.size() != 0 && k > 0){
                    index = pos.size() - k;
                    map.getModel().getMapElements().get(i).getDiagramDevice().setPosition(pos.get(index));
                }
            }
        }
        k--;


        map.getModel().notifySubscribers(null);
    }
}
