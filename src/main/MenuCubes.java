package main;

import java.awt.*;
import java.util.Random;

public class MenuCubes extends GameObject {

    private Handler handler;

    private static int WIDTH = 25;
    private static int HEIGHT = WIDTH;
    private Random r = new Random();
    public MenuCubes(int x, int y, ID id, Handler handler) {
        super(x, y, id);


        int dir1 = 1;
        int dir2 = 1;
        if(r.nextInt(2)==0) dir1 = -1;
        if(r.nextInt(2)==0) dir2 = -1;

        velX = (r.nextInt(5)+1)*dir1;
        velY = (r.nextInt(5)+1)*dir2;

        this.handler = handler;
    }


    public void tick() {
        x += velX;
        y += velY;
        if(y <= 0|| y >= Game.HEIGHT - 30) velY *= -1;
        if(x <= 0|| x >= Game.WIDTH - 18) velX *= -1;



        handler.addObject(new Trail(x,y,ID.Trail,handler,0.1f,color,WIDTH,HEIGHT));
    }

    private Color color;
    int col = 0;

    public void render(Graphics g) {
        if (col == 0) {
            col++;
            color = Color.green;
            g.setColor(Color.GREEN);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else if (col == 1) {
            col++;
            color = Color.yellow;
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else if (col == 2) {
            col++;
            color = Color.blue;
            g.setColor(Color.BLUE);
            g.fillRect(x, y, WIDTH, HEIGHT);
        } else {
            col = 0;
            color = Color.red;
            g.setColor(Color.RED);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
