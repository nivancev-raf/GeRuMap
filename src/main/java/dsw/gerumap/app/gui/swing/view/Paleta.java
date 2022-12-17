package dsw.gerumap.app.gui.swing.view;


import javax.swing.*;

public class Paleta extends JToolBar {
    public Paleta() {
        super(VERTICAL);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(MainFrame.getInstance().getActionManager().getPojamAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getVezaAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getBrisanjeAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getPomeranjeAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSelekcijaAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSettingsAction());
        addSeparator();

    }
}
