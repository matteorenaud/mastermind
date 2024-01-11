package view.gamePanels;

import javax.swing.*;

//Un Panel classique mais avec un tag en plus pour identifier le numéros de la ligne
public class LinePanel extends JPanel
{
    private int tag;
    public LinePanel(int tag)
    {
        this.tag = tag;
    }
    public int getTag()
    {
        return this.tag;
    }
}
