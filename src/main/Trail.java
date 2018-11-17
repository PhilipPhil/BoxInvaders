package main;

import java.awt.*;

public class Trail extends GameObject {
    /// trail doesnt work because it keeps adding a new item to the LinkedList while itterating over all the elements
    // to get it to work you need to change the for loops to not itterate over each element but to go from i=0 to length of list
    // fucking stupid

    private float life;
    private float alpha = 1;
    private Color color;
    private Handler handler;
    int WIDTH, HEIGHT;

    public Trail(int x, int y, ID id, Handler handler, float life, Color color, int width, int height) {
        super(x, y, id);
        this.handler = handler;
        this.life = life;
        this.color = color;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    public void tick() {
        if(alpha > life){
            alpha -= life;
        } else {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g2d.setComposite(makeTransparent(1));

    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }



    public Rectangle getBounds() {
        return null;
    }
}
