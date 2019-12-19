/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.statics;

import twinkingdom.entities.Entity;
import twinkingdom.gfx.Animation;

/**
 *
 * @author mario
 */
public abstract class StaticEntity extends Entity{
    protected Animation animation;
    public StaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
        isStatic=true;
    }
    
    @Override
    public void hurt(int amt){
        return;
    }
    
    
}
