package com.shiv.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GUI App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        JMenu lookAndFeelMenu = new JMenu("Look and Feel");

        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : lookAndFeelInfos) {
            JMenuItem menuItem = new JMenuItem(info.getName());
            menuItem.addActionListener(new LookAndFeelActionListener(info.getClassName(), frame));
            lookAndFeelMenu.add(menuItem);
        }


        menuBar.add(lookAndFeelMenu);
        frame.setJMenuBar(menuBar);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
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
