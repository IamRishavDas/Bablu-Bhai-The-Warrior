package NewGame;

import java.awt.Graphics;

import Entities.Player;
import Levels.LevelManager;

import javax.swing.Timer;

@SuppressWarnings("unused")
public class Game implements Runnable {
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);

    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private LevelManager levelManager;

    // intitial player position
    private final int initialPlayerPosX = 200;
    private final int initialPlayerPosY = 200;
    


    // FPS settter
    private Thread gameThread;
    private final int FPS = 120;

    // UPS setter (Update per Sec)
    private final int UPS = 200;

    // creating a player in Game
    private Player player;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void initClasses(){
        levelManager = new LevelManager(this);
        player = new Player(initialPlayerPosX, initialPlayerPosY, (int)(64 * SCALE), (int)(40 * SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        levelManager.render(g);
        player.render(g);
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

            deltaU += (currTime - prevTime)/timePerUpddate;
            deltaF += (currTime - prevTime)/timePerFrame;

            prevTime = currTime;
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1){
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

    // enhance frame updates and update per second
    private final int DELAY = 1000;

    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            double timePerFrame = 1e9 / FPS;
            double timePerUpddate = 1e9 / UPS;

            long prevTime = System.nanoTime();
            int updates = 0;

            double deltaU = 0;
            double deltaF = 0;

            int frames = 0;
            long lastCheck = System.currentTimeMillis();

            long currTime = System.nanoTime();

            deltaU += (currTime - prevTime)/timePerUpddate;
            deltaF += (currTime - prevTime)/timePerFrame;

            prevTime = currTime;
            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            
            if(deltaF >= 1){
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
    };

    Timer timer = new Timer(DELAY, taskPerformer);
    //timer.start();

    public Player getPlayer(){
        return this.player;
    }

    public void windowFocousLost(){
        player.resetDirections();
    }
}
