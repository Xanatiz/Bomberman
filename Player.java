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

    public Player(Position position, Playfield background) {
        this.position = position;
        this.background = background;
        this.explosionRadius = 3;
        this.alive = true;
        this.numberOfBombs=1;
        this.activeBombs=0;
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

    public void improveNumberOfBombs(){
        numberOfBombs++;
    }

    public void improveExplosionRadius(){
        explosionRadius++;
    }

    public void moveRight(){
        position.nextRight(background);
    }

    public void moveLeft(){
        position.nextLeft(background);
    }

    public void moveUp(){
        position.nextUp(background);
    }

    public void moveDown(){
        position.nextDown(background);
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