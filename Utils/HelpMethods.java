package Utils;

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
}
