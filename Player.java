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

    private int standardExplosionRadius = 3;
    private Playfield background;
    private Position position;
    private int explosionRadius;
    private boolean alive;
    private boolean activeBomb;

    public Player(Position position, Playfield background) {
        this.position = position;
        this.background = background;
        this.explosionRadius = standardExplosionRadius;
        this.alive = true;
        this.activeBomb = false;
    }

    public Position getPosition(){
        return position;
    }

    public boolean isAlive(){
        return alive;
    }

    public boolean isActiveBomb(){
        return activeBomb;
    }

    public void kill(){
        alive=false;
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
        if(!activeBomb){
            Bomb bomb = new Bomb(new Position(position), explosionRadius, background, this);
            activeBomb=true;
            bomb.activateBomb();
        }
    }
    public void deactivateBomb(){
        activeBomb=false;
    }
}