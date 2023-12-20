package view.GamePanels;

import controller.GameMasterController;
import model.GameColor;
import helpersLib.Helpers;

import javax.swing.*;
import java.awt.*;
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

    //Construit une ligne d'essai
    private void constructLine(JPanel pnlLine)
    {
        List<GameColor> lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.lineSize;i++)
        {
            JComboBox comboBox = new JComboBox<>();

            comboBox.setSize(new Dimension(200,40));
            comboBox.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            comboBox.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));

            for(int j=0;j<this.controller.getAvailableColors().size();j++)
            {
                comboBox.addItem(
                Helpers.translateColorToFrench(lstAvailableColor.get(j)).toUpperCase());
            }

            comboBox.setSelectedIndex(-1);

            comboBox.addItemListener(e -> {
                int idx=comboBox.getSelectedIndex();
                if(idx!=-1) {
                    GameColor[] colors = GameColor.values();
                    GameColor color = colors[idx];
                    Color backColor = Helpers.transformGameColorIntoColor(color);
                    comboBox.setBackground(backColor);
                }
            });

            pnlLine.add(comboBox);
        }
    }
}
