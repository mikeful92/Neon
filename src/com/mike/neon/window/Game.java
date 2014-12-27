/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.window;

import com.mike.neon.framework.KeyInput;
import com.mike.neon.framework.ObjectId;
import com.mike.neon.objects.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class Game extends Canvas implements Runnable{
    
    //Serial Control
    private static final long serialVersionUID = 1L;
    
    //
    private boolean running = false;
    private Thread thread;
    public static int WIDTH, HEIGHT;
   
    //Object
    Handler handler;
    
    
    //Camera
    Camera cam;
    Random r = new Random();
    
    public void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        handler = new Handler();
        cam = new Camera(0,0);
        
        handler.addObject(new Player(100,100, ObjectId.Player, handler));
        
        handler.createLevel();
        
        this.addKeyListener(new KeyInput(handler));
        
    }
    
    public synchronized void start(){
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
        
    } 
    
    
    public void run(){
        this.requestFocus();
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    private synchronized void stop(){
        if(!running)
            return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(1);
        
    }
    
    private void tick(){
        handler.tick();
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ObjectId.Player){
                cam.tick(handler.object.get(i));
            }
            
        }
        
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        
        //Draw here
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        
        g2d.translate(cam.getX(), cam.getY()); //begin of cam
        
        handler.render(g);
        
        g2d.translate(-cam.getX(), -cam.getY()); //end of cam
        //
        g.dispose();
        bs.show();
    }
    
    public static void main(String args[]){
        
        new Window(800, 600, "Neon PLatform Game Prototype", new Game());
        
    }
    
}
