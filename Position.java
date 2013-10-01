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

    public String put(){
        StringBuilder sb = new StringBuilder();
        sb.append("x: "+x+" y: "+y);
        return sb.toString();
    }

    public boolean equals(Position position){
        return x==position.getX() && y==position.getY();
    }

    public void nextRight(){
        x++;
    }

    public void nextLeft(){
        x--;
    }

    public void nextUp(){
        y--;
    }

    public void nextDown(){
        y++;
    }

    public void deathMove(){
        x=0;
        y=0;
    }

}
