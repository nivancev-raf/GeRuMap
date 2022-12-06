package dsw.gerumap.app.gui.swing.tree.controller;




import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.mapRepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.logger.EventType;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener, Publisher {


    private Object clickedOn =null;
    private JTextField edit=null;

    private Subscriber subscriber;
    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }



    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }



    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof MapTreeItem))
            return;
        addSubscriber(MainFrame.getInstance().getProjectView());

        MapTreeItem clicked = (MapTreeItem) clickedOn;
        if (e.getActionCommand().trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().generate(EventType.FIELD_CANNOT_BE_EMPTY);
            return;
        }
        clicked.setName(e.getActionCommand());
        if (clicked.getMapNode() instanceof Project)
            notifySubscribers((Project)clicked.getMapNode());

        if (clicked.getMapNode() instanceof MindMap)
            notifySubscribers((Project)clicked.getMapNode().getParent());

        MainFrame.getInstance().getMapTree().deselect();

    }


    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {

    }

    @Override
    public void notifySubscribers(Object notification) {
        subscriber.update(notification);
    }
}
