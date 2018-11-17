package main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {

    private Handler handler;

    private static int WIDTH = 12;
    private static int HEIGHT = WIDTH;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);


        Random r = new Random();
        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5) + 1;

        this.handler = handler;
    }


    public void tick() {
        x += velX;
        y += velY;
        if(y <= 0|| y >= Game.HEIGHT - 30) velY *= -1;
        if(x <= 0|| x >= Game.WIDTH - 18) velX *= -1;



        handler.addObject(new Trail(x,y,ID.Trail,handler,0.1f,Color.red,WIDTH,HEIGHT));  // comented out because didn't want to change my code to make this feature work
    }


    public void render(Graphics g) {


        g.setColor(Color.MAGENTA);
        g.fillRect(x,y,WIDTH,HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
