package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.elements.MindMapModel;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class AllocationAction extends AbstractGeRuMapAction{


    MapView map;
    private  List<DevicePainter> listaLinijaZaElement;
    private List<Integer> listaIndexaZaDrugi;
    private List<Integer> listaIndexaZaTreci;


    public AllocationAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/centralni1.png"));
        putValue(NAME, "Allocate mind map");
        putValue(SHORT_DESCRIPTION, "Allocate mind map ");
        listaLinijaZaElement = new ArrayList<>();
        listaIndexaZaDrugi = new ArrayList<>();
        listaIndexaZaTreci = new ArrayList<>();

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().size() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CANNOT_BE_USED);
        }
        int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
        if(MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().size() != 0) {
            map = MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap);
        } else {
            return;
        }
        if (map.getMap().getModel().getMapElements().size()==0 || map.getMap().getModel().getVeze().size() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_CONNECTION);
            return;
        }

        if (map.getMap().getModel().getSelectedElements().size() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED);
            return;
        }
        if (map.getMap().getModel().getSelectedElements().size() > 1){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ONLY_ONE_SELECTED);
            return;
        }

            int indexGlavnog = 0;
            for (int i = 0; i < map.getMap().getModel().getMapElements().size(); i++){
                if (map.getMap().getModel().getSelectedElements().get(0).equals(map.getMap().getModel().getMapElements().get(i))){
                    map.getMap().getModel().getMapElements().get(i).getDiagramDevice().setSize(new Dimension(170,70));
                    map.getMap().getModel().getMapElements().get(i).getDiagramDevice().setPaint(new float[]{121, 161, 246});
                    map.getMap().getModel().getMapElements().get(i).getDiagramDevice().setStroke(3.0F);
                    indexGlavnog = i;
                    break;
                }
            }
        map.getMap().getModel().notifySubscribers(null);



        Point pointGlavnog = map.getMap().getModel().getMapElements().get(indexGlavnog).getDiagramDevice().getPosition();
        int angle = 30;
        for (int i = 0; i < map.getMap().getModel().getMapElements().size(); i++){
            boolean bool = false;
            boolean bool1 = false;
            if (listaIndexaZaDrugi.size() != 0){
                for (int m = 0; m < listaIndexaZaDrugi.size(); m++){
                    if (listaIndexaZaDrugi.get(m) == i){
                        bool = true;
                        break;
                    }
                }
            }
            if (bool) continue;

            if (listaIndexaZaTreci.size() != 0){
                for (int m = 0; m < listaIndexaZaTreci.size(); m++){
                    if (listaIndexaZaTreci.get(m) == i){
                        bool1 = true;
                        break;
                    }
                }
            }
            if (bool1) continue;


            if (i != indexGlavnog) {
                Point pointMalog = map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition();
                int distance = 150;
                pointMalog.x = (int) (pointGlavnog.x + distance * Math.cos(Math.toRadians(angle)));
                pointMalog.y = (int) (pointGlavnog.y + distance * Math.sin(Math.toRadians(angle)));
                map.getMap().getModel().getMapElements().get(i).getDiagramDevice().setPosition(new Point(pointMalog));
                for (int j = 0; j < map.getMap().getModel().getVeze().size(); j++) {
                    LinePainter line = (LinePainter) map.getMap().getModel().getVeze().get(j);
                    if (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().equals(line.getDoPojma())
                    || map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().equals(line.getOdPojma())){
                        listaLinijaZaElement.add(line);
                    }
                }
                if (listaLinijaZaElement.size() > 1){
                    int angle2 = angle;
                    for (int k = 1; k < listaLinijaZaElement.size(); k++){
                        LinePainter linijaZaDrugiKrug = (LinePainter) listaLinijaZaElement.get(k);
                        if (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().equals(linijaZaDrugiKrug.getDoPojma())){
                            for (int n = 0; n < map.getMap().getModel().getMapElements().size(); n++){
                                if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(linijaZaDrugiKrug.getOdPojma())){
                                    Point pointMalogZaDrugi = map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition();
                                    pointMalogZaDrugi.x = (int) (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle2)));
                                    pointMalogZaDrugi.y = (int) (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle2)));
                                    listaIndexaZaDrugi.add(n);
                                    int angle3 = angle2;
                                    // za treci krug
                                    for (int j = 0; j < map.getMap().getModel().getVeze().size(); j++){
                                        LinePainter line = (LinePainter) map.getMap().getModel().getVeze().get(j);
                                        if (line.equals(linijaZaDrugiKrug)) continue;
                                        if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(line.getDoPojma())){
                                            for (int l = 0; l < map.getMap().getModel().getMapElements().size(); l++){
                                                if (map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition().equals(line.getOdPojma())){
                                                    Point pointMalogZaTreci = map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition();
                                                    pointMalogZaTreci.x = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle3)));
                                                    pointMalogZaTreci.y = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle3)));
                                                    listaIndexaZaTreci.add(l);
                                                    angle3 -= 40;
                                                    break;
                                                }
                                            }
                                        } else if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(line.getOdPojma())){
                                            for (int l = 0; l < map.getMap().getModel().getMapElements().size(); l++){
                                                if (map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition().equals(line.getDoPojma())){
                                                    Point pointMalogZaTreci = map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition();
                                                    pointMalogZaTreci.x = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle3)));
                                                    pointMalogZaTreci.y = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle3)));
                                                    listaIndexaZaTreci.add(l);
                                                    angle3 -= 40;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }else if (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().equals(linijaZaDrugiKrug.getOdPojma())){
                            for (int n = 0; n < map.getMap().getModel().getMapElements().size(); n++){
                                if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(linijaZaDrugiKrug.getDoPojma())){
                                    Point pointMalogZaDrugi = map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition();
                                    pointMalogZaDrugi.x = (int) (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle2)));
                                    pointMalogZaDrugi.y = (int) (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle2)));
                                    listaIndexaZaDrugi.add(n);
                                    int angle3 = angle2;
                                    for (int j = 0; j < map.getMap().getModel().getVeze().size(); j++){
                                        LinePainter line = (LinePainter) map.getMap().getModel().getVeze().get(j);
                                        if (line.equals(linijaZaDrugiKrug)) continue;
                                        if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(line.getDoPojma())){
                                            for (int l = 0; l < map.getMap().getModel().getMapElements().size(); l++){
                                                if (map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition().equals(line.getOdPojma())){
                                                    Point pointMalogZaTreci = map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition();
                                                    pointMalogZaTreci.x = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle3)));
                                                    pointMalogZaTreci.y = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle3)));
                                                    listaIndexaZaTreci.add(l);
                                                    angle3 -= 40;
                                                    break;
                                                }
                                            }
                                        } else if (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().equals(line.getOdPojma())){
                                            for (int l = 0; l < map.getMap().getModel().getMapElements().size(); l++){
                                                if (map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition().equals(line.getDoPojma())){
                                                    Point pointMalogZaTreci = map.getMap().getModel().getMapElements().get(l).getDiagramDevice().getPosition();
                                                    pointMalogZaTreci.x = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().x + distance * Math.cos(Math.toRadians(angle3)));
                                                    pointMalogZaTreci.y = (int) (map.getMap().getModel().getMapElements().get(n).getDiagramDevice().getPosition().y + distance * Math.sin(Math.toRadians(angle3)));
                                                    listaIndexaZaTreci.add(l);
                                                    angle3 -= 40;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        angle2 -= 40;
                    }
                }
                listaLinijaZaElement.clear();
                angle += 50;
            }
        }



        map.getMap().getModel().notifySubscribers(null);
        listaIndexaZaTreci.clear();
        listaIndexaZaDrugi.clear();


    }
}
