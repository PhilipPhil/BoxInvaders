package main;

import java.awt.*;

public class HUD {

    public static int HEALTH = 100;
    private int colorValue = 255;

    private int score = 0;
    private int level = 1;

    private Game game;
    private Handler handler;


    private int topScore = 0;

    public HUD(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;

    }

    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        colorValue = Game.clamp(colorValue,0,255);
        colorValue = HEALTH*2;

        score++;

        if(HEALTH <= 0){
            MusicPlayer.stopMusic();
            AudioPlayer.playSound(AudioPlayer.getSound("dead"));
            handler.objects.clear();
            HEALTH = 100;
            game.gameState = Game.STATE.End;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.DARK_GRAY);
        g.fillRect(15,15, 200,32);
        g.setColor(new Color(75, colorValue, 0));
        g.fillRect(15,15, HEALTH*2,32);
        g.setColor(Color.white);
        g.drawRect(15,15, 200,32);

        Font fnt2 = new Font("arial",1,20);
        g.setFont(fnt2);
        g.drawString("Score: " + score, 12, 66);
        g.drawString("Level: " + level, 12, 86);
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public void setTopScore(int currScore){
        if(currScore > topScore){
            topScore = currScore;
        }
    }

    public int getTopScore(){
        return topScore;
    }
}
