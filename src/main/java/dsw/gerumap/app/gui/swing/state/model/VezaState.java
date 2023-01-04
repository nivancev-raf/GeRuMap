package dsw.gerumap.app.gui.swing.state.model;
import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.AddVeza;
import dsw.gerumap.app.gui.swing.elements.LineElement;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.DevicePainter;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;
import dsw.gerumap.app.logger.EventType;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class VezaState extends State {

    Point from = new Point();
    Point to = new Point();
    Point poslednjaKoordinata = new Point();
    Point pocetnaKoordinata = new Point();
    DevicePainter pocetni = null;
    DevicePainter krajnji = null;
    LineElement line;
    LinePainter linePainter;

    @Override
    public void misKliknut(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        for (int i = 0; i < map.getModel().getMapElements().size(); i++) {
            if (map.getModel().getMapElements().get(i).elementAt(generatePoint(e.getPoint()))) {
                from = map.getModel().getMapElements().get(i).getDiagramDevice().getPosition();
                to = from; // crta tacku
                line = new LineElement(e.getPoint(), new Dimension(50, 20), new float[]{0,0,0}, 1.0F); // podesavanje same linije
                linePainter = new LinePainter(line, from, to,2, new float[]{0,0,0});
                line.setDevice1(map.getModel().getMapElements().get(i));
                AbstractCommand komanda = new AddVeza(map,linePainter);
                int selectedMindMap = MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedIndex();
                MainFrame.getInstance().getProjectView().getTabbedPane().getMapViewList().get(selectedMindMap).getMap().getCommandManager().addCommand(komanda);
                break;
            }
        }
    }

    @Override
    public void misPovucen(MouseEvent e, MindMap map) {
        super.misKliknut(e, map);
        to = generatePoint(e.getPoint());

        if(this.linePainter!=null) {
            linePainter.setDoPojma(to);
        }
        map.getModel().notifySubscribers(null);
    }


    @Override
    public void misOtpsuten(MouseEvent e, MindMap map) {
        super.misOtpsuten(e, map);

        if(this.linePainter!=null) {
            poslednjaKoordinata = linePainter.getDoPojma();
            pocetnaKoordinata = linePainter.getOdPojma();
        }

        pocetni = null;
        krajnji = null;

        for (int i = 0; i < map.getModel().getMapElements().size(); i++) {
            if (map.getModel().getMapElements().get(i).elementAt(pocetnaKoordinata)) {
                pocetni = map.getModel().getMapElements().get(i);
            }
            if (map.getModel().getMapElements().get(i).elementAt(poslednjaKoordinata)) {
                krajnji = map.getModel().getMapElements().get(i);
            }
        }

        if (pocetni != null && krajnji != null){
            linePainter.setDoPojma(generatePoint(e.getPoint()));
            linePainter.setOdPojma(pocetnaKoordinata);
            ((LineElement)linePainter.getDiagramDevice()).setDevice2(krajnji);
            Point x = new Point((int) (pocetni.getDiagramDevice().getPosition().getX()), (int)(pocetni.getDiagramDevice().getPosition().getY()));
            Point y = new Point((int) (krajnji.getDiagramDevice().getPosition().getX()), (int)(krajnji.getDiagramDevice().getPosition().getY()));

            linePainter.setOdPojma(x);
            linePainter.setDoPojma(y);


            map.getModel().getVeze().set(map.getModel().getVeze().size() - 1, linePainter);
            makeDialog(e);
            map.getModel().notifySubscribers(null);

            }else{
                if(this.linePainter!=null) {
                    linePainter.setDoPojma(new Point(-1, -1));
                    linePainter.setOdPojma(new Point(-1, -1));
                    linePainter = null;
                    }
                map.getModel().notifySubscribers(null);
            }
        }


//----------------- DIALOG --------------------------//


    private JLabel labela3;
    private JButton colorButton;
    private Color color;
    private JLabel labela4;
    private JPanel panelColor;
    JDialog dialog;
    JLabel labela;
    JTextField textField;

    private void makeDialog(MouseEvent e){
        dialog = new JDialog();
        dialog.setTitle("Line settings");
        dialog.setSize(490, 240);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 20));
        GridBagConstraints cs = new GridBagConstraints();

        labela = new JLabel("Change stroke of line: (NUM ONLY) ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;

        panel.add(labela, cs);


        textField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;


        panel.add(textField, cs);
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
                    color = JColorChooser.showDialog(null, "Izaberite boju", Color.black);
                    panelColor.setBackground(color);
                }
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (color != Color.black && color!=null){
                    float r = color.getRed();
                    float g = color.getGreen();
                    float b = color.getBlue();
                    linePainter.setOldColor(new float[]{r,g,b});
                }

                String noviStroke = textField.getText();
                if (!noviStroke.isEmpty()) {
                    try {
                        float stroke = Float.parseFloat(noviStroke);
                        linePainter.setLineStroke(stroke);
                    } catch (NumberFormatException exception) {
                        ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NUMBERS_ONLY);
                    }
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        if (pocetni.elementAt(generatePoint(e.getPoint())) || krajnji.elementAt(generatePoint(e.getPoint()))){
            dialog.setVisible(true);
        }

        pocetni = null;
        krajnji = null;

    }
}
