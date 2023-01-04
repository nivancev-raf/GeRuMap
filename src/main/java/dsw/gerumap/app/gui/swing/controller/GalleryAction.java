package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class GalleryAction extends AbstractGeRuMapAction{

    static JFrame f;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    private static final String SABLON1 = "/templateGallery/sablon1.png";
    private static final String SABLON2 = "/templateGallery/sablon2.png";
    private static final String SABLON3 = "/templateGallery/sablon3.png";
    private static final String SABLON4 = "/templateGallery/sablon4.png";


    public GalleryAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/galerija1.png"));
        putValue(NAME, "Template Gallery");
        putValue(SHORT_DESCRIPTION, "Template Gallery");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JDialog d = new JDialog(f, "Choose pattern");
        try {
            button1 = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(SABLON1))));
            button2 = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(SABLON2))));
            button3 = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(SABLON3))));
            button4 = new JButton(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(SABLON4))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        button1.setMargin(new Insets(10, 10, 10, 10));
        button2.setMargin(new Insets(10, 10, 10, 10));
        button3.setMargin(new Insets(10, 10, 10, 10));
        button4.setMargin(new Insets(10, 10, 10, 10));


        JPanel panel = new JPanel(new GridLayout(2,2, 10, 20));

        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(button1,cs);

        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;

        panel.add(button2,cs);

        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(button3,cs);

        cs.gridx = 2;
        cs.gridy = 2;
        cs.gridwidth = 2;

        panel.add(button4,cs);

        d.add(panel);
        d.setSize(500, 400);
        d.setVisible(true);
        d.setLocationRelativeTo(f);



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(getClass().getResource("/templateGallery").getPath());
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (file.getPath().contains("sablon1.json")){
                            Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                            MainFrame.getInstance().getMapTree().loadProject(p);
                        }
                    }
                }
                d.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(getClass().getResource("/templateGallery").getPath());
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (file.getPath().contains("sablon2.json")){
                            Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                            MainFrame.getInstance().getMapTree().loadProject(p);
                        }
                    }
                }
                d.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(getClass().getResource("/templateGallery").getPath());
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (file.getPath().contains("sablon3.json")){
                            Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                            MainFrame.getInstance().getMapTree().loadProject(p);
                        }
                    }
                }
                d.setVisible(false);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(getClass().getResource("/templateGallery").getPath());
                File[] listOfFiles = folder.listFiles();

                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (file.getPath().contains("sablon4.json")){
                            Project p = ApplicationFramework.getInstance().getSerializer().loadProject(file);
                            MainFrame.getInstance().getMapTree().loadProject(p);
                        }
                    }
                }
                d.setVisible(false);
            }
        });
    }
}
