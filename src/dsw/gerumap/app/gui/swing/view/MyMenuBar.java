package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

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

        file.add(miNew);
        file.addSeparator();
        file.add(miInfo);

        help.add(miEdit);

        add(file);
        add(help);

    }
}
