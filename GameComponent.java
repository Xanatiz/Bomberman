import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class GameComponent extends JComponent implements ListenerHandler {
    private Playfield background;
    private PlayerList playerList;
    private BombList bombList;

    public GameComponent(Playfield background, PlayerList playerList, BombList bombList) { //, List<Flame> flameList) {
        this.background = background;
        this.playerList = playerList;
        this.bombList = bombList;
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
                paintPosition(g2 ,new Position(i, j));
            }
        }
    }

    public void paintPosition(Graphics g2, Position position){
        if (playerList.contains(position) && playerList.get(position).isAlive()){
            if(playerList.get(position).getId().read()==1)
                g2.setColor(BlockType.PLAYER1.getColor());
            else
                g2.setColor(BlockType.PLAYER2.getColor());
            g2.fillRect(position.getX() * 20, position.getY() * 20, 20, 20);
        } else if (bombList.contains(position)){
            g2.setColor(BlockType.BOMB.getColor());
            g2.fillRect(position.getX() * 20, position.getY() * 20, 20, 20);
        } else {
            g2.setColor(background.getData(position).getColor());
            g2.fillRect(position.getX() * 20, position.getY() * 20, 20, 20);
        }
    }


    private void addBindings() {
        final ID id1 = new ID(1);
        final ID id2 = new ID(2);
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
                playerList.get(id2).moveUp();
                updateBoard();
            }
        };
        Action moveDown = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id2).moveDown();
                updateBoard();
            }
        };

        Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id2).moveRight();
                updateBoard();
            }
        };
        Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id2).moveLeft();
                updateBoard();
            }
        };

        Action dropBomb = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id2).dropBomb();
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
                playerList.get(id1).moveUp();
                updateBoard();
            }
        };
        Action moveDown2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id1).moveDown();
                updateBoard();
            }
        };

        Action moveRight2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id1).moveRight();
                updateBoard();
            }
        };
        Action moveLeft2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id1).moveLeft();
                updateBoard();
            }
        };

        Action dropBomb2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playerList.get(id1).dropBomb();
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

