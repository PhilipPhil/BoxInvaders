package main;

import java.awt.*;
import java.util.Random;

public class SmartBossBullets extends GameObject {

    private Handler handler;

    private static int WIDTH = 5;
    private static int HEIGHT = WIDTH;

    private GameObject player;

    public SmartBossBullets(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        // find the player
        for(int i = 0; i < handler.objects.size(); i++){
            if(handler.objects.get(i).getId() == ID.Player) player = handler.objects.get(i);
        }

        Random r = new Random();
//        velX = r.nextInt(10) + 1;
//        velY = r.nextInt(10) + 1;

    }


    public void tick() {


        int diffx = player.getX() - x;
        int diffy = player.getY() - y;
        int temp =(diffx*diffx) + (diffy*diffy);
        double dist = (float) Math.sqrt(temp);


        if(diffx > 0){
            velX = Math.max((int) (diffx/dist), 1);
        } else {
            velX = Math.min((int) (diffx/dist), -1);
        }

        if(diffy > 0){
            velY = Math.max((int) (diffy/dist), 1);
        } else {
            velY = Math.min((int) (diffy/dist), -1);
        }



        x += velX*2;
        y += velY*2;


        handler.addObject(new Trail(x,y,ID.Trail,handler,0.1f,Color.cyan,WIDTH,HEIGHT));  // comented out because didn't want to change my code to make this feature work
    }


    public void render(Graphics g) {


        g.setColor(Color.CYAN);
        g.fillRect(x,y,WIDTH,HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
