package dsw.gerumap.app.core;

import dsw.gerumap.app.logger.ConsoleLogger;
import dsw.gerumap.app.logger.FileLogger;
import dsw.gerumap.app.logger.MessageGenerator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public  class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected ConsoleLogger consoleLogger;
    protected FileLogger fileLogger;

    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, ConsoleLogger consoleLogger, FileLogger fileLogger)
    {
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
        this.consoleLogger = consoleLogger;
        this.fileLogger = fileLogger;
    }




    // Singleton
    private static ApplicationFramework instance;

    private ApplicationFramework(){

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
