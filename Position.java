/**
 * Created with IntelliJ IDEA.
 * User: henke
 * Date: 9/27/13
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Position(Position position) {
        this.x=position.getX();
        this.y=position.getY();
    }

    public void copy(Position position){
        x=position.getX();
        y=position.getY();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int newX){
        x=newX;
    }

    public void setY(int newY){
        y=newY;
    }

    public void nextRight(Playfield playfield){
        x++;
        if(!playfield.getData(this).isWalkable()){
            x--;
        }
    }

    public void nextLeft(Playfield playfield){
        x--;
        if(!playfield.getData(this).isWalkable()){
            x++;
        }
    }
    public void nextUp(Playfield playfield){
        y--;
        if(!playfield.getData(this).isWalkable()){
            y++;
        }
    }

    public void nextDown(Playfield playfield){
        y++;
        if(!playfield.getData(this).isWalkable()){
            y--;
        }
    }
}
