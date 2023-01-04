package dsw.gerumap.app.gui.swing.commands;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommandManager {

    private List<AbstractCommand> commands = new ArrayList<AbstractCommand>();

    private int currentCommand = 0; // pokazivac na stek

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        redoCommand();
    }

    public void redoCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).redoCommand();
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }

    public void undoCommand(){
        if(currentCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }


}
