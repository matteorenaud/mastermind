package view;

import controller.GameMasterController;
import model.GameColor;
import model.TagComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame
{
    private GameMasterController controller;
    private int lineSize;
    private int lineCount;
    private int colorCount;
    private int nbRound;
    private int activeLine = 0;
    private String playerName;

    private JPanel boardPanel;

    public GameWindow(GameMasterController controller,String playerName,
                      int nbRound,int lineSize,int lineCount, int colorCount)
    {
        super("MasterMind");
        setSize(1000,900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran

        this.activeLine=lineCount-1;
        this.controller=controller;
        this.playerName=playerName;
        this.nbRound=nbRound;
        this.lineCount=lineCount;
        this.lineSize=lineSize;
        this.colorCount=colorCount;

        JPanel mainPanel = new JPanel();
        JPanel backPanel=new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel,BoxLayout.Y_AXIS));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel pnlInfoPlayer=new JPanel(new FlowLayout());
        JLabel lblPlayerName=new JLabel("Pseudo : "+playerName);
        lblPlayerName.setMinimumSize(new Dimension(50,50));
        lblPlayerName.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPlayerName.setForeground(Color.BLACK);
        lblPlayerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JLabel lblScore=new JLabel("Score : ");
        lblScore.setMinimumSize(new Dimension(50,50));
        lblScore.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lblScore.setForeground(Color.BLACK);
        lblScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        JLabel lblTitle=new JLabel("MasterMind");
        lblTitle.setMinimumSize(new Dimension(50,50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        pnlInfoPlayer.add(lblPlayerName);
        pnlInfoPlayer.add(lblTitle);
        pnlInfoPlayer.add(lblScore);
        backPanel.add(pnlInfoPlayer);
        backPanel.add(mainPanel);

        constraints.gridx = 0;
        constraints.gridy = 0;
        boardPanel=new JPanel();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.RED,5,true));
        boardPanel.setSize(300,600);
        boardPanel.setLayout(new BoxLayout(boardPanel,BoxLayout.Y_AXIS));
        constructTry(boardPanel);
        mainPanel.add(boardPanel,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JPanel pnlIndice=new JPanel();
        JLabel l=new JLabel("indices");
        pnlIndice.add(l);
        mainPanel.add(pnlIndice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JPanel pnlChoiceColor=new JPanel();
        pnlChoiceColor.setLayout(new FlowLayout());

        constructAvailableColor(pnlChoiceColor);
        mainPanel.add(pnlChoiceColor,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        JPanel pnlModeIndice=new JPanel();
        pnlModeIndice.setLayout(new BoxLayout(pnlModeIndice,BoxLayout.Y_AXIS));
        JRadioButton rdbEasyMode=new JRadioButton("Mode facile");
        JRadioButton rdbClassicMode=new JRadioButton("Mode classique");
        JRadioButton rdbNumeric=new JRadioButton("Mode numérique");
        pnlModeIndice.add(rdbEasyMode);
        pnlModeIndice.add(rdbClassicMode);
        pnlModeIndice.add(rdbNumeric);
        mainPanel.add(pnlModeIndice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        //JPanel pnlValidate=new JPanel();
        JButton btnValidate=new JButton("Valider");
        btnValidate.setMaximumSize(new Dimension(200,100));
        btnValidate.addActionListener(ActionEvent->{
            colorOfTheLine();
            System.out.println(controller.verifyCurrentLine());
            controller.nextLine();
            updateCombBox();
        });
        //pnlValidate.add(btnValidate);
        mainPanel.add(btnValidate,constraints);
        //mainPanel.add(pnlValidate);

        constraints.gridx = 1;
        constraints.gridy = 3;
        //JPanel pnlReset=new JPanel();
        JButton btnReset=new JButton("Réinitialiser");
        btnReset.setMaximumSize(new Dimension(200,100));
        //pnlReset.add(btnReset);
        mainPanel.add(btnReset,constraints);
        //mainPanel.add(pnlReset);

        constraints.gridx = 0;
        constraints.gridy = 4;
        JButton btnRestart=new JButton("Relancer une nouvelle partie");
        mainPanel.add(btnRestart,constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        JButton btnPassTurn=new JButton("Abandonner la manche actulle");
        mainPanel.add(btnPassTurn,constraints);


        add(backPanel);
        setVisible(true);
    }

    private void constructTry(JPanel pnlTry)
    {
        for(int i=0;i<this.lineCount;i++)
        {
            TagComponent pnlOneTry=new TagComponent();
            pnlOneTry.setLayout(new GridLayout(1,this.lineSize+1));
            constructOneTryLine(pnlOneTry);
            pnlOneTry.setTag(i);
            //je mets en actif juste la 1ère ligne
            if(i!=this.lineCount-1)
                for(Component cbo:pnlOneTry.getComponents())
                    cbo.setEnabled(false);

            pnlTry.add(pnlOneTry);
        }
    }
    private void constructOneTryLine(JPanel pnlOneTry)
    {
        List<GameColor>lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.lineSize;i++)
        {
            JComboBox cboOnePiece=new JComboBox<>();
            for(int j=0;j<this.colorCount;j++)
            {
                cboOnePiece.addItem(lstAvailableColor.get(j).toString());
            }

            pnlOneTry.add(cboOnePiece);
        }
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
            if(pnl.getClass()==TagComponent.class)
            {
                TagComponent pnlTheTry=(TagComponent) pnl;
                for(Component cbo : pnlTheTry.getComponents())
                {
                    cbo.setEnabled(false);
                }
            }
        }
        this.activeLine--;
        for(Component pnl:boardPanel.getComponents())
        {
            if(pnl.getClass()== TagComponent.class)
            {
                TagComponent pnlTheTry=(TagComponent) pnl;
                if(pnlTheTry.getTag()==activeLine) {

                    for (Component cbo : pnlTheTry.getComponents()) {
                        cbo.setEnabled(true);
                    }
                }
            }
        }
    }
    private void colorOfTheLine()
    {

        for (Component pnl : boardPanel.getComponents()) {
            if (pnl.getClass() == TagComponent.class) {
                TagComponent pnlTheTry = (TagComponent) pnl;
                if (pnlTheTry.getTag() == activeLine) {
                    Component[] components = pnlTheTry.getComponents();
                    for (int i = 0; i < components.length; i++) {
                        JComboBox cbo = (JComboBox) components[i];
                        controller.setCurrentLineCellColor(GameColor.values()[cbo.getSelectedIndex()], i);
                    }
                }
            }
        }
    }
}
