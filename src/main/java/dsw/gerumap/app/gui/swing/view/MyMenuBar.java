package dsw.gerumap.app.gui.swing.view;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class MyMenuBar extends JMenuBar {



    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction()); // mora biti selektovan prvo "My projec exp" da
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());    // bi se pravili novi child-ovi
        //fileMenu.add(MainFrame.getInstance().getActionManager().getEditAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAuthorAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());


        JMenu help =new JMenu("Help");
        //help.add(MainFrame.getInstance().getActionManager().getEditAction()); // kad bude bio edit omogucen dodati ovo
        help.add(MainFrame.getInstance().getActionManager().getEditAction());
        help.setMnemonic(KeyEvent.VK_H);

        this.add(fileMenu);
        this.add(help);

    }







/*


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
*/

}
