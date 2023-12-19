package view;

import model.Helpers;
import model.MasterMindGame;

import javax.swing.*;
import java.awt.*;

public class EndWindow extends JFrame {
    private MasterMindGame game;
    public EndWindow(MasterMindGame game)
    {
        super("Fin de la partie");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        ImageIcon icon = new ImageIcon("../images/ball.png");
        this.setIconImage(icon.getImage());

        this.game = game;

        JPanel backPanel=new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));
        this.add(backPanel);

        JLabel lblRes=new JLabel("Résultat de la partie !");
        lblRes.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        backPanel.add(lblRes);

        JLabel lblScore=new JLabel("Votre score : "+game.getScore());
        backPanel.add(lblScore);

        JPanel pnlRecapSettings=new JPanel();
        pnlRecapSettings.setLayout(new GridLayout(3,2));

        JLabel lblName=new JLabel(game.getPlayerName());
        pnlRecapSettings.add(lblName);

        JLabel lblNbRound=new JLabel("Nombre de manches : "+game.getNbRoud());
        pnlRecapSettings.add(lblNbRound);

        JLabel lblNbTryPerRound=new JLabel("Tentatives par manche : "+game.getNbTry());
        pnlRecapSettings.add(lblNbTryPerRound);

        JLabel lblNbColor=new JLabel("Nombre de couleurs : "+game.getColorCount());
        pnlRecapSettings.add(lblNbColor);

        JLabel lblCluesMode=new JLabel("Mode des indices : "+ Helpers.translateCluesModeToFrench(game.getCluesMode()).toUpperCase());
        pnlRecapSettings.add(lblCluesMode);

        backPanel.add(pnlRecapSettings);

        JButton btnNewGame=new JButton("Relancer une partie");
        backPanel.add(btnNewGame);

        JButton btnExit=new JButton("Quitter");
        backPanel.add(btnExit);

        this.setVisible(true);
    }
}
