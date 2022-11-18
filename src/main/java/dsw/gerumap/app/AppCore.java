package dsw.gerumap.app;


import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.gui.swing.mapRepository.MapRepositoryImplementation;
import dsw.gerumap.app.logger.ConsoleLogger;
import dsw.gerumap.app.logger.FileLogger;
import dsw.gerumap.app.logger.MessageGenerator;
import dsw.gerumap.app.logger.MessageGeneratorImplementation;

public class AppCore  {

    public static void main(String[] args) {
        Gui gui = new SwingGui();
        ApplicationFramework appcore = ApplicationFramework.getInstance();
        MapRepository mapRepository = new MapRepositoryImplementation();
        MessageGenerator msg = new MessageGeneratorImplementation();
        ConsoleLogger consoleLogger = new ConsoleLogger();
        FileLogger fileLogger = new FileLogger();
        msg.addSubscriber(consoleLogger);
        msg.addSubscriber(fileLogger);
        appcore.initialise(gui,mapRepository, msg, consoleLogger, fileLogger);
        appcore.run();
    }
}
