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
        Playfield playfield = new Playfield(row, column);
        Playfield background = new Playfield(row, column);
        Player player = new Player(startPosition1, background, playfield);
        background.fillFieldArray();
        playfield.createFrontPlayfield(background);

        new Menu();
        final GameFrame frame = new GameFrame(playfield, background, player);

        frame.setSize(playfield.getRow() * 21, playfield.getColumn() * 21);
        frame.setVisible(true);

    }


}
