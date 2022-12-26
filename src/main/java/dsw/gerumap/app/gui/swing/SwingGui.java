package dsw.gerumap.app.gui.swing;


import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.Message;
import javax.swing.*;

public class SwingGui implements Gui,Subscriber {

    private MainFrame instance;
    private CommandManager commandManager;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        commandManager = new CommandManager();
        instance.setVisible(true);
    }


    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(Object notification) {
        Message msg = (Message) notification;
        JOptionPane.showMessageDialog(MainFrame.getInstance(), msg);
    }
}
