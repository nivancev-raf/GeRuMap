package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.controller.InfoAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class MyMenuBar extends JMenuBar {

    static JFrame f;
    public static final float CENTER_ALIGNMENT = 0.5f;


    public MyMenuBar(){
        JMenu file=new JMenu("File"); 
        JMenu help =new JMenu("Help");
        file.setMnemonic(KeyEvent.VK_F);
        JMenu miNew =new JMenu("New");
        miNew.addSeparator();

        JMenuItem miInfo = new JMenuItem("Info");
        JMenuItem miEdit = new JMenuItem("Edit");
        MainFrame.getInstance();

        miInfo.addActionListener(MainFrame.getInstance().getActionManager().getInfoAction());

        file.add(miNew);
        file.addSeparator();
        file.add(miInfo);

        help.add(miEdit);

        add(file);
        add(help);

    }


}
