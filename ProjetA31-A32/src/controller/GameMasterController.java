package controller;

import model.GameColor;
import model.MasterMindBoard;
import model.MasterMindGame;
import view.EndWindow;
import view.GameWindow;
import view.StartWindow;

import java.util.ArrayList;
import java.util.List;

public class GameMasterController
{
    private StartWindow startWindow; //Start window where the player select the game parameters
    private GameWindow gameWindow; //Game window where the mastermind is played
    private EndWindow endWindow; //End window where the score is displayed
    private MasterMindGame game;

    //Method used by the main class to start a new game
    //the method creates a new start window for the player to select the game parameters
    public void startNewGame()
    {
        this.startWindow = new StartWindow(this);
    }

    //Method that is used by the view to launch the game
    //with the selected parameters
    public void launchGame(String playerName,int nbRound,int lineSize,int lineCount,int colorCount)
    {
        //startWindow.dispose();
        this.game = new MasterMindGame(playerName,nbRound,lineSize,colorCount,lineCount);
        this.gameWindow=new GameWindow(this,playerName,nbRound,lineSize,lineCount,colorCount);
    }

    //Method that ends the current game
    public void endGame()
    {

    }

    //Function used to get the list of all availables colors
    public ArrayList<GameColor> getAvailableColors()
    {
        return this.game.getAvailableColors();
    }

    //Function used to verify the state of the current line
    //returns true if the line is equal to the secret combination
    public boolean verifyCurrentLine(GameColor[] tab)
    {
        game.getMasterMindBoard().getCurrentLine().printAllInformationsAboutTheLine(game.getMasterMindBoard().getSecretCombination());
        return game.getMasterMindBoard().verifyCurrentLine(tab);
    }

    //Function that selects the next line in the MasterMinBoard
    //return true if the next line exists
    public boolean nextLine()
    {
        return game.getMasterMindBoard().nextLine();
    }

    //Method to set the color of a cell of the current line
    public void setCurrentLineCellColor(GameColor color, int index)
    {
        this.game.getMasterMindBoard().getCurrentLine().setCellColor(color,index);
    }

    public int getCurentLineWellPlaced()
    {
        return this.game.getMasterMindBoard().getCurrentLine().getWellPlaced();
    }

    public int getCurrentLineWellChosen()
    {
        return this.game.getMasterMindBoard().getCurrentLine().getWellChosen();
    }

    public int getCurrentLineWrongColor()
    {
        return this.game.getMasterMindBoard().getCurrentLine().getWrongColor();
    }

}
