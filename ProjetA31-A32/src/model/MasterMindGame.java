package model;

import java.util.ArrayList;
import java.util.Random;

public class MasterMindGame
{
    private int nbRoud;
    private int score;
    private int nbPieceOfCombinaison;
    int nbTotalPiece;
    int nbTry;
    private String playerName;
    private MasterMindBoard masterMindBoard;
    private ArrayList<GameColor> availableColors;

    public MasterMindGame(String playerName, int nbRoud,int nbPieceOfCombinaison,int nbTotalPiece,int nbTry)
    {
        this.nbRoud=nbRoud;
        this.score=0;
        this.playerName=playerName;
        this.nbPieceOfCombinaison=nbPieceOfCombinaison;
        this.nbTotalPiece=nbTotalPiece;
        this.nbTry=nbTry;

        this.availableColors = new ArrayList<GameColor>();

        generateListAvailableGameColor();
        generateNewRound();
    }

    public void generateNewRound()
    {
        this.masterMindBoard=new MasterMindBoard(nbPieceOfCombinaison,nbTry,availableColors);
    }

    public MasterMindBoard getMasterMindBoard()
    {
        return this.masterMindBoard;
    }

    public void endGame()
    {

    }

    public void generateListAvailableGameColor()
    {
        Random rand = new Random();
        for(int i=0;i<nbTotalPiece;i++)
        {
            boolean containsSameValue=true;
            while(containsSameValue)
            {
                int value =rand.nextInt(GameColor.values().length);

                if(!availableColors.contains(GameColor.values()[value]))
                {
                    this.availableColors.add(GameColor.values()[value]);
                    containsSameValue=false;
                }
            }
        }
    }

    public ArrayList<GameColor> getAvailableColors()
    {
        return availableColors;
    }


}
