package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface StateMethods {
    public void update();
    public void render(Graphics g);

    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);

    public void test();
}
