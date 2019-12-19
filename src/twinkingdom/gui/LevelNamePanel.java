/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author bened
 */
public class LevelNamePanel extends ObservingPanel {
    public LevelNamePanel() {
        super();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawString("Level", 85, 20);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
