package dsw.gerumap.app.logger;

import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import javax.swing.*;

public class ConsoleLogger implements ErrorLogger, Subscriber {

    @Override
    public void log(Message message) {

        if (message.getType().equals(EventType.ADD_AUTHOR_ERROR)) {
            System.out.println("You cant add author here");
        } else if (message.getType().equals(EventType.ADDING_ELEMENT)) {
            System.out.println("You cant add element manually");
        } else if (message.getType().equals(EventType.REMOVE_PROJECT_EXPLORER)) {
            System.out.println("You cant remove project explorer");
        } else if (message.getType().equals(EventType.NON_SELECTED)) {
            System.out.println("Nothing is selected");
        } else if (message.getType().equals(EventType.FIELD_CANNOT_BE_EMPTY)) {
            System.out.println("Field cannot be empty");
        } else if (message.getType().equals(EventType.FIELD_CANNOT_BE_EMPTY)){
            System.out.println("Select item properly");
        }

    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;
        JOptionPane.showMessageDialog(MainFrame.getInstance(), msg.text);
    }
}
