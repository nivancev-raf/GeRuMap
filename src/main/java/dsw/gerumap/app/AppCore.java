package dsw.gerumap.app;


import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.gui.swing.mapRepository.MapRepositoryImplementation;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore() {
    }

    public static AppCore getInstance(){
        if(instance==null){
            instance = new AppCore();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public static void main(String[] args) {
        Gui gui = new SwingGui();
        ApplicationFramework appcore = AppCore.getInstance();
        MapRepository mapRepository = new MapRepositoryImplementation();
        appcore.initialise(gui,mapRepository);
        appcore.run();
    }
}
