package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH*9/12;

    private Thread thread;
    private boolean running = false;
    private  Handler handler;
    private HUD hud;
    private Spawn spawner;

    private Random r = new Random();

    private Menu menu;
    private Help help;
    private End end;

    public static boolean paused = false;





    public enum STATE {
        Menu,
        Help,
        End,
        Game
    }


    public STATE gameState = STATE.Menu;

    public static void main(String arg[]){


        new Game();

    }


    public Game(){
        handler = new Handler();
        hud = new HUD(this,handler);
        spawner = new Spawn(handler,hud);
        menu = new Menu(this, handler,hud,spawner);
        help = new Help(this,handler);
        end = new End(this,handler, hud,spawner);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);
        this.addMouseListener(help);
        this.addMouseListener(end);
        new Window(WIDTH, HEIGHT,"BOX-INVADERS", this);


        AudioPlayer.load();
        MusicPlayer.load();




    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // game loop copied from the internet
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = Integer.MAX_VALUE / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += now/ns - lastTime/ns;
            lastTime = now;
            while (delta >=1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS:" + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        } else if(gameState== STATE.Menu){
            menu.render(g);
        } else if(gameState == STATE.Help){
            help.render(g);
        } else if(gameState == STATE.End){
            end.render(g);
        }

        if(paused){
            Font fnt2 = new Font("arial",1,75);
            g.setFont(fnt2);
            g.drawString("PAUSE",215,222);
        }



        g.dispose();
        bs.show();

    }

    private void tick() {
        if(!paused){
            handler.tick();
            if(gameState == STATE.Game){
                hud.tick();
                spawner.tick();
            } else if(gameState == STATE.Menu){
                menu.tick();
            } else if(gameState == STATE.Help){
                help.tick();
            } else if(gameState == STATE.End){
                end.tick();
            }
        }


    }


    public static int clamp(int var, int min, int max){
        if(var>=max){
            return max;
        } else if(var <= min) {
            return min;
        } else {
            return var;
        }
    }
}
