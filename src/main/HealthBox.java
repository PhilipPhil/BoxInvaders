package main;

import java.awt.*;
import java.util.Random;

public class HealthBox extends GameObject {

    private Handler handler;

    private static int WIDTH = 16;
    private static int HEIGHT = WIDTH;

    public HealthBox(int x, int y, ID id, Handler handler) {
        super(x, y, id);


        Random r = new Random();
        int speed =r.nextInt(30) + 5;
        velX = speed;
        velY = 35 - speed;

        this.handler = handler;
    }


    public void tick() {
        x += velX;
        y += velY;
        if(y <= 0|| y >= Game.HEIGHT - 30) velY *= -1;
        if(x <= 0|| x >= Game.WIDTH - 18) velX *= -1;


    }

    int col = 0;
    public void render(Graphics g) {
//        Random r = new Random();
//        int col = r.nextInt(4);
        if (col == 0) {
            col++;
            g.setColor(Color.GREEN);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else if (col == 1) {
            col++;
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else if (col == 2) {
            col++;
            g.setColor(Color.BLUE);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else {
            col = 0;
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
