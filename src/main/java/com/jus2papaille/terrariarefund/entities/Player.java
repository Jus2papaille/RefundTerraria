package com.jus2papaille.terrariarefund.entities;

import com.jus2papaille.terrariarefund.game.KeyHandler;
import com.jus2papaille.terrariarefund.tiles.TilesManager;

import java.awt.*;

public class Player extends Entity {

    private KeyHandler keyHandler;
    private TilesManager tilesManager;
    private int[][] map; // La carte du jeu contenant les IDs des tuiles
    private final int TILE_SIZE; // Taille des tuiles

    // Variables de physique
    private float gravity = 0.5f;       // Force de gravité : augmentation progressive de la vitesse verticale
    private float velocityY = 0;       // Vitesse verticale du joueur
    private float maxFallSpeed = 10f;  // Vitesse maximale de chute
    private boolean isOnGround = false; // Indique si le joueur est sur le sol


    public Player(KeyHandler keyHandler, TilesManager tilesManager, int[][] map, int tileSize) {
        super(100, 100, 20, 4); // Initialisation du joueur
        this.keyHandler = keyHandler;
        this.tilesManager = tilesManager;
        this.map = map;
        this.TILE_SIZE = tileSize; // Taille d'une tuile (ex. 32x32 pixels)
    }

    @Override
    public void update() {
        // Variables pour les futures positions X et Y
        int newX = x;
        int newY = (int) (y + velocityY);

        // Gérer les déplacements latéraux (gauche/droite)
        if (keyHandler.isLeft()) {
            newX -= speed; // Déplacement vers la gauche
        }
        if (keyHandler.isRight()) {
            newX += speed; // Déplacement vers la droite
        }

        // Appliquer la gravité uniquement si le joueur n'est pas sur le sol
        if (!isOnGround) {
            velocityY += gravity;
            if (velocityY > maxFallSpeed) {
                velocityY = maxFallSpeed; // Limite la vitesse de chute
            }
        }

        // Vérifier les collisions verticales (gravité + sol)
        if (checkVerticalCollision(x, newY)) {
            isOnGround = true;
            velocityY = 0; // Arrête la chute
            // Aligner précisément le joueur sur le sol
            y = ((newY + size - 1) / TILE_SIZE) * TILE_SIZE - size;
        } else {
            isOnGround = false;
            y = newY; // Met à jour la position Y
        }

        // Vérifier les collisions horizontales (marcher)
        if (!checkHorizontalCollision(newX, y)) {
            x = newX; // Déplacement horizontal seulement si pas d'obstacle
        }

        // Gestion du saut (déclenché par une touche)
        if (keyHandler.isJumpPressed() && isOnGround) {
            velocityY = -10f;  // Impulsion verticale pour le saut
            isOnGround = false; // Le joueur n'est plus au sol
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        super.draw(g);
    }

    private boolean isTileColliding(int tileX, int tileY) {
        if (tileX < 0 || tileY < 0 || tileX >= map.length || tileY >= map[0].length) {
            return true; // Les bords de la carte comptent comme des collisions
        }

        int tileId = map[tileX][tileY];
        boolean collides = tilesManager.hasCollision(tileId);

        return collides;
    }

    private boolean checkHorizontalCollision(int futureX, int currentY) {
        // Vérifie uniquement les côtés gauche/droit (en haut et en bas du joueur)
        int topTile = currentY / TILE_SIZE; // Tuile correspondant au coin supérieur
        int bottomTile = (currentY + size - 1) / TILE_SIZE; // Tuile correspondant au coin inférieur

        int leftTile = futureX / TILE_SIZE; // Tuile du côté gauche
        int rightTile = (futureX + size - 1) / TILE_SIZE; // Tuile du côté droit

        boolean leftCollision = isTileColliding(leftTile, topTile) || isTileColliding(leftTile, bottomTile);
        boolean rightCollision = isTileColliding(rightTile, topTile) || isTileColliding(rightTile, bottomTile);

        // Retourne `true` si une collision est détectée à gauche ou à droite
        return leftCollision || rightCollision;
    }

    private boolean checkVerticalCollision(int currentX, int futureY) {
        // Vérifie uniquement les tuiles sous les pieds du joueur
        int leftTile = currentX / TILE_SIZE; // Tuile gauche sous le joueur
        int rightTile = (currentX + size - 1) / TILE_SIZE; // Tuile droite sous le joueur
        int bottomTile = (futureY + size - 1) / TILE_SIZE; // Tuile au bas du joueur

        boolean bottomCollision = isTileColliding(leftTile, bottomTile) || isTileColliding(rightTile, bottomTile);
        return bottomCollision;
    }

}