package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class End extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;

    private Random r = new Random();

    private int tittleLoc = -500;
    private int vel = 5;

    private Spawn spawn;


    public End(Game game, Handler handler,HUD hud, Spawn spawn){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();



        if(game.gameState == Game.STATE.End){
            // Start game
            AudioPlayer.playSound(AudioPlayer.getSound("StartGame"));

            if(handler.objects.size() <= 200){
                handler.addObject(new MenuCubes(mx,my,ID.MenuCube,handler));
            }

            if(mouseOver(mx,my,Game.WIDTH/2 - 100,125,200,75)){
                spawn.resetScoreKeep();
                MusicPlayer.startMusic();
                hud.setScore(0);
                hud.setLevel(1);
                handler.objects.clear();
                handler.addObject(new Player(Game.WIDTH/2 - 32,Game.HEIGHT/2 - 32, ID.Player, handler));
                game.gameState = Game.STATE.Game;
            }


            if(mouseOver(mx,my,Game.WIDTH/2 - 100,325,200,75)){
                game.gameState = Game.STATE.Menu;
            }


        }
        // play button





    }

    public void mouseReleased(MouseEvent e){

    }

    private boolean mouseOver(int mx, int my,int x, int y, int width, int height){
        if(mx > x && mx < x + width){
            if(my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }

    public void tick(){
        tittleLoc += vel;
        if(tittleLoc >= Game.WIDTH) tittleLoc =-500;

    }

    public void render(Graphics g){
        hud.setTopScore(hud.getScore());


        Font fnt = new Font("arial",1,50);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + hud.getScore(), 185,75);

        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("Top Score: " + hud.getTopScore(), 145,275);

        Font fnt2 = new Font("arial",1,30);
        g.setFont(fnt2);
        g.setColor(Color.PINK);
        g.drawRect(Game.WIDTH/2 - 100,125,200,75);
        g.drawString("Play Again", Game.WIDTH/2 - 75,175);




        g.setColor(Color.PINK);
        g.drawRect(Game.WIDTH/2 - 100,325,200,75);
        g.drawString("Main Menu", Game.WIDTH/2 - 75,375);

    }
}
