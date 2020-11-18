package breaker.bricks;

import processing.core.PApplet;

public abstract class Brick {

    protected int x;
    protected int y;
    public int[] xrange = new int[20];
    public int[] yrange = new int[10];
    public Powerup power;
    
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void draw(PApplet app);
}
