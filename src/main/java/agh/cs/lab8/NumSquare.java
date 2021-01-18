package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;

public class NumSquare extends JComponent {
    private int value;
    private int height;
    private int width;
    private final int SCALE = 100;
    private final int FONT_SIZE = (int)(SCALE*0.4);
    private final Font FONT = new Font("Consolas", Font.PLAIN, FONT_SIZE);
    NumSquare(int value, int height, int width){
        this.value=value;
        this.height = height;
        this.width = width;
        setFont(FONT);
        setPreferredSize(new Dimension(100, 100));
        setVisible(true);
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        ((Graphics2D)graphics).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if(this.value == 0){
            graphics.setColor(Color.BLACK);
        }
        else if(this.value == 2){
            graphics.setColor(Color.GREEN);
        }
        else if(this.value == 4){
            graphics.setColor(Color.MAGENTA);
        }
        else if(this.value == 8){
            graphics.setColor(Color.ORANGE);
        }
        else if(this.value == 16){
            graphics.setColor(Color.PINK);
        }
        else if(this.value == 32){
            graphics.setColor(Color.RED);
        }
        else if(this.value == 64){
            graphics.setColor(Color.YELLOW);
        }
        else if(this.value == 128){
            graphics.setColor(Color.WHITE);
        }
        else {
            graphics.setColor(Color.GRAY);
        }
        graphics.fillRect(2,2,this.width,this.height);
        graphics.setColor(Color.CYAN);
        if(this.value>0){
            graphics.drawString(String.valueOf(this.value),
                    (this.width - getFontMetrics(FONT).stringWidth(String.valueOf(this.value))) / 2,
                    this.height / 2 + getFontMetrics(FONT).getAscent() / 3);
        }
    }
}
