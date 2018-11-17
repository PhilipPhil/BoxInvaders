package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Help extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    private int tittleLoc = 0;
    private int vel = 1;

    public Help(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){

        int mx = e.getX();
        int my = e.getY();


        if(game.gameState == Game.STATE.Help){
            AudioPlayer.playSound(AudioPlayer.getSound("StartGame"));
            if(handler.objects.size() <= 200){
                handler.addObject(new MenuCubes(mx,my,ID.MenuCube,handler));
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
        if(tittleLoc >= Game.WIDTH - 250) vel *=-1;
        if(tittleLoc<=0) vel *=-1;

    }

    public void render(Graphics g){
        Font fnt = new Font("arial",1,50);
        g.setFont(fnt);
        g.setColor(Color.WHITE);
        g.drawString("Help Menu", tittleLoc,75);


        Font fnt2 = new Font("arial",1,20);
        g.setFont(fnt2);
        g.setColor(Color.PINK);
        g.drawString("Dodge the blocks.", Game.WIDTH/2 - 100,175);
        g.drawString("Rainbow blocks give extra health.", Game.WIDTH/2 - 155,200);
        g.drawString("Use arrow keys to move. Space Bar to Pause.",100,225);
        g.drawString("Every 1000 points you level up",Game.WIDTH/2 - 150,250);
        g.drawString("Game will crash if clicking rapidly before pressing play",Game.WIDTH/2 - 250,Game.HEIGHT -40);


        g.setFont(new Font("arial",1,30));
        g.setColor(Color.PINK);
        g.drawRect(Game.WIDTH/2 - 100,325,200,75);
        g.drawString("Back", Game.WIDTH/2 - 40,370);

    }
}
