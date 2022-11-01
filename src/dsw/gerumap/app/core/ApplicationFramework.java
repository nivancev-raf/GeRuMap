package dsw.gerumap.app.core;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor

public abstract class ApplicationFramework {

    protected Gui gui;

    public abstract void run();

    public void initialise(Gui gui, MapRepository mapRepository){
        this.gui = gui;

    }
}
