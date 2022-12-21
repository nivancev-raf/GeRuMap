package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import lombok.Getter;
import lombok.Setter;


import javax.swing.*;
import java.awt.*;

@Setter
@Getter

public class MainFrame extends JFrame{

    private static MainFrame instance = null;
    private MyMenuBar menu;
    private Toolbar toolbar;
    private Paleta elementToolbar;
    private ActionManager actionManager;
    private MapTree mapTree;
    private ProjectView projectView;
    JPanel panel2;
    private MainFrame(){
    }

    private void initialise(){
        actionManager = new ActionManager();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        panel2 = new JPanel(new BorderLayout());

        screenSize.setSize(500, 800);
        Image img = kit.getImage("/images/iko.ico");
        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap");

        menu= new MyMenuBar();
        setJMenuBar(menu);

        toolbar=new Toolbar();
        elementToolbar = new Paleta();
        add(toolbar, BorderLayout.NORTH);
        add(elementToolbar, BorderLayout.EAST);

        mapTree = new MapTreeImplementation();
        JTree projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());

        panel2.setPreferredSize(new Dimension(900,700));

        projectView = new ProjectView();


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(projectExplorer),
                new JScrollPane(projectView));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(splitPane);
        this.setSize(700,550);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        splitPane.setDividerLocation(150);
    }

    public ActionManager getActionManager(){
        return actionManager;
    }


    public static MainFrame getInstance(){
        if (instance == null){
            instance  = new MainFrame();
            instance.initialise();

        }
        return instance;
    }
}
