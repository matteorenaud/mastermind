package model;

import java.util.ArrayList;
import java.util.Random;

public class MasterMindGame
{
    private int actualRound=0;
    private int nbTotalPiece;
    private int nbRoud; //Number of rounds to be played
    private int score; //Global score of the game
    private int nbTry; //Number of try to guess the secret combination
    private int lineSize; //Number of cells in a combination
    private int colorCount; //Number of colors available
    private String playerName;
    private MasterMindBoard masterMindBoard;
    private ArrayList<GameColor> availableColors;

    public MasterMindGame(String playerName, int nbRoud,int lineSize,int colorCount,int nbTry)
    {
        this.nbRoud=nbRoud;
        this.score=0;
        this.playerName=playerName;
        this.lineSize=lineSize;
        this.colorCount = colorCount;

        this.nbTry=nbTry;

        this.availableColors = new ArrayList<GameColor>();

        generateListAvailableGameColor();
        generateNewRound();
    }

    //Method that creates a new round of the game by creating a whole new board
    public void generateNewRound()
    {
        this.actualRound++;
        this.masterMindBoard=new MasterMindBoard(this.lineSize,nbTry,availableColors);
    }

    //Function that gets the MasterMindBoard
    public MasterMindBoard getMasterMindBoard()
    {
        return this.masterMindBoard;
    }

    //Method that generates a list with all the colors available in the current game
    public void generateListAvailableGameColor()
    {
        for(int i=0;i<this.colorCount;i++)
        {
            this.availableColors.add(GameColor.values()[i]);
        }
    }

    public ArrayList<GameColor> getAvailableColors()
    {
        return availableColors;
    }

    public int getActualRound()
    {
        return this.actualRound;
    }
    public void setActualRound(int round)
    {
        this.actualRound=round;
    }
    public int getNbTry(){return this.nbTry;}
    public int getNbRoud(){return this.nbRoud;}

    public void printInfoAboutGame()
    {
        System.out.println("    --> Jeu de Mastermind en ligne de commande\t <--");
        System.out.println("    --> Voici les paramêtres\t\t\t\t\t <--");
        System.out.println("    --> MasterMind Info\t\t\t\t\t\t\t <--");
        System.out.println("    --> Nom joueur : "+this.playerName+"\t\t\t\t\t\t <--");
        System.out.println("    --> Nb manches : "+this.nbRoud+"\t\t\t\t\t\t\t <--");
        System.out.println("    --> Nb tentatives/manches : "+this.nbTry+"\t\t\t\t <--");
        System.out.println("    --> Nb total couleur : " +this.nbTotalPiece+"\t\t\t\t\t <--");
        System.out.println("    --> Tour actuel : "+this.actualRound+"\t\t\t\t\t\t\t <--");
        System.out.println("    --> Score : "+this.score+"\t\t\t\t\t\t\t\t <--");
        printAvailableColor();
    }
    public void printAvailableColor()
    {
        System.out.println("Couleur disponible : ");
        for(int i=0;i<this.availableColors.size();i++)
            System.out.print(this.availableColors.get(i)+" ");
    }

}
