package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    private boolean[] keyDown = new boolean[4];

    private Game game;

    public KeyInput(Handler handler,Game game){
        this.handler = handler;

        keyDown[0] = false; // up
        keyDown[1] = false; // down
        keyDown[2] = false; // left
        keyDown[3] = false; // right

        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getExtendedKeyCode(); // GET INT VALUE OF KEY PRESSED

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if(object.getId() == ID.Player) {
                int speed = 7;
                if(key == KeyEvent.VK_UP) {object.setVelY(-speed); keyDown[0] = true;}
                if(key == KeyEvent.VK_DOWN) {object.setVelY(speed); keyDown[1] = true;}
                if(key == KeyEvent.VK_LEFT) {object.setVelX(-speed); keyDown[2] = true;}
                if(key == KeyEvent.VK_RIGHT) {object.setVelX(speed); keyDown[3] = true;}
            }
        }



        if(key == KeyEvent.VK_SPACE){
            if(Game.STATE.Game == game.gameState){
                if(!Game.paused){
                    Game.paused = true;
                    MusicPlayer.stopMusic();

                } else{
                    Game.paused = false;
                    MusicPlayer.startMusic();

                }
            }

        }


        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

    }
    public void keyReleased(KeyEvent e){
        int key = e.getExtendedKeyCode();

        for(int i = 0; i < handler.objects.size(); i++){
            GameObject object = handler.objects.get(i);
            if(object.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP)  keyDown[0] = false; // object.setVelY(0);
                if(key == KeyEvent.VK_DOWN) keyDown[1] = false;//object.setVelY(0);
                if(key == KeyEvent.VK_LEFT) keyDown[2] = false; //object.setVelX(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[3] = false; //object.setVelX(0);

                if(!keyDown[0] && !keyDown[1]) object.setVelY(0);
                if(!keyDown[2] && !keyDown[3]) object.setVelX(0);

            }
        }
    }

}
