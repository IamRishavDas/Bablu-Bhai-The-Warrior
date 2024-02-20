package NewGame;

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
    }
    
}
