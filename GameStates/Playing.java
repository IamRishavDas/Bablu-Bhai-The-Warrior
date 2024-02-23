package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entities.Player;
import Levels.LevelManager;
import NewGame.Game;

public class Playing extends State implements StateMethods{

    private Player player;
    private LevelManager levelManager;
    
    public Playing(Game game) {
        super(game);
        initClasses();
    }


    public void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(Game.initialPlayerPosX, Game.initialPlayerPosY, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    public Player getPlayer() {
        return this.player;
    }

    public void windowFocousLost() {
        player.resetDirections();
    }


    @Override
    public void update() {
        levelManager.update();
        player.update();
    }


    @Override
    public void render(Graphics g) {
        levelManager.render(g);
        player.render(g);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT -> player.setRight(true); 
            case KeyEvent.VK_LEFT  -> player.setLeft(true);
            case KeyEvent.VK_A     -> player.setAttacking(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT -> player.setRight(false); 
            case KeyEvent.VK_LEFT  -> player.setLeft(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }


    @Override
    public void test() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
    }
}
