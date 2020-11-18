package breaker.bricks;

import processing.core.PApplet;
import processing.core.PImage;

public class Powerup {

    private PImage img;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int[] rangex = new int[16];
    protected int[] rangey = new int[16];

    public Powerup(PImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = 16;
        this.height = 16;
        for (int i = 0; i < 15; i ++){
            rangex[i] = this.x + i;
        }
        for (int i = 0; i < 15; i ++){
            rangey[i] = this.y + i;
        }
    }
    
    public void drop(){
        this.x = this.x;
        this.y -= 10;
    }
    
    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
        drop();
    }
}