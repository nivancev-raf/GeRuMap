package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.AppCore;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter

public class MainFrame extends JFrame{

    private static MainFrame instance = null;
    MyMenuBar menu;
    Toolbar toolbar;


    private MainFrame(){
        //initialise();
    }

    private void initialise(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        screenSize.setSize(500, 800);
        Image img = kit.getImage("images/iko.jpg");
        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Look and feel");

        menu= new MyMenuBar();
        setJMenuBar(menu);

        toolbar=new Toolbar();
        add(toolbar, BorderLayout.NORTH);
    }



    public Toolbar getToolbar() {
        return toolbar;
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
