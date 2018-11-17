package main;

import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;

    public int scoreKeep = 0;

    private Random r = new Random();


    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){

        int time = 1000;


        if(scoreKeep >= time){
            resetScoreKeep();
            hud.setLevel(hud.getLevel() + 1);
        }

        // start level 1
        if(hud.getLevel()%4 == 1 && scoreKeep%(time/25)==0){

            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),0,ID.BasicEnemy,handler));
            if(r.nextInt(2) == 0) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),Game.HEIGHT,ID.SmartEnemy,handler));
        }
        // start level 2
        if(hud.getLevel()%4 == 2 && scoreKeep%(time/50)==0){

            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),0,ID.BasicEnemy,handler));
            if(r.nextInt(2) == 0) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50),Game.HEIGHT,ID.SmartEnemy,handler));
        }


        // end level 2 start boss level
        if(hud.getLevel()%4 == 3 && scoreKeep == 0){
            handler.clearEnemies();
            handler.addObject(new Boss1(Game.WIDTH/2 - 150, -80, ID.Boss1,handler));
        }

        // health level
        if(hud.getLevel()%4 == 0 && scoreKeep%(time/4)==0){
            handler.addObject(new HealthBox(r.nextInt(Game.WIDTH- 50), r.nextInt(Game.HEIGHT - 50),ID.HealthBox,handler));
        }

        scoreKeep++;
        // end boss level
        if(hud.getLevel()%4 == 3 && scoreKeep == time){
            handler.clearEnemies();
        }
        // end health level
        if(hud.getLevel()%4 == 0 && scoreKeep == time){
            handler.clearEnemies();
        }

        // new level sound
        if(hud.getScore() == time && hud.getLevel()%4 != 3){
            AudioPlayer.playSound(AudioPlayer.getSound("newlevel"));
        }

         if(scoreKeep%(time-50) <= 50 && scoreKeep > 50){
                AudioPlayer.playSound(AudioPlayer.getSound("DingFast")); }



    }

    public void resetScoreKeep(){
        scoreKeep = 0;
    }
}
