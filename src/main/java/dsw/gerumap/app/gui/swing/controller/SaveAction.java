package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveAction extends AbstractGeRuMapAction{

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save file");
        putValue(SHORT_DESCRIPTION, "Save file");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ako nije selektovan projekat ne moze da se sacuva
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED_PROJECT);
            return;
        }
        Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();


        if (project.getFilePath() != null){
            ApplicationFramework.getInstance().getSerializer().saveProject(project);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.SAVE_AS_PROJECT);
        }
    }
}
