package view;

import model.MasterMindGame;

import javax.swing.*;

public class EndWindow extends JFrame {
    private MasterMindGame game;
    public EndWindow(MasterMindGame game)
    {
        super("End of the game");
        this.setSize(100,100);

        ImageIcon icon = new ImageIcon("./ball.png");
        this.setIconImage(icon.getImage());

        this.game = game;


        this.setVisible(true);
    }
}
