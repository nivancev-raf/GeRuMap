package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.logger.EventType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportAction extends AbstractGeRuMapAction{

    MapView map;

    public ExportAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/export.png"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().size() == 0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NO_MAPVIEW);
        }
        int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
        if(MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().size() != 0) {
            map = MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap);
        } else {
            return;
        }
        if (map.getMap().getModel().getMapElements().size()==0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.EMPTY_MINDMAP);
        } else {
                BufferedImage image = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                map.printAll(g);
                g.dispose();
                try {
                    // slika je sacuvana dole kod log.txt
                    ImageIO.write(image, "png", new File("Paint.png"));
                } catch (IOException ee) {
                    ee.printStackTrace();
                }

        }

    }


}
