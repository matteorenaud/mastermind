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
        this.cellInfos = new ArrayList<CellInfo>();

        for(int i=0; i<size; i++)
        {
            this.cells.add(GameColor.NONE);
            this.cellInfos.add(CellInfo.NONE);
        }
    }

    //Function that return true if the line is equal to the secret combination
    //the function also fills the cellInfos array
    public boolean verify(MasterMindLine secretCombination)
    {
        int wellPlaced = 0;

        for(int i=0; i<this.size; i++)
        {
            if(this.getCellColor(i) == secretCombination.getCellColor(i))
            {
                this.cellInfos.set(i,CellInfo.WELL_PLACED);
                wellPlaced ++;
            }
        }

        for(int i=0; i<this.size; i++)
        {
            if(this.cellInfos.get(i) != CellInfo.WELL_PLACED)
            {
                for(int j=0; j<this.size; j++)
                {
                    if(this.cellInfos.get(j) != CellInfo.WELL_PLACED && this.getCellColor(i) == secretCombination.getCellColor(j))
                    {
                        this.cellInfos.set(i,CellInfo.GOOD_COLOR);
                    }
                }
            }

            if(this.cellInfos.get(i) != CellInfo.GOOD_COLOR && this.cellInfos.get(i) != CellInfo.WELL_PLACED)
            {
                this.cellInfos.set(i, CellInfo.NOT_PRESENT);
            }
        }

        return wellPlaced == this.size;

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

    //Function that returns the number of well placed cells
    public int getWellPlaced()
    {
        int nb = 0;

        for(int i=0; i<this.size; i++)
        {
            if(this.cellInfos.get(i) == CellInfo.WELL_PLACED)
            {
                nb++;
            }
        }
        return nb;
    }

    //Function that returns the number of well vhosen cells
    public int getWellChosen()
    {
        int nb = 0;

        for(int i=0; i<this.size; i++)
        {
            if(this.cellInfos.get(i) == CellInfo.GOOD_COLOR)
            {
                nb++;
            }
        }
        return nb;
    }

    //Function that returns the number of wrongly chose cells
    public int getWrongColor()
    {
        int nb = 0;

        for(int i=0; i<this.size; i++)
        {
            if(this.cellInfos.get(i) == CellInfo.NOT_PRESENT)
            {
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<GameColor> getCells()
    {
        return cells;
    }

    public void printAllInformationsAboutTheLine(MasterMindLine secretCombination)
    {
        System.out.print("\t| Récapitulatif de la ligne");
        System.out.print("\n\t| Couleur de la ligne : \n\t| ");
        for(int i=0;i<this.cells.size();i++)
            System.out.print(cells.get(i)+" ");
        System.out.print("\n\t| Combinaison secrète : \n\t| ");
        for(int i=0;i<this.cells.size();i++)
            System.out.print(secretCombination.getCellColor(i)+" ");
        System.out.print("\n\t| Indices : \n\t| ");
        for(int i=0;i<this.cells.size();i++)
            System.out.print(cellInfos.get(i)+" ");
        System.out.print("\n\t| Bien placé : "+this.getWellPlaced());
        System.out.print(" Mal placé : "+this.getWellChosen());
        System.out.println(" Non présent : "+this.getWrongColor());
    }
    public ArrayList<CellInfo> getCellInfos()
    {
        return this.cellInfos;
    }
}
