/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.tiles;

import twinkingdom.gfx.Assets;

/**
 *
 * @author Amedeo
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