package com.shiv.app.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookAndFeelManager{

    private LookAndFeelManager(){}

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

    public static JMenu getLookAndFeelMenuItem(JFrame frame){
        JMenu lookAndFeelMenu = new JMenu("Look and Feel");
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : lookAndFeelInfos) {
            JMenuItem menuItem = new JMenuItem(info.getName());
            menuItem.addActionListener(new LookAndFeelActionListener(info.getClassName(), frame));
            lookAndFeelMenu.add(menuItem);
        }
        return lookAndFeelMenu;
    }
}