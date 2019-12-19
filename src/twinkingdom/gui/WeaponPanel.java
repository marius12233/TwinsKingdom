/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import twinkingdom.gfx.ImageLoader;

/**
 *
 * @author bened
 */
public class WeaponPanel extends ObservingPanel {

    private BufferedImage bow, sword, spell;
    public Weapons chosenWeapon;

    public WeaponPanel() throws IOException {
        //super();
        bow = ImageLoader.loadImage("/gui/weapon_bow.png");
        sword = ImageLoader.loadImage("/gui/weapon_sword.png");
        spell = ImageLoader.loadImage("/gui/weapon_spell.png");
        chosenWeapon = Weapons.SWORD;
        this.setLayout(null);
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (chosenWeapon == Weapons.SWORD) { // sword selected
            g.drawImage(sword, 0, 0, null);
        } else if(chosenWeapon == Weapons.BOW) {
            g.drawImage(bow, 0, 0, null);
        } else {
            g.drawImage(spell, 0, 0, null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setWeapon(Weapons weapon) {
        this.chosenWeapon = weapon;
        repaint();
    }
}
