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
public class Throne2Tile extends Tile {

    public Throne2Tile(int id) {
        super(Assets.throne[1], id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
