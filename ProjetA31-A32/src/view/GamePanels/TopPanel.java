package view.GamePanels;

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

        JPanel pnlInfoPlayer=new JPanel(new FlowLayout());
        JPanel pnlTitle=new JPanel(new FlowLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        pnlTitle.add(lblTitle);
        this.add(pnlTitle);
        this.add(pnlInfoPlayer);

        JLabel lblPlayerName=new JLabel("Pseudo : "+playerName);
        lblPlayerName.setMinimumSize(new Dimension(50,50));
        lblPlayerName.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPlayerName.setForeground(Color.BLACK);
        lblPlayerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JLabel lblScore=new JLabel("Score : "+masterMindGame.getScore());
        lblScore.setMinimumSize(new Dimension(50,50));
        lblScore.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblScore.setForeground(Color.BLACK);
        lblScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JLabel lblRound=new JLabel("Tour "+masterMindGame.getCurrentRound()+" / "+nbRound);
        lblRound.setMinimumSize(new Dimension(50,50));
        lblRound.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblRound.setForeground(Color.BLACK);
        lblRound.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        pnlInfoPlayer.add(lblPlayerName);
        pnlInfoPlayer.add(lblScore);
        pnlInfoPlayer.add(lblRound);
    }
}
