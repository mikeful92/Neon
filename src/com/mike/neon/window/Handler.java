/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.window;

import com.mike.neon.framework.GameObject;
import com.mike.neon.framework.ObjectId;
import com.mike.neon.objects.Block;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Handler {
    
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    public void tick(){
        for (GameObject tempObject : object) {
            tempObject.tick(object);
        }
        
    }
    
    public void render(Graphics g){
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
        
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
    public void createLevel(){
        for(int xx = 0; xx < Game.WIDTH + 32; xx += 32){
            addObject(new Block(xx, Game.HEIGHT -32, ObjectId.Block));
        }
        for(int xx = 0; xx < Game.HEIGHT-64; xx += 32){
            addObject(new Block(0, xx, ObjectId.Block));
            addObject(new Block(Game.WIDTH - 33, xx, ObjectId.Block));
        }
        for(int xx = 192; xx < 608; xx += 32){
            addObject(new Block(xx, Game.HEIGHT*2/3, ObjectId.Block));
        }
            
    }
}
