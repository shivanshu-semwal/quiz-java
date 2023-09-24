package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import lombok.Getter;

public class HomeScreen{

    @Getter
    private JFrame frame;

    @Getter
    private JMenuBar menuBar;

    public HomeScreen(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        go();
    }

    public void go(){
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        
        JPanel home = new JPanel(new GridLayout(0, 1));
        home.setBorder(BorderFactory.createTitledBorder("Home"));
        JButton newQuiz = new JButton("New Quiz");
        JButton closeApp = new JButton("Close");
        home.add(newQuiz);
        home.add(closeApp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(home);
        frame.setSize(500, 500);
    }
}