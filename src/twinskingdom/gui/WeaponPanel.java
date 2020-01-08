/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import  twinskingdom.gfx.ImageLoader;

/**
 * This class displays a panel for player weapons, that will be updated each
 * time he/she decides to change weapon.
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

    /**
     * Paints the chosen weapon on screen.
     *
     * @param g Graphics object used to draw.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (chosenWeapon == Weapons.SWORD) { // sword selected
            g.drawImage(sword, 0, 0, null);
        } else if (chosenWeapon == Weapons.BOW) {
            g.drawImage(bow, 0, 0, null);
        } else {
            g.drawImage(spell, 0, 0, null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Sets and displays a new weapon.
     *
     * @param weapon Weapon to set.
     */
    public void setWeapon(Weapons weapon) {
        this.chosenWeapon = weapon;
        repaint();
    }
}
