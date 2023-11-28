package model;

import javax.swing.*;
import java.awt.*;

//tag fait maison parce que yen a pas en java swing mamamia mario et luigi c terrible
//tjr fait maison
public class TagComponent extends JPanel
{
    private int tag;
    public TagComponent()
    {

    }
    public int getTag()
    {
        return this.tag;
    }
    public void setTag(int newTag)
    {
        this.tag=newTag;
    }
}
