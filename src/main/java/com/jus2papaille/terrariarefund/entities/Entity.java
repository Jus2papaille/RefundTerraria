package com.jus2papaille.terrariarefund.entities;

import java.awt.*;

public abstract class Entity {
    protected int x, y, size, speed; // Attributs communs aux entités

    public Entity(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public abstract void update(); // Méthode commune mais spécifique à chaque sous-classe

    public void draw(Graphics g) {
        g.setColor(Color.RED); // Couleur par défaut (peut être redéfini)
        g.fillRect(x, y, size, size);
    }
}