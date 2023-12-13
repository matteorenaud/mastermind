package view;

import controller.GameMasterController;
import model.GameColor;
import model.MasterMindGame;
import model.MasterMindGameObserver;
import view.GamePanels.*;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameWindow extends JFrame implements MasterMindGameObserver
{
    //------------------------------------------------------------------
    private GameMasterController controller;
    private MasterMindGame masterMindGame;
    private int lineSize;
    private int lineCount;
    private int colorCount;
    private int nbRound;
    private int activeLine;
    private String playerName;

    private JPanel boardPanel;
    private JPanel pnlModeIndice;
    private JPanel pnlIndice;
    private JPanel pnlNumeric;
    private JPanel pnlEasyClassicMode;
    private JLabel lblRound;
    //------------------------------------------------------------------

    public GameWindow(GameMasterController controller,MasterMindGame game,String playerName,
                      int nbRound,int lineSize,int lineCount, int colorCount)
    {
        super("MasterMind");
        setSize(1100,900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.activeLine = lineCount-1;
        this.controller = controller;
        this.masterMindGame = game;
        this.playerName = playerName;
        this.nbRound = nbRound;
        this.lineCount = lineCount;
        this.lineSize = lineSize;
        this.colorCount = colorCount;

        JPanel backPanel=new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));

        MainPanel mainPanel = new MainPanel();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;


        //backPanel.add(pnlInfoPlayer);
        backPanel.add(new TopPanel(this.playerName,this.nbRound,this.masterMindGame));
        backPanel.add(mainPanel);

        constraints.gridx = 0;
        constraints.gridy = 0;

        boardPanel= new BoardPanel(this.lineCount,this.lineSize,this.controller);
        mainPanel.add(boardPanel,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        CluePanel pnlClue = new CluePanel(this.lineCount,this.lineSize,this.controller);

        mainPanel.add(pnlClue,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JPanel pnlChoiceColor=new JPanel();
        pnlChoiceColor.setLayout(new FlowLayout());

        constructAvailableColor(pnlChoiceColor);
        mainPanel.add(pnlChoiceColor,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        //pnlModeIndice=new ClueModePanel(this.controller);

        //mainPanel.add(pnlModeIndice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        JButton btnValidate=new JButton("Valider");
        btnValidate.setMaximumSize(new Dimension(200,100));

        btnValidate.addActionListener(ActionEvent->{


            GameColor[] lineColor=colorOfTheLine();
            System.out.println("Couleur récupére");
            for (int i=0;i<lineColor.length;i++)
            {
                System.out.print(lineColor[i]+" ");
                controller.setCurrentLineCellColor(lineColor[i],i);
            }
            controller.verifyCurrentLine();
            pnlClue.updateClues(activeLine);
            controller.nextLine();

            updateCombBox();

        });
        mainPanel.add(btnValidate,constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        JButton btnReset=new JButton("Réinitialiser");
        btnReset.setMaximumSize(new Dimension(200,100));
        mainPanel.add(btnReset,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        JButton btnRestart=new JButton("Relancer une nouvelle partie");
        mainPanel.add(btnRestart,constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        JButton btnPassTurn=new JButton("Abandonner la manche actulle");
        btnPassTurn.addActionListener(ActionEvent->{
            newGame();
        });
        mainPanel.add(btnPassTurn,constraints);


        add(backPanel);
        setVisible(true);
    }
    private void constructAvailableColor(JPanel pnlChoiceColor)
    {
        List<GameColor>lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.colorCount;i++)
        {
            JLabel lblOneColor=new JLabel("Color "+lstAvailableColor.get(i).toString());
            pnlChoiceColor.add(lblOneColor);
        }
    }
    private void updateCombBox()
    {
        for(Component pnl:boardPanel.getComponents())
        {
            if(pnl.getClass()== LinePanel.class)
            {
                LinePanel pnlTheTry=(LinePanel) pnl;
                for(Component cbo : pnlTheTry.getComponents())
                {
                    cbo.setEnabled(false);
                }
            }
        }
        this.activeLine--;
        for(Component pnl:boardPanel.getComponents())
        {
            if(pnl.getClass()== LinePanel.class)
            {
                LinePanel pnlTheTry=(LinePanel) pnl;
                if(pnlTheTry.getTag()==activeLine) {

                    for (Component cbo : pnlTheTry.getComponents()) {
                        cbo.setEnabled(true);
                    }
                }
            }
        }
    }
    private GameColor[] colorOfTheLine()
    {
        GameColor[] lineColor=new GameColor[this.lineSize];

        for (Component pnl : boardPanel.getComponents())
        {
            if (pnl.getClass() == LinePanel.class)
            {
                LinePanel pnlTheTry = (LinePanel) pnl;
                if (pnlTheTry.getTag() == activeLine)
                {
                    Component[] components = pnlTheTry.getComponents();
                    for (int i = 0; i < components.length; i++)
                    {
                        JComboBox cbo = (JComboBox) components[i];
                        lineColor[i]=GameColor.values()[cbo.getSelectedIndex()];
                    }

                }
            }
        }
        return lineColor;
    }

    private void updateAllIndicesMode()
    {

    }

    private void newGame()
    {
        controller.newRound(this.playerName, this.nbRound, this.lineSize, this.lineCount, this.colorCount);
    }


    @Override
    public void updateActualRound(int actualRound)
    {
        //controller.newRound(this.playerName, this.nbRound, this.lineSize, this.lineCount, this.colorCount);
    }
    @Override
    public void updateEndGame(int score)
    {
        controller.endGame();
    }
}