package agh.cs.lab8;

import java.util.Random;

public class Game {
    private IWorldMap map;
    private Square first;
    private IEngine gameEngine;
    public Game(int fieldHeight, int fieldWidth){
        this.map = new SquareMap(fieldWidth,fieldHeight);
        this.first = firstGenerator();
        this.gameEngine = new GameEngine(this.map, this.first);
    }

    private Square firstGenerator(){
        Random generator = new Random();
        return new Square(new Vector2d(generator.nextInt(map.getWidth()),generator.nextInt(map.getHeight())),map);
    }

    public void oneStep(MoveDirection moveDirection){
        try {
            gameEngine.run(moveDirection);
        }catch(IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }

    public IWorldMap getMap() {
        return map;
    }
}

