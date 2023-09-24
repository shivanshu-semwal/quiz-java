package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

public class GUI implements ActionListener{

    private JButton button;

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        
        JPanel question = new JPanel(new BorderLayout());
        question.add(new JLabel("hllo"));
        
        question.setBorder(BorderFactory.createTitledBorder("Question"));

        JPanel options = new JPanel(new GridLayout(2, 2));
        options.setBorder(BorderFactory.createTitledBorder("Options"));
        options.add(new JButton("Option 1"));
        options.add(new JButton("Option 2"));
        options.add(new JButton("Option 3"));
        options.add(new JButton("Option 4"));

        frame.setLayout(new GridLayout(0, 1));
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(question);
        frame.getContentPane().add(options);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        button.setText("you clicked me");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); // Set Nimbus L&F
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.go();
        });
    }
}