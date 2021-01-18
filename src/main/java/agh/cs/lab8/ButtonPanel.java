package agh.cs.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ButtonPanel extends JPanel implements ActionListener {
    private int height =4, width =4;
    private JTextField heightField, widthField;
    private Timer timer;
    private final JButton startingButton, undoLastMoveButton, upButton, leftButton, rightButton, downButton;
    private JPanel panelGame;
    private Game Game;
    private JTextArea gameOverText, yourResult, highResult, victory,errorValue;
    private int highestResult=0;
    public ButtonPanel() {
        startingButton = createButton("New Game", 120,100,100,20);
        undoLastMoveButton = createNonVisibleButton("Undo Last Move",100,120,130,20);
        widthField = createTextField("Width", 20,25,50,20,"4",20,5, 50,20);
        heightField = createTextField("Height", 80,25,50,20,"4",80,5, 50,20);
        upButton = createNonVisibleButton("UP", 90,150,80,20);
        leftButton = createNonVisibleButton("LEFT", 10,170,80,20);
        downButton = createNonVisibleButton("DOWN", 90,170,80,20);
        rightButton = createNonVisibleButton("RIGHT", 170,170,80,20);
        timer = new Timer(100,this);
        gameOverText =createTextArea("GAME OVER",10,150,200,100);
        gameOverText.setVisible(false);
        add(gameOverText);
        victory = createTextArea("You win",10,280,130,20);
        victory.setVisible(false);
        add(victory);
        yourResult = createTextArea("",10,300,130,20);
        yourResult.setVisible(false);
        add(yourResult);
        highResult = createTextArea("",10,320,130,20);
        highResult.setVisible(false);
        add(highResult);
        errorValue = createTextArea("Height and Width must be greater than 0",270,20,300,300);
        errorValue.setBackground(Color.white);
        errorValue.setVisible(false);
        add(errorValue);
        setLayout(null);
        setBackground(Color.PINK);

        setPreferredSize(new Dimension(1, 1));
    }
    private JButton createButton(String text, int x, int y, int width, int height){
        JButton Button = new JButton(text);
        Button.addActionListener(this);
        Button.setBounds(x, y, width, height);
        add(Button);
        return Button;
    }
    private JButton createNonVisibleButton(String text, int x, int y, int width, int height){
        JButton Button = createButton(text, x, y, width, height);
        Button.setVisible(false);
        return Button;
    }
    private JTextField createTextField(String text, int x, int y, int width, int height, String text2,
                                 int x2, int y2, int width2, int height2){

        add(createTextArea(text, x, y, width, height));
        JTextField Field = new JTextField(text2);
        Field.setBounds(x2,y2, width2,height2);
        add(Field);
        return Field;
    }
    private JTextArea createTextArea(String text, int x, int y, int width, int height){
        JTextArea jTextArea = new JTextArea(text);
        jTextArea.setBounds(x, y, width, height);
        jTextArea.setBackground(null);
        return jTextArea;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == startingButton) {
            undoLastMoveButton.setVisible(true);
            directionButtonChangeVisible(true);
            gameOverText.setVisible(false);
            this.height = Integer.parseInt(heightField.getText());
            this.width = Integer.parseInt(widthField.getText());
            timer = new Timer(100, this);
            if(this.height > 0 && this.width >0 ){
                try {
                    gameStart();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            else{
                errorValue.setVisible(true);
            }
            timer.start();
        } else if (source == undoLastMoveButton) {
            Game.getMap().undoLastMove();
            directionButtonChangeVisible(true);
            gameOverText.setVisible(false);
            drawMap(Game.getMap());
        }
        else if(source == upButton){
            move(MoveDirection.BACKWARD);
        }
        else if(source == leftButton){
            move(MoveDirection.LEFT);
        }
        else if (source == downButton){
            move(MoveDirection.FORWARD);
        }
        else if (source == rightButton){
            move(MoveDirection.RIGHT);
        }
        else{
            if(this.Game != null){
                if (Game.getMap().getResult()>highestResult){
                    highestResult = Game.getMap().getResult();
                    String result ="Highest result is "+highestResult;
                    highResult.setText(result);
                }
                isVictory();
                gameEnd();
            }
        }
    }
    private void gameStart() throws FileNotFoundException {
        Game = new Game(this.height, this.width);
        errorValue.setVisible(false);
        drawMap(Game.getMap());
        highestResult = new ResultToFile().readHighestResult();
        String result ="Highest result is "+highestResult;
        highResult.setText(result);
        highResult.setVisible(true);
    }
    private void move(MoveDirection moveDirection){
        Game.oneStep(moveDirection);
        drawMap(Game.getMap());
    }
    public void drawMap(IWorldMap map){
        if(panelGame!=null){
            remove(panelGame);
        }
        panelGame = new PanelGame(map);
        add(panelGame);
        panelGame.repaint();
        panelGame.revalidate();
        String result ="Your result is "+map.getResult();
        yourResult.setText(result);
        yourResult.setVisible(true);
    }
    public void gameEnd(){
        if(Game.getMap().gameOver()){
            directionButtonChangeVisible(false);
            gameOverText.setVisible(true);
            new ResultToFile().writeHighestResult(highestResult);
        }
    }
    public void directionButtonChangeVisible(boolean b){
        upButton.setVisible(b);
        downButton.setVisible(b);
        leftButton.setVisible(b);
        rightButton.setVisible(b);
    }
    public void isVictory(){
        if(Game.getMap().won()){
            victory.setVisible(true);
        }
    }
}
