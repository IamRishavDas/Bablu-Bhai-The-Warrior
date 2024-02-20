package NewGame;

@SuppressWarnings("unused")
public class Game implements Runnable {
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    // FPS settter
    private Thread gameThread;
    private final int FPS = 120;

    // UPS setter (Update per Sec)
    private final int UPS = 200;

    public Game() {
        gamePanel = new GamePanel();
        gameFrame = new GameFrame(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        gamePanel.updateGame();
    }

    // game loop (fps counter)
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
}