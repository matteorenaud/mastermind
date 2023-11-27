package view;

import controller.GameMasterController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartWindow extends JFrame//evidemment faut hériter
{
    private GameMasterController controller;
    public StartWindow(GameMasterController gmc)
    {
        super("Menu Principal");//ou setTitle("Menu Principal");
        this.setSize( 600, 800 );
        this.setLocationRelativeTo(null);//Fentêre qui apprait au milieu de l'écran

        this.controller=gmc;

        //On mets une taille minimum, comme cela pas de problème avec le fait que c'est trop petit
        this.setMinimumSize(new Dimension(600,700));

        JPanel panel=new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setBackground(new Color(195, 211, 250));
        //USINE USINE USINE USINE FACTORY PATRON DE CONCEPTION USINE
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(50,50,50,50),
                BorderFactory.createLineBorder(new Color(255, 36, 36, 168),10,true)));
        //Pour faire un genre de padding

        //BoxLayout, car on veut tout placer à la vertical (les uns au dessus des autres)
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel lblTitle=new JLabel("Mastermind");
        lblTitle.setMinimumSize(new Dimension(50,50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        panel.add(lblTitle);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JLabel lblPlayerName=new JLabel("Entrer votre nom :");
        lblPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPlayerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(lblPlayerName);

        JTextArea txtPlayerName=new JTextArea();
        txtPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPlayerName.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,20));
        txtPlayerName.setSize(new Dimension(400,30));
        txtPlayerName.setMaximumSize(new Dimension(400,30));
        panel.add(txtPlayerName);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JLabel lblNbRound=new JLabel("Nombre de manche :");
        lblNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbRound.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(lblNbRound);

        JComboBox cboNbRound=new JComboBox(new String[]{"3", "4", "5"});
        cboNbRound.setSelectedIndex(0);//Je mets sur la position 0 (le 3)
        cboNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        cboNbRound.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        cboNbRound.setMaximumSize(new Dimension(100,50));
        panel.add(cboNbRound);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JLabel lblNbTotalPiece=new JLabel("Nombre de couleurs :");
        lblNbTotalPiece.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbTotalPiece.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(lblNbTotalPiece);

        JComboBox cboNbTotalPiece=new JComboBox(new String[]{"4", "5", "6","7","8"});
        cboNbTotalPiece.setSelectedIndex(4);//Je mets sur la position 4 (le 8)
        cboNbTotalPiece.setAlignmentX(Component.CENTER_ALIGNMENT);
        cboNbTotalPiece.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        cboNbTotalPiece.setMaximumSize(new Dimension(100,50));
        panel.add(cboNbTotalPiece);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JLabel lblNbPieceOfCombinaison=new JLabel("Nombre de pions d'une combinaison :");
        lblNbPieceOfCombinaison.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbPieceOfCombinaison.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(lblNbPieceOfCombinaison);

        JComboBox cboNbPieceOfCombinaison=new JComboBox(new String[]{"4", "5", "6"});
        cboNbPieceOfCombinaison.setSelectedIndex(0);//Je mets sur la position 0 (le 4)
        cboNbPieceOfCombinaison.setAlignmentX(Component.CENTER_ALIGNMENT);
        cboNbPieceOfCombinaison.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        cboNbPieceOfCombinaison.setMaximumSize(new Dimension(100,50));
        panel.add(cboNbPieceOfCombinaison);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JLabel lblNbTry=new JLabel("Nombre de tentatives par manche :");
        lblNbTry.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbTry.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        panel.add(lblNbTry);

        JComboBox cboNbTry=new JComboBox(new String[]{"10", "11", "12"});
        cboNbTry.setSelectedIndex(0);//Je mets sur la position 0 (le 10)
        cboNbTry.setAlignmentX(Component.CENTER_ALIGNMENT);
        cboNbTry.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        cboNbTry.setMaximumSize(new Dimension(100,50));
        panel.add(cboNbTry);

        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JButton btnLaunchGame=new JButton("Lancer le jeu !");
        btnLaunchGame.setCursor(new Cursor(Cursor.HAND_CURSOR));//main pour le bouton :)
        Color originalColorButton=btnLaunchGame.getBackground();

        //Ici, c'est classe anonyme voila (il me faut 2 events)
        btnLaunchGame.addMouseListener(new MouseAdapter() {
                                           public void mouseEntered(MouseEvent e) {
                                                btnLaunchGame.setBackground(new Color(245, 175, 175));
                                           }

                                           public void mouseExited(MouseEvent e) {
                                               btnLaunchGame.setBackground(originalColorButton);
                                           }
                                       });

                btnLaunchGame.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnLaunchGame.setAlignmentX(Component.CENTER_ALIGNMENT);//Aligner au milieu

        //mais ici expression lambda
        btnLaunchGame.addActionListener(actionEvent -> {
            String playerName=txtPlayerName.getText();
            int nbRound=Integer.parseInt(cboNbRound.getSelectedItem().toString());
            int nbTotalPiece=Integer.parseInt(cboNbTotalPiece.getSelectedItem().toString());
            int nbPieceOfCombinaison= Integer.parseInt(cboNbPieceOfCombinaison.getSelectedItem().toString());
            int nbTry=Integer.parseInt(cboNbTry.getSelectedItem().toString());

            if(!playerName.isEmpty())
                controller.launchGame(playerName,nbRound,nbPieceOfCombinaison,nbTry,nbTotalPiece);
            else
                JOptionPane.showMessageDialog(new JFrame(),
                        "Vous devez entrer un nom de joueur",
                        "Entrer un nom",
                        JOptionPane.ERROR_MESSAGE);
        });

        panel.add(btnLaunchGame);
        panel.add(Box.createVerticalStrut(20));//Espace vertical vide

        JButton btnExit=new JButton("Quitter");
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));//main pour le bouton :)
        btnExit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(actionEvent -> {
            this.closeWindow();
        });

        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnExit.setBackground(new Color(245, 175, 175));
            }

            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(originalColorButton);
            }
        });


        panel.add(btnExit);

        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private void closeWindow()
    {
        this.dispose();
    }
}
