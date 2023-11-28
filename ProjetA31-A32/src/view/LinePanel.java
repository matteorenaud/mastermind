package view;

import javax.swing.*;

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
