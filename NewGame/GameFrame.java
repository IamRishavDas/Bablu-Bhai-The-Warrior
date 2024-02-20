package NewGame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame(GamePanel gamePanel) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("GameFrame");
        add(gamePanel);
        pack(); // adjust the frame size according to the panel dim
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                
            }

            // if the game window loose the focous anytime then the player direction booleans will again set as false
            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocousLost();
            }
            
        });
    }
    
}
