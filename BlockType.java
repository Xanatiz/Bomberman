import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public enum BlockType {
    BOX(Color.ORANGE, false, true, false),
    WALL(Color.GRAY, false, false, false),
    GROUND(Color.WHITE, true, true, false),
    SPAWNGROUND(Color.WHITE, true, true, false),
    PLAYER(Color.BLUE, false, true, false),
    BOMB(Color.BLACK, false, true, false),
    EXPLOSION(Color.YELLOW, false, false, false),
    EXPLOSIONRADIUSBOOST(Color.PINK, false, true, true),
    BOMBBOOST(Color.GREEN, false, true, true),
    KICKBOMB(Color.RED, false, true, true);

    private final Color color;
    private final Boolean walkable;
    private final Boolean destructible;
    private final Boolean pickUp;

    BlockType(Color color, Boolean walkable, Boolean destructible, Boolean pickUp){
        this.color = color;
        this.walkable= walkable;
        this.destructible=destructible;
        this.pickUp=pickUp;
    }

    public Color getColor(){
        return this.color;
    }
    public boolean isWalkable(){
        return this.walkable;
    }
    public boolean isDestructible(){
        return this.destructible;
    }
    public boolean isPickUp(){
        return pickUp;
    }

}
