
package breaker;

import processing.core.PApplet;
import processing.core.PImage;


public class Ball {

    private PImage img;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int[] velocity;

    public Ball(PImage img, int x, int y, int[] velocity) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 5;
        this.velocity = velocity;
    }
    
    public void tick(){
        this.x += velocity[0];
        this.y += velocity[1];
        if (this.x == 0 || this.x == 516){
            velocity[0] = velocity[0]*-1;
        }
        if (this.y == 0){
            velocity[1] = velocity[1]*-1;
        }
    }
    
    public void rebound(Paddle p){
        for (int n = 0; n < 10; n++){
            if (this.x == p.range[n] && this.y == p.y){
                if (velocity[0] > 0){
                    velocity[0] = 2;
                    velocity[1] = -1;
                } else if (velocity [0] < 0){
                    velocity[0] = -2;
                    velocity[1] = -1;
                }
            }
        }
        for (int n = 10; n < 20; n++){
            if (this.x == p.range[n] && this.y == p.y){
                if (velocity[0] > 0){
                    velocity[0] = 1;
                    velocity[1] = -2;
                } else if (velocity [0] < 0){
                    velocity[0] = -1;
                    velocity[1] = -2;
                }
            }
        }
        for (int n = 20; n < 30; n++){
            if (this.x == p.range[n] && this.y == p.y){
                if (velocity[0] > 0){
                    velocity[0] = 1;
                    velocity[1] = -2;
                } else if (velocity [0] < 0){
                    velocity[0] = -1;
                    velocity[1] = -2;
                }
            }
        }
        for (int n = 30; n < 39; n++){
            if (this.x == p.range[n] && this.y == p.y){
                if (velocity[0] > 0){
                    velocity[0] = 2;
                    velocity[1] = -1;
                } else if (velocity [0] < 0){
                    velocity[0] = -2;
                    velocity[1] = -1;
                }
            }
        }
    }
    
    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
        tick();
    }
}
