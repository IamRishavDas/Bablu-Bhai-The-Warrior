package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import NewGame.Game;

public class Menu extends State implements StateMethods{

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Menu", Game.GAME_WIDTH/2, Game.GAME_HEIGHT/2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyEvent = e.getKeyCode();
        if(keyEvent == KeyEvent.VK_ESCAPE){
            GameState.STATE = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void test(){

    }
    
}
