package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;


public class GameFrame  extends JFrame {
    JPanel buttonPanel, panelGame;
    public GameFrame(){
            super("2048");

            buttonPanel = new ButtonPanel();
            add(buttonPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(250,250);
            setSize(600,400);
            setVisible(true);
    }
}
