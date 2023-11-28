package model;

import java.util.ArrayList;
import java.util.Random;

public class MasterMindGame
{
    private int nbRoud;
    private int score;
    private int nbTry;
    private int lineSize;
    private int nbTotalPiece;
    private String playerName;
    private MasterMindBoard masterMindBoard;
    private ArrayList<GameColor> availableColors;

    public MasterMindGame(String playerName, int nbRoud,int nbPieceOfCombinaison,int nbTotalPiece,int nbTry)
    {
        this.nbRoud=nbRoud;
        this.score=0;
        this.playerName=playerName;
        this.lineSize=nbPieceOfCombinaison;
        this.nbTotalPiece = nbTotalPiece;

        this.nbTry=nbTry;

        this.availableColors = new ArrayList<GameColor>();

        generateListAvailableGameColor();
        generateNewRound();
    }

    public void generateNewRound()
    {
        this.masterMindBoard=new MasterMindBoard(this.lineSize,nbTry,availableColors);
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
        for(int i=0;i<nbTotalPiece;i++)
        {
            this.availableColors.add(GameColor.values()[i]);
        }
    }

    public ArrayList<GameColor> getAvailableColors()
    {
        return availableColors;
    }


}
