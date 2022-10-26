package dsw.gerumap.app.gui.swing;

import com.sun.tools.javac.Main;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui {

    private MainFrame instance;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
