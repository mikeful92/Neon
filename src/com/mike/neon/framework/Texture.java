/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mike.neon.framework;

import com.mike.neon.window.BufferedImageLoader;
import java.awt.image.BufferedImage;

/**
 *
 * @author Mike
 */
public class Texture {
    
    SpriteSheet bs, ps;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    
    public BufferedImage[] block = new BufferedImage[1];
    
    public Texture(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            block_sheet = loader.loadImage("src/res/sprite_sheet.png");
            //player_sheet = loader.loadImage("src/res/player_sheet.png");
        } catch(Exception e){
            e.printStackTrace();
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        
        getTextures();
    }
    
    private void getTextures(){
        block[0] = bs.grabImage(1, 1, 32, 32); //first block
        //any other blocks added below
    }
    
}
