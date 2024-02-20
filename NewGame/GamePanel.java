package NewGame;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;
import Utils.Constants;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GamePanel extends JPanel {

    // load image
    private BufferedImage image;

    // animations

    private int aniTick, aniSpeed = 15, aniIndex; // the lower aniSpeed leads to increase the animation speed

    // idle animations
    private BufferedImage[][] animations;

    // current state of the player
    private int playerAction = Constants.PlayerConstants.IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    private final int move = 5;

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
        loadAnimations();
        addKeyListener(new KeyController(this));
        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));
        setPanelSize();
    }

    private void loadAnimations(){
        animations = new BufferedImage[9][6];

        for(int i = 0; i < animations.length; i++) {
            for(int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j*64, i*40, 64, 40);
            }
        }
    }

    private void importImage(){
        String filePath = "/Resources/player_sprites.png";
        InputStream is = getClass().getResourceAsStream(filePath);
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // setting panel dimension
    private void setPanelSize(){
        Dimension size = new Dimension(panelWidth,panelHeight);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void setAnimation(){
        if(moving){
            playerAction = Constants.PlayerConstants.RUNNING;
        }else{
            playerAction = Constants.PlayerConstants.IDLE;
        }
    }

    public void updatePos(){
        if(moving){
            switch (playerDir) {
                case Constants.Directions.LEFT -> posX -= move;
                case Constants.Directions.RIGHT -> posX += move;
                case Constants.Directions.UP -> posY -= move;
                case Constants.Directions.DOWN -> posY += move;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimation();
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][aniIndex], (int)posX, (int)posY, 64*3, 40*3, null);
    }

    private void updateAnimation(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= Constants.PlayerConstants.GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
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
