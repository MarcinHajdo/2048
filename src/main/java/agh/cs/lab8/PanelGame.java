package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;

public class PanelGame extends JPanel {
    private IWorldMap map;

    PanelGame(IWorldMap map){
        this.map=map;
        setPreferredSize(new Dimension(1, 1));
        setLayout(new GridLayout(map.getHeight(), map.getWidth()));
        for (int i = 0; i < map.getHeight(); i++) {
            for(int j = 0;j<map.getWidth();j++){
                if (this.map.isOccupied(new Vector2d(j,i))){
                    add(new NumSquare(map.getSquares().get(new Vector2d(j,i)).getValue(),
                            300/map.getHeight(),300/ map.getWidth()));
                }
                else {
                    add(new NumSquare(0,300/map.getHeight(),300/ map.getWidth()));
                }
            }

        }
        setBounds(270,20,300,300);
    }

}
