package view.GamePanels;

import model.CluesMode;

import javax.swing.*;
import java.awt.*;

public class pnlIndiceMode extends JPanel
{
    public pnlIndiceMode()
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JRadioButton rdbEasyMode=new JRadioButton("Mode facile");
        JRadioButton rdbClassicMode=new JRadioButton("Mode classique");
        JRadioButton rdbNumeric=new JRadioButton("Mode numérique");
        rdbEasyMode.addActionListener(ActionEvent->{
            updateIndiceMode(rdbEasyMode, CluesMode.EASY_MODE);
        });
        rdbClassicMode.addActionListener(ActionEvent->{
            updateIndiceMode(rdbClassicMode,CluesMode.CLASSIC_MODE);
        });
        rdbNumeric.addActionListener(ActionEvent->{
            updateIndiceMode(rdbNumeric,CluesMode.NUMERIC_MODE);
        });
        this.add(rdbEasyMode);
        this.add(rdbClassicMode);
        this.add(rdbNumeric);
    }

    private void updateIndiceMode(JRadioButton rdbActual,CluesMode indicesMode)
    {
        for (Component c: this.getComponents())
        {
            JRadioButton rdb=(JRadioButton)c;
            rdb.setSelected(false);
        }
        rdbActual.setSelected(true);

        this.repaint();
    }
}
