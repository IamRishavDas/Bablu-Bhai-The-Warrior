package NewGame;

import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;

import java.awt.Graphics;

public class GamePanel extends JPanel {
    private int posX = 100;
    private int posY = 100;

    public GamePanel() {
        addKeyListener(new KeyController(this));
        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));
    }

    //for mouse Listeners
    public void setPos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(int value){
        posX += value;
    }

    public void setPosY(int value){
        posY += value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillOval(posX, posY, 100, 100);
        repaint();
    }
}
