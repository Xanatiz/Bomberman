import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class Gametest {
    public static void main(String[] args) {
        int row = 21;
        int column = 21;
        Position startPosition1 = new Position(1,1);
        Position startPosition2 = new Position(row-2, column-2);

        Playfield background = new Playfield(row, column);
        background.fillFieldArray();
        BombList bombList = new BombList();

        List<Player> playerList = new ArrayList<Player>();

        Player player1 = new Player(startPosition1, background, bombList);
        playerList.add(player1);
        Player player2 = new Player(startPosition2, background, bombList);
        playerList.add(player2);


        new Menu();
        final GameFrame frame = new GameFrame(background, playerList);

        frame.setSize(background.getRow() * 21, background.getColumn() * 21);
        frame.setVisible(true);

    }


}
