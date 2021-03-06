/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package breaker;

import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import processing.core.PApplet;
import processing.core.PImage;

import breaker.bricks.Brick;
import breaker.bricks.BlueBrick;
import breaker.bricks.RedBrick;
import breaker.bricks.GreenBrick;
import breaker.bricks.PinkBrick;
import breaker.bricks.Powerup;

import breaker.Paddle;

import breaker.Ball;

public class App extends PApplet {
    //all requisite lists and objects instantiated here with null value
    List<Brick> bricks;
    Paddle player;
    String name;
    ArrayList<Long> longx = new ArrayList<>();
    ArrayList<Long> longy = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    // ArrayList<String> power = new ArrayList<>();
    ArrayList<Integer> intx = new ArrayList<>();
    ArrayList<Integer> inty = new ArrayList<>();
    ArrayList<Ball> ball = new ArrayList<>();
    //filename of desired level
    String filename = "Level1.json";
    String nextlevel;
    

    public App() {
        bricks = new ArrayList<>();
    }

    public void settings() {
        size(520, 400);
    }

    public void setup() {
        frameRate(60);
        //instantiating player and ball
        player = new Paddle(loadImage("paddle.png"), 240, 360);
        ball.add(new Ball(loadImage("ball.png"), 258, 100, new int[] {1, 2}));
        
        //code block for reading json file
        
        JSONParser parser = new JSONParser();
        try {
            if (filename == null){
              System.out.println("YOU WIN! WELL DONE! WINNER!");
              System.exit(0);
            }
          
            Reader reader = new FileReader(filename);
        
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            
            name = (String) jsonObject.get("name");
            System.out.println(name + "!");
            nextlevel = (String) jsonObject.get("next level");
        
            // loop array
            JSONArray brickbuilder = (JSONArray) jsonObject.get("bricks");
            Iterator<JSONObject> iterator = brickbuilder.iterator();
            while (iterator.hasNext()) {
                JSONObject brickiterator = iterator.next();
                longx.add((Long) brickiterator.get("x"));
                longy.add((Long) brickiterator.get("y"));
                id.add((String) brickiterator.get("id"));
                // power.add((String) brickiterator.get("powerup"));
            }
            // convert long arrays to int arrays 
            for (int i = 0; i < longx.size(); i ++){
                Integer intchanger = longx.get(i).intValue();
                intx.add(intchanger);
            }
            for (int i = 0; i < longy.size(); i ++){
                Integer intchanger = longy.get(i).intValue();
                inty.add(intchanger);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //level builder loop block
        int len = id.size();
        
        for (int i = 0; i < len; i++){
            if (id.get(i).equals("brick_blue")){
                //checks to see if block contains powerup
                // if (power.get(i) != null){
                //     bricks.add(new BlueBrick(loadImage("brick_blue.png"), intx.get(i), inty.get(i), new Powerup(loadImage("ball_powerup.png"), intx.get(i)+2, inty.get(i))));
                // } else {
                //     bricks.add(new BlueBrick(loadImage("brick_blue.png"), intx.get(i), inty.get(i), null));
                // }
                bricks.add(new BlueBrick(loadImage("brick_blue.png"), intx.get(i), inty.get(i)));
            }
            if (id.get(i).equals("brick_red")){
                bricks.add(new RedBrick(loadImage("brick_red.png"), intx.get(i), inty.get(i)));
            }
            if (id.get(i).equals("brick_green")){
                bricks.add(new GreenBrick(loadImage("brick_green.png"), intx.get(i), inty.get(i)));
            }
            if (id.get(i).equals("brick_pink")){
                bricks.add(new PinkBrick(loadImage("brick_pink.png"), intx.get(i), inty.get(i)));
            }
        }
    }
    //keyboard controls
    public void keyPressed(){
        if (key == CODED){
            if (keyCode == LEFT && player.x > 0){
                player.x -= 15;
            }
            if (keyCode == RIGHT && player.x < 480){
                player.x += 15;
            }
        }
    }

    public void draw() {
        background(0);
      
        for(Brick b : bricks) {
            b.draw(this);
        }
        // draws player paddle
        player.draw(this);
        // draws ball(s) (multiple balls possible through powerup)
        for(Ball ba : ball){
            ba.draw(this);
        }
        // paddle collision with ball
        for (int i = 0; i < ball.size(); i++){
            ball.get(i).rebound(player);
        }
        // ball collision with bricks
        for (int l = 0; l < ball.size(); l++){
            for (int i = 0; i < 20; i++){
                for (int j = 0; j < 10; j++){
                    for (int k = 0; k < bricks.size(); k++){
                        if (ball.get(l).x == bricks.get(k).xrange[i] && ball.get(l).y == bricks.get(k).yrange[8]){
                            ball.get(l).velocity[1] = ball.get(l).velocity[1]*-1;
                            if (bricks.get(k).power != null){
                                bricks.get(k).power.draw(this);
                            }
                            bricks.remove(k);
                        }
                        else if (ball.get(l).x == bricks.get(k).xrange[i] && ball.get(l).y == bricks.get(k).yrange[0]){
                            ball.get(l).velocity[1] = ball.get(l).velocity[1]*-1;
                            if (bricks.get(k).power != null){
                                bricks.get(k).power.draw(this);
                            }
                            bricks.remove(k);
                        }
                        else if (ball.get(l).x == bricks.get(k).xrange[0] && ball.get(l).y == bricks.get(k).yrange[j]){
                            ball.get(l).velocity[0] = ball.get(l).velocity[0]*-1;
                            if (bricks.get(k).power != null){
                                bricks.get(k).power.draw(this);
                            }
                            bricks.remove(k);
                        }
                        else if (ball.get(l).x == bricks.get(k).xrange[19] && ball.get(l).y == bricks.get(k).yrange[j]){
                            ball.get(l).velocity[0] = ball.get(l).velocity[0]*-1;
                            if (bricks.get(k).power != null){
                                bricks.get(k).power.draw(this);
                            }
                            bricks.remove(k);
                        }
                    }
                }
            }
        }
        //Loss condition
        for (int i = 0; i < ball.size(); i++){
            if (ball.get(i).y >= 400){
                ball.remove(i);
            }
        }
        if (ball.size() == 0){
            System.out.println("YOU LOSE! TRY AGAIN! LOSER!");
            exit();
        }
        // end of level check -> loads next level
        if (bricks.size() == 0){
            //reset ball
            for (int i = 0; i < ball.size(); i ++){
                ball.remove(i);
            }
            filename = nextlevel;
            setup();
        }
    }

    public static void main(String[] args) {
        PApplet.main("breaker.App");
    }
}
