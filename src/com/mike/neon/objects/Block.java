/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.objects;

import com.mike.neon.framework.GameObject;
import com.mike.neon.framework.ObjectId;
import com.mike.neon.framework.Texture;
import com.mike.neon.window.Game;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Block extends GameObject{
    
    Texture tex = Game.getInstance();
    private int type;

    public Block(float x, float y, ObjectId id, int type) {
        super(x, y, id);
        this.type = type;
    }

    public void tick(LinkedList<GameObject> object) {
    }

    public void render(Graphics g) {
        if(type == 0)//dirty block
            g.drawImage(tex.block[0], (int)x,(int) y, null);
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, 32, 32);
    }
}
