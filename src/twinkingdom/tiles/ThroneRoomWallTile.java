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
public class ThroneRoomWallTile extends Tile{
    
    public ThroneRoomWallTile(int id) {
        super(Assets.throneRoom[1],id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
    
}