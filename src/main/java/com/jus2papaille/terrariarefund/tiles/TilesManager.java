package com.jus2papaille.terrariarefund.tiles;

import com.jus2papaille.terrariarefund.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TilesManager {

    GamePanel gp;
    public Tile[] tile;

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // Tableau pour stocker les textures des blocs
        getTileImage();
    }

    public void getTileImage() {
        try {
            // Bloc de terre
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/block/dirt_block.png"));
            tile[1].collision = true;

            // Bloc d'herbe
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/block/stone_block.png"));
            tile[2].collision = true;

            // Bloc de pierre
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/block/grass_block.png"));
            tile[3].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement des textures !");
        }
    }

    // Nouvelle méthode pour récupérer une image à partir de l'ID du bloc
    public BufferedImage getTileImage(int id) {
        if (id >= 1 && id < tile.length && tile[id] != null) {
            return tile[id].image;
        }
        return null;
    }
    public boolean hasCollision(int id) {
        if (id >= 1 && id < tile.length && tile[id] != null) {
            return tile[id].collision;
        }
        return false;
    }
}