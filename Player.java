
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
        position.deathMove();
    }

    public void pickUp(){
        if(background.getData(position)==BlockType.EXPLOSIONRADIUSBOOST){
            explosionRadius++;}
        else if(background.getData(position)==BlockType.BOMBBOOST){
            numberOfBombs++;}
        else if(background.getData(position)==BlockType.KICKBOMB){
            kickBomb=true;}
        background.setData(position, BlockType.GROUND);
    }

    public void moveRight(){
        if(alive){
            position.nextRight();
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextLeft();
            }
        }
    }

    public void moveLeft(){
        if(alive){
            position.nextLeft();
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextRight();
            }
        }
    }

    public void moveUp(){
        if(alive){
            position.nextUp();
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextDown();
            }
        }
    }

    public void moveDown(){
        if(alive){
            position.nextDown();
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextUp();
            }
        }
    }

    public void dropBomb(){
        if(activeBombs<numberOfBombs&&alive&&background.getData(position).isWalkable()){
            Bomb bomb = new Bomb(new Position(position), explosionRadius, background, this);
            activeBombs++;
            bomb.activateBomb();
        }
    }
    public void deactivateBomb(){
        activeBombs--;
    }
}