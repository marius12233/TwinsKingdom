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
public class Carpet11Tile extends Tile{
    
    public Carpet11Tile(int id) {
        super(Assets.carpet[3],id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
    
}