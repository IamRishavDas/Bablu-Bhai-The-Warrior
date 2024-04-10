package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import NewGame.Game;
import UI.MenuButton;
import Utils.LoadSave;

@SuppressWarnings("unused")
public class Menu extends State implements StateMethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImage = LoadSave.getImage(LoadSave.MENU_BACKGROUND);
        menuWidth  = (int)(backgroundImage.getWidth() * Game.SCALE);
        menuHeight = (int)(backgroundImage.getHeight() * Game.SCALE);
        menuX = (Game.GAME_WIDTH / 2) - (menuWidth / 2);
        menuY = (int)(45 * Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, GameState.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backgroundImage, menuX, menuY, null);
        for (MenuButton mb : buttons)
            mb.render(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            GameState.STATE = GameState.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        for(MenuButton mb: buttons) mb.setMouseOver(false);
        for(MenuButton mb: buttons){
            if(isIn(e, mb)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGameState();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb: buttons) mb.resetBooleans();
    }
}
