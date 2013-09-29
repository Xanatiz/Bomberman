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

    public void nextRight(Playfield background, Playfield frontPlayfield){
        x++;
        if(!isWalkable(background, frontPlayfield)){
            x--;
        }
    }

    public void nextLeft(Playfield background, Playfield frontPlayfield){
        x--;
        if(!isWalkable(background, frontPlayfield)){
            x++;
        }
    }
    public void nextUp(Playfield background, Playfield frontPlayfield){
        y--;
        if(!isWalkable(background, frontPlayfield)){
            y++;
        }
    }

    public void nextDown(Playfield background, Playfield frontPlayfield){
        y++;
        if(!isWalkable(background, frontPlayfield)){
            y--;
        }
    }

    public boolean isWalkable(Playfield background, Playfield frontPlayfield){
        return ((background.getData(this).isWalkable()) && (frontPlayfield.getData(this) == null));
    }

}
