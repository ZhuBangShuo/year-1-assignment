
package breaker.bricks;

import processing.core.PApplet;
import processing.core.PImage;


public class GreenBrick extends Brick {

    private PImage img;
    protected int width;
    protected int height;
    // public Powerup power;

    public GreenBrick(PImage img, int x, int y) {
        super(x, y);
        // this.power = power;
        this.img = img;
        this.width = 20;
        this.height = 10;
        for (int i = 0; i < 20; i ++){
            xrange[i] = this.x + i;
        }
        for (int i = 0; i < 10; i ++){
            yrange[i] = this.y + i;
        }
    }
    
    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
    }
}
