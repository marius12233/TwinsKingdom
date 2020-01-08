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
public class FlowersTile extends Tile {

    public FlowersTile(int id) {
        super(Assets.flowers, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
