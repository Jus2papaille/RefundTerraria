package com.jus2papaille.terrariarefund.game;

import  com.jus2papaille.terrariarefund.game.GamePanel;

import javax.swing.*;

public class Jeu {

    public void demarrer() {
        // Création de la fenêtre principale
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ferme proprement l'application à la fermeture
        window.setResizable(false); // Empêche le redimensionnement de la fenêtre
        window.setTitle("TerrariaRefund"); // Titre de la fenêtre

        // Création et ajout du GamePanel à la fenêtre
        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        // Ajuste la fenêtre en fonction de la taille du GamePanel (pour éviter des erreurs d'affichage)
        window.pack();

        // Centre la fenêtre sur l'écran
        window.setLocationRelativeTo(null);

        // Rend la fenêtre visible
        window.setVisible(true);

        // Facultatif : Démarrez le jeu (si une méthode est prévue dans GamePanel)
        gamepanel.startGame();
    }

}
