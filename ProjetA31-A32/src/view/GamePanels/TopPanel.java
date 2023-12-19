package view.GamePanels;

import model.Helpers;
import model.MasterMindGame;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel
{
    public TopPanel(String playerName,int nbRound, MasterMindGame masterMindGame)
    {
        JLabel lblTitle=new JLabel("MasterMind");
        lblTitle.setMinimumSize(new Dimension(50,50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));

        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(100);//Espace entre les composants
        JPanel pnlInfoPlayer=new JPanel(flowLayout);

        JPanel pnlTitle=new JPanel(new FlowLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //this.setLayout(new GridLayout(3,2));
        pnlTitle.add(lblTitle);
        this.add(pnlTitle);

        JPanel pnlInfoGame=new JPanel();
        pnlInfoPlayer.setLayout(new GridLayout(3,2));
        this.add(pnlInfoGame);

        JLabel lblPlayerName=new JLabel("Pseudo : "+playerName);
        lblPlayerName.setMinimumSize(new Dimension(50,50));
        lblPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPlayerName.setForeground(Color.BLACK);
        lblPlayerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblScore=new JLabel("Score : "+masterMindGame.getScore());
        lblScore.setMinimumSize(new Dimension(50,50));
        lblScore.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblScore.setForeground(Color.BLACK);
        lblScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        lblScore.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblRound=new JLabel("Tour "+masterMindGame.getCurrentRound()+" / "+nbRound);
        lblRound.setMinimumSize(new Dimension(50,50));
        lblRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblRound.setForeground(Color.BLACK);
        lblRound.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        lblRound.setHorizontalAlignment(SwingConstants.CENTER);

        pnlInfoGame.add(lblPlayerName);
        pnlInfoGame.add(lblScore);
        pnlInfoGame.add(lblRound);

        pnlInfoGame.setLayout(new GridLayout(2,2));

        JLabel lblNbPiece=new JLabel("Taille d'une combinaison : "+masterMindGame.getLineSize());
        lblNbPiece.setAlignmentX(CENTER_ALIGNMENT);
        lblNbPiece.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblNbPiece.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInfoGame.add(lblNbPiece);

        JLabel lblNbColor=new JLabel("Nombre de couleurs : "+masterMindGame.getColorCount());
        lblNbColor.setAlignmentX(CENTER_ALIGNMENT);
        lblNbColor.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblNbColor.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInfoGame.add(lblNbColor);

        JLabel lblCluesMode=new JLabel("Mode des indices : "+
                Helpers.translateCluesModeToFrench(masterMindGame.getCluesMode()));
        lblCluesMode.setAlignmentX(CENTER_ALIGNMENT);
        lblCluesMode.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
        lblCluesMode.setHorizontalAlignment(SwingConstants.CENTER);
        pnlInfoGame.add(lblCluesMode);

        pnlTitle.setBackground(new Color(216, 222, 152));
        pnlInfoGame.setBackground(new Color(216, 222, 152));

        this.add(pnlInfoGame);


    }
}
