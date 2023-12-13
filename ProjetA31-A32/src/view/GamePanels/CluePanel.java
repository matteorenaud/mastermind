package view.GamePanels;

import javax.swing.*;
import java.awt.*;

public class pnlIndice extends JPanel
{
    private JPanel pnlEasyClassicMode;
    private JPanel pnlNumeric;
    public pnlIndice(int lineCount, int lineSize)
    {
        this.pnlEasyClassicMode = new JPanel();
        this.pnlEasyClassicMode.setLayout(new BoxLayout(pnlEasyClassicMode,BoxLayout.Y_AXIS));

        this.pnlNumeric = new JPanel();
        this.pnlNumeric.setLayout(new BoxLayout(pnlNumeric,BoxLayout.Y_AXIS));

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
        //pour mode numérique
        for(int i=0;i<lineCount;i++)
        {
            JPanel clue = new JPanel();
            clue.setLayout(new FlowLayout());
            JLabel lblGoodPlace=new JLabel("Bien placé : ");
            JLabel lblBadPlace=new JLabel("Mal placé : ");

            clue.add(lblGoodPlace);
            clue.add(lblBadPlace);
            pnlNumeric.add(clue);
        }
    }
}
