package main;

import java.awt.*;
import java.util.Random;

public class Boss1 extends GameObject {

    private Handler handler;

    private static int WIDTH = Game.WIDTH/2;
    private static int HEIGHT = 40;

    private int timer = 80;
    private int timer2 = 80;

    private Random r = new Random();

    public Boss1(int x, int y, ID id, Handler handler) {
        super(x, y, id);


        Random r = new Random();
        velX = 0;
        velY = 1;

        this.handler = handler;
    }


    public void tick() {
        if(timer == 77) AudioPlayer.playSound(AudioPlayer.getSound("BossShowingUp"));
        timer--;
        if(timer <= 0){
            velY = 0;
        }
        if(timer <= 0 ){
            timer2--;
        }
        if(timer2 <= 0){
            int dir = 1;
            if(r.nextInt(2)==1) dir = -1;
            if(velX == 0) velX = 2*dir;
            int spawn = r.nextInt(2);
            if(spawn == 0){
                handler.addObject(new BossBullet(x+150,y+50,ID.Bullet,handler));
                AudioPlayer.playSound(AudioPlayer.getSound("BasicBossShooting"));
            }
            int tracker = r.nextInt(20);
            if(tracker==0){
                handler.addObject(new SmartBossBullets(x+150,y+50,ID.SmartBullet,handler));
                AudioPlayer.playSound(AudioPlayer.getSound("SmartBossShooting"));
            }
        }

        x += velX;
        y += velY;


        if(x <= 0|| x >= Game.WIDTH - 18) velX *= -1;

        //handler.addObject(new Trail(x,y,ID.Trail,handler,0.1f,Color.red,WIDTH,HEIGHT));
    }


    public void render(Graphics g) {


        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,WIDTH,HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
