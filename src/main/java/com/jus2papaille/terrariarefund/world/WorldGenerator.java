package com.jus2papaille.terrariarefund.world;

import java.util.Random;

public class WorldGenerator {
    private int width, height;
    private int[][] world; // 0 = air, 1 = terre, 2 = pierre, 3 = herbe
    private Random random;

    public WorldGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.world = new int[width][height];
        this.random = new Random();

        generateWorld();
    }

    private void generateWorld() {
        int groundLevel = height / 2; // Niveau du sol
        int[] terrainHeights = new int[width];

        // Génération des hauteurs de base avec un lissage
        terrainHeights[0] = groundLevel + random.nextInt(5) - 2;  // Hauteur de la première colonne
        for (int x = 1; x < width; x++) {
            // Lissage des hauteurs en fonction des voisins
            int heightVariation = random.nextInt(3) - 1;  // Variation plus douce
            terrainHeights[x] = terrainHeights[x - 1] + heightVariation;
        }

        // Création du terrain avec des hauteurs lissées
        for (int x = 0; x < width; x++) {
            int finalHeight = terrainHeights[x];

            for (int y = 0; y < height; y++) {
                if (y < finalHeight) {
                    world[x][y] = 0; // Air
                } else if (y == finalHeight) {
                    world[x][y] = 3; // Herbe sur le dessus
                } else if (y < finalHeight + 3) {
                    world[x][y] = 1; // Terre sous la surface
                } else {
                    world[x][y] = 2; // Pierre plus en profondeur
                }
            }
        }
    }

    public int[][] getWorld() {
        return world;
    }
}
