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
 *
 * @author bened
 */
public class SidebarPanel extends JPanel {

    private WeaponPanel weaponPanel;

    public SidebarPanel() throws IOException {
        this.setOpaque(false);
        createWeaponPanel();
        this.setLayout(null);

        this.add(weaponPanel);
        weaponPanel.setBounds(0, 120, 145, 197);
        weaponPanel.repaint();
    }

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
