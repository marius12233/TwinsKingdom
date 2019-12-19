/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.tiles;

import twinkingdom.gfx.Assets;

/**
 *
 * @author Antonia
 */
public class CastleCandleTile extends Tile{
    
    public CastleCandleTile(int id) {
        super(Assets.candle,id);
    }
    
    @Override
    public boolean isSolid() {
        return false;
    }
    
}