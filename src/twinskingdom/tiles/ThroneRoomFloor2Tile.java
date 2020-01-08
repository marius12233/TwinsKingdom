/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.tiles;

import  twinskingdom.gfx.Assets;

/**
 *
 *  
 */
public class ThroneRoomFloor2Tile extends Tile {

    public ThroneRoomFloor2Tile(int id) {
        super(Assets.carpet[0], id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
