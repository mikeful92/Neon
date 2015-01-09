/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Mike
 */
public abstract class GameObject {
    
    protected float x,y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = false;
    protected boolean jumping = false;
    protected int facing = 1;
    
    public GameObject(float x, float y, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
        
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public float getX(){
        return x;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public float getY(){
        return y;
    }
    public void setY(float y){
        this.y = y;
    }
    
    public float getVelX(){
        return velX;
    }
    
    public void setVelX(float velX){
        this.velX = velX;
    }
    
    public float getVelY(){
        return velY;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    
    
    
    public ObjectId getId(){
        return id;
    }

    /**
     * @return the falling
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * @param falling the falling to set
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    /**
     * @return the jumping
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * @param jumping the jumping to set
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    
    /**
     * @return the facing
     */
    public int getFacing() {
        return facing;
    }

    /**
     * @param facing the facing to set
     */
    public void setFacing(int facing) {
        this.facing = facing;
    }
    
}
