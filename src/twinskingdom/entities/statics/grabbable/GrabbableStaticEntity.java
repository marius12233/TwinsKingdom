/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics.grabbable;

import  twinskingdom.entities.Entity;
import  twinskingdom.entities.statics.StaticEntity;

/**
 *
 *  
 */
public abstract class GrabbableStaticEntity extends StaticEntity {

    public GrabbableStaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void touchEntity(Entity e) {
        if (e.isPlayer()) {
            isActive = false;
        }
    }

}
