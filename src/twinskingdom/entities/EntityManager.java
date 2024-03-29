/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities;

import  twinskingdom.entities.mobs.player.Player;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import  twinskingdom.game.GameHandler;

/**
 *
 * This class manages entities. It allows you to add new entities to the game, 
 * render them and delete them when you set the isActive attribute of each entity to false.
 */
public final class EntityManager {

    private GameHandler handler;
    private Player player;
    private List<Entity> entities;
    private final Comparator<Entity> renderSorter;

    public EntityManager(GameHandler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new LinkedList();
        this.addEntity(player);
        renderSorter = (Entity a, Entity b) -> {
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
                return -1;
            }
            return 1;
        };
    }

    public void tick() {

        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.isEmpty()) {
                break;
            }
            try {
                Entity e = entities.get(i);

                e.tick();
                if (!e.isActive()) {
                    entities.remove(i);
                }

            } catch (Exception ex) {
                continue;
            }

            entities.sort(renderSorter);
        }
    }

    public void render(Graphics g) {
        entities.forEach((e) -> {
            e.render(g);
        });
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public GameHandler getHandler() {
        return handler;
    }

    public void setHandler(GameHandler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        entities.remove(this.player);
        this.player = player;
        addEntity(this.player);
    }

    public Collection<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Collection<Entity> entities) {
        this.entities.addAll(entities);
    }

    public void removeAllEntities() {
        this.entities.clear();
    }

}
