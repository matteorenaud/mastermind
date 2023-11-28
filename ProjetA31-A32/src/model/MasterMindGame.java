package model;

public class MasterMindGame
{
    private int nbRoud;
    private int score;
    private int nbPieceOfCombinaison;
    int nbTotalPiece;
    int nbTry;
    private String playerName;
    private MasterMindBoard masterMindBoard;

    public MasterMindGame(String playerName, int nbRoud,int nbPieceOfCombinaison,int nbTotalPiece,int nbTry)
    {
        this.nbRoud=nbRoud;
        this.score=0;
        this.playerName=playerName;
        this.nbPieceOfCombinaison=nbPieceOfCombinaison;
        this.nbTotalPiece=nbTotalPiece;
        this.nbTry=nbTry;

        generateNewRound();
    }

    public MasterMindBoard getMasterMindBoard()
    {
        return this.masterMindBoard;
    }

    public void generateNewRound()
    {
        this.masterMindBoard=new MasterMindBoard(nbPieceOfCombinaison,nbTry,playerName,nbRoud,nbPieceOfCombinaison,nbTry,nbTotalPiece);
    }

    public void endGame()
    {

    }

}
