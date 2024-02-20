package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import NewGame.GamePanel;
import Utils.Constants;

public class KeyController implements KeyListener{
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
            case KeyEvent.VK_RIGHT -> gamePanel.setDirection(Constants.Directions.RIGHT);
            case KeyEvent.VK_LEFT  -> gamePanel.setDirection(Constants.Directions.LEFT);
            case KeyEvent.VK_UP    -> gamePanel.setDirection(Constants.Directions.UP);
            case KeyEvent.VK_DOWN  -> gamePanel.setDirection(Constants.Directions.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_DOWN  -> gamePanel.setMoving(false);
        }
    }
}
