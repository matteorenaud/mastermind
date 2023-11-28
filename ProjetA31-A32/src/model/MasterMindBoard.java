package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterMindBoard
{

    private int lineCount; //Number of lines in the board
    private int lineSize; //Size of a board line
    private int currentLine; //Index of the currentLine
    private MasterMindLine secretCombination; //Secret combination of the current board
    private ArrayList<MasterMindLine> board; //Board that contains all the lines
    private ArrayList<GameColor> availableColors; //Available colors

    public MasterMindBoard(int lineSize, int lineCount, ArrayList<GameColor> availableColors)
    {
        this.lineSize = lineSize;
        this.lineCount = lineCount;

        this.availableColors = availableColors;
        this.board = new ArrayList<MasterMindLine>();
        this.secretCombination = new MasterMindLine(this.lineSize);

        generateBoardLines();
        generateSecretCombination();
    }

    //Function that select the next line of the board
    //the function returns true if the next line exists
    public boolean nextLine()
    {
        this.currentLine++;

        return this.currentLine < this.lineCount;
    }

    private void generateBoardLines()
    {
        for(int i=0; i<this.lineCount; i++)
        {
            this.board.add(new MasterMindLine(this.lineSize));
        }
    }

    //Method that fills the secretCombination with random colors
    //within the available colors list
    private void generateSecretCombination()
    {
        int value;

        Random rand = new Random();

        for(int i=0; i<this.lineSize; i++)
        {
            value= rand.nextInt(this.availableColors.size());
            this.secretCombination.setCellColor(this.availableColors.get(value),i);
        }
    }

    public MasterMindLine getCurrentLine()
    {
        return this.board.get(this.currentLine);
    }

    public MasterMindLine getSecretCombination()
    {
        return this.secretCombination;
    }

    public void printBoard()
    {
        for(int i=0; i<this.lineCount; i++)
        {
            System.out.println();
            for(int j=0; j<this.lineSize; j++)
            {
                System.out.print("[ " + this.board.get(i).getCellColor(j) + " ]");
            }
            System.out.println();
        }
    }

    public void printSecretCombination()
    {
        for(int i=0; i<this.lineSize;i++)
        {
            System.out.print(this.secretCombination.getCellColor(i) + " ");
        }

        System.out.println();
    }
}
