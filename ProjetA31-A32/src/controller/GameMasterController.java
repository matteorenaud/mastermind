package controller;

import model.GameColor;
import model.MasterMindBoard;
import model.MasterMindGame;
import view.GameWindow;
import view.StartWindow;

import java.util.ArrayList;
import java.util.List;

public class GameMasterController
{
    private StartWindow startWindow;
    private GameWindow gameWindow;
    //private EndWindow endWindow;
    private MasterMindGame game;

    public GameMasterController()
    {}
    public GameMasterController(StartWindow startWindow, GameWindow gameWindow/*, EndWindow endWindow*/)
    {
        this.startWindow=startWindow;
        this.gameWindow=gameWindow;
        //this.endWindow=endWindow;
    }

    public void setStartWindow(StartWindow startWindow)
    {
        this.startWindow=startWindow;
    }
    public void setGameWindow(GameWindow gameWindow)
    {
        this.startWindow=startWindow;
    }

    public void launchGame(String playerName,int nbRound,int nbPieceOfCombinaison,int nbTry,int nbTotalPiece)
    {
        startWindow.dispose();//Fermer
        this.game = new MasterMindGame(playerName,nbRound,nbPieceOfCombinaison,nbTotalPiece,nbTry);
        this.gameWindow=new GameWindow(this,playerName,nbRound,nbPieceOfCombinaison,nbTry,nbTotalPiece);
    }

    public ArrayList<GameColor> getAvailableColors()
    {
        return this.game.getAvailableColors();
    }

    public boolean verifyCurrentLine()
    {
        return game.getMasterMindBoard().verifyCurrentLine();
    }

    public boolean nextLine()
    {
        return game.getMasterMindBoard().nextLine();
    }

    public void setCurrentLineCellColor(GameColor color, int index)
    {
        this.game.getMasterMindBoard().getCurrentLine().setCellColor(color,index);
    }
}
