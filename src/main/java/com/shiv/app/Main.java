package com.shiv.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.shiv.app.gui.HomeScreen;
import com.shiv.app.gui.LookAndFeelManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeScreen.getFrame().setSize(400, 300);
        homeScreen.getMenuBar().add(LookAndFeelManager.getLookAndFeelMenuItem(homeScreen.getFrame()));
        homeScreen.getFrame().setVisible(true);
    }
}
