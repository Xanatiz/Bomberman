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
//    private Playfield frontPlayfield;
    private Position position;
    private int explosionRadius;
    public boolean activeBomb;
    final JFrame frame = new JFrame();

    public Player(Position position, Playfield background) {
        this.position = position;
        this.background = background;
        this.explosionRadius = standardExplosionRadius;
        this.activeBomb = false;
    }

    public Position getPosition(){
        return position;
    }

    public int getXPosition(){
        return position.getX();
    }

    public int getYPosition(){
        return position.getY();
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

    // Lade in dessa tre metoder. Simpla. Första två kollar bara om bomben och spelares nuvarande position är i samma rad/kolumn.
    // Tredje metoder kollar bara om bombens X - spelares X <= explosionRadius(3) samt om de är i samma kolumn eller rad.
    // likadant för Y-positionerna.
/*
    public boolean sameRow(Bomb bomb){
        return this.getYPosition() == bomb.getyPos();
    }

    public boolean sameColumn(Bomb bomb){
        return this.getXPosition() == bomb.getxPos();
    }

    public boolean isWithinKillZone(Bomb bomb){
        try {
            if((((bomb.getxPos() - this.getXPosition() <= explosionRadius) && (bomb.getyPos() - this.getYPosition() <= explosionRadius)) && ((sameColumn(bomb)||(sameRow(bomb))))) && !bomb.checkVicinity()){
                int answer = JOptionPane.showConfirmDialog(frame, "new game?"); //"New game?", JOptionPane.YES_NO_OPTION);
                //System.out.println("WITHIN KILL ZONE U DED BICH");
                //System.exit(0);
                if(answer == JOptionPane.YES_OPTION){
                    new Menu();
                }
                else if(answer == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                return true;
            }
        }
        catch (NullPointerException e){
            System.out.println("NULL POINT");
        }
        return false;

    }*/
}