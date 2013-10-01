import javax.swing.*;
import java.math.*;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private Playfield background;
    private Position position;
    private int explosionRadius;
    private boolean alive;
    private int numberOfBombs;
    private int activeBombs;
    private boolean kickBomb;

    public Player(Position position, Playfield background) {
        this.position = position;
        this.background = background;
        this.explosionRadius = 3;
        this.alive = true;
        this.numberOfBombs=1;
        this.activeBombs=0;
        this.kickBomb=false;
    }

    public Position getPosition(){
        return position;
    }

    public boolean isAlive(){
        return alive;
    }

    public void kill(){
        alive=false;
    }

    public void pickUp(){
        if(background.getData(position)==BlockType.EXPLOSIONRADIUSBOOST){
            explosionRadius++;}
        else if(background.getData(position)==BlockType.BOMBBOOST){
            numberOfBombs++;}
        else if(background.getData(position)==BlockType.KICKBOMB){
            kickBomb=true;
            System.out.println(kickBomb);}
        background.setData(position, BlockType.GROUND);
    }


    public void moveRight(){
        position.nextRight();
        if(background.getData(position).isPickUp()){
            pickUp();
        }
        else if(!background.getData(position).isWalkable()){
            position.nextLeft();
        }
    }

    public void moveLeft(){
        position.nextLeft();
        if(background.getData(position).isPickUp()){
            pickUp();
        }
        else if(!background.getData(position).isWalkable()){
            position.nextRight();
        }
    }
    public void moveUp(){
        position.nextUp();
        if(background.getData(position).isPickUp()){
            pickUp();
        }
        else if(!background.getData(position).isWalkable()){
            position.nextDown();
        }
    }
    public void moveDown(){
        position.nextDown();
        if(background.getData(position).isPickUp()){
            pickUp();
        }
        else if(!background.getData(position).isWalkable()){
            position.nextUp();
        }
    }

    public void dropBomb(){
        if(activeBombs<numberOfBombs){
            Bomb bomb = new Bomb(new Position(position), explosionRadius, background, this);
            activeBombs++;
            bomb.activateBomb();
        }
    }
    public void deactivateBomb(){
        activeBombs--;
    }
}