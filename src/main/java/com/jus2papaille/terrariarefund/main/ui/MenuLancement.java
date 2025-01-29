package com.jus2papaille.terrariarefund.main.ui;

import com.jus2papaille.terrariarefund.game.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLancement extends JFrame implements ActionListener {

    private JButton btnJouer;
    private JButton btnParametres;
    private JButton btnQuitter;

    public MenuLancement() {
        // Configuration de la fenêtre
        setTitle("Menu de Lancement");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Création des boutons
        btnJouer = new JButton("Jouer");
        btnParametres = new JButton("Paramètres");
        btnQuitter = new JButton("Quitter");

        // Ajout des boutons à la fenêtre
        add(btnJouer);
        add(btnParametres);
        add(btnQuitter);

        // Ajout des écouteurs d'événements
        btnJouer.addActionListener(this);
        btnParametres.addActionListener(this);
        btnQuitter.addActionListener(this);

        // Centrer la fenêtre à l'écran
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJouer) {
            dispose();  // Ferme le menu
            new Jeu().demarrer();  // Lance le jeu
        } else if (e.getSource() == btnParametres) {
            JOptionPane.showMessageDialog(this, "Les paramètres seront implémentés bientôt !");
        } else if (e.getSource() == btnQuitter) {
            System.exit(0);
        }
    }
}