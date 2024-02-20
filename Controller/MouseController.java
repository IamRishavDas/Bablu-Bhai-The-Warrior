package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // gamePanel.setPos(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // gamePanel.setPos(e.getX(), e.getY());
    }
}
