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
    
}
