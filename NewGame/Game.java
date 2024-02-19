package NewGame;

public class Game {
    private GameFrame gameFrame;
    private GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel();
        gameFrame = new GameFrame();
        gameFrame.add(gamePanel);
        gamePanel.requestFocus();
    }
}