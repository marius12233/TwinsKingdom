/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 *
 * @author mario
 */
public abstract class EntityAssets {
    
    public abstract Map<String, Map<String, BufferedImage[]>> getAnimations();
    public abstract void init();
}


