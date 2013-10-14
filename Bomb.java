import javax.swing.*;
import java.awt.event.ActionEvent;

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
    private int numberOfExplosions;

    public Bomb(ID id, Position position, int explosionRadius, Playfield background){
        this.id = id;
        this.position=position;
        this.explosionRadius = explosionRadius;
        this.background=background;
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
                if(background.getData(newPosition).isWalkable()&&!Main.playerList.contains(position))
                    position.nextPosition(xDirection, yDirection);
                else
                    kickTimer.stop();

                background.updatePlayfield();
            }
        };
        kickTimer = new Timer(300, fly);
        kickTimer.setCoalesce(true);
        kickTimer.start();
    }
    public void explode(){
        bombTimer.stop();
        if(kickTimer!=null)
            kickTimer.stop();

        Main.playerList.get(id).deactivateBomb(position);

        //first check on top of bomb, then each way
        checkAndStore(position);
        explodeEast(position);
        explodeWest(position);
        explodeSouth(position);
        explodeNorth(position);
        background.updatePlayfield();

        final Action flame = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                flameTimer.stop();
                while(numberOfExplosions>0){
                    Main.explosionList.pop();
                    numberOfExplosions--;
                }
                background.updatePlayfield();
            }
        };
        flameTimer = new Timer(300, flame);
        flameTimer.setCoalesce(true);
        flameTimer.start();
        background.updatePlayfield();

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
        if(background.legal(position)&&(background.getData(position)!=BlockType.WALL)){
            if(Main.playerList.contains(position))
                Main.playerList.get(position).kill();
            else if(Main.bombList.contains(position))
                Main.bombList.explodeBomb(position);
            else if(background.getData(position).isDestructible())
                background.placeReplacement(position);
            else if(background.getData(position).isWalkable())
                proceed=true;
            Main.explosionList.push(new Position(position));
            numberOfExplosions++;
        }

        return proceed;
    }

}
