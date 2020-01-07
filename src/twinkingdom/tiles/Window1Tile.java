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
public class Window1Tile extends Tile{
    
    public Window1Tile(int id) {
        super(Assets.throneRoom[4],id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
    
}