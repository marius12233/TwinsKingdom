/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.game;

import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for collecting every layer of each map, in order to render
 * each of them and control collisions.
 *
 */
public class RenderableLayers {

    /**
     * List of all the layers of a map.
     */
    private List<Layer> layers;
    private String base_path;
    private int width, height;
    private GameHandler handler;

    /**
     * @param base_path Map base directory path.
     */
    public RenderableLayers(int width, int height, String base_path) {
        this.layers = new ArrayList<>();
        this.height = height;
        this.width = width;
        this.handler = GameHandler.instance;
        this.base_path = base_path;
    }

    /**
     * This method loads all the layers of a map, located inside its base
     * directory.
     */
    public void loadLayers() {
        layers.removeAll(layers);
        int files = new File(base_path).list().length;
        for (int i = 2; i < files + 1; i++) {
            String file_path = base_path + "layer" + i + ".txt";
            Layer layer = new Layer(width, height, file_path);
            layers.add(layer);
        }
    }

    /**
     * This method displays on screen all the layers of the collection.
     *
     * @param g Graphics object used for rendering.
     */
    public void renderLayers(Graphics g) {
        for (int i = 0; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.render(g, this.handler);
        }
    }

    /**
     * This method displays on screen one layer of the collection.
     *
     * @param id Layer identifier.
     * @param g Graphics object used for rendering.
     */
    public void renderLayer(int id, Graphics g) {
        Layer layer = layers.get(id);
        layer.render(g, this.handler);
    }

    /**
     * @param index Layer index to search in the collection.
     * @return If it is present, this method returns the layer.
     * @throws IndexOutOfBoundsException - if the index is out of range
     */
    public Layer getLayer(int index) {
        return layers.get(index);
    }

    /**
     * @return The number of layers in the collection.
     */
    public int countLayers() {
        return layers.size();
    }
}
