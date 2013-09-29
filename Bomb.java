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
    private Timer timer;
    private int explosionRadius;
    private BlockType originalBlock;
    private Playfield background;
    private Player player;
    private Position position;

    // Bomb får ta in x- och y-koordinater istället för position. kanske går med position också men då blir player helt fuckad. lätt fixat kanske men nu är det såhär
    public Bomb(Position position, int explosionRadius, Playfield background, Player player){
        this.position=position;
        this.explosionRadius = explosionRadius;
        this.background=background;
        this.player=player;
    }

    public Position getPosition(){
        return position;
    }

    public void activateBomb(){
        final Action explode = new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                bang();
            }
        };
        timer = new Timer(3000, explode);
        originalBlock = background.getData(position);
        background.setData(position, BlockType.BOMB);
        timer.setCoalesce(true);
        timer.start();
    }

    public void bang(){
        timer.stop();
        player.deactivateBomb();
        bangRadius(new Position(position), true, 1);
        bangRadius(new Position(position), true, -1);
        bangRadius(new Position(position), false, 1);
        bangRadius(new Position(position), false, -1);
        background.setData(position, originalBlock);
    }
    //Kommer placera fel block när Player dödas på ett SPAWNBLOCK.
    //Axis == true => X-axis
    //Axis == false => Y-axis
    public void bangRadius(Position position, boolean axis, int n){
        for(int i = 0; i <explosionRadius; i++){
            if (axis)
                position.setX(position.getX()+n);
            else
                position.setY(position.getY() + n);
            if(background.getData(position).isDestructible()&&!background.getData(position).isWalkable()){
                background.setData(position, BlockType.GROUND);
                break;
            }
            else if (!background.getData(position).isDestructible()) {
                break;
            }
        }
    }
}