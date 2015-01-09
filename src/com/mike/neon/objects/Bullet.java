/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.objects;

import com.mike.neon.framework.GameObject;
import com.mike.neon.framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Bullet extends GameObject{
    private float width = 8, height = 8;
    private final float MAX_SPEED = 20;

    public Bullet(float x, float y, ObjectId id, int velX) {
        super(x, y, id);
        this.velX = velX;
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
    }

    
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int) x, (int)y, (int) width, (int)height);
    }

    
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int)y, (int) width, (int)height);
    }
    
}
