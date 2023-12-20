package view.StartPanels;

import controller.GameMasterController;
import model.CluesMode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Le Panel de choix des paramêtre de jeu
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
        //Mets une petite bordure autour
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(25,50,25,50),
                BorderFactory.createLineBorder(new Color(255, 36, 36, 168),10,true)));//Pour faire un genre de padding

        //BoxLayout, car on veut tout placer à la vertical (les uns au dessus des autres)
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.add(Box.createVerticalStrut(10));//Espace vertical vide

        constructLabelPicture();//Label avec l'image

        this.add(Box.createVerticalStrut(10));//Espace vertical vide

        constructMyLabel("Entrer votre pseudo :");//Label du pseudo
        constructPlayerTextBox();//TexteBox du pseudo

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de manche :");//Label Nombre de manche
        cboNbRound=constructMyComboBox(new String[]{"3", "4", "5"},0,cboNbRound);//ComboBox du nombre de manche
        this.add(cboNbRound);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de couleurs :");//Label du nombre de couleur
        cboNbTotalPiece=constructMyComboBox(new String[]{"4", "5", "6","7","8"},4,cboNbTotalPiece);//ComboBox du nombre de couleur
        this.add(cboNbTotalPiece);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de pions d'une combinaison :");//Label taille de la combinaison
        cboNbPieceOfCombinaison=constructMyComboBox(new String[]{"4", "5", "6"},0,cboNbPieceOfCombinaison);//COmboBox taille de la combinaison
        this.add(cboNbPieceOfCombinaison);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Nombre de tentatives par manche :");//Label nombre de manche
        cboNbTry=constructMyComboBox(new String[]{"10", "11", "12"},0,cboNbTry);//ComboBox nombre de manche
        this.add(cboNbTry);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructMyLabel("Choissisez votre mode d'indices :");//Label mode d'indice
        clueModePanel=new ClueModePanel(controller);//Panel des indices
        this.add(clueModePanel);

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructLaunchGameButton();//Bouton pour lancer le jeu

        this.add(Box.createVerticalStrut(20));//Espace vertical vide

        constructExitButton();//Bouton pour quitter

        this.add(Box.createVerticalStrut(10));//Espace vertical vide
    }

    //Construit une ComboBox avec les valeurs fournit par le tableau et l'index par défaut de la ComBox
    private JComboBox constructMyComboBox(String [] numberTab,int startIndex,JComboBox cbo)
    {
        cbo=new JComboBox(numberTab);
        cbo.setSelectedIndex(startIndex);
        cbo.setAlignmentX(Component.CENTER_ALIGNMENT);
        cbo.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        cbo.setMaximumSize(new Dimension(100,50));
        return cbo;
    }

    //Construit le Label du titre
    private void constructTitleLabel()
    {
        JLabel lblTitle=new JLabel("Mastermind");
        lblTitle.setMinimumSize(new Dimension(50,50));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(Color.RED);
        lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        this.add(lblTitle);
    }

    //Contruit la zone de texte du pseudo
    private void constructPlayerTextBox()
    {
        txtPlayerName=new JTextArea();
        txtPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPlayerName.setFont(new Font(Font.DIALOG_INPUT,Font.PLAIN,20));
        txtPlayerName.setSize(new Dimension(400,30));
        txtPlayerName.setMaximumSize(new Dimension(400,30));

        //Ajout d'un évènement de touche du clavier
        txtPlayerName.addKeyListener(new KeyListener() {
                                         @Override
                                         public void keyTyped(KeyEvent e) {
                                             //Pseudo doit être inférieur à 20 caractères
                                             //Limites fixés par nous
                                             if(txtPlayerName.getText().length() > 20)
                                                 e.consume();

                                         }
                                         @Override
                                         public void keyPressed(KeyEvent e) {
                                             //Pas de retour à la ligne
                                             if(e.getKeyChar()=='\n')
                                                 e.consume();
                                         }
                                         @Override
                                         public void keyReleased(KeyEvent e) {}
                                     }
        );
        this.add(txtPlayerName);
    }

    //Consruite un Label avec le texte en paramêtre et l'ajoute au Panel
    private void constructMyLabel(String text)
    {
        JLabel lblNbRound=new JLabel(text);
        lblNbRound.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbRound.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        this.add(lblNbRound);
    }

    //Construit le bouton de lancement du jeu
    private void constructLaunchGameButton()
    {
        JButton btnLaunchGame=new JButton("Lancer le jeu !");
        this.originalColorButton=btnLaunchGame.getBackground();//On récupère la couleur par défaut

        createMyStartButtonMouseHoverEvent(btnLaunchGame);
        btnLaunchGame.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnLaunchGame.setAlignmentX(Component.CENTER_ALIGNMENT);//Aligner au milieu

        btnLaunchGame.addActionListener(actionEvent -> {
            startTheGame();
        });

        this.add(btnLaunchGame);
    }

    //Construit le bouton pour quitter
    private void constructExitButton()
    {
        JButton btnExit=new JButton("Quitter");
        btnExit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.addActionListener(actionEvent -> {
            controller.exitStartWindow();
        });

        createMyStartButtonMouseHoverEvent(btnExit);
        this.add(btnExit);

    }

    //Construit le Label avec l'image
    private void constructLabelPicture()
    {
        try
        {
            //Charge l'image
            BufferedImage myPicture = ImageIO.read(new File("./ProjetA31-A32/images/mastermind_title.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            //Taille
            picLabel.setMaximumSize(new Dimension(415,225));
            //Alignement
            picLabel.setHorizontalAlignment(SwingConstants.CENTER);
            picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(picLabel);
        }
        catch (IOException e)
        {
            //Si jamais, on n'arrive pas a charger, l'image, on met juste le Label de Titre classique
            constructTitleLabel();
        }
    }

    //Applique le curseur de main et le fait que le boutton change de couleur quand on survole avec la souris à un boutton
    private void createMyStartButtonMouseHoverEvent(JButton button)
    {
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));//Curseur de main

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(245, 175, 175));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColorButton);
            }
        });
    }

    //Méthode lors du clique sur le bouton "Lancer le jeu"
    private void startTheGame()
    {
        String playerName=txtPlayerName.getText();
        int nbRound=Integer.parseInt(cboNbRound.getSelectedItem().toString());
        int nbTotalPiece=Integer.parseInt(cboNbTotalPiece.getSelectedItem().toString());
        int nbPieceOfCombinaison= Integer.parseInt(cboNbPieceOfCombinaison.getSelectedItem().toString());
        int nbTry=Integer.parseInt(cboNbTry.getSelectedItem().toString());
        CluesMode cluesMode=clueModePanel.getSelectedCluesMode();

        //On vérifie si la zone de texte du pseudo n'est pas vide
        if(!playerName.isEmpty())
            controller.launchGame(playerName,nbRound,nbPieceOfCombinaison,nbTry,nbTotalPiece,cluesMode);
        else
        {
            //Petit message d'information
            JOptionPane.showMessageDialog(new JFrame(),
                    "Vous devez entrer un nom de joueur",
                    "Entrer un nom",
                    JOptionPane.ERROR_MESSAGE);
            txtPlayerName.requestFocus();//Focus sur la zone de texte
        }
    }
}
