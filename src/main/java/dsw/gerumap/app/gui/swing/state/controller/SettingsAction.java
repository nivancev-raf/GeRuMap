package dsw.gerumap.app.gui.swing.state.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.MapView;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SettingsAction extends AbstractGeRuMapAction {

    private JDialog dialog;
    private JLabel labela;
    private JLabel labela2;
    private JLabel labela3;
    private JLabel labela4;
    private JPanel panelColor;
    private JButton colorButton;
    private Color color;
    private JTextField textField;
    private JTextField textField2;

    public SettingsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/Settings-icon.png"));
        putValue(NAME, "Settings");
        putValue(SHORT_DESCRIPTION, "Settings");
    }

    MapView map;

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
        if (map.getMap().getModel().getMapElements().size()==0){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.CANNOT_BE_USED);
        }


        if(map.getMap().getModel().getSelectedElements().size() > 1){
            multiSelectionDialog();
        }else if(map.getMap().getModel().getVeze().size()!=0){
            vezaDialog();
        }else if(map.getMap().getModel().getVeze().size() == 0 && map.getMap().getModel().getSelectedElements().size() == 1){
            singleSelectionDialog();
        }
    }

    private void multiSelectionDialog(){
        dialog = new JDialog();
        dialog.setTitle("Settings");

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 20));
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        labela = new JLabel("Change name of element: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(labela, cs);


        textField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;


        panel.add(textField, cs);

        labela2 = new JLabel("Change stroke of element: (NUM ONLY) ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;

        panel.add(labela2, cs);

        textField2 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;


        panel.add(textField2, cs);

        labela3 = new JLabel("Change color of element: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;

        panel.add(labela3, cs);

        colorButton = new JButton("Pick a color");
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;

        panel.add(colorButton, cs);

        labela4 = new JLabel("Color selected: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;

        panel.add(labela4, cs);

        panelColor = new JPanel();
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;

        //border za panel
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panelColor.setBorder(blackline);

        panel.add(panelColor, cs);

        dialog.setSize(490, 270);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);


        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(okButton, cs);

        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(cancelButton, cs);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        dialog.add(panel2);


        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == colorButton) {
                    JColorChooser colorChooser = new JColorChooser();
                    color = JColorChooser.showDialog(null, "Izaberite boju", Color.black);
                    panelColor.setBackground(color);
                    for(int i = 0; i<map.getMap().getModel().getSelectedElements().size(); i++){
                        int r = color.getRed();
                        int g = color.getGreen();
                        int b = color.getBlue();

                        map.getMap().getModel().getSelectedElements().get(i).getDiagramDevice().setPaint(new float[]{r,g,b});
                    }
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String noviStroke = textField2.getText();
                if (!noviStroke.isEmpty()) {
                    try {
                        float stroke = Float.parseFloat(noviStroke);
                        for(int i = 0; i<map.getMap().getModel().getSelectedElements().size(); i++){
                            map.getMap().getModel().getSelectedElements().get(i).getDiagramDevice().setStroke(stroke);
                        }
                    } catch (NumberFormatException exception) {
                        ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NUMBERS_ONLY);
                    }
                }

                map.getMap().getModel().notifySubscribers(null);
                dialog.dispose();

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.setVisible(true);

    }

    private void vezaDialog(){
        boolean bool = false;
        for(int i = 0; i<map.getMap().getModel().getVeze().size(); i++){
            if(map.getMap().getModel().getVeze().get(i).getDiagramDevice().isSelected()){
                bool = true;
                dialog = new JDialog();
                dialog.setTitle("Settings");

                JPanel panel = new JPanel(new GridLayout(4, 2, 10, 20));
                GridBagConstraints cs = new GridBagConstraints();

                cs.fill = GridBagConstraints.HORIZONTAL;



                labela2 = new JLabel("Change stroke of element: (NUM ONLY) ");
                cs.gridx = 0;
                cs.gridy = 1;
                cs.gridwidth = 1;

                panel.add(labela2, cs);

                textField2 = new JTextField(20);
                cs.gridx = 1;
                cs.gridy = 1;
                cs.gridwidth = 2;


                panel.add(textField2, cs);

                labela3 = new JLabel("Change color of element: ");
                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(labela3, cs);

                colorButton = new JButton("Pick a color");
                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(colorButton, cs);


                labela4 = new JLabel("Color selected: ");
                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(labela4, cs);

                panelColor = new JPanel();
                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;

                //border za panel
                Border blackline = BorderFactory.createLineBorder(Color.black);
                panelColor.setBorder(blackline);

                panel.add(panelColor, cs);

                dialog.setSize(490, 230);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);


                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Cancel");

                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(okButton, cs);

                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(cancelButton, cs);

                panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPanel panel2 = new JPanel();
                panel2.add(panel);
                dialog.add(panel2);

                int finalI = i;
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == colorButton) {
                            LinePainter line = (LinePainter) map.getMap().getModel().getVeze().get(finalI);
                            color = JColorChooser.showDialog(null, "Izaberite boju", Color.black);
                            float r = color.getRed();
                            float g = color.getGreen();
                            float b = color.getBlue();
                            line.setOldColor(new float[]{r,g,b});
                            line.getDiagramDevice().setSelected(false);
                            map.getMap().getModel().notifySubscribers(null);
                            panelColor.setBackground(color);
                        }
                    }
                });

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String noviStroke = textField2.getText();
                        if (!noviStroke.isEmpty()) {
                            try {
                                float stroke = Float.parseFloat(noviStroke);
                                LinePainter line = (LinePainter) map.getMap().getModel().getVeze().get(finalI);
                                line.setLineStroke(stroke);
                            } catch (NumberFormatException exception) {
                                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NUMBERS_ONLY);
                            }
                        }

                        map.getMap().getModel().notifySubscribers(null);
                        dialog.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
                break;
            }
        }
        if (!bool) singleSelectionDialog();
    }


    private void singleSelectionDialog(){
        for (int i = 0; i < map.getMap().getModel().getMapElements().size(); i++) {


            if (map.getMap().getModel().getMapElements().get(i).getDiagramDevice().isSelected()) {

                dialog = new JDialog();
                dialog.setTitle("Settings");

                JPanel panel = new JPanel(new GridLayout(5, 2, 10, 20));
                GridBagConstraints cs = new GridBagConstraints();

                cs.fill = GridBagConstraints.HORIZONTAL;

                labela = new JLabel("Change name of element: ");
                cs.gridx = 0;
                cs.gridy = 0;
                cs.gridwidth = 1;

                panel.add(labela, cs);


                textField = new JTextField(20);
                //textField.setText(name);
                cs.gridx = 1;
                cs.gridy = 0;
                cs.gridwidth = 2;


                panel.add(textField, cs);

                labela2 = new JLabel("Change stroke of element: (NUM ONLY) ");
                cs.gridx = 0;
                cs.gridy = 1;
                cs.gridwidth = 1;

                panel.add(labela2, cs);

                textField2 = new JTextField(20);
                cs.gridx = 1;
                cs.gridy = 1;
                cs.gridwidth = 2;


                panel.add(textField2, cs);

                labela3 = new JLabel("Change color of element: ");
                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(labela3, cs);

                colorButton = new JButton("Pick a color");
                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(colorButton, cs);

                labela4 = new JLabel("Color selected: ");
                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;

                panel.add(labela4, cs);

                panelColor = new JPanel();
                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;

                //border za panel
                Border blackline = BorderFactory.createLineBorder(Color.black);
                panelColor.setBorder(blackline);

                panel.add(panelColor, cs);


                dialog.setSize(490, 270);
                dialog.setLocationRelativeTo(null);
                dialog.setModal(true);


                JButton okButton = new JButton("OK");
                JButton cancelButton = new JButton("Cancel");

                cs.gridx = 0;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(okButton, cs);

                cs.gridx = 1;
                cs.gridy = 2;
                cs.gridwidth = 2;
                panel.add(cancelButton, cs);

                panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPanel panel2 = new JPanel();
                panel2.add(panel);
                dialog.add(panel2);

                int finalI = i;
                colorButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == colorButton) {
                            JColorChooser colorChooser = new JColorChooser();
                            color = JColorChooser.showDialog(null, "Izaberite boju", Color.black);
                            panelColor.setBackground(color);
                            int r = color.getRed();
                            int g = color.getGreen();
                            int b = color.getBlue();
                            map.getMap().getModel().getMapElements().get(finalI).getDiagramDevice().setPaint(new float[]{r,g,b});
                        }
                    }
                });

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String novoIme = textField.getText();
                        if (novoIme.isEmpty()) {
                        } else {
                            map.getMap().getModel().getMapElements().get(finalI).getDiagramDevice().setName(novoIme);
                        }
                        map.getMap().getModel().notifySubscribers(null);
                        dialog.dispose();

                        String noviStroke = textField2.getText();
                        if (!noviStroke.isEmpty()) {
                            try {
                                float stroke = Float.parseFloat(noviStroke);
                                map.getMap().getModel().getMapElements().get(finalI).getDiagramDevice().setStroke(stroke);
                            } catch (NumberFormatException exception) {
                                ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NUMBERS_ONLY);
                            }
                        }
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });
                dialog.setVisible(true);
                break;
            }
        }
    }

}