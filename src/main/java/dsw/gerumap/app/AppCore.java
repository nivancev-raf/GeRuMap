package dsw.gerumap.app;


import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.gui.swing.mapRepository.MapRepositoryImplementation;

public class AppCore  {

    public static void main(String[] args) {
        Gui gui = new SwingGui();
        ApplicationFramework appcore = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepositoryImplementation();
        appcore.initialise(gui,mapRepository);
        appcore.run();
    }
}
