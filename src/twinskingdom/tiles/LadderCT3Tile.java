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
public class LadderCT3Tile extends Tile {

    public LadderCT3Tile(int id) {
        super(Assets.carpet[10], id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
