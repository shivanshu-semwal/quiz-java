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

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Array;

import com.shiv.app.model.Question;
import com.shiv.app.AppConfig;
import com.shiv.app.dao.QuestionsProvider;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    @Getter
    private JFrame frame;
    @Getter
    private JMenuBar menuBar;
    @Getter
    private JTextPane question;
    @Getter
    private JButton option1;
    @Getter
    private JButton option2;
    @Getter
    private JButton option3;
    @Getter
    private JButton option4;
    @Getter
    private Integer score;
    @Getter
    private Integer counter;
    @Getter
    private JButton next;
    @Getter
    private JButton prev;
    @Getter
    private ArrayList<JRadioButton> jRadioButtons;
    private JLabel scoreLabel;
    private JLabel counterLabel;
    private QuestionsProvider questionsProvider;
    private OrderProvider orderProvider;
    private ArrayList<ArrayList<Integer>> combinations;

    public Quiz() {
        questionsProvider = QuestionsProvider.getQuestionsProvider();
        orderProvider = OrderProvider.getOrderProvider(); 
        frame = new JFrame();
        menuBar = new JMenuBar();
        score = 0;
        counter = 0;
        scoreLabel = new JLabel("Score: " + score.toString());
        counterLabel = new JLabel("Current Question: " + counter.toString());
        question = new JTextPane();
        // question.setWrapStyleWord(true);
        // question.setLineWrap(true);
        question.setEditorKit(new CustomHTMLEditorKit());
        question.setOpaque(false);
        question.setBorder(null);
        question.setEditable(false);
        question.setFocusable(false);
        question.setContentType("text/html");
        question.setText("Sample Question!");

        jRadioButtons = new ArrayList<JRadioButton>(AppConfig.getAppConfig().getOptionSize());
        for (Integer i = 0; i < AppConfig.getAppConfig().getOptionSize(); i++) {
            jRadioButtons.add(i, new JRadioButton("option" + i));
            jRadioButtons.get(i).setActionCommand((i.toString()));
        }
        go();
    }

    public void go() {
        // menubar options
        JMenu fileMenu = new JMenu("File");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        // add status
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        statusPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        statusPanel.setBorder(BorderFactory.createTitledBorder("Status"));
        statusPanel.add(scoreLabel);
        statusPanel.add(new JLabel("/"));
        statusPanel.add(counterLabel);

        // add question
        JScrollPane questionPanel = new JScrollPane(question);
        // questionPanel.setLayout(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createTitledBorder("Question"));
        // questionPanel.add(question);

        // add options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        for (JRadioButton i : jRadioButtons) {
            optionsPanel.add(i);
        }

        // make a container for question and options
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridLayout(0, 1));
        quizPanel.add(questionPanel);
        quizPanel.add(optionsPanel);

        // make question selector pane
        JPanel questionsNoPanel = new JPanel();
        questionsNoPanel.setLayout(new BoxLayout(questionsNoPanel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        QuestionSelectListener questionSelectListener = new QuestionSelectListener();
        for (int i = 1; i < AppConfig.getAppConfig().getTotalQuestions(); i++) {
            JRadioButton radioButton = new JRadioButton(Integer.toString(i));
            radioButton.addActionListener(questionSelectListener);
            buttonGroup.add(radioButton);
            questionsNoPanel.add(radioButton);
        }
        questionsNoPanel.setBorder(BorderFactory.createTitledBorder("Question List"));
        JScrollPane questionsNoScrollPanel = new JScrollPane(questionsNoPanel);

        // middle panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        centerPanel.add(questionsNoScrollPanel, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        centerPanel.add(quizPanel, c);

        // adding next prev buttons
        NextButtonListener nextButtonListener = new NextButtonListener();
        PreviousButtonListener previousButtonListener = new PreviousButtonListener();
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        next = new JButton("Next");
        prev = new JButton("Prev");
        buttonPanel.add(prev);
        buttonPanel.add(next);
        JButton submit = new JButton("Submit");
        next.addActionListener(nextButtonListener);
        prev.addActionListener(previousButtonListener);

        c = new GridBagConstraints();
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

        //get option order
        combinations = orderProvider.getOrder();
        // frame options
        frame.setJMenuBar(menuBar);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.NORTH, statusPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, controlPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class QuestionSelectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JRadioButton selectedRadioButton = (JRadioButton) event.getSource();
            Integer questionNumber = Integer.parseInt(selectedRadioButton.getText());
            // setQuestion(questionsProvider.getQuestionNumber(questionNumber));
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
            if (counter - 1 > 0) {
                counter--;
                setQuestion(counter);
            }
        }
    }

    public void setQuestion(Integer questionNumber) {
        counter = questionNumber;
        counterLabel.setText("Current Question: " + counter.toString());
        Question q = questionsProvider.getQuestionNumber(questionNumber);
        ArrayList<Integer> index = combinations.get(questionNumber);
        question.setText(q.getQuestion());
        jRadioButtons.get(index.get(0)).setText(q.getAnswer());
        jRadioButtons.get(index.get(1)).setText(q.getOtherOptions().get(0));
        jRadioButtons.get(index.get(2)).setText(q.getOtherOptions().get(1));
        jRadioButtons.get(index.get(3)).setText(q.getOtherOptions().get(2));
    }
}