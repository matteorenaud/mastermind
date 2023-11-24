package model;

import java.awt.*;
import java.util.Random;

public class MasterMindBoard {

    private int lineCount;
    private int lineSize;
    private int currentLine = 0;
    private Color[] secretCombination;
    private Color[][] board;
    public MasterMindBoard(int lineSize, int lineCount)
    {
        this.lineSize = lineSize;
        this.lineCount = lineCount;

        this.secretCombination = new Color[lineSize];
        this.board = new Color[lineCount][lineSize];

        GenerateSecretCombination();
    }

    public void NextLine()
    {
        currentLine++;
    }
    public void VerifyCurrentLine()
    {

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
            secretCombination[i] = Color.values()[value];
        }
    }
}
