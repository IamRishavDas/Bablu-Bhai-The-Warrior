package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import NewGame.Game;
import Utils.LoadSave;

@SuppressWarnings("unused")
public class LevelManager {

    private Game game;
    private BufferedImage[] levelImage;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        // levelImage = LoadSave.getImage(LoadSave.LEVEL_1);
        importOutsideSprite();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprite(){
        BufferedImage img =  LoadSave.getImage(LoadSave.LEVEL_1);
        levelImage = new BufferedImage[12*4];
        for(int i=0; i<4; i++){
            for(int j=0; j<12; j++){
                int index = i * 12 + j;
                levelImage[index] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void render(Graphics g){
        for(int i = 0; i<Game.TILES_IN_HEIGHT; i++){
            for(int j = 0; j<Game.TILES_IN_WIDTH; j++){
                int index = levelOne.getSpriteIndex(j, i);
                g.drawImage(levelImage[index], Game.TILES_SIZE * j, Game.TILES_SIZE * i, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelOne;
    }
}
