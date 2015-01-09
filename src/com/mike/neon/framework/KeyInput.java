/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.framework;

import com.mike.neon.objects.Bullet;
import com.mike.neon.window.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Mike
 */
public class KeyInput extends KeyAdapter{
    
    Handler handler;
    
    private boolean keyDown[] = {false, false, false, false};
    
    public KeyInput(Handler handler){
        this.handler = handler;
        
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i =0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_RIGHT){
                    tempObject.setVelX(5);
                    tempObject.setFacing(1);
                    keyDown[0] = true;
                }else if(key == KeyEvent.VK_LEFT){
                    tempObject.setVelX(-5);
                    tempObject.setFacing(-1);
                    keyDown[1] = true;
                } 
                if(key == KeyEvent.VK_UP && !tempObject.isJumping()){
                    tempObject.setVelY(-10);
                    tempObject.setJumping(true);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_SPACE){
                    //.addBullet();
                }
            }
        }
        
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for (int i =0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_RIGHT) keyDown[0] = false;
                if(key == KeyEvent.VK_LEFT) keyDown[1] = false;
                if(key == KeyEvent.VK_W) keyDown[2] = false;
                //if(key == KeyEvent.VK_S) keyDown[3] = false;
            }
            if(!keyDown[0] && !keyDown[1]) tempObject.setVelX(0);
            if(!keyDown[2] && !keyDown[3]) tempObject.setVelY(0);
        }
    }
}
