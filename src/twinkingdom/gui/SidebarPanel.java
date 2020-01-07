/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JPanel;

/**
 * This class represents a side panel, where the WeaponPanel and the LevelNamePanel are located.
 */
public class SidebarPanel extends JPanel {

    private WeaponPanel weaponPanel;

    /**
     * Sets all the configurations for the SidebarPanel and the relative WeaponPanel.
     * @throws IOException 
     */
    public SidebarPanel() throws IOException {
        this.setOpaque(false);
        createWeaponPanel();
        this.setLayout(null);

        this.add(weaponPanel);
        weaponPanel.setBounds(0, 120, 145, 197);
        weaponPanel.repaint();
    }

    /**
     * Creates a new WeaponPanel for this SidebarPanel.
     * @throws IOException 
     */
    public void createWeaponPanel() throws IOException {
        this.weaponPanel = new WeaponPanel();
    }

    public WeaponPanel getWeaponPanel() {
        return weaponPanel;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //weaponPanel.repaint();
        //g.drawRect(0, 0, 100, 100);
    }

}
