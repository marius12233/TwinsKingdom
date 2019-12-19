/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.util.Observer;
import javax.swing.JPanel;

/**
 * This class extends javax.swing.JPanel in order to perform basic render
 * operations.
 * <br>
 * java.util.Observer interface is meant to be implemented by child classes in
 * order to have a general mechanism to detect changes in some values (e.g.
 * life, weapon, items) and properly update their graphic representation.
 *
 * @author Ginestra Benedetto Salvatore
 */
public abstract class ObservingPanel extends JPanel implements Observer {

}
