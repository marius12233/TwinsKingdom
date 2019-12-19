/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 *
 * @author bened
 */
public class ManaBar extends ObservingPanel {
    
    private BufferedImage manaTrapeze;
    /**
     * These fields.
     */
    public int value, max;
    private boolean isManaEnabled;
    private final int barWidth, barHeight;
    private final Color barColor, barBorderColor;
    
    public ManaBar() throws IOException {
        /**
         * This field.
         */
        this.barWidth = 315;
        this.barHeight = 23;
        this.max = 120;
        this.value = 60;
        this.barColor = new Color(22,117,153);
        this.barBorderColor = new Color(232,142,0);
        this.isManaEnabled = false;
        
        manaTrapeze = ImageIO.read(this.getClass().getResource("/gui/healthbg.png"));
       
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Drawing bar filling according to actual left mana 
        g.setColor(this.barColor);
        int health = (int)Math.floor((double)this.barWidth/(double)max * (double)value);
        g.fillRect(10,18,health,23);
        
        // Drawing bar contours... Just graphics
        g.setColor(this.barBorderColor);
        g.drawRect(10, 18, this.barWidth, this.barHeight);
        
        // Drawing text information of the remaining mana
        g.drawImage(manaTrapeze, 10,41, null);
        g.setColor(Color.white);
        String remainingLife = String.format("%1$"+10+ "s", this.value + "/" + this.max + " MP");
        g.drawString(remainingLife, 21, 53);
        

        
    }

    @Override
    public void update(Observable o, Object arg) {
        Mana m = (Mana) arg;
        this.value = m.getMana();
        this.max = m.getMaxMana();
        this.isManaEnabled = m.isEnabled();
        this.repaint();
    }

   
    
}
