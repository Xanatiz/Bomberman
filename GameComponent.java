import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class GameComponent extends JComponent implements ListenerHandler {
    Playfield background;
    Player player;
    public GameComponent(Playfield background, Player player){
        this.background = background;
        this.player = player;
        this.setLayout(new BorderLayout());
        addBindings();
    }

    public void updateBoard(){
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;

        for (int i = 0; i < background.getRow(); i++){
            for (int j = 0; j < background.getColumn(); j++){
                if(player.getPosition().getX()==j && player.getPosition().getY()==i&&player.isAlive()){
                    g2.setColor(BlockType.PLAYER.getColor());
                }
                else{
                    g2.setColor(this.background.getData(i, j).getColor());
                }
                g2.draw(new Rectangle2D.Double(j*20, i*20,20,20));
                g2.fillRect(j*20,i*20,20,20);
            }
        }
    }

    private void addBindings(){
        InputMap map1 =getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map2 =getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map3 =getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map4 =getInputMap(JComponent.WHEN_FOCUSED);
        InputMap map5 =getInputMap(JComponent.WHEN_FOCUSED);

        map1.put(KeyStroke.getKeyStroke("UP"),"moveUp");
        map2.put(KeyStroke.getKeyStroke("DOWN"),"moveDown");
        map3.put(KeyStroke.getKeyStroke("RIGHT"),"moveRight");
        map4.put(KeyStroke.getKeyStroke("LEFT"),"moveLeft");
        map5.put(KeyStroke.getKeyStroke("SPACE"),"dropBomb");

        Action moveUp = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.moveUp();
                updateBoard();
            }
        };
        Action moveDown = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.moveDown();
                updateBoard();
            }
        };

        Action moveRight = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.moveRight();
                updateBoard();
            }
        };
        Action moveLeft = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.moveLeft();
                updateBoard();
            }
        };

        Action dropBomb = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                player.dropBomb();
                updateBoard();
            }
        };

        getActionMap().put("moveUp", moveUp);
        getActionMap().put("moveDown", moveDown);
        getActionMap().put("moveRight", moveRight);
        getActionMap().put("moveLeft", moveLeft);
        getActionMap().put("dropBomb", dropBomb);

    }



}

