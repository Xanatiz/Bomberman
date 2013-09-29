import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public enum BlockType {
    BOX(Color.ORANGE, false, true), WALL(Color.GRAY, false, false), GROUND(Color.GREEN, true, true), SPAWNGROUND(Color.WHITE, true, true),
    PLAYER(Color.BLUE, false, true), BOMB(Color.BLACK, false, true), EXPLOSION(Color.YELLOW, false, false);

    private final Color color;
    private final Boolean walkable;
    private final Boolean destructible;

    BlockType(Color color, Boolean walkable, Boolean destructible){
        this.color = color;
        this.walkable= walkable;
        this.destructible=destructible;
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

}
