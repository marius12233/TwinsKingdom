/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author bened
 */
public class GameScenePanel extends JPanel {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Border gameSceneBorder = BorderFactory.createLineBorder(new Color(232, 142, 0));
        this.setBorder(gameSceneBorder);
    }

}
