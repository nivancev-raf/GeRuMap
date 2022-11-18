package dsw.gerumap.app.gui.swing;


import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.view.MainFrame;

public class SwingGui implements Gui,Subscriber {

    private MainFrame instance;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }


    @Override
    public void update(Object notification) {
        //JOption pane mora da se napravi
    }
}
