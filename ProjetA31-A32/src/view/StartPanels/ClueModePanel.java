package view.StartPanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.swing.*;
import java.awt.*;

public class ClueModePanel extends JPanel
{
    private GameMasterController controller;
    private JRadioButton rdbEasyMode;
    private JRadioButton rdbClassicMode;
    private JRadioButton rdbNumeric;
    public ClueModePanel(GameMasterController controller)
    {
        this.controller = controller;

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        rdbEasyMode=new JRadioButton("Mode facile");
        rdbEasyMode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        rdbClassicMode=new JRadioButton("Mode classique");
        rdbClassicMode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        rdbNumeric=new JRadioButton("Mode numérique");
        rdbNumeric.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        rdbEasyMode.setSelected(true);


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

        this.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void updateIndiceMode(JRadioButton rdbActual,CluesMode indicesMode)
    {
        for (Component c: this.getComponents())
        {
            JRadioButton rdb = (JRadioButton)c;
            rdb.setSelected(false);
        }

        rdbActual.setSelected(true);

        this.repaint();
    }

    public CluesMode getSelectedCluesMode()
    {
        CluesMode cluesMode;
        if(rdbEasyMode.isSelected())
            cluesMode=CluesMode.EASY_MODE;
        else if(rdbClassicMode.isSelected())
            cluesMode=CluesMode.CLASSIC_MODE;
        else
            cluesMode=CluesMode.NUMERIC_MODE;
        return cluesMode;
    }
}
