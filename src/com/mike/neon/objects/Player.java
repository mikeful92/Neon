/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.objects;

import com.mike.neon.framework.GameObject;
import com.mike.neon.framework.ObjectId;
import com.mike.neon.framework.Texture;
import com.mike.neon.window.Animation;
import com.mike.neon.window.Game;
import com.mike.neon.window.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public class Player extends GameObject {
    
    private float width = 48, height = 96;
    private float gravity = 0.3f;
    private final float MAX_SPEED = 10;
    
    private Handler handler;
    
    Texture tex = Game.getInstance();
    
    private Animation playerWalk;

    public Player(float x, float y, ObjectId id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        
        playerWalk = new Animation(5, tex.player[1], tex.player[3]);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;
        
        if(falling || jumping){
            velY += gravity;
            if(velY > MAX_SPEED)
                velY = MAX_SPEED;
        }
        
        collision(object);
        
        playerWalk.runAnimation();
    }

    
    private void collision(LinkedList<GameObject> object){
        for(GameObject tempObject: object){
            if(tempObject.getId() == ObjectId.Block){
                //Bottom
                if(getBoundsBottom().intersects(tempObject.getBounds())){
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else
                    falling = true;
                //Top
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY() + 33;
                    velY = 0;
                }
                //Left & Right
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    x = tempObject.getX() + 32;
                }else if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                }
            }
        }
    }
    
    public void render(Graphics g) {
        //g.setColor(Color.blue);
        //g.fillRect((int)x, (int)y,(int) width,(int) height);
        if(velX != 0){
            playerWalk.drawAnimation(g, (int) x, (int)y, 48, 96);
        }
        else
            g.drawImage(tex.player[0],(int) x,(int) y, 48, 96, null);
        
        
        /* Hitboxes
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
        g2d.draw(getBoundsBottom());
        */
    }
    
    public Rectangle getBounds(){
        return null;
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+(width-5)), (int)y+2,(int) 5,(int) height-4);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y+2,(int) 5,(int) height-4);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x+(width/4)), (int)y,(int) width/2,(int) height/2);
    }
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)(x+(width/4)), (int)(y+(height/2)),(int) width/2,(int) height/2);
    }
    
}
