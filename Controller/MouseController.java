package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import NewGame.GamePanel;

public class MouseController implements MouseListener, MouseMotionListener{

    private GamePanel gamePanel;

    public MouseController(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
       switch (GameState.STATE) {
        case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
        case PLAYING -> gamePanel.getGame().getMenu().mousePressed(e);
        default -> throw new IllegalArgumentException("Unexpected value: " + GameState.STATE);
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.STATE) {
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> gamePanel.getGame().getMenu().mouseReleased(e);
            default -> throw new IllegalArgumentException("Unexpected value: " + GameState.STATE);
           }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.STATE) {
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> gamePanel.getGame().getMenu().mouseMoved(e);
            default -> throw new IllegalArgumentException("Unexpected value: " + GameState.STATE);
           }
    }
}
