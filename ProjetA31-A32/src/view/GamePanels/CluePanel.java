package view.GamePanels;

import controller.GameMasterController;
import model.CellInfo;
import model.CluesMode;
import model.MasterMindGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CluePanel extends JPanel
{
    private GameMasterController controller;
    private MasterMindGame game;
    private ArrayList<Component/*JLabel*/> clues;


    public CluePanel(int lineCount, int lineSize, GameMasterController controller,MasterMindGame game)
    {
        this.controller = controller;
        this.game=game;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.clues = new ArrayList<Component/*JLabel*/>();

        if(game.getCluesMode()==CluesMode.NUMERIC_MODE) {

            for (int i = 0; i < lineCount; i++) {
                JLabel lbl = new JLabel("------------");
                lbl.setOpaque(true);
                lbl.setSize(0, 40);

                clues.add(lbl);
                this.add(lbl);
            }
        }
        else
        {
            for (int i = 0; i < lineCount; i++) {
                JPanel clue = new JPanel();
                clue.setLayout(new FlowLayout());
                for (int j = 0; j < lineSize; j++) {
                    JLabel lbl = new JLabel(" ");
                    lbl.setOpaque(true);
                    lbl.setBackground(Color.BLACK);
                    clue.add(lbl);
                }
                clues.add(clue);
                this.add(clue);
            }
        }

    }

    public void updateClues(int line)
    {
        Component c = this.clues.get(line);

        if(this.game.getCluesMode() == CluesMode.NUMERIC_MODE)
        {
            JLabel clue=(JLabel)c;
            clue.setText(
                        "Bien placé : "
                        + this.game.getMasterMindBoard().getCurrentLine().getWellPlaced()
                        + " | Bonne couleur : " +
                        this.game.getMasterMindBoard().getCurrentLine().getWellChosen()
                        + " | Mauvaise couleur : " +
                        this.game.getMasterMindBoard().getCurrentLine().getWrongColor());
        }
        else
        {
            JPanel p=(JPanel) c;
            ArrayList<CellInfo> info=game.getMasterMindBoard().getCurrentLine().getCellInfos();
            int i=0;

            if (this.game.getCluesMode()==CluesMode.EASY_MODE)
            {
                for (Component cc : p.getComponents()) {
                    JLabel l = (JLabel) cc;
                    if (info.get(i) == CellInfo.WELL_PLACED)
                        l.setBackground(Color.WHITE);
                    else
                        l.setBackground(Color.BLACK);
                    i++;
                }
            }
            else
            {
                i=0;
                int nbWellPlace=game.getMasterMindBoard().getCurrentLine().getWellPlaced();
                for (Component cc : p.getComponents())
                {
                    JLabel l = (JLabel) cc;
                    if(i<nbWellPlace)
                        l.setBackground(Color.BLACK);
                    else
                        l.setBackground(Color.WHITE);
                    i++;
                }
            }
        }
    }
}
