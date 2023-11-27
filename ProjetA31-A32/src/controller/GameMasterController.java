package controller;

import model.GameColor;
import model.MasterMindBoard;
import view.GameWindow;
import view.StartWindow;

import java.util.List;

public class GameMasterController
{
    private StartWindow startWindow;
    private GameWindow gameWindow;
    //private EndWindow endWindow;
    private MasterMindBoard masterMindBoard;

    public GameMasterController()
    {}
    public GameMasterController(StartWindow startWindow, GameWindow gameWindow/*, EndWindow endWindow*/,MasterMindBoard masterMindBoard)
    {
        this.startWindow=startWindow;
        this.gameWindow=gameWindow;
        //this.endWindow=endWindow;
        this.masterMindBoard=masterMindBoard;
    }

    public void setStartWindow(StartWindow startWindow)
    {
        this.startWindow=startWindow;
    }
    public void setGameWindow(GameWindow gameWindow)
    {
        this.startWindow=startWindow;
    }
    public void setMasterMindBoard(MasterMindBoard masterMindBoard)
    {
        this.masterMindBoard=masterMindBoard;
    }

    public void launchGame(String playerName,int nbRound,int nbPieceOfCombinaison,int nbTry,int nbTotalPiece)
    {
        startWindow.dispose();//Fermer
        MasterMindBoard masterMindBoard=new MasterMindBoard(nbPieceOfCombinaison,nbTry,playerName,nbRound, nbPieceOfCombinaison, nbTry, nbTotalPiece);
        this.setMasterMindBoard(masterMindBoard);
        this.gameWindow=new GameWindow(this,playerName,nbRound,nbPieceOfCombinaison,nbTry,nbTotalPiece);
    }

    public List<GameColor> getLstAvailableColor()
    {
        return this.masterMindBoard.getLstAvailableColor();
    }
}
