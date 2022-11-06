package dsw.gerumap.app.gui.swing.view;

import javax.swing.JToolBar;

public class Toolbar extends JToolBar {



    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());

    }

}

