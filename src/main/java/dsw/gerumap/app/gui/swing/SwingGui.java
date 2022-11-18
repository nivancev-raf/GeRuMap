package dsw.gerumap.app.gui.swing;


import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.Message;
import dsw.gerumap.app.logger.MessageGeneratorImplementation;

import javax.swing.*;

public class SwingGui implements Gui,Subscriber {

    MessageGeneratorImplementation msg;
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
        Message msg = (Message) notification;
        System.out.println(msg.getText());
        JOptionPane.showMessageDialog(MainFrame.getInstance(), msg);
    }
}
