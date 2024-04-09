package NewGame;

import java.awt.Graphics;

import Entities.Player;
import GameStates.GameState;
import GameStates.Menu;
import GameStates.Playing;
import Levels.LevelManager;

@SuppressWarnings("unused")
public class Game implements Runnable {
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);

    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // intitial player position
    public final static int initialPlayerPosX = 100;
    public final static int initialPlayerPosY = 200;

    private Playing playing;
    private Menu menu;

    // FPS settter
    private Thread gameThread;
    private final int FPS = 120;

    // UPS setter (Update per Sec)
    private final int UPS = 200;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        
        switch(GameStates.GameState.STATE){
            case MENU    -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS -> System.exit(0);
            case QUIT    -> System.exit(0);
            default -> throw new IllegalArgumentException("Unexpected value: " + GameStates.GameState.STATE);
        }
    }

    public void render(Graphics g) {
        
        switch(GameStates.GameState.STATE){
            case MENU    -> menu.render(g);
            case PLAYING -> playing.render(g);
            default -> throw new IllegalArgumentException("Unexpected value: " + GameStates.GameState.STATE);
                
        }
    }

    // game loop (fps counter) && UPS counter
    @Override
    public void run() {
        double timePerFrame = 1e9 / FPS;
        double timePerUpddate = 1e9 / UPS;

        long prevTime = System.nanoTime();
        int updates = 0;

        double deltaU = 0;
        double deltaF = 0;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            long currTime = System.nanoTime();

            deltaU += (currTime - prevTime) / timePerUpddate;
            deltaF += (currTime - prevTime) / timePerFrame;

            prevTime = currTime;
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();

                frames++;
                deltaF--;
            }

            if ((System.currentTimeMillis() - lastCheck) >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocousLost() {
        if(GameState.STATE == GameState.PLAYING){
            playing.getPlayer().resetDirections();
        }
    }

    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying(){
        return playing;
    }
}
