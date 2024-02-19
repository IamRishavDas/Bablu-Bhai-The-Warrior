package NewGame;

import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GamePanel extends JPanel {
    private float posX = 50f;
    private float posY = 50f;

    // controlling speed
    private float dirX = 3f;
    private float dirY = 3f;
    
    //ball color
    private Color color = new Color(24,255,254);

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

        g.setColor(color);
        g.fillOval((int)posX, (int)posY, 100, 100);
        updateOval();      
    }

    private void updateOval() {
        
        posX += dirX;
        if (posX > 900 || posX < 0){
            dirX *= -1;
            color = getRandomColor();
        }

        posY += dirY;

        if (posY > 500 || posY < 0){
            dirY *= -1;
            color = getRandomColor();
        }
    }

    private Color getRandomColor(){
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r,g,b);
    }

}
