package com.jus2papaille.terrariarefund.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean up, down, left, right,saut;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) up = true;
        if (key == KeyEvent.VK_S) down = true;
        if (key == KeyEvent.VK_Q) left = true;
        if (key == KeyEvent.VK_D) right = true;
        if (key == KeyEvent.VK_SPACE) saut = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) up = false;
        if (key == KeyEvent.VK_S) down = false;
        if (key == KeyEvent.VK_Q) left = false;
        if (key == KeyEvent.VK_D) right = false;
        if (key == KeyEvent.VK_SPACE) saut = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Méthodes pour récupérer l'état des touches
    public boolean isUp() { return up; }
    public boolean isDown() { return down; }
    public boolean isLeft() { return left; }
    public boolean isRight() { return right; }
    public boolean isJumpPressed() { return saut; }
}
