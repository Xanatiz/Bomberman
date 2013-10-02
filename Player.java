import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */
public class Player {

    private ID id;
    private Position position;
    private Playfield background;
    private BombList bombList;
    private List<Player> playerList;
    private boolean alive;
    private int xDirection;
    private int yDirection;
    private int explosionRadius;
    private int numberOfBombs;
    private int activeBombs;
    private boolean kickBomb;

    public Player(ID id, Position position, Playfield background, BombList bombList, List<Player> playerList) {
        this.id = id;
        this.position = position;
        this.background = background;
        this.bombList=bombList;
        this.playerList=playerList;
        this.alive = true;
        this.xDirection=0;
        this.yDirection=0;
        this.explosionRadius = 3;
        this.numberOfBombs=1;
        this.activeBombs=0;
        this.kickBomb=false;
    }


    public ID getId(){
        return id;
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
            xDirection=1;
            yDirection=0;
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(background.getData(position)==BlockType.BOMB&&kickBomb){
                bombList.kickBomb(position, xDirection, yDirection);
                position.nextLeft();
            }
            else if(!background.getData(position).isWalkable()) {
                position.nextLeft();
            }
        }
        background.clearFromExplosion();
    }

    public void moveLeft(){
        if(alive){
            position.nextLeft();
            xDirection=-1;
            yDirection=0;
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(background.getData(position)==BlockType.BOMB&&kickBomb){
                bombList.kickBomb(position, xDirection, yDirection);
                position.nextRight();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextRight();
            }
        }
    }

    public void moveUp(){
        if(alive){
            position.nextUp();
            xDirection=0;
            yDirection=-1;
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(background.getData(position)==BlockType.BOMB&&kickBomb){
                bombList.kickBomb(position, xDirection, yDirection);
                position.nextDown();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextDown();
            }
        }
    }

    public void moveDown(){
        if(alive){
            position.nextDown();
            xDirection=0;
            yDirection=1;
            if(background.getData(position).isPickUp()){
                pickUp();
            }
            else if(background.getData(position)==BlockType.BOMB&&kickBomb){
                bombList.kickBomb(position, xDirection, yDirection);
                position.nextUp();
            }
            else if(!background.getData(position).isWalkable()){
                position.nextUp();
            }
        }
    }

    public void dropBomb(){
        if(activeBombs<numberOfBombs&&alive&&background.getData(position).isWalkable()){
            Bomb bomb = new Bomb(id,new Position(position), explosionRadius, background, playerList);
            bombList.add(bomb);
            activeBombs++;
            bombList.activateBomb(position);
        }
    }

    public void deactivateBomb(Position position){
        bombList.remove(position);
        activeBombs--;
    }

}