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
import com.shiv.app.model.Question;
import com.shiv.app.dao.QuestionsProvider;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz{

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

    private JLabel scoreLabel;
    private JLabel counterLabel;
    private QuestionsProvider questionsProvider;

    public Quiz(){
        questionsProvider = QuestionsProvider.getQuestionsProvider();
        frame = new JFrame();
        menuBar = new JMenuBar();
        score = 0;
        counter = 1;
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
        question.setText("Sample Question!" );
        option1 = new JButton("Option 1");
        option2 = new JButton("Option 2");
        option3 = new JButton("Option 3");
        option4 = new JButton("Option 4");
        go();
    }

    public void go(){
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
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

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
        for (int i=1; i < 20; i++) {
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
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton next = new JButton("Next");
        JButton prev = new JButton("Prev");
        buttonPanel.add(prev);
        buttonPanel.add(next);
        JButton submit = new JButton("Submit");

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
        
        // frame options
        frame.setJMenuBar(menuBar);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.NORTH, statusPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, controlPanel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class QuestionSelectListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            JRadioButton selectedRadioButton = (JRadioButton) event.getSource();
            Integer questionNumber = Integer.parseInt(selectedRadioButton.getText());
            setQuestion(questionsProvider.getQuestionNumber(questionNumber));
        }
    }

    public void setQuestion(Question q){
        question.setText(q.getQuestion());
        ArrayList<JButton> optionList = new ArrayList<>();
        optionList.add(option1);
        optionList.add(option2);
        optionList.add(option3);
        optionList.add(option4);
        Collections.shuffle(optionList);
        
        optionList.get(1).setText(q.getAnswer());
        optionList.get(1).setText(q.getOtherOptions().get(0));
        optionList.get(2).setText(q.getOtherOptions().get(1));
        optionList.get(3).setText(q.getOtherOptions().get(2));
    }
}