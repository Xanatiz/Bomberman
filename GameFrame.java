import javax.swing.*;
import java.util.List;
import java.awt.*;
/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */

public class GameFrame extends JFrame{


    public GameFrame(Playfield background, List<Player> playerList){
        super("test");
        GameComponent bomberman = new GameComponent(background, playerList);
        background.addListener(bomberman);
        this.setLayout(new BorderLayout());
        this.add(bomberman, BorderLayout.CENTER);

    }

}
