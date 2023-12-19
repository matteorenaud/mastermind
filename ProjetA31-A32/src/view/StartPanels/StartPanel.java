package view.StartPanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartPanel extends JPanel
{
    private JTextArea txtPlayerName;
    private GameMasterController controller;

    private JComboBox cboNbRound;
    private JComboBox cboNbTotalPiece;
    private JComboBox cboNbTry;
    private JComboBox cboNbPieceOfCombinaison;

    private ClueModePanel clueModePanel;

    private Color originalColorButton;

    public StartPanel(GameMasterController controller)
    {
        this.controller=controller;
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.setBackground(new Color(195, 211, 250));
        //USINE USINE USINE USINE FACTORY PATRON DE CONCEPTION USINE
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(50,50,50,50),
                BorderFactory.createLineBorder(new Color(255, 36, 36, 168),10,true)));
        //Pour faire un genre de padding

        //BoxLayout, car on veut tout placer à la vertical (les uns au dessus des autres)
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        constructTitleLabel();

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Entrer votre nom :");

        constructPlayerTextBox();

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de manche :");

        cboNbRound=constructMyComboBox(new String[]{"3", "4", "5"},0,cboNbRound);
        this.add(cboNbRound);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de couleurs :");

        cboNbTotalPiece=constructMyComboBox(new String[]{"4", "5", "6","7","8"},4,cboNbTotalPiece);
        this.add(cboNbTotalPiece);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de pions d'une combinaison :");

        cboNbPieceOfCombinaison=constructMyComboBox(new String[]{"4", "5", "6"},0,cboNbPieceOfCombinaison);
        this.add(cboNbPieceOfCombinaison);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de tentatives par manche :");

        cboNbTry=constructMyComboBox(new String[]{"10", "11", "12"},0,cboNbTry);
        this.add(cboNbTry);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Choissisez votre mode d'indices :");

        clueModePanel=new ClueModePanel(controller);
        this.add(clueModePanel);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructLaunchGameButton();

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructExitButton();

    }

    private JComboBox constructMyComboBox(String [] numberTab,int startIndex,JComboBox cbo)
    {
        cbo=new JComboBox(numberTab);
        cbo.setSelectedIndex(startIndex);
        cbo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cbo.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        cbo.setMaximumSize(new Dimension(100,50));
        return cbo;
    }
    private void constructTitleLabel()
    {
        JLabel lblTitle=new JLabel("Mastermind");
        lblTitle.setMinimumSize(new Dimension(50,50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        this.add(lblTitle);
    }
    private void constructPlayerTextBox()
    {
        txtPlayerName=new JTextArea();
        txtPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPlayerName.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,20));
        txtPlayerName.setSize(new Dimension(400,30));
        txtPlayerName.setMaximumSize(new Dimension(400,30));

        txtPlayerName.addKeyListener(new KeyListener() {
                                         @Override
                                         public void keyTyped(KeyEvent e) {
                                             if(txtPlayerName.getText().length() > 20)
                                             {
                                                 e.consume();
                                             }
                                         }
                                         @Override
                                         public void keyPressed(KeyEvent e) {}
                                         @Override
                                         public void keyReleased(KeyEvent e) {}
                                     }
        );
        this.add(txtPlayerName);
    }
    private void constructMyLabel(String text)
    {
        JLabel lblNbRound=new JLabel(text);
        lblNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbRound.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(lblNbRound);
    }

    private void constructLaunchGameButton()
    {
        JButton btnLaunchGame=new JButton("Lancer le jeu !");
        btnLaunchGame.setCursor(new Cursor(Cursor.HAND_CURSOR));//main pour le bouton :)
        this.originalColorButton=btnLaunchGame.getBackground();

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
            CluesMode cluesMode=clueModePanel.getSelectedCluesMode();

            if(!playerName.isEmpty())
                controller.launchGame(playerName,nbRound,nbPieceOfCombinaison,nbTry,nbTotalPiece,cluesMode);
            else
                JOptionPane.showMessageDialog(new JFrame(),
                        "Vous devez entrer un nom de joueur",
                        "Entrer un nom",
                        JOptionPane.ERROR_MESSAGE);
        });

        this.add(btnLaunchGame);
    }

    private void constructExitButton()
    {
        JButton btnExit=new JButton("Quitter");
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));//main pour le bouton :)
        btnExit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(actionEvent -> {
            controller.exitStartWindow();
        });

        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnExit.setBackground(new Color(245, 175, 175));
            }

            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(originalColorButton);
            }
        });
        this.add(btnExit);

    }
}
