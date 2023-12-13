package view.GamePanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.swing.*;
import java.awt.*;

public class ClueModePanel extends JPanel
{
    private GameMasterController controller;
    public ClueModePanel(GameMasterController controller)
    {
        this.controller = controller;

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JRadioButton rdbEasyMode=new JRadioButton("Mode facile");
        JRadioButton rdbClassicMode=new JRadioButton("Mode classique");
        JRadioButton rdbNumeric=new JRadioButton("Mode numérique");

        switch (this.controller.getCurrentGameCluesMode())
        {
            case EASY_MODE -> rdbEasyMode.setSelected(true);
            case CLASSIC_MODE -> rdbClassicMode.setSelected(true);
            default -> rdbNumeric.setSelected(true);
        }

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
            JRadioButton rdb = (JRadioButton)c;
            rdb.setSelected(false);
        }

        rdbActual.setSelected(true);

        this.controller.setCurrentGameCluesMode(indicesMode);

        this.repaint();
    }
}
