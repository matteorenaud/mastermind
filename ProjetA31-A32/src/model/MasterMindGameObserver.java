package model;

public interface MasterMindGameObserver
{
    void updateActualRound(int actualRound);
    void updateEndGame(int score);

}
