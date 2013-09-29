
/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    public int standardExplosionRadius = 3;
    private Playfield background;
    private Playfield frontPlayfield;
    private Position position;
    private int explosionRadius;
    private boolean activeBomb;

    public Player(Position position, Playfield background, Playfield frontPlayfield) {
        this.position = position;
        this.background = background;
        this.frontPlayfield = frontPlayfield;
        this.explosionRadius = standardExplosionRadius;
        this.activeBomb = false;
    }

    public Position getPosition(){
        return position;
    }


    public void moveRight(){
        position.nextRight(background, frontPlayfield);
    }

    public void moveLeft(){
        position.nextLeft(background, frontPlayfield);
    }

    public void moveUp(){
        position.nextUp(background, frontPlayfield);
    }

    public void moveDown(){
        position.nextDown(background, frontPlayfield);
    }

    public void dropBomb(){
        if(!activeBomb){
            Bomb bomb = new Bomb(position.getX(), position.getY(), explosionRadius, frontPlayfield, background, this);
            activeBomb=true;
            bomb.activateBomb();
        }
    }
    public void deactivateBomb(){
        activeBomb=false;
    }
}
