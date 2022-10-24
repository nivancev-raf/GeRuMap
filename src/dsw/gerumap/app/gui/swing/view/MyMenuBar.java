package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu file=new JMenu("File"); 
        JMenu help =new JMenu("Help");
        file.setMnemonic('F');
        JMenu miNew =new JMenu("New");
        //miNew.setIcon(new ImageIcon("images/new16x16.jpg"));
        miNew.addSeparator();
        JMenuItem miInfo =new JMenuItem("Info");
        JMenuItem miEdit = new JMenuItem("Edit");
        MainFrame.getInstance();


        miInfo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                final JPopupMenu popup = new JPopupMenu();
                popup.add(new JMenuItem(new AbstractAction("Nikola Ivancev 5321 RN i Luka Vukadinovic 2921 RN") {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                       /// ukrasiti
                    }
                }));
                popup.show(e.getComponent(), e.getX(), e.getY());
            }});



        file.add(miNew);
        file.addSeparator();
        file.add(miInfo);

        help.add(miEdit);

        add(file);
        add(help);

    }
}
