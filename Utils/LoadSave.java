package Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import NewGame.Game;

public class LoadSave {


    public static final String LEVEL_1           = "outside_sprites.png";
 
    public static final String PLAYER            = "player_sprites.png";
    public static final String LEVEL_ONE_DATA    = "level_one_data.png";
    public static final String MENU_BUTTONS      = "menu_buttons.png";
    public static final String MENU_BACKGROUND   = "menu_background.png";
    public static final String PAUSE_BACKGROUND  = "pause_menu.png";

    public static BufferedImage getImage(String filename) {
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

    public static int[][] getLevelData() {
        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getImage(LEVEL_ONE_DATA);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                levelData[i][j] = value;
            }
        }
        return levelData;
    }
}
