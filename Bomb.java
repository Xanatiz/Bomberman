import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: henke
 * Date: 9/26/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bomb {
    private BlockType oldBlock;
    private ID id;
    private Timer bombTimer, kickTimer, flameTimer;
    private int explosionRadius;
    private Playfield background;
    private Position position;
    private PlayerList playerList;
    private BombList bombList;
    private List<Position> explosionPositions = new ArrayList<Position>();

    public Bomb(ID id, Position position, int explosionRadius, Playfield background, PlayerList playerList, BombList bombList){
        this.id = id;
        this.position=position;
        this.explosionRadius = explosionRadius;
        this.background=background;
        this.playerList=playerList;
        this.bombList=bombList;
    }

    public ID getId(){
        return id;
    }

    public Position getPosition(){
        return position;
    }

    public void activate(){
        final Action explode = new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                explode();
            }
        };
        bombTimer = new Timer(3000, explode);
        bombTimer.setCoalesce(true);
        bombTimer.start();
    }

    public void kick(final int xDirection, final int yDirection){
        final Position newPosition = new Position(position);
        final Action fly = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newPosition.nextPosition(xDirection, yDirection);
                if(background.getData(newPosition).isWalkable()&&!playerList.contains(position))
                    position.nextPosition(xDirection, yDirection);
                else
                    kickTimer.stop();

                background.updatePlayfield();
            }
        };
        kickTimer = new Timer(500, fly);
        kickTimer.setCoalesce(true);
        kickTimer.start();
    }
    public void explode(){
        bombTimer.stop();
        if(kickTimer!=null)
            kickTimer.stop();

        playerList.get(id).deactivateBomb(position);

        //first check on top of bomb, then each way
        checkAndStore(position);
        explodeEast(position);
        explodeWest(position);
        explodeSouth(position);
        explodeNorth(position);
        //for(int f = 0; f<=explosionPositions.size()-1; f++)
            //System.out.println(explosionPositions.get(f).put());
            //flame(explosionPositions.get(f));
        background.updatePlayfield();

    }
    public void flame(final Position position){
        final Action flame = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                flameTimer.stop();
                background.setData(position, oldBlock);
            }
        };
        oldBlock=background.getData(position);
        flameTimer = new Timer(300, flame);
        flameTimer.setCoalesce(true);
        flameTimer.start();
        background.setData(position, BlockType.EXPLOSION);
    }

    public void explodeEast(Position position){
        Position testPosition = new Position(position);
        for(int x = position.getX()+1; x <= position.getX()+explosionRadius; x++){
            testPosition.setX(x);
            if(!checkAndStore(testPosition))
                break;
        }
    }

    public void explodeWest(Position position){
        Position testPosition = new Position(position);
        for(int x = position.getX()-1; x >= position.getX()-explosionRadius; x--){
            testPosition.setX(x);
            if(!checkAndStore(testPosition))
                break;
        }
    }

    public void explodeSouth(Position position){
        Position testPosition = new Position(position);
        for(int y = position.getY()+1; y <= position.getY()+explosionRadius; y++){
            testPosition.setY(y);
            if(!checkAndStore(testPosition))
                break;
        }
    }

    public void explodeNorth(Position position){
        Position testPosition = new Position(position);
        for(int y = position.getY()-1; y >= position.getY()-explosionRadius; y--){
            testPosition.setY(y);
            if(!checkAndStore(testPosition))
                break;
        }
    }

    public boolean checkAndStore(Position position){
        boolean proceed = false;
        if(background.legal(position)){
            if(playerList.contains(position))
                playerList.get(position).kill();
            else if(bombList.contains(position))
                bombList.explodeBomb(position);
            else if(background.getData(position).isDestructible())
                background.placeReplacement(position);
            else if(background.getData(position).isWalkable())
                proceed=true;
            explosionPositions.add(position);
        }

        return proceed;
    }

}
