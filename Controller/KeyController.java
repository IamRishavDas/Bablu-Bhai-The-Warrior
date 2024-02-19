package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import NewGame.GamePanel;

public class KeyController implements KeyListener{
    private int move = 10;
    GamePanel gamePanel;
    public KeyController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT -> gamePanel.setPosX(move);
            case KeyEvent.VK_LEFT  -> gamePanel.setPosX(-move);
            case KeyEvent.VK_UP    -> gamePanel.setPosY(-move);
            case KeyEvent.VK_DOWN  -> gamePanel.setPosY(move);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
