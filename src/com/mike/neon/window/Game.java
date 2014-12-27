/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.window;

import com.mike.neon.framework.KeyInput;
import com.mike.neon.framework.ObjectId;
import com.mike.neon.framework.Texture;
import com.mike.neon.objects.Block;
import com.mike.neon.objects.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Mike
 */
public class Game extends Canvas implements Runnable{
    
    //Serial Control
    private static final long serialVersionUID = 1L;

    public static Texture getInstance() {
        return tex;
    }
    
    //
    private boolean running = false;
    private Thread thread;
    public static int WIDTH, HEIGHT;
   
    //Level load
    private BufferedImage level = null;
    
    //Object
    Handler handler;
    
    //Texture
    static Texture tex;
    
    //Camera
    Camera cam;
    Random r = new Random();
    
    public void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        tex = new Texture();
        
        //load level
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("src/res/level.png");
        
        handler = new Handler();
        cam = new Camera(0,0);
        
        loadImageLevel(level);
        
        //handler.addObject(new Player(100,100, ObjectId.Player, handler));
        
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
    
    
    private void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        
        System.out.println("width " + w + ", height " + h);
        
        for(int xx = 0; xx < h; xx++){
            for(int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                if(red == 255 && green == 255 & blue == 255) handler.addObject(new Block(xx*32, yy*32, ObjectId.Block, 0));
                if(red == 0 && green == 0 & blue == 255) handler.addObject(new Player(xx*32, yy*32, ObjectId.Player, handler));
            }
        }
    }
    
    
    public static void main(String args[]){
        
        new Window(800, 600, "Neon PLatform Game Prototype", new Game());
        
    }
    
}
