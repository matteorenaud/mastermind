package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterMindBoard
{
    private int lineCount;
    private int lineSize;
    private int currentLine;
    private GameColor[] secretCombination;
    private GameColor[][] board;
    public MasterMindBoard(int lineSize, int lineCount,String playerName,int nbRound,int nbPieceOfCombinaison,int nbTry, int nbTotalPiece)
    {
        this.lineSize = lineSize;
        this.lineCount = lineCount;

        this.playerName=playerName;
        this.nbRound=nbRound;
        this.nbTry=nbTry;
        this.nbPieceOfCombinaison=nbPieceOfCombinaison;
        this.nbTotalPiece=nbTotalPiece;
        this.currentLine = lineCount-1;

        this.secretCombination = new GameColor[lineSize];
        this.board = new GameColor[lineCount][lineSize];

        generateLstAvailableGameColor();
        GenerateSecretCombination();

        PrintBoard();
        PrintSecretCombination();
        printGameInfo();
    }

    public boolean NextLine()
    {
        this.currentLine--;

        if(this.currentLine >= this.lineCount)
        {
            return false;
        }

        return true;
    }
    public boolean VerifyCurrentLine(/*Info*/)//c quoi  le Info ?????
    {
        int nbWellPlaced = 0;

        for(int i=0; i<this.lineSize; i++)
        {
            if(GetCellColor(currentLine,i) != this.secretCombination[i])
            {

            }
        }

        if(nbWellPlaced == this.lineSize)
        {
            return true;
        }

        return false;
    }
    public GameColor GetCellColor(int line, int column)
    {
        return this.board[line][column];
    }
    public void SetCellColor(GameColor color, int line, int column)
    {
        this.board[line][column] = color;
    }
    public void GenerateSecretCombination()
    {
        int value;
        Random rand = new Random();
        for(int i=0; i<this.lineSize; i++)
        {
            //value = rand.nextInt(GameColor.values().length);
            value= rand.nextInt(this.lstAvailableColor.size());
            this.secretCombination[i] = GameColor.values()[value];
        }
    }

    public void PrintBoard()
    {
        for(int i=0; i<this.lineCount; i++)
        {
            System.out.println();
            for(int j=0; j<this.lineSize; j++)
            {
                System.out.print("[ " + this.GetCellColor(i,j) + " ]");
            }
            System.out.println();
        }
    }
    public void printGameInfo()
    {
        System.out.println("Nom joueur : "+playerName);
        System.out.println("Nb couleurs : "+nbTotalPiece);
        System.out.println("Piece d'une combinaison : "+nbPieceOfCombinaison);
        System.out.println("Nb manches : "+nbRound);
        System.out.println("Nb Tentative : "+nbTry);
    }

    public void PrintSecretCombination()
    {
        for(int i=0; i<this.lineSize;i++)
        {
            System.out.print(this.secretCombination[i] + " ");
        }

        System.out.println();
    }
    /////////
    private List<GameColor>lstAvailableColor=new ArrayList<>();
    private String playerName;
    private int nbRound;
    private int nbPieceOfCombinaison;
    private int nbTry;
    private int nbTotalPiece;
    public void generateLstAvailableGameColor()
    {
        Random rand = new Random();
        for(int i=0;i<nbTotalPiece;i++)
        {
            boolean containsSameValue=true;
            while(containsSameValue)
            {
                int value =rand.nextInt(GameColor.values().length);

                if(!lstAvailableColor.contains(GameColor.values()[value]))
                {
                    this.lstAvailableColor.add(GameColor.values()[value]);
                    containsSameValue=false;
                }
            }


        }
    }
    public List<GameColor> getLstAvailableColor()
    {
        return this.lstAvailableColor;
    }
}
