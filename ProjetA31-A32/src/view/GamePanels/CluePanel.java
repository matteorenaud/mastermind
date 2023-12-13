package view.GamePanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CluePanel extends JPanel
{
    private GameMasterController controller;
    private ArrayList<JLabel> clues;

    public CluePanel(int lineCount, int lineSize, GameMasterController controller)
    {
        this.controller = controller;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.clues = new ArrayList<JLabel>();

        for(int i=0;i<lineCount;i++)
        {
            JLabel lbl  = new JLabel("------------");
            lbl.setOpaque(true);
            lbl.setSize(0,40);

            clues.add(lbl);
            this.add(lbl);
        }

/*
        for(int i=0;i<lineCount;i++)
        {
            JPanel clue = new JPanel();
            clue.setLayout(new FlowLayout());
            for(int j=0;j<lineSize;j++)
            {
                JLabel lbl  =new JLabel(" ");
                lbl.setOpaque(true);
                lbl.setBackground(Color.BLACK);
                clue.add(lbl);
            }
            pnlEasyClassicMode.add(clue);
        }
        */
    }

    public void updateClues(int line)
    {
        JLabel clue = this.clues.get(line);

        if(this.controller.getCurrentGameCluesMode() == CluesMode.NUMERIC_MODE)
        {
            clue.setText(
                    "Bien placé : "
                            + this.controller.getCurrentLineWellPlaced()
                            + "Mal placé : " +
                            this.controller.getCurrentLineWellChosen());
        }
    }
}
