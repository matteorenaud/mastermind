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
            System.out.println("====================Tour n°" + game.getActualRound() + " sur " + game.getNbRoud()+"==================");
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
                            tabColor[i] = GameColor.RED;
                            ok = true;
                        } else if (saisie.contains("GREEN")) {
                            tabColor[i] = GameColor.GREEN;
                            ok = true;
                        } else if (saisie.contains("BLUE")) {
                            tabColor[i] = GameColor.BLUE;
                            ok = true;
                        } else if (saisie.contains("YELLOW")) {
                            tabColor[i] = GameColor.YELLOW;
                            ok = true;
                        } else if (saisie.contains("ORANGE")) {
                            tabColor[i] = GameColor.ORANGE;
                            ok = true;
                        } else if (saisie.contains("PINK")) {
                            tabColor[i] = GameColor.PINK;
                            ok = true;
                        } else if (saisie.contains("GREY")) {
                            tabColor[i] = GameColor.GREY;
                            ok = true;
                        } else if (saisie.contains("WHITE")) {
                            tabColor[i] = GameColor.WHITE;
                            ok = true;
                        } else
                            System.out.print("Veulliez entrer une couleur qui existe :");
                    }


                }
                boolean find = game.getMasterMindBoard().verifyCurrentLine(tabColor);
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
                if(game.getActualRound() == game.getNbRoud())
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
