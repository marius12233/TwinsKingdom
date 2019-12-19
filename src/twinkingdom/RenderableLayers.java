/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Clover
 */
public class RenderableLayers {
    
    private List<Layer> layers;
    private String base_path;
    private int width, height;
    private GameHandler handler;
    
    public RenderableLayers(int width, int height,String base_path) {
        this.layers = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.handler = GameHandler.instance;
        this.base_path = base_path;
    }
    
    public void loadLayers() {
        layers.removeAll(layers);
        System.out.println(base_path);
        int files = new File(base_path).list().length;
        for(int i = 2; i < files + 1; i++) {
            String file_path = base_path + "layer" + i + ".txt";
            Layer layer = new Layer(width, height, file_path);
            layers.add(layer);
        }
    }
    
    public void renderLayers(Graphics g) {
        for(int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.render(g, this.handler);
        }
    }
    
    public void renderLayer(int id, Graphics g) {
        Layer layer = layers.get(id);
        layer.render(g, this.handler);
    }
    
    public void printAll() {
        for(int i = 0; i<layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.printLayer();
        }
    }
    
    public Layer getLayer(int index) {
        return layers.get(index);
    }
    
    public int countLayers() {
        return layers.size();
    }
}