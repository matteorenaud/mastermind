package view;

import controller.GameMasterController;
import jdk.jfr.Event;
import model.Helpers;
import model.MasterMindGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndWindow extends JFrame {
    private MasterMindGame game;
    private GameMasterController controller;
    public EndWindow(MasterMindGame game, GameMasterController gmc)
    {
        super("Fin de la partie");
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        ImageIcon icon = new ImageIcon("./ProjetA31-A32/images/TresSecret.png");
        this.setIconImage(icon.getImage());

        this.game = game;
        this.controller=gmc;

        JPanel backPanel=new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));
        this.add(backPanel);

        JLabel lblRes=new JLabel("Résultat de la partie !");
        lblRes.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        lblRes.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRes.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblRes);

        /*
        try
        {
            BufferedImage myPicture = ImageIO.read(new File("./ProjetA31-A32/images/mastermind.jpg"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setMaximumSize(new Dimension(200,175));
            picLabel.setSize(200,200);
            backPanel.add(picLabel);

        }
        catch (IOException e)
        {

        }*/

        JLabel lblScore=new JLabel("Votre score : "+game.getScore());
        lblScore.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(lblScore);

        JPanel pnlRecapSettings=new JPanel();
        pnlRecapSettings.setLayout(new GridLayout(3,2));

        JLabel lblName=new JLabel("Votre pseudo : "+game.getPlayerName());
        lblName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblName);

        JLabel lblNbRound=new JLabel("Nombre de manches : "+game.getNbRoud());
        lblNbRound.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbRound.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblNbRound);

        JLabel lblNbTryPerRound=new JLabel("Tentatives par manche : "+game.getNbTry());
        lblNbTryPerRound.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblNbTryPerRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbTryPerRound.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblNbTryPerRound);

        JLabel lblNbColor=new JLabel("Nombre de couleurs : "+game.getColorCount());
        lblNbColor.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblNbColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbColor.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblNbColor);

        JLabel lblSizeCombination=new JLabel("Taille combinaison : "+game.getLineSize());
        lblSizeCombination.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblSizeCombination.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSizeCombination.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblSizeCombination);

        JLabel lblCluesMode=new JLabel("Mode des indices : "+ Helpers.translateCluesModeToFrench(game.getCluesMode()).toUpperCase());
        lblCluesMode.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblCluesMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCluesMode.setHorizontalAlignment(SwingConstants.CENTER);
        pnlRecapSettings.add(lblCluesMode);

        backPanel.add(pnlRecapSettings);

        JButton btnNewGame=new JButton("Relancer une partie");
        btnNewGame.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewGame.addActionListener(ActionEvent->{
            controller.startNewGame();
        });
        backPanel.add(btnNewGame);
        backPanel.add(Box.createVerticalStrut(15));

        JButton btnExit=new JButton("Quitter");
        btnExit.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(ActionEvent->{
            controller.shutDownApp();
        });
        backPanel.add(btnExit);
        backPanel.add(Box.createVerticalStrut(15));


        this.setVisible(true);
    }
}
