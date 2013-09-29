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
        Player player = new Player(startPosition2, background);
        background.fillFieldArray();

        new Menu();
        final GameFrame frame = new GameFrame(background, player);

        frame.setSize(background.getRow() * 21, background.getColumn() * 21);
        frame.setVisible(true);

    }


}
