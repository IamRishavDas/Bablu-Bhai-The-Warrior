package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import NewGame.GamePanel;

public class KeyController implements KeyListener{
    private GamePanel gamePanel;
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
            case KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().setRight(true); 
            case KeyEvent.VK_LEFT  -> gamePanel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_UP    -> gamePanel.getGame().getPlayer().setUp(true);
            case KeyEvent.VK_DOWN  -> gamePanel.getGame().getPlayer().setDown(true);
            case KeyEvent.VK_A     -> gamePanel.getGame().getPlayer().setAttacking(true);
            case KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT -> gamePanel.getGame().getPlayer().setRight(false); 
            case KeyEvent.VK_LEFT  -> gamePanel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_UP    -> gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_DOWN  -> gamePanel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(false);
        }
    }
}
