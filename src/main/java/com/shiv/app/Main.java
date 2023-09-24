package com.shiv.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.shiv.app.gui.HomeScreen;
import com.shiv.app.gui.LookAndFeelManager;
import com.shiv.app.AppConfig;

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
        try {
            UIManager.setLookAndFeel(AppConfig.getAppConfig().getLookAndFeel());
            SwingUtilities.updateComponentTreeUI(homeScreen.getFrame());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        homeScreen.getFrame().setVisible(true);
    }
}
