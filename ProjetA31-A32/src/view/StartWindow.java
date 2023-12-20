package view;

import controller.GameMasterController;
import view.StartPanels.StartPanel;

import javax.swing.*;
import java.awt.*;

//Fenêtre du début de choix des paramêtre de la partie
public class StartWindow extends JFrame//Hériter de JFrame
{
    private GameMasterController controller;

    public StartWindow(GameMasterController gmc)
    {
        super("Menu Principal");//ou setTitle("Menu Principal");
        this.setSize( 600, 920 );
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran
        //Change l'icône par défaut de la fenêtre
        ImageIcon icon = new ImageIcon("./ProjetA31-A32/images/icon_start_game.png");
        this.setIconImage(icon.getImage());

        this.controller=gmc;

        //On mets une taille minimum, comme cela pas de problème avec le fait que c'est trop petit
        this.setMinimumSize(new Dimension(600,1000));
        this.setResizable(false);//Peut pas redimensionner la fenêtre comme cela pas de problème d'affichage bizarre

        JPanel startPanel=new StartPanel(gmc);

        this.setContentPane(startPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Coupe le programme quand on appuie sur la croix
        this.setVisible(true);
    }

    public void closeWindow()
    {
        this.dispose();
    }
}
