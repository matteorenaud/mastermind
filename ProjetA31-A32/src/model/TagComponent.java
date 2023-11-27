package model;

import java.awt.*;

//tag fait maison parce que yen a pas en java swing mamamia mario et luigi c terrible
//tjr fait maison
public class TagComponent extends Component
{
    private int tag;
    public TagComponent(int tag)
    {
        this.tag=tag;
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
