/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.tiles;

import twinkingdom.gfx.Assets;

/**
 *
 * @author Angelica
 */
public class Window2Tile extends Tile{
    
    public Window2Tile(int id) {
        super(Assets.throneRoom[5],id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
    
}