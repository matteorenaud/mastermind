package model;

import java.util.ArrayList;

public class MasterMindLine {

    private int size; //Size of the line
    private ArrayList<GameColor> cells; //Cells of the line
    private ArrayList<CellInfo> cellInfos; //Infos on each cell

    public MasterMindLine(int size)
    {
        this.size = size;
        this.cells = new ArrayList<GameColor>();
        this.cellInfos = new ArrayList<>(cellInfos)();

        for(int i=0; i<size; i++)
        {
            this.cells.add(GameColor.NONE);
            this.cellInfos.add(CellInfo.NOT_PRESENT);
        }
    }

    //Function that return true if the line is equal to the secret combination
    //the function also fills the wellPLace and wellChosen attributes
    public boolean verify(MasterMindLine secretCombination)
    {
        for(int i=0; i<this.size; i++)
        {
            if(this.getCellColor(i) == secretCombination.getCellColor(i))
            {
                this.cellInfos.set(i,CellInfo.WELL_PLACED);
            }
            else
            {
                for(int j=0; j<this.size; j++)
                {
                    if(this.cellInfos.get(j) != CellInfo.WELL_PLACED)
                    {

                    }
                }
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

    public int getWellPlaced()
    {
        return wellPlaced;
    }

    public int getWellChosen()
    {
        return wellChosen;
    }

    public ArrayList<GameColor> getCells()
    {
        return cells;
    }
}
