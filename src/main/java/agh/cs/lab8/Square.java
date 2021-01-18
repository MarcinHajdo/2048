package agh.cs.lab8;

public class Square {
    private int value = 2;
    private Vector2d position;
    private IWorldMap map = null;
    private int height = 4;
    private int width = 4;

    public Square(Vector2d position, IWorldMap map){
        this.position = border(position);
        this.map = map;
    }
    public Square(Vector2d position, IWorldMap map, int value){
        this.position = border(position);
        this.map = map;
        this.value = value;
    }
    private Vector2d border(Vector2d p){
       if (this.map != null){
            this.width = this.map.getWidth();
            this.height = this.map.getHeight();
        }
        if (p.getY()>=this.height)
        {
            p= p.subtract(new Vector2d(0,this.height));
        }
        if (p.getX()>=this.width)
        {
            p= p.subtract(new Vector2d(this.width,0));
        }
        if (p.getY()<0)
        {
            p= p.add(new Vector2d(0,this.height));
        }
        if (p.getX()<0)
        {
            p= p.add(new Vector2d(this.width,0));
        }
        return p;
    }
    public void move(MoveDirection direction) {
        this.position = border(this.position.add(direction.toUnitVector()));
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public int getValue() {
        return value;
    }

    public String toString(){
        return String.valueOf(this.value);
    }
}
