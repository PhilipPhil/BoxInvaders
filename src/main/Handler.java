package main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {



    LinkedList<GameObject> objects = new LinkedList<>();

    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).render(g);
        }
    }

    public void addObject(GameObject object){
        objects.add(object);
    }
    public void removeObject(GameObject object){
        if(objects.contains(object)){
            objects.remove(object);
        }
    }

    public void clearEnemies(){
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i).getId() == ID.Player){
                GameObject temp = objects.get(i);
                objects.clear();
                addObject(new Player(temp.getX(),temp.getY(),ID.Player,this));
            }
        }
    }

}
