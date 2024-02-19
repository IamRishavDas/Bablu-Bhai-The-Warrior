package NewGame;

import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;

import java.awt.Graphics;

public class GamePanel extends JPanel {
    private float posX = 50f;
    private float posY = 50f;

    // controlling speed
    private float dirX = 0.5f;
    private float dirY = 0.5f;;

    // fps counter
    private int frames = 0;
    private long lastCheck = 0;

    public GamePanel() {
        addKeyListener(new KeyController(this));
        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));
    }

    // for mouse Listeners
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(int value) {
        posX += value;
    }

    public void setPosY(int value) {
        posY += value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillOval((int)posX, (int)posY, 100, 100);
        updateOval();

        frames++;
        if ((System.currentTimeMillis() - lastCheck) >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }

        repaint();
    }

    private void updateOval() {
        
        posX += dirX;
        if (posX > 900 || posX < 0)
            dirX *= -1;

        posY += dirY;

        if (posY > 500 || posY < 0)
            dirY *= -1;
    }

}
