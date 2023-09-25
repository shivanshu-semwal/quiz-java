package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import lombok.Getter;

import com.shiv.app.gui.Quiz;
import com.shiv.app.AppConfig;

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
        // file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem settingsMenuItem = new JMenuItem("Settings...");
        JMenuItem openMenuItem = new JMenuItem("Quit");
        fileMenu.add(settingsMenuItem);
        fileMenu.add(openMenuItem);

        // about menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JPanel home = new JPanel(new GridLayout(0, 1, 30, 30));
        home.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        JButton newQuiz = new JButton("New Quiz");
        JButton closeApp = new JButton("Close");
        newQuiz.addActionListener(new NewQuizListener());
        home.add(newQuiz);
        home.add(closeApp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, home);
        frame.setSize(500, 500);
    }

    class NewQuizListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            Quiz quiz = new Quiz();
            try {
                UIManager.setLookAndFeel(AppConfig.getAppConfig().getLookAndFeel());
                SwingUtilities.updateComponentTreeUI(quiz.getFrame());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
            quiz.getFrame().setVisible(true);
        }
    }
}