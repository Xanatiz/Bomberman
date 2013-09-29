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
    private int xPos;
    private int yPos;
    private int explosionRadius;
    private Playfield frontPlayfield;
    private Playfield background;
    private Player player;
    private Position position;

    // Bomb får ta in x- och y-koordinater istället för position. kanske går med position också men då blir player helt fuckad. lätt fixat kanske men nu är det såhär
    public Bomb(int xPos, int yPos, int explosionRadius, Playfield frontPlayfield, Playfield background, Player player){
        this.xPos = xPos;
        this.yPos = yPos;
        this.explosionRadius = explosionRadius;
        this.frontPlayfield=frontPlayfield;
        this.background=background;
        this.player=player;
    }

   /* public int getBombXPosition(){
        return this.position.getX();
    }

    public int getBombYPosition(){
        return this.position.getY();
    }*/

    public int getxPos(){
        return this.xPos;
    }

    public int getyPos(){
        return this.yPos;
    }


    public void activateBomb(){
        final Action explode = new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                bang();
            }
        };
        timer = new Timer(3000, explode);
        frontPlayfield.setData(yPos, xPos, BlockType.BOMB);
        timer.setCoalesce(true);
        timer.start();
    }

    public void bang(){
        timer.stop();
        player.deactivateBomb();
        frontPlayfield.setData(yPos, xPos, null);
        if(!player.isWithinKillZone(this)){                 // Här ligger checken för tillfället.
            for(int i = xPos; i>=xPos-explosionRadius; i--){
                if ((background.getData(yPos, i).isDestructible())&&((frontPlayfield.getData(yPos, i)!=null) && (frontPlayfield.getData(yPos, i).isDestructible()))){
                    frontPlayfield.setData(yPos, i, null);
                    break;}
                else if (!background.getData(yPos, i).isDestructible())
                    break;
            }
            for(int i = xPos; i<=xPos+explosionRadius; i++){
                if ((background.getData(yPos, i).isDestructible())&&((frontPlayfield.getData(yPos, i)!=null) && (frontPlayfield.getData(yPos, i).isDestructible()))){
                    frontPlayfield.setData(yPos, i, null);
                    break;}
                else if (!background.getData(yPos, i).isDestructible())
                    break;
            }
            for(int i = yPos; i>=yPos-explosionRadius; i--){
                if ((background.getData(i, xPos).isDestructible())&&((frontPlayfield.getData(i, xPos)!=null) && (frontPlayfield.getData(i, xPos).isDestructible()))){
                    frontPlayfield.setData(i, xPos, null);
                    break;}
                else if (!background.getData(i, xPos).isDestructible())
                    break;
            }
            for(int i = yPos; i<=yPos+explosionRadius; i++){
                if ((background.getData(i, xPos).isDestructible())&&((frontPlayfield.getData(i, xPos)!=null) && (frontPlayfield.getData(i, xPos).isDestructible()))){
                    frontPlayfield.setData(i, xPos, null);
                    break;}
                else if (!background.getData(i, xPos).isDestructible())
                    break;
            }
        }
    }

    public boolean checkVicinity(){
        for(int i = xPos; i>=xPos-explosionRadius; i--){
            if ((background.getData(yPos, i).isDestructible())&&((frontPlayfield.getData(yPos, i)!=null) && (frontPlayfield.getData(yPos, i).isDestructible()))){
                return true;
                //frontPlayfield.setData(yPos, i, null);
                }
            else if (!background.getData(yPos, i).isDestructible())
                return false;
        }
        for(int i = xPos; i<=xPos+explosionRadius; i++){
            if ((background.getData(yPos, i).isDestructible())&&((frontPlayfield.getData(yPos, i)!=null) && (frontPlayfield.getData(yPos, i).isDestructible()))){
                return true;
                //frontPlayfield.setData(yPos, i, null);
                }
            else if (!background.getData(yPos, i).isDestructible())
                return false;
        }
        for(int i = yPos; i>=yPos-explosionRadius; i--){
            if ((background.getData(i, xPos).isDestructible())&&((frontPlayfield.getData(i, xPos)!=null) && (frontPlayfield.getData(i, xPos).isDestructible()))){
                return true;
                //frontPlayfield.setData(i, xPos, null);
                }
            else if (!background.getData(i, xPos).isDestructible())
                return false;
        }
        for(int i = yPos; i<=yPos+explosionRadius; i++){
            if ((background.getData(i, xPos).isDestructible())&&((frontPlayfield.getData(i, xPos)!=null) && (frontPlayfield.getData(i, xPos).isDestructible()))){
                return true;
                //frontPlayfield.setData(i, xPos, null);
                }
            else if (!background.getData(i, xPos).isDestructible())
                return false;
        }
        return false;

    }
}