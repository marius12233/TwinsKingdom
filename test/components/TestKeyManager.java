/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import twinkingdom.input.KeyManager;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author aless
 */
public class TestKeyManager implements KeyListener{
    
    private static KeyManager keyManager;
    private static String s = "Frame";
    private JFrame frame;
    
    JLabel label;
    
    public TestKeyManager() throws AWTException {
        frame = new JFrame(s);
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        frame.add(p);
        frame.addKeyListener(this);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}   
    @Test
    public static void main(String[] args) throws AWTException {
        System.out.println("-----------test KeyManager Class------------");
        System.out.println("");
        
        System.out.println("Key value for w/up: " + KeyEvent.getExtendedKeyCodeForChar('W') + " W or up arrow");
        System.out.println("Key value for s/down: " + KeyEvent.getExtendedKeyCodeForChar('S') + " S or down arrow");
        System.out.println("Key value for a/left: " + KeyEvent.getExtendedKeyCodeForChar('A') + " A or left arrow");
        System.out.println("Key value for d/right: " + KeyEvent.getExtendedKeyCodeForChar('D') + " D or right arrow");
        System.out.println("Key value for spacebar/attack: " + 32 + " SPACE BAR");
        System.out.println("Key value for 1/swordSelected: " + KeyEvent.getExtendedKeyCodeForChar('1') + " 1");
        System.out.println("Key value for 2/bowSelected: " + KeyEvent.getExtendedKeyCodeForChar('2') + " 2");
        System.out.println("Key value for 3/spellSelected: " + KeyEvent.getExtendedKeyCodeForChar('3') + " 3");
        System.out.println("Key value for p/pause: " + KeyEvent.getExtendedKeyCodeForChar('P') + " P");
        
        System.out.println("");
        System.out.println("");
        
        TestKeyManager test = new TestKeyManager();
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('W')) || ((e.getKeyCode() == 38))) {
            System.out.println("Key value to go UP");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('S')) || ((e.getKeyCode() == 40))) {
            System.out.println("Key value to go DOWN");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('A')) || ((e.getKeyCode() == 37))){
            System.out.println("Key value to go LEFT");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('D')) || ((e.getKeyCode() == 39))){
            System.out.println("Key value to go RIGHT");
        }
        if (e.getKeyCode() == 32) {
            System.out.println("Key value to ATTACK");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('1')) {
            System.out.println("Key value to select the SWORD");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('2')) {
            System.out.println("Key value to select the BOW");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('3')) {
            System.out.println("Key value to select the SPELL");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('P')) {
            System.out.println("Key value to PAUSE");
        }
        if (e.getKeyCode() == 27) {
            System.out.println("Exit TEST");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('W')) || ((e.getKeyCode() == 38))) {
            System.out.println("Key value to go UP");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('S')) || ((e.getKeyCode() == 40))) {
            System.out.println("Key value to go DOWN");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('A')) || ((e.getKeyCode() == 37))){
            System.out.println("Key value to go LEFT");
        }
        if ((e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('D')) || ((e.getKeyCode() == 39))){
            System.out.println("Key value to go RIGHT");
        }
        if (e.getKeyCode() == 32) {
            System.out.println("Key value to ATTACK");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('1')) {
            System.out.println("Key value to select the SWORD");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('2')) {
            System.out.println("Key value to select the BOW");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('3')) {
            System.out.println("Key value to select the SPELL");
        }
        if (e.getKeyCode() == KeyEvent.getExtendedKeyCodeForChar('P')) {
            System.out.println("Key value to PAUSE");
        }
        if (e.getKeyCode() == 27) {
            System.out.println("Exit TEST");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
