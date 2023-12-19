package view.GamePanels;

import controller.GameMasterController;
import model.GameColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel
{
    //-----------------------------------------------------------------------------
    private GameMasterController controller;
    private int lineSize;
    //-----------------------------------------------------------------------------
    public BoardPanel(int lineCount, int lineSize, GameMasterController controller)
    {
        this.controller = controller;
        this.lineSize = lineSize;

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        for(int i=0;i<lineCount;i++)
        {
            LinePanel pnlLine = new LinePanel(i);

            pnlLine.setLayout(new GridLayout(1,lineSize+1));

            constructLine(pnlLine);

            if(i!=lineCount-1)

                for(Component cbo:pnlLine.getComponents())

                    cbo.setEnabled(false);

            this.add(pnlLine);
        }
    }

    private void constructLine(JPanel pnlLine)
    {
        List<GameColor> lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.lineSize;i++)
        {
            JComboBox comboBox = new JComboBox<>();

            comboBox.setSize(new Dimension(200,40));

            for(int j=0;j<this.controller.getAvailableColors().size();j++)
            {
                comboBox.addItem(lstAvailableColor.get(j).toString());
            }

            pnlLine.add(comboBox);
        }
    }

    public void updateLine(ArrayList<GameColor> colors)
    {
        for(int i=0; i<this.lineSize; i++)
        {
            
        }
    }

}
