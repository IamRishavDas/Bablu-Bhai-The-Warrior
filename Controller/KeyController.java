package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameStates.GameState;
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
        switch (GameState.STATE) {
            case MENU    -> gamePanel.getGame().getMenu().keyPressed(e);
            case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.STATE) {
            case MENU    -> gamePanel.getGame().getMenu().keyReleased(e);
            case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);            
        }
    }
}
