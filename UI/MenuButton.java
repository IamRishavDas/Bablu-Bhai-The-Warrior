package UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import GameStates.GameState;
import Utils.LoadSave;
import Utils.Constants.Ui;

public class MenuButton {
    private int xPos;
    private int yPos;
    private int index;
    private int rowIndex;
    private int xOffsetCenter = Ui.Buttons.B_WIDTH/2;
    private GameState state;

    private Rectangle bounds;

    private boolean mouseOver, mousePressed;

    private BufferedImage[] images;

    public MenuButton(int xPos, int yPos, int rowIndex, GameState state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
        initBounds();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, Ui.Buttons.B_WIDTH, Ui.Buttons.B_HEIGHT);
    }

    private void loadImages(){
        images = new BufferedImage[3];
        BufferedImage img = LoadSave.getImage(LoadSave.MENU_BUTTONS);
        for(int i=0; i<images.length; i++){
            images[i] = img.getSubimage(i * Ui.Buttons.B_WIDTH_DEFAULT, rowIndex * Ui.Buttons.B_HEIGHT_DEFAULT, Ui.Buttons.B_WIDTH_DEFAULT, Ui.Buttons.B_HEIGHT_DEFAULT);
        }
    }

    public void render(Graphics g){
        g.drawImage(images[index], xPos - xOffsetCenter, yPos, Ui.Buttons.B_WIDTH, Ui.Buttons.B_HEIGHT, null);
    }

    public void update(){
        index = 0;
        if(mouseOver) index = 1;
        if(mousePressed) index = 2;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGameState(){
        GameState.STATE = state;
    }

    public void resetBooleans(){
        mouseOver = false;
        mousePressed = false;
    }

}
