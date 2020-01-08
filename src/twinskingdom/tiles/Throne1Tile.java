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
public class Throne1Tile extends Tile {

    public Throne1Tile(int id) {
        super(Assets.throne[0], id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
