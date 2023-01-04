package dsw.gerumap.app.gui.swing.view;


import javax.swing.*;
import java.awt.event.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAllocationAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getGalleryAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());

        fileMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAuthorAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu help = new JMenu("Help");
        help.add(MainFrame.getInstance().getActionManager().getEditAction());
        help.setMnemonic(KeyEvent.VK_H);

        this.add(fileMenu);
        this.add(help);
    }
}
