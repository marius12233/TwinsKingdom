/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bened
 */
public class LevelUtilities {

    public static Map<Integer, String> levels; // id -> name
    public static Integer currentLevel;

    static {
        LevelUtilities.levels = new HashMap<>();
        LevelUtilities.levels.put(1, "Level 1");
        LevelUtilities.levels.put(2, "Boss 1");
        LevelUtilities.currentLevel = 1;
    }
}
