package view.GamePanels;

import controller.GameMasterController;
import model.GameColor;
import view.LinePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoardPanel extends JPanel
{
    private GameMasterController controller;
    private int lineSize;
    private int lineCount;
    public BoardPanel(int lineCount, int lineSize, GameMasterController controller)
    {
        this.controller = controller;
        this.lineCount = lineCount;
        this.lineSize = lineSize;

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for(int i=0;i<lineCount;i++)
        {
            LinePanel pnlOneTry=new LinePanel(i);
            pnlOneTry.setLayout(new GridLayout(1,lineSize+1));
            constructOneTryLine(pnlOneTry);

            if(i!=lineCount-1)
                for(Component cbo:pnlOneTry.getComponents())
                    cbo.setEnabled(false);

            this.add(pnlOneTry);
        }
    }

    private void constructOneTryLine(JPanel pnlOneTry)
    {
        List<GameColor> lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.lineSize;i++)
        {
            JComboBox cboOnePiece=new JComboBox<>();
            cboOnePiece.setSize(new Dimension(200,40));
            for(int j=0;j<this.controller.getAvailableColors().size();j++)
            {
                cboOnePiece.addItem(lstAvailableColor.get(j).toString());
            }

            pnlOneTry.add(cboOnePiece);
        }
    }

}
