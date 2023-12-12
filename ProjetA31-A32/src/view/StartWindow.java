package view;

import controller.GameMasterController;
import view.StartPanels.StartPanel;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame//evidemment faut hériter
{
    private GameMasterController controller;
    public StartWindow(GameMasterController gmc)
    {
        super("Menu Principal");//ou setTitle("Menu Principal");
        this.setSize( 600, 800 );
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran

        this.controller=gmc;

        //On mets une taille minimum, comme cela pas de problème avec le fait que c'est trop petit
        this.setMinimumSize(new Dimension(600,700));

        JPanel startPanel=new StartPanel(gmc);

        this.setContentPane(startPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void closeWindow()
    {
        this.dispose();
    }
}
