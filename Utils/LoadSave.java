package Utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER = "player_sprites.png";

    public static BufferedImage getPlayerImage(String filename){
        BufferedImage image = null;
        String filePath = "/Resources/" + filename;
        InputStream is = LoadSave.class.getResourceAsStream(filePath);
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
