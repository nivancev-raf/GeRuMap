package dsw.gerumap.app.gui.swing.view;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar {



    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getEditAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(MainFrame.getInstance().getActionManager().getAuthorAction());
        add(MainFrame.getInstance().getActionManager().getExitAction());
    }

}

