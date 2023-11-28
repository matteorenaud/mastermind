package model;

import java.util.ArrayList;

public class MasterMindLine {

    private int size; //Size of the line
    private ArrayList<GameColor> cells; //Cells of the line
    private int wellPlaced = 0; //Number of cells well-placed compared to the secret combination
    private int wellChosen = 0; //Number of cells with a color present in the secret combination

    public MasterMindLine(int size)
    {
        this.size = size;
        this.cells = new ArrayList<GameColor>();

        for(int i=0; i<size; i++)
        {
            this.cells.add(GameColor.NONE);
        }
    }

    //Function that return true if the line is equal to the secret combination
    //the function also fills the wellPLace and wellChosen attributes
    public boolean verify(MasterMindLine secretCombination)
    {
        this.wellPlaced = 0;
        this.wellChosen = 0;

        for(int i=0; i<this.size; i++)
        {
            if(this.getCellColor(i) == secretCombination.getCellColor(i))
            {
                this.wellPlaced ++;
            }

            if(this.cells.contains(secretCombination.getCellColor(i)))
            {
                this.wellChosen ++;
            }
        }

        return this.wellPlaced == this.size;
    }

    //Function that gets the GameColor of a specific cell
    public GameColor getCellColor(int index) throws ArrayIndexOutOfBoundsException
    {
        if(index >= size)
        {
            throw new ArrayIndexOutOfBoundsException("The size of the array is : " + this.size);
        }

        return this.cells.get(index);
    }

    //Function that sets the GameColor of a specific cell
    public void setCellColor(GameColor color, int index) throws ArrayIndexOutOfBoundsException
    {
        if(index >= size)
        {
            throw new ArrayIndexOutOfBoundsException("The size of the array is : " + this.size);
        }

        this.cells.set(index,color);
    }

    public int getWellPlaced() {
        return wellPlaced;
    }

    public int getWellChosen() {
        return wellChosen;
    }
}
