import model.GameColor;
import model.MasterMindGame;

import java.util.Scanner;

public class CommandLineMain
{
    static Scanner sc=new Scanner(System.in);

    public static void main(String args[])
    {
        MasterMindGame game=new MasterMindGame("Joueur",3,4,4,10);

        game.printInfoAboutGame();

        boolean jeu=true;
        boolean manche=true;

        while(jeu) {
            manche=true;

            System.out.println("\n====================================================");
            System.out.println("====================Tour n°" + game.getCurrentRound() + " sur " + game.getNbRoud()+"==================");
            System.out.println("====================================================");
            while (manche)
            {
                System.out.println("Combinaison secrète");
                game.getMasterMindBoard().printSecretCombination();

                System.out.println("\nTentative n°" + game.getMasterMindBoard().getIndexCurrentLine() + " sur " + game.getNbTry());

                GameColor[] tabColor = new GameColor[game.getAvailableColors().size()];
                game.printAvailableColor();
                for (int i = 0; i < tabColor.length; i++) {
                    boolean ok = false;
                    while (!ok) {
                        System.out.print("\nEntrer couleur " + i + " : ");
                        String saisie;
                        saisie = sc.nextLine();
                        if (saisie.contains("RED")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.RED, i);
                            ok = true;
                        } else if (saisie.contains("GREEN")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.GREEN, i);
                            ok = true;
                        } else if (saisie.contains("BLUE")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.BLUE, i);
                            ok = true;
                        } else if (saisie.contains("YELLOW")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.YELLOW, i);
                            ok = true;
                        } else if (saisie.contains("ORANGE")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.ORANGE, i);
                            ok = true;
                        } else if (saisie.contains("PINK")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.PINK, i);
                            ok = true;
                        } else if (saisie.contains("GREY")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.GREY, i);
                            ok = true;
                        } else if (saisie.contains("WHITE")) {
                            game.getMasterMindBoard().getCurrentLine().setCellColor(GameColor.WHITE, i);
                            ok = true;
                        } else
                            System.out.print("Veulliez entrer une couleur qui existe :");
                    }


                }
                boolean find = game.getMasterMindBoard().verifyCurrentLine();
                System.out.println("Résultat : "+find);
                game.getMasterMindBoard().getCurrentLine().printAllInformationsAboutTheLine(game.getMasterMindBoard().getSecretCombination());

                boolean doitOnRelancerUneManche=false;

                //Si on a trouvé
                if (find == true)
                {
                    System.out.println("Vous avez trouver");
                    doitOnRelancerUneManche=true;
                    manche=false;
                }

                //Si on utiliser toutes les tentatives
                if (game.getMasterMindBoard().nextLine() == false)
                {
                    System.out.println("Manche PERDU (plus de tentatives)");
                    manche=false;
                    doitOnRelancerUneManche=true;
                }
                //Fin du jeu
                if(game.getCurrentRound() == game.getNbRoud())
                {
                    jeu=false;
                    manche=false;
                }

                if(doitOnRelancerUneManche)
                {
                    game.generateNewRound();
                    System.out.println("Nouvelle Manche");
                }
            }
        }

        System.out.print("Fin du Jeu");

    }
}
