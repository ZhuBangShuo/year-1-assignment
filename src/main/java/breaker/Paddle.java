package breaker;

import processing.core.PApplet;
import processing.core.PImage;

public class Paddle {

    private PImage img;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int[] range = new int[40];

    public Paddle(PImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }
    
    public void tock(){
        for (int i = 0; i < 40; i++){
            range[i] = this.x + i;
        }
    }
    
    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
        tock();
    }
}