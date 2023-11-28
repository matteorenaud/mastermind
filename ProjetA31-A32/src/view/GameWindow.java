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
    private String playerName;
    private int nbRound;
    private int nbPieceOfCombinaison;
    private int nbTry;
    private int nbTotalPiece;
    private int activeLine=0;
    private JPanel pnlTry;


    public GameWindow(GameMasterController gmc,String playerName,int nbRound,int nbPieceOfCombinaison,int nbTry, int nbTotalPiece)
    {
        super("MasterMind");
        setSize(1000,900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran

        this.activeLine=nbTry-1;

        this.controller=gmc;
        this.playerName=playerName;
        this.nbRound=nbRound;
        this.nbTry=nbTry;
        this.nbPieceOfCombinaison=nbPieceOfCombinaison;
        this.nbTotalPiece=nbTotalPiece;

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
        pnlTry=new JPanel();
        pnlTry.setBorder(BorderFactory.createLineBorder(Color.RED,5,true));
        pnlTry.setSize(300,600);
        pnlTry.setLayout(new BoxLayout(pnlTry,BoxLayout.Y_AXIS));
        constructTry(pnlTry);
        mainPanel.add(pnlTry,constraints);

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
        //JLabel d=new JLabel("choix couleur");
        //pnlChoiceColor.add(d);
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

            ArrayList<GameColor>lineColor=colorOfTheLine();
            for(int i=0;i<lineColor.size();i++)
                controller.getGame().getMasterMindBoard().getCurrentLine().setCellColor(lineColor.get(i),i);
            boolean find=controller.checkLine();
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
        /*
        choisir les couleurs de sa prochaine combinaison
        valider sa combinaison pour recevoir l'indice de l'ordinateur
        remettre à zéro sa combinaison
        abandonner la manche courante pour passer à la suivante
        modifier le mode d'affichage des indices :

        soit en mode "facile" : les jetons noirs et blancs sont affichés en correspondance de la combinaison proposée par le joueur (i.e. à la même place)
        soit en mode "classique" (mode par défaut) : les jetons noirs sont affichés en premier, puis les jetons blancs
        soit en mode numérique : on affiche le nombre de pions bien placés et le nombre de pions mal placés.

        Bonus : pouvoir recommencer une partie sans relancer l'application.
         */

        add(backPanel);
        setVisible(true);
    }

    private void constructTry(JPanel pnlTry)
    {
        for(int i=0;i<nbTry;i++)
        {
            TagComponent pnlOneTry=new TagComponent();
            pnlOneTry.setLayout(new GridLayout(1,this.nbPieceOfCombinaison+1));
            constructOneTryLine(pnlOneTry);
            pnlOneTry.setTag(i);
            //je mets en actif juste la 1ère ligne
            if(i!=this.nbTry-1)
                for(Component cbo:pnlOneTry.getComponents())
                    cbo.setEnabled(false);

            pnlTry.add(pnlOneTry);
        }
    }
    private void constructOneTryLine(JPanel pnlOneTry)
    {
        List<GameColor>lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<nbPieceOfCombinaison;i++)
        {
            JComboBox cboOnePiece=new JComboBox<>();
            for(int j=0;j<this.nbTotalPiece;j++)
            {
                cboOnePiece.addItem(lstAvailableColor.get(j).toString());
            }

            pnlOneTry.add(cboOnePiece);
        }
    }
    private void constructAvailableColor(JPanel pnlChoiceColor)
    {
        List<GameColor>lstAvailableColor=this.controller.getAvailableColors();
        for(int i=0;i<this.nbTotalPiece;i++)
        {
            JLabel lblOneColor=new JLabel("Color "+lstAvailableColor.get(i).toString());
            pnlChoiceColor.add(lblOneColor);
        }
    }
    private void updateCombBox()
    {

        for(Component pnl:pnlTry.getComponents())
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
        for(Component pnl:pnlTry.getComponents())
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
    private ArrayList<GameColor>colorOfTheLine()
    {
        ArrayList<GameColor>lst=new ArrayList<>();
        for(Component pnl:pnlTry.getComponents())
        {
            if(pnl.getClass()== TagComponent.class)
            {
                TagComponent pnlTheTry=(TagComponent) pnl;
                if(pnlTheTry.getTag()==activeLine)
                {
                    for(Component c:pnlTheTry.getComponents())
                    {
                        JComboBox cbo=(JComboBox) c;
                        lst.add(GameColor.values()[cbo.getSelectedIndex()]);
                    }
            }
        }
        }
            return lst;

    }

}
