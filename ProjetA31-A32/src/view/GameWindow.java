package view;

import javax.swing.*;

public class GameWindow extends JFrame
{
    public GameWindow()
    {
        super("MasterMind");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();



        add(mainPanel);
        setVisible(true);
    }
}
