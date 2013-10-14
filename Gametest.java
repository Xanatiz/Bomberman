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
        int numberOfPlayers = 2;
        List<Position> startPositions = new ArrayList<Position>();
        startPositions.add(new Position(1, 1));
        startPositions.add(new Position(row-2, column-2));

        Playfield background = new Playfield(row, column);
        background.fillFieldArray();
        BombList bombList = new BombList();
        PlayerList playerList = new PlayerList();

        for(int i = 1; i<=numberOfPlayers; i++)
            playerList.add(new Player(new ID(i), startPositions.get(i-1), background, bombList, playerList));

        new Menu();
        final GameFrame frame = new GameFrame(background, playerList, bombList);

        frame.setSize(background.getRow() * 21, background.getColumn() * 21);
        frame.setVisible(true);
    }
}

