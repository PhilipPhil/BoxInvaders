package main;

import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject {

    private Handler handler;

    private static int WIDTH = 10;
    private static int HEIGHT = WIDTH;

    private Random r = new Random();

    public BossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        GameObject p = null;
        for(int i = 0; i < handler.objects.size(); i++){
            if(handler.objects.get(i).getId() == ID.Player){
                p = handler.objects.get(i);
            }
        }

        velX = (p.getX() - x)/50;
        velY = r.nextInt(3) + 3;

        this.handler = handler;
    }


    public void tick() {
        x += velX;
        y += velY;


        if(y >= Game.HEIGHT) handler.removeObject(this);



    }


    public void render(Graphics g) {


        g.setColor(Color.BLUE);
        g.fillRect(x,y,WIDTH,HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
