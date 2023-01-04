package dsw.gerumap.app.gui.swing.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAsAction extends AbstractGeRuMapAction{

    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/saveAs.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        // ako nije selektovan projekat ne moze da se sacuva
        if (!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.NON_SELECTED_PROJECT);
            return;
        }

        Project project = (Project) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        File projectFile = null;

        if (project.getFilePath() == null || project.getFilePath().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            }
        }else{
            System.out.println("Putanja file: " + project.getFilePath());
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.ALREADY_SAVED);
            return;
        }

        ApplicationFramework.getInstance().getSerializer().saveProject(project);

    }
}
