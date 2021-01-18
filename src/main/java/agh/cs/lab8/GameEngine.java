package agh.cs.lab8;

public class GameEngine implements IEngine {

    IWorldMap simulatedMap;

    GameEngine(IWorldMap map, Square first){
        this.simulatedMap = map;
        this.simulatedMap.place(first);
    }

    @Override
    public void run(MoveDirection moveDirection) {
        simulatedMap.run(moveDirection);
    }
}