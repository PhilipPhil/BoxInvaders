package main;

import java.awt.*;

public class Player extends GameObject {
    private static int WIDTH = 5;
    private static int HEIGHT = WIDTH;
    Handler handler;


    public Player(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x,0,Game.WIDTH - 11);
        y = Game.clamp(y,0,Game.HEIGHT - 34);
        collision();

    }



    private void collision() {
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if(object.getId() == ID.BasicEnemy){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH -=4;
                    handler.objects.remove(object);
                    AudioPlayer.playSound(AudioPlayer.getSound("BasicEnemyCrash"));

                }
            }
            if(object.getId() == ID.SmartEnemy){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH -=2;
                    AudioPlayer.playSound(AudioPlayer.getSound("SmartEnemyCrash"));
                }
            }

            if(object.getId() == ID.Bullet){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH -=20;
                    handler.objects.remove(object);
                    AudioPlayer.playSound(AudioPlayer.getSound("BasicEnemyCrash"));

                }
            }
            if(object.getId() == ID.SmartBullet){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH -=10;
                    handler.objects.remove(object);
                    AudioPlayer.playSound(AudioPlayer.getSound("SmartEnemyCrash"));

                }
            }

            if(object.getId() == ID.HealthBox){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH +=30;
                    handler.objects.remove(object);
                    AudioPlayer.playSound(AudioPlayer.getSound("HealthUp"));

                }
            }
            if(object.getId() == ID.Boss1){
                if(getBounds().intersects(object.getBounds())){
                    HUD.HEALTH -=99;
                }
            }


        }
    }


    public void render(Graphics g) {

        if(id == ID.RedPlayer) g.setColor(Color.red);
        if(id == ID.BluePlayer) g.setColor(Color.blue);
        if(id == ID.Player) g.setColor(Color.WHITE);
        g.fillRect(x,y,WIDTH,HEIGHT);


        handler.addObject(new Trail(x,y,ID.Trail,handler,.8f,Color.WHITE,WIDTH,HEIGHT));
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }
}
