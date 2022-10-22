package dsw.gerumap.app;

import dsw.gerumap.app.gui.swing.view.MainFrame;

public class AppCore {

    public static void main(String[] args) {

        MainFrame b = MainFrame.getInstance();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        b.setSize(800, 500);
    }
}
