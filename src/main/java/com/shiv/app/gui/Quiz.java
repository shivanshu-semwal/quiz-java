package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;

import com.shiv.app.util.CustomHTMLEditorKit;
import com.shiv.app.util.OrderProvider;

import com.shiv.app.model.Question;
import com.shiv.app.AppConfig;
import com.shiv.app.dao.QuestionsProvider;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    @Getter
    private JFrame frame;
    @Getter
    private Integer score;
    @Getter
    private Integer counter;
    private JButton next;
    private JButton prev;
    private JButton submit;

    private JMenuBar menuBar;
    private JPanel statusPanel;

    private JPanel centerPanel;
    private JPanel controlPanel;
    private JLabel scoreLabel;
    private JLabel counterLabel;
    private QuizComponent quizComponent;
    private QuestionListComponent questionListComponent;

    private ArrayList<Integer> selectedOption;

    public Quiz() {
        frame = new JFrame();
        selectedOption = new ArrayList<Integer>();
        for(int i=0;i<AppConfig.getAppConfig().getTotalQuestions(); i++)
            selectedOption.add(QuizComponent.OFF_OPTION);
        initComponents();
    }

    public void initMenuBar(){
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
    }

    public void initStatusPanel(){
        counter = 0;
        counterLabel = new JLabel(counter.toString());

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        statusPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));
        statusPanel.add(new JLabel("Current Question: "));
        statusPanel.add(counterLabel);
    }

    public void initCenterPanel(){
        quizComponent = new QuizComponent();
        quizComponent.setActionListener(new OptionButtonListener());
        questionListComponent = new QuestionListComponent();

        questionListComponent.setActionListener(new QuestionSelectListener());

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        centerPanel.add(questionListComponent.getContainer(), c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        centerPanel.add(quizComponent.getPanel(), c);
    }

    public void initControlPanel(){
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        next = new JButton("Next");
        prev = new JButton("Prev");
        buttonPanel.add(prev);
        buttonPanel.add(next);
        submit = new JButton("Submit");
        next.addActionListener(new NextButtonListener());
        prev.addActionListener(new PreviousButtonListener());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        controlPanel.add(submit, c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        controlPanel.add(buttonPanel, c);
    }

    public void initComponents() {
        initMenuBar();
        initStatusPanel();
        initCenterPanel();
        initControlPanel();
        frame.setJMenuBar(menuBar);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.NORTH, statusPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, controlPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setQuestion(0);
    }

    public void setQuestion(Integer questionNumber) {
        counter = questionNumber; // change counter
        counterLabel.setText(Integer.toString(counter + 1)); // set label
        questionListComponent.setQuestion(counter); // set it in sidebar too
        quizComponent.setQuestion(questionNumber, selectedOption.get(counter)); // change question with option
    }

    class QuestionSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JRadioButton selectedRadioButton = (JRadioButton) event.getSource();
            Integer questionNumber = Integer.parseInt(selectedRadioButton.getText()) - 1;
            setQuestion(questionNumber);
        }
    }

    class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (counter + 1 < AppConfig.getAppConfig().getTotalQuestions()) {
                counter++;
                setQuestion(counter);
            }
        }
    }

    class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (counter - 1 >= 0) {
                counter--;
                setQuestion(counter);
            }
        }
    }

    class OptionButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            // get option selected
            JRadioButton selectedRadioButton = (JRadioButton) event.getSource();
            selectedOption.set(counter, Integer.parseInt(selectedRadioButton.getName()));
        }
    }
}