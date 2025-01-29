package com.jus2papaille.terrariarefund.game;

import com.jus2papaille.terrariarefund.entities.Player;
import com.jus2papaille.terrariarefund.tiles.TilesManager;
import com.jus2papaille.terrariarefund.world.WorldGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private boolean running = false;
    private final int FPS = 60; // Limite de FPS

    // Dimensions du jeu
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Joueur
    private KeyHandler keyHandler;
    private Player player;
    private WorldGenerator worldGenerator;
    private TilesManager tilesManager;
    private int blockSize = 32; // Taille des textures

    private int[][] world;


    public GamePanel() {
        keyHandler = new KeyHandler();
        worldGenerator = new WorldGenerator(50, 30);
        tilesManager = new TilesManager(this);
        this.world = worldGenerator.getWorld();

        player = new Player(keyHandler,tilesManager,world,blockSize);

        addKeyListener(keyHandler);
        setFocusable(true);
        requestFocus();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        setVisible(true);

        startGame();
    }

    // Méthode pour démarrer le jeu
    public void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            running = true;
            gameThread.start();
        }
    }

    // Boucle de jeu principale
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000.0 / FPS;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (delta >= 1) {
                updateGame();
                repaint(); // Redessine l'écran
                delta--;
            }
        }
    }

    // Mise à jour de la logique du jeu
    private void updateGame() {
        if (player != null) { // Vérifier que player est bien initialisé
            player.update();
        }
    }

    // Méthode pour dessiner les éléments du jeu
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessiner le monde
        int[][] world = this.world;
        int blockSize = 32;

        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[0].length; y++) {
                BufferedImage tileImage = tilesManager.getTileImage(world[x][y]);

                if (tileImage != null) { // On dessine uniquement les blocs valides
                    g.drawImage(tileImage, x * blockSize, y * blockSize, blockSize, blockSize, null);
                }
            }
        }

        // Dessiner le joueur
        if (player != null) { // Vérifier que player n'est pas null
            player.draw(g);
        }
    }

}
