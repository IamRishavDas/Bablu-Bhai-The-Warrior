package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entities.Player;
import Levels.LevelManager;
import NewGame.Game;
import UI.PauseOverlay;

public class Playing extends State implements StateMethods{

    private Player player;
    private LevelManager levelManager;

    private boolean paused;
    private PauseOverlay pauseOverlay;
    
    public Playing(Game game) {
        super(game);
        initClasses();
    }


    public void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(Game.initialPlayerPosX, Game.initialPlayerPosY, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay  = new PauseOverlay();
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
        pauseOverlay.render(g);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        switch(keyEvent){
            case KeyEvent.VK_RIGHT  -> player.setRight(true); 
            case KeyEvent.VK_LEFT   -> player.setLeft(true);
            case KeyEvent.VK_A      -> player.setAttacking(true);
            case KeyEvent.VK_SPACE  -> player.setJump(true);
            case KeyEvent.VK_ESCAPE -> GameState.STATE = GameState.MENU;
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
    public void mouseMoved(MouseEvent e){
        
    }


    @Override
    public void mousePressed(MouseEvent e) {
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
}
