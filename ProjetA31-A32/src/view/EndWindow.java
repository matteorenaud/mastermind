package view;

import controller.GameMasterController;
import helpersLib.Helpers;
import model.MasterMindGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndWindow extends JFrame {
    private MasterMindGame game;
    private GameMasterController controller;
    private Color originalColorButton;
    public EndWindow(MasterMindGame game, GameMasterController gmc)
    {
        super("Fin de la partie");
        this.setSize(700,950);
        this.setResizable(false);//Comme la fenêtre de début, pas de modification de la taille
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("./ProjetA31-A32/images/icon_end_game.png");
        this.setIconImage(icon.getImage());

        this.game = game;
        this.controller=gmc;

        JPanel backPanel=new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));
        backPanel.setBackground(new Color(215, 222, 141));
        this.add(backPanel);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblRes=new JLabel("Résultat de la partie !");
        lblRes.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
        lblRes.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRes.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblRes);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblScore=new JLabel("Votre score : "+game.getScore());
        lblScore.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        lblScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        backPanel.add(lblScore);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblName=new JLabel("Votre pseudo : "+game.getPlayerName());
        lblName.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblName);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblNbRound=new JLabel("Nombre de manches : "+game.getNbRoud());
        lblNbRound.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbRound.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblNbRound);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblNbTryPerRound=new JLabel("Tentatives par manche : "+game.getNbTry());
        lblNbTryPerRound.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblNbTryPerRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbTryPerRound.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblNbTryPerRound);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblNbColor=new JLabel("Nombre de couleurs : "+game.getColorCount());
        lblNbColor.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblNbColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbColor.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblNbColor);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblSizeCombination=new JLabel("Taille combinaison : "+game.getLineSize());
        lblSizeCombination.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblSizeCombination.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSizeCombination.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblSizeCombination);

        backPanel.add(Box.createVerticalStrut(15));

        JLabel lblCluesMode=new JLabel("Mode des indices : "+ Helpers.translateCluesModeToFrench(game.getCluesMode()).toUpperCase());
        lblCluesMode.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,22));
        lblCluesMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblCluesMode.setHorizontalAlignment(SwingConstants.CENTER);
        backPanel.add(lblCluesMode);

        backPanel.add(Box.createVerticalStrut(15));

        try
        {
            BufferedImage myPicture = ImageIO.read(new File("./ProjetA31-A32/images/homer_simpson.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setMaximumSize(new Dimension(500,400));
            picLabel.setHorizontalAlignment(SwingConstants.CENTER);
            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            backPanel.add(picLabel);
        }
        //Si ne trouve pas l'image, un petit Label à la place
        catch (IOException e)
        {
            JLabel label=new JLabel("MASTERMIND");
            label.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            backPanel.add(label);
        }

        backPanel.add(Box.createVerticalStrut(15));

        JButton btnNewGame=new JButton("Relancer une partie");
        btnNewGame.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewGame.addActionListener(ActionEvent->{
            controller.startNewGame();
        });
        this.originalColorButton=btnNewGame.getBackground();
        createMyEndGameButtonMouseHoverEvent(btnNewGame);
        backPanel.add(btnNewGame);
        backPanel.add(Box.createVerticalStrut(15));

        JButton btnExit=new JButton("Quitter");
        btnExit.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(ActionEvent->{
            controller.shutDownApp();
        });
        createMyEndGameButtonMouseHoverEvent(btnExit);
        backPanel.add(btnExit);
        backPanel.add(Box.createVerticalStrut(15));

        this.setVisible(true);
    }

    //Applique le curseur de main et le fait que le boutton change de couleur quand on survole avec la souris un boutton
    private void createMyEndGameButtonMouseHoverEvent(JButton button)
    {
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(245, 175, 175));
        }

        public void mouseExited(MouseEvent e) {
            button.setBackground(originalColorButton);
        }
    });
    }
}
