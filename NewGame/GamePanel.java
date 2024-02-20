package NewGame;


import javax.swing.JPanel;

import Controller.KeyController;
import Controller.MouseController;

import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    // panel dim
    private final int panelWidth  = 1280;
    private final int panelHeight = 800;

    // using constructor initialize the game Component
    private Game game;

    // adding panel attributes and listeners
    public GamePanel(Game game) {
        this.game = game;
        addKeyListener(new KeyController(this));
        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));
        setPanelSize();
    }

    // setting panel dimension
    private void setPanelSize(){
        Dimension size = new Dimension(panelWidth,panelHeight);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return this.game;
    }

}
