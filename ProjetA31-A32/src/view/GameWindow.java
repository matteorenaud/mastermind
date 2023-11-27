package view;

import controller.GameMasterController;
import model.GameColor;
import model.TagComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameWindow extends JFrame
{
    private GameMasterController controller;
    private String playerName;
    private int nbRound;
    private int nbPieceOfCombinaison;
    private int nbTry;
    private int nbTotalPiece;

    public GameWindow(GameMasterController gmc,String playerName,int nbRound,int nbPieceOfCombinaison,int nbTry, int nbTotalPiece)
    {
        super("MasterMind");
        setSize(800,900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran

        this.controller=gmc;
        this.playerName=playerName;
        this.nbRound=nbRound;
        this.nbTry=nbTry;
        this.nbPieceOfCombinaison=nbPieceOfCombinaison;
        this.nbTotalPiece=nbTotalPiece;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        JPanel pnlTry=new JPanel();
        pnlTry.setBorder(BorderFactory.createLineBorder(Color.RED,5,true));
        pnlTry.setSize(300,600);
        pnlTry.setLayout(new BoxLayout(pnlTry,BoxLayout.Y_AXIS));
        constructTry(pnlTry);
        mainPanel.add(pnlTry,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        JPanel pnlIndice=new JPanel();
        JLabel l=new JLabel("indices");
        pnlIndice.add(l);
        mainPanel.add(pnlIndice,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        JPanel pnlChoiceColor=new JPanel();
        pnlChoiceColor.setLayout(new FlowLayout());
        //JLabel d=new JLabel("choix couleur");
        //pnlChoiceColor.add(d);
        constructAvailableColor(pnlChoiceColor);
        mainPanel.add(pnlChoiceColor,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
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
        constraints.gridy = 2;
        //JPanel pnlValidate=new JPanel();
        JButton btnValidate=new JButton("Valider");
        btnValidate.setMaximumSize(new Dimension(200,100));
        //pnlValidate.add(btnValidate);
        mainPanel.add(btnValidate,constraints);
        //mainPanel.add(pnlValidate);

        constraints.gridx = 1;
        constraints.gridy = 2;
        //JPanel pnlReset=new JPanel();
        JButton btnReset=new JButton("Réinitialiser");
        btnReset.setMaximumSize(new Dimension(200,100));
        //pnlReset.add(btnReset);
        mainPanel.add(btnReset,constraints);
        //mainPanel.add(pnlReset);

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

        add(mainPanel);
        setVisible(true);
    }

    private void constructTry(JPanel pnlTry)
    {
        for(int i=0;i<nbTry;i++)
        {
            JPanel pnlOneTry=new JPanel(new GridLayout(1,this.nbPieceOfCombinaison+1));
            constructOneTryLine(pnlOneTry);
            TagComponent tag=new TagComponent(i);
            tag.setMaximumSize(new Dimension(0,0));
            pnlOneTry.add(tag);
            //je mets en actif juste la 1ère ligne
            if(i!=this.nbTry-1)
                for(Component cbo:pnlOneTry.getComponents())
                    cbo.setEnabled(false);

            pnlTry.add(pnlOneTry);
        }
    }
    private void constructOneTryLine(JPanel pnlOneTry)
    {
        List<GameColor>lstAvailableColor=this.controller.getLstAvailableColor();
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
        List<GameColor>lstAvailableColor=this.controller.getLstAvailableColor();
        for(int i=0;i<this.nbTotalPiece;i++)
        {
            JLabel lblOneColor=new JLabel("Color "+lstAvailableColor.get(i).toString());
            pnlChoiceColor.add(lblOneColor);
        }
    }

}
