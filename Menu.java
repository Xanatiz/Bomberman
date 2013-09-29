import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: cake
 * Date: 2013-09-25
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */
public class Menu {
    static boolean counter = false;
    static boolean activate = false;
    final JPanel panel = new JPanel();
    final JFrame frame;
    final JButton button = new JButton("EXIT");
    final JButton onePlayerButton = new JButton("One player");
    final JButton twoPlayerButton = new JButton("Two player");
    public Menu(){

        this.frame = new JFrame("TEST");
        createMenus();
        frame.setSize(100, 140);
        frame.setVisible(true);
        while(!activate ){
            while(!counter)
                activate = true;

        }
    }

    private void createMenus(){
        panel.add(onePlayerButton);
        panel.add(twoPlayerButton);
        panel.add(button);
        button.setBackground(Color.ORANGE);
        panel.setBackground(Color.BLACK);
        button.addActionListener(exit);
        onePlayerButton.addActionListener(newGame);
        twoPlayerButton.addActionListener(newGame);
        frame.add(panel);
    }

    ActionListener newGame = new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e){
            counter = true;
            activate = true;
        }
    };

    ActionListener exit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int answer = JOptionPane.showConfirmDialog(frame,"Do you rly?", "Quit?", JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.YES_OPTION)
                System.exit(0);
        }
    };
}


