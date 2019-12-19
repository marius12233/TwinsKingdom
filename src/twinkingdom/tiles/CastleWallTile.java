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
public class CastleWallTile extends Tile{

    public CastleWallTile(int index, int id) {
        super(Assets.castleWall[index], id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}