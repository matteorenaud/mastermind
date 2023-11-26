package model;

import java.awt.*;
import java.util.Random;

public class MasterMindBoard
{
    private int lineCount;
    private int lineSize;
    private int currentLine;
    private Color[] secretCombination;
    private Color[][] board;
    public MasterMindBoard(int lineSize, int lineCount)
    {
        this.lineSize = lineSize;
        this.lineCount = lineCount;

        this.currentLine = lineCount-1;

        this.secretCombination = new Color[lineSize];
        this.board = new Color[lineCount][lineSize];

        GenerateSecretCombination();
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
    public boolean VerifyCurrentLine(Info)
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
    public Color GetCellColor(int line, int column)
    {
        return this.board[line][column];
    }
    public void SetCellColor(Color color, int line, int column)
    {
        this.board[line][column] = color;
    }
    public void GenerateSecretCombination()
    {
        int value;
        Random rand = new Random();
        for(int i=0; i<this.lineSize; i++)
        {
            value = rand.nextInt(Color.values().length);
            this.secretCombination[i] = Color.values()[value];
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

    public void PrintSecretCombination()
    {
        for(int i=0; i<this.lineSize;i++)
        {
            System.out.print(this.secretCombination[i] + " ");
        }

        System.out.println();
    }
}
