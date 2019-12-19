/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.tiles;

import twinkingdom.gfx.Assets;

/**
 *
 * @author Clover
 */
public class InvisibleTile extends Tile {
    
    public InvisibleTile(int id) {
        super(Assets.invisible, id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
    
}
