import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class GameComponent extends JComponent implements ListenerHandler {
    private Playfield background;
    private List<Player> playerList;

    public GameComponent(Playfield background, List<Player> playerList) {
        this.background = background;
        this.playerList = playerList;
        this.setLayout(new BorderLayout());
        addBindings();
    }

    public void updateBoard() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < background.getRow(); i++) {
            for (int j = 0; j < background.getColumn(); j++) {
                for (int p = 0; p <= playerList.size() - 1; p++) {
                    if (playerList.get(p).getPosition().getX() == j && playerList.get(p).getPosition().getY() == i && playerList.get(p).isAlive()){
                        if (p == 0){
                            g2.setColor(BlockType.PLAYER1.getColor());
                            g2.fillRect(j * 20, i * 20, 20, 20);
                            break;
                        }else{
                            g2.setColor(BlockType.PLAYER2.getColor());
                            g2.fillRect(j * 20, i * 20, 20, 20);
                            break;
                        }
                    }else{
                        g2.setColor(background.getData(i, j).getColor());
                        g2.fillRect(j * 20, i * 20, 20, 20);
                    }
                }
            }
        }
    }

    private void addBindings() {
        InputMap map1 = getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map2 = getInputMap(JComponent.WHEN_FOCUSED);

        map1.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        map1.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        map1.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        map1.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        map2.put(KeyStroke.getKeyStroke("ENTER"), "dropBomb");

        Action moveUp = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(0).moveUp();
                updateBoard();
            }
        };
        Action moveDown = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(0).moveDown();
                updateBoard();
            }
        };

        Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(0).moveRight();
                updateBoard();
            }
        };
        Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(0).moveLeft();
                updateBoard();
            }
        };

        Action dropBomb = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(0).dropBomb();
                updateBoard();
            }
        };

        getActionMap().put("moveUp", moveUp);
        getActionMap().put("moveDown", moveDown);
        getActionMap().put("moveRight", moveRight);
        getActionMap().put("moveLeft", moveLeft);
        getActionMap().put("dropBomb", dropBomb);



        InputMap map3 = getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map4 = getInputMap(JComponent.WHEN_FOCUSED);

        map3.put(KeyStroke.getKeyStroke("W"), "moveUp2");
        map3.put(KeyStroke.getKeyStroke("S"), "moveDown2");
        map3.put(KeyStroke.getKeyStroke("D"), "moveRight2");
        map3.put(KeyStroke.getKeyStroke("A"), "moveLeft2");
        map4.put(KeyStroke.getKeyStroke("SPACE"), "dropBomb2");

        Action moveUp2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(1).moveUp();
                updateBoard();
            }
        };
        Action moveDown2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(1).moveDown();
                updateBoard();
            }
        };

        Action moveRight2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(1).moveRight();
                updateBoard();
            }
        };
        Action moveLeft2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(1).moveLeft();
                updateBoard();
            }
        };

        Action dropBomb2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(1).dropBomb();
                updateBoard();
            }
        };

        getActionMap().put("moveUp2", moveUp2);
        getActionMap().put("moveDown2", moveDown2);
        getActionMap().put("moveRight2", moveRight2);
        getActionMap().put("moveLeft2", moveLeft2);
        getActionMap().put("dropBomb2", dropBomb2);

    }

}

