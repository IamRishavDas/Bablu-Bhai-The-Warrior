package NewGame;

public class Game implements Runnable{
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    private Thread gameThread;
    private final int FPS = 120;

    public Game() {
        gamePanel = new GamePanel();
        gameFrame = new GameFrame();
        gameFrame.add(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double timePerFrame = 1000000000.0 / FPS;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        while (true) {
            now = System.nanoTime();
            if(now - lastFrame > timePerFrame){
                gamePanel.repaint();
                lastFrame = now;
            }
        }
    }
}