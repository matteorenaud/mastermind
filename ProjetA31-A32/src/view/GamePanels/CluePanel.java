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
    private ArrayList<Component> clues;


    public CluePanel(int lineCount, int lineSize, GameMasterController controller,MasterMindGame game)
    {
        this.controller = controller;
        this.game=game;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.clues = new ArrayList<Component>();

        if(game.getCluesMode()==CluesMode.NUMERIC_MODE) {

            for (int i = 0; i < lineCount; i++) {
                JLabel lbl = new JLabel("---------------");
                lbl.setOpaque(true);
                lbl.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
                this.add(Box.createVerticalStrut(10));//Espace vertical vide


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
                    JLabel lbl = new JLabel("  ");
                    lbl.setOpaque(false);
                    lbl.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,30));
                    clue.add(lbl);
                }
                clue.setBackground(new Color(140, 218, 218));

                clues.add(clue);
                this.add(clue);
            }
        }

    }

    public void updateClues(int line)
    {
        Component c = this.clues.get(line+1);

        if(this.game.getCluesMode() == CluesMode.NUMERIC_MODE)
        {
            JLabel clue=(JLabel)c;
            clue.setText(
                        "Bien placé : "
                        + this.game.getMasterMindBoard().getCurrentLine().getWellPlaced()
                        + " | Mal placé : "
                        + this.game.getMasterMindBoard().getCurrentLine().getWellChosen());
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
                    l.setOpaque(true);
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
                    if(i>=nbWellPlace)
                        l.setBackground(Color.BLACK);
                    else
                        l.setBackground(Color.WHITE);
                    i++;
                }
            }
        }
    }
}
