package Utils;

import java.awt.geom.Rectangle2D;

import NewGame.Game;

public class HelpMethods {
    public static boolean canMove(float x, float y, float width, float height, int[][] levelData) {
        if (!isSollid(x, y, levelData)) {
            if (!isSollid(x + width, y + height, levelData)) {
                if (!isSollid(x + width, y, levelData)) {
                    if (!isSollid(x, y + height, levelData)) {
                        return true;
                    } else
                        return false;
                } else
                    return false;
            } else
                return false;
        } else
            return false;
    }

    public static boolean isSollid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Game.GAME_WIDTH) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        if (value >= 48 || value < 0 || value != 11) { // 12th sprtie is blank
            return true;
        } else {
            return false;
        }
    }

    public static float GetEntityPosNextToWall(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile =(int)(hitBox.x / Game.TILES_SIZE);
        if(xSpeed > 0){ // right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitBox.width);
            return tileXPos + xOffset - 1;
        } else { // left
            return currentTile * Game.TILES_SIZE;
        }
    }

}
