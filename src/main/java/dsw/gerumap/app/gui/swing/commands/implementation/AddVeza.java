package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.view.painters.LinePainter;

public class AddVeza extends AbstractCommand {

    private LinePainter line;
    private MindMap map;
    private int size_veze;

    public AddVeza(MindMap map, LinePainter line){
        this.map = map;
        this.line = line;
        size_veze = map.getModel().getVeze().size();
    }
    @Override
    public void undoCommand() {
        map.getModel().getVeze().remove(size_veze--);
        map.getModel().notifySubscribers(null);
    }

    @Override
    public void redoCommand() {
        map.getModel().addVeza(line);
        if (MainFrame.getInstance().getActionManager().getRedoAction().isEnabled()){
            size_veze++;
        }

    }

}
