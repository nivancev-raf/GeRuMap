package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.gui.swing.controller.ActionManager;
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
    private ActionManager actionManager;

    JSplitPane jsp;


    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        screenSize.setSize(500, 800);
        Image img = kit.getImage("images/iko.jpg");
        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap");

        menu= new MyMenuBar();
        setJMenuBar(menu);

        toolbar=new Toolbar();
        add(toolbar, BorderLayout.NORTH);

        JLabel leftLabel = new JLabel();
        JLabel rightLabel = new JLabel();



        JPanel panel1 = new JPanel();

        panel1.setPreferredSize(new Dimension(400,400));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(400,400));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(panel1),
                new JScrollPane(panel2));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(splitPane);
        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        splitPane.setDividerLocation(150);




    }



    public Toolbar getToolbar() {
        return toolbar;
    }

    public ActionManager getActionManager(){
        return actionManager;
    }


    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public static MainFrame getInstance(){
        if (instance == null){
            instance  = new MainFrame();
            instance.initialise();

        }
        return instance;
    }
}
