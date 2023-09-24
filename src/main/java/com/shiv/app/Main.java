package com.shiv.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.shiv.app.gui.HomeScreen;

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

        JMenu lookAndFeelMenu = new JMenu("Look and Feel");
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : lookAndFeelInfos) {
            JMenuItem menuItem = new JMenuItem(info.getName());
            menuItem.addActionListener(new LookAndFeelActionListener(info.getClassName(), homeScreen.getFrame()));
            lookAndFeelMenu.add(menuItem);
        }
        homeScreen.getMenuBar().add(lookAndFeelMenu);
        homeScreen.getFrame().setVisible(true);
    }

    private static class LookAndFeelActionListener implements ActionListener {
        private String className;
        private JFrame frame;

        public LookAndFeelActionListener(String className, JFrame frame) {
            this.className = className;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(frame);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }
    }

}
