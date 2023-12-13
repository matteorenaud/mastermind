package model;

import java.util.ArrayList;
import java.util.Random;

public class MasterMindGame
{
    private int currentRound = 0;
    private int nbRoud; //Number of rounds to be played
    private int score = 0; //Global score of the game
    private int nbTry; //Number of try to guess the secret combination
    private int lineSize; //Number of cells in a combination
    private int colorCount; //Number of colors available
    private CluesMode cluesMode = CluesMode.EASY_MODE;
    private String playerName;
    private MasterMindBoard masterMindBoard;
    private ArrayList<GameColor> availableColors;

    private ArrayList<MasterMindGameObserver>lstGameObserver;

    public MasterMindGame(String playerName, int nbRoud,int lineSize,int colorCount,int nbTry,CluesMode cluesMode)
    {
        this.nbRoud=nbRoud;
        this.playerName=playerName;
        this.lineSize=lineSize;
        this.colorCount = colorCount;
        this.cluesMode=cluesMode;

        this.nbTry=nbTry;

        this.availableColors = new ArrayList<GameColor>();
        this.lstGameObserver=new ArrayList<MasterMindGameObserver>();

        generateListAvailableGameColor();
        generateNewRound();
    }

    public void addMasterMindGameObserver(MasterMindGameObserver o)
    {
        this.lstGameObserver.add(o);
    }
    public void removeMasterMindGameObserver(MasterMindGameObserver o)
    {
        this.lstGameObserver.remove(o);
    }

    //Method that creates a new round of the game by creating a whole new board
    public boolean generateNewRound()
    {
        this.currentRound++;

        if(currentRound<=nbRoud)
        {
            this.masterMindBoard = new MasterMindBoard(this.lineSize, nbTry, availableColors);
            notifyGameObserverNewRound();
        }
        else
        {
            endGame();
            return false;
        }
        return true;
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

    public void updateScore()
    {
        for(MasterMindLine line : this.masterMindBoard.getBoard())
        {
            this.score += line.getWellPlaced() + line.getWellPlaced() * 3;
        }

        if(this.cluesMode == CluesMode.EASY_MODE)
        {
            this.score += 4;
        }
    }
    public int getScore()
    {
        return this.score;
    }

    public ArrayList<GameColor> getAvailableColors()
    {
        return availableColors;
    }

    public int getCurrentRound()
    {
        return this.currentRound;
    }
    public void setActualRound(int round)
    {
        this.currentRound=round;
    }
    public int getNbTry(){return this.nbTry;}
    public int getNbRoud(){return this.nbRoud;}

    public CluesMode getCluesMode()
    {
        return this.cluesMode;
    }


    public void printInfoAboutGame()
    {
        System.out.println("    --> Jeu de Mastermind en ligne de commande\t <--");
        System.out.println("    --> Voici les paramêtres\t\t\t\t\t <--");
        System.out.println("    --> MasterMind Info\t\t\t\t\t\t\t <--");
        System.out.println("    --> Nom joueur : "+this.playerName+"\t\t\t\t\t\t <--");
        System.out.println("    --> Nb manches : "+this.nbRoud+"\t\t\t\t\t\t\t <--");
        System.out.println("    --> Nb tentatives/manches : "+this.nbTry+"\t\t\t\t <--");
        System.out.println("    --> Nb total couleur : " +this.colorCount+"\t\t\t\t\t <--");
        System.out.println("    --> Tour actuel : "+this.currentRound+"\t\t\t\t\t\t\t <--");
        System.out.println("    --> Score : "+this.score+"\t\t\t\t\t\t\t\t <--");
        printAvailableColor();
    }
    public void printAvailableColor()
    {
        System.out.println("Couleur disponible : ");
        for(int i=0;i<this.availableColors.size();i++)
            System.out.print(this.availableColors.get(i)+" ");
    }

    private void notifyGameObserverNewRound()
    {
        for(MasterMindGameObserver o:this.lstGameObserver)
        {
            o.updateActualRound(this.currentRound);
        }
    }

    public void endGame()
    {
        System.out.println("FIN DE LA PARTIE");
        notifyGameObserverEndGame();

    }
    private void notifyGameObserverEndGame()
    {
        for(MasterMindGameObserver o:this.lstGameObserver)
        {
            o.updateEndGame(this.score);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getLineSize() {
        return lineSize;
    }

    public int getLineCount() {
        return nbTry;
    }
}
