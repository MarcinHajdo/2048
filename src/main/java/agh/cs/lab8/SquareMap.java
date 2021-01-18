package agh.cs.lab8;

import java.util.*;
import java.lang.Math;

public class SquareMap implements IWorldMap{
    private int width;
    private int height;
    private int result=0;
    private int resultChange=0;
    protected Map<Vector2d, Square> squares = new HashMap<>();
    protected Map<Vector2d, Square> oldSquares = new HashMap<>();
    ArrayList<Integer> values = new ArrayList<>();
    public  SquareMap(int width,int height){
        this.width = width;
        this.height = height;
        values.add(2);
    }
    @Override
    public boolean place(Square square) {
        if (canMoveTo(square.getPosition())){
            squares.put(square.getPosition(),square);
        }
        return false;
    }

    @Override
    public void run(MoveDirection direction) {
        boolean somethingChanged = false;
        Map<Vector2d, Square> tmp = new HashMap<>(squares);
        int resultChangetmp=0;
        switch (direction){
            case LEFT->{
                for(int i = 0; i < getWidth(); i++){
                    for(int j = 0; j < getHeight(); j++){
                        if(squares.containsKey(new Vector2d(i,j))){
                            for(int k = i+1; k < getWidth(); k++){
                                if(squares.containsKey(new Vector2d(k,j))) {
                                    if (squares.get(new Vector2d(k,j)).getValue()==squares.get(new Vector2d(i,j)).getValue()){
                                        int v = squares.get(new Vector2d(k,j)).getValue()+squares.get(new Vector2d(i,j)).getValue();
                                        this.result += v;
                                        resultChangetmp+=v;
                                        int s = (int)Math.sqrt(v);
                                        if (v > Math.pow(values.get(values.size()-1),2) && isPower2(s)){
                                            values.add(s);
                                        }
                                        somethingChanged = true;
                                        squares.remove(new Vector2d(k,j));
                                        squares.remove(new Vector2d(i,j));
                                        squares.put(new Vector2d(i,j), new Square(new Vector2d(i,j),this, v));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int i = 0; i < getWidth(); i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        if (isOccupied(new Vector2d(i, j)) == false) {
                            for (int k = i + 1; k < getWidth(); k++) {
                                if (isOccupied(new Vector2d(k, j))) {
                                    int v = squares.get(new Vector2d(k, j)).getValue();
                                    somethingChanged = true;
                                    squares.remove(new Vector2d(k, j));
                                    squares.put(new Vector2d(i, j), new Square(new Vector2d(i, j), this, v));//*/
                                    break;
                                }
                            }//*/
                        }
                    }
                }//*/
            }
            case RIGHT -> {
                for(int i = getWidth()-1; i >= 0; i--){
                    for(int j = 0; j < getHeight(); j++){
                        if(squares.containsKey(new Vector2d(i,j))){
                            for(int k = i-1; k >= 0; k--){
                                if(squares.containsKey(new Vector2d(k,j))) {
                                    if (squares.get(new Vector2d(k,j)).getValue()==squares.get(new Vector2d(i,j)).getValue()){
                                        int v = squares.get(new Vector2d(k,j)).getValue()+squares.get(new Vector2d(i,j)).getValue();
                                        this.result += v;
                                        resultChangetmp+=v;
                                        int s = (int)Math.sqrt(v);
                                        if (v > Math.pow(values.get(values.size()-1),2) && isPower2(s)){
                                            values.add(s);
                                        }
                                        somethingChanged = true;
                                        squares.remove(new Vector2d(k,j));
                                        squares.remove(new Vector2d(i,j));
                                        squares.put(new Vector2d(i,j), new Square(new Vector2d(i,j),this, v));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int i = getWidth()-1; i >= 0; i--) {
                    for (int j = 0; j < getHeight(); j++) {
                        if (isOccupied(new Vector2d(i, j)) == false) {
                            for (int k = i - 1; k >= 0; k--) {
                                if (isOccupied(new Vector2d(k, j))) {
                                    int v = squares.get(new Vector2d(k, j)).getValue();
                                    somethingChanged = true;
                                    squares.remove(new Vector2d(k, j));
                                    squares.put(new Vector2d(i, j), new Square(new Vector2d(i, j), this, v));//*/
                                    break;
                                }
                            }//*/
                        }
                    }
                }//*/
            }
            case BACKWARD -> {
                for(int i = 0; i < getHeight(); i++){
                    for(int j = 0; j < getWidth(); j++){
                        if(squares.containsKey(new Vector2d(j,i))){
                            for(int k = i+1; k < getHeight(); k++){
                                if(squares.containsKey(new Vector2d(j,k))) {
                                    if (squares.get(new Vector2d(j,k)).getValue()==squares.get(new Vector2d(j,i)).getValue()){
                                        int v = squares.get(new Vector2d(j,k)).getValue()+squares.get(new Vector2d(j,i)).getValue();
                                        this.result += v;
                                        resultChangetmp+=v;
                                        int s = (int)Math.sqrt(v);
                                        if (v > Math.pow(values.get(values.size()-1),2) && isPower2(s)){
                                            values.add(s);
                                        }
                                        somethingChanged = true;
                                        squares.remove(new Vector2d(j,k));
                                        squares.remove(new Vector2d(j,i));
                                        squares.put(new Vector2d(j,i), new Square(new Vector2d(j,i),this, v));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int i = 0; i < getHeight(); i++) {
                    for (int j = 0; j < getWidth(); j++) {
                        if (isOccupied(new Vector2d(j,i)) == false) {
                            for (int k = i + 1; k < getHeight(); k++) {
                                if (isOccupied(new Vector2d(j,k))) {
                                    int v = squares.get(new Vector2d(j, k)).getValue();
                                    somethingChanged = true;
                                    squares.remove(new Vector2d(j, k));
                                    squares.put(new Vector2d(j, i), new Square(new Vector2d(j, i), this, v));//*/
                                    break;
                                }
                            }//*/
                        }
                    }
                }//*/
            }
            case FORWARD -> {
                for(int i = getHeight()-1; i >= 0; i--){
                    for(int j = 0; j < getWidth(); j++){
                        if(squares.containsKey(new Vector2d(j,i))){
                            for(int k = i-1; k >= 0; k--){
                                if(squares.containsKey(new Vector2d(j,k))) {
                                    if (squares.get(new Vector2d(j,k)).getValue()==squares.get(new Vector2d(j,i)).getValue()){
                                        int v = squares.get(new Vector2d(j,k)).getValue()+squares.get(new Vector2d(j,i)).getValue();
                                        this.result += v;
                                        resultChangetmp+=v;
                                        int s = (int)Math.sqrt(v);
                                        if (v > Math.pow(values.get(values.size()-1),2) && isPower2(s)){
                                            values.add(s);
                                        }
                                        somethingChanged = true;
                                        squares.remove(new Vector2d(j,k));
                                        squares.remove(new Vector2d(j,i));
                                        squares.put(new Vector2d(j,i), new Square(new Vector2d(j,i),this, v));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int i = getHeight()-1; i >= 0; i--) {
                    for (int j = 0; j < getWidth(); j++) {
                        if (isOccupied(new Vector2d(j,i)) == false) {
                            for (int k = i - 1; k >= 0; k--) {
                                if (isOccupied(new Vector2d(j,k))) {
                                    int v = squares.get(new Vector2d(j,k)).getValue();
                                    somethingChanged = true;
                                    squares.remove(new Vector2d(j,k));
                                    squares.put(new Vector2d(j,i), new Square(new Vector2d(j,i), this, v));//*/
                                    break;
                                }
                            }//*/
                        }
                    }
                }//*/
            }
        }
        if(somethingChanged){
            ArrayList<Vector2d> freePositions = freePositions();
            Random generator = new Random();
            if (freePositions.size()!=0){
                place(new Square(freePositions.get(generator.nextInt(freePositions.size())),this, values.get(generator.nextInt(values.size()))));
            }
            this.resultChange=resultChangetmp;
            oldSquares.clear();
            oldSquares = new HashMap<>(tmp);
        }
    }
    private boolean isPower2(int a){
        if (a == 1){
            return true;
        }
        else if (a%2 == 0){
            return isPower2(a/2);
        }
        else {
            return false;
        }
    }
    public ArrayList<Vector2d> freePositions(){
        ArrayList<Vector2d> freePositions = new ArrayList<>();
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++){
                if(isOccupied(new Vector2d(i,j)) == false){
                    freePositions.add(new Vector2d(i,j));
                }
            }
        }
        return freePositions;
    }

    @Override
    public boolean gameOver() {
        if (freePositions().size()==0){
            for(int i = 0; i < getWidth()-1; i++) {
                for (int j = 0; j < getHeight()-1; j++){
                    if(squares.get(new Vector2d(i,j)).getValue() == squares.get(new Vector2d(i+1,j)).getValue()
                            || squares.get(new Vector2d(i,j)).getValue() == squares.get(new Vector2d(i,j+1)).getValue()){
                        return false;
                    }
                }
            }
            for(int i = 0; i < getWidth()-1; i++) {
                if(squares.get(new Vector2d(i, getHeight()-1)).getValue() == squares.get(new Vector2d(i+1,getHeight()-1)).getValue()){
                    return false;
                }
            }
            for(int i = 0; i < getHeight()-1; i++) {
                if(squares.get(new Vector2d(getWidth()-1, i)).getValue() == squares.get(new Vector2d(getWidth()-1,i+1)).getValue()){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (squares.get(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(isOccupied(position)){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return squares.get(position);
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }
    public int getResult(){
        return this.result;
    }
    public Map<Vector2d, Square> getSquares(){ return this.squares; }
    public void undoLastMove(){
        if(oldSquares.size()>0){
            squares.clear();
            squares = new HashMap<>(oldSquares);
            oldSquares.clear();
            this.result = this.result - this.resultChange - 2;
        }
    }
    public boolean won(){
        for (Square s : squares.values()){
            if(s.getValue() == 2048){
                return true;
            }
        }
        return false;
    }
}
