/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.statics.grabbable;

import twinkingdom.entities.statics.StaticEntity;

/**
 *
 * @author SSQ1
 */
public abstract class GrabbableStaticEntity extends StaticEntity{

    public GrabbableStaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
        isGrabbable=true;
    }
    
    

    
    
}
