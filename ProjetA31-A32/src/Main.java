import controller.GameMasterController;
import model.MasterMindBoard;
import view.StartWindow;

public class Main
{
    public static void main(String args[])
    {
        GameMasterController gmc=new GameMasterController();
        StartWindow startWindow=new StartWindow(gmc);
        gmc.setStartWindow(startWindow);
    }
}
