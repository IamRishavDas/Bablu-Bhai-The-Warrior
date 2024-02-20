package NewGame;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GamePanel extends JPanel {

    // load image
    private BufferedImage image, subImage;

    // panel dim
    private final int panelWidth  = 1280;
    private final int panelHeight = 800;

    // ball pos
    private float posX = 50f;
    private float posY = 50f;

    // // controlling speed
    // private float dirX = 3f;
    // private float dirY = 3f;

    // adding panel attributes and listeners
    public GamePanel() {
        importImage();
        addKeyListener(new KeyController(this));
        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));
        setPanelSize();
    }

    private void importImage(){
        String filePath = "/Resources/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(filePath);
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // setting panel dimension
    private void setPanelSize(){
        Dimension size = new Dimension(panelWidth,panelHeight);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
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
        subImage = image.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImage, (int)posX, (int)posY, 64*2, 40*2, null);
    }

    //chagning oval direction for bounce effect
    // private void updateOval() {
        
    //     posX += dirX;
    //     if (posX > (panelWidth - 100) || posX < 0){
    //         dirX *= -1;
    //         color = getRandomColor();
    //     }

    //     posY += dirY;
    //     if (posY > (panelHeight - 100) || posY < 0){
    //         dirY *= -1;
    //         color = getRandomColor();
    //     }
    // }

    // private Color getRandomColor(){
    //     Random random = new Random();
    //     int r = random.nextInt(256);
    //     int g = random.nextInt(256);
    //     int b = random.nextInt(256);
    //     return new Color(r,g,b);
    // }

}
