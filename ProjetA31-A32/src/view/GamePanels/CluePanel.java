package view.GamePanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CluePanel extends JPanel
{
    private GameMasterController controller;
    private JPanel pnlEasyClassicMode;
    private JPanel pnlNumeric;
    private ArrayList<JLabel> clues;
    private int lineCount;

    public CluePanel(int lineCount, int lineSize, GameMasterController controller)
    {
        this.controller = controller;

        this.lineCount = lineCount;

        this.pnlEasyClassicMode = new JPanel();
        this.pnlEasyClassicMode.setLayout(new BoxLayout(pnlEasyClassicMode,BoxLayout.Y_AXIS));

        this.pnlNumeric = new JPanel();
        this.pnlNumeric.setLayout(new BoxLayout(pnlNumeric,BoxLayout.Y_AXIS));

        this.clues = new ArrayList<JLabel>();

        for(int i=0;i<lineCount;i++)
        {
            JLabel lbl  =new JLabel("");
            lbl.setOpaque(true);
            clues.add(lbl);
            pnlNumeric.add(lbl);
            pnlEasyClassicMode.add(lbl);
        }


        updateClues(lineCount-1);
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

        if(this.controller.getCurrentGameCluesMode() == CluesMode.NUMERIC_MODE)
        {
            this.add(pnlNumeric);
        }
        else
        {
            this.add(pnlEasyClassicMode);
        }
    }

    public void updateClues(int line)
    {
        JLabel clue = this.clues.get(line);

        clue.setText(
                "Bien placé : "
                        + this.controller.getCurrentLineWellPlaced()
                        + "Mal placé : " +
                        this.controller.getCurrentLineWellChosen());

        pnlNumeric.add(clue);
    }


}
