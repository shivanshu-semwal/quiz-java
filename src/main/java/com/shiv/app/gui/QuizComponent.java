package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.shiv.app.dao.QuestionsProvider;
import com.shiv.app.gui.OptionComponent;
import com.shiv.app.gui.QuestionComponent;
import com.shiv.app.model.Question;
import com.shiv.app.util.OrderProvider;

public class QuizComponent{

    @Getter
    JPanel panel;
    private QuestionComponent question;
    private ArrayList<OptionComponent> options;
    private QuestionsProvider questionsProvider;
    private int totalOptions;
    private OrderProvider orderProvider;

    public static final int OFF_OPTION = 4;

    QuizComponent(){
        totalOptions = 4;

        questionsProvider = QuestionsProvider.getQuestionsProvider();
        orderProvider = OrderProvider.getOrderProvider();
        question = new QuestionComponent();
        options = new ArrayList<>();
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i=0;i<totalOptions;i++){
            OptionComponent option = new OptionComponent(i, Integer.toString(i));
            buttonGroup.add(option.getRadioButton());
            options.add(option);
        }

        // add off option, but don't display it
        {
            OptionComponent option = new OptionComponent(OFF_OPTION, Integer.toString(OFF_OPTION));
            options.add(option);
            buttonGroup.add(option.getRadioButton());
        }

        panel = new JPanel();
        go();
    }

    public void go(){
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        for (int i=0;i<totalOptions; i++) optionsPanel.add(options.get(i).getPanel());
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question.getContainer());
        panel.add(optionsPanel);
    }

    public void setQuestion(Integer questionNumber, Integer optionNumber) {
        Question q = questionsProvider.getQuestionNumber(questionNumber);
        question.setText(q.getQuestion());

        ArrayList<String> optionsList = new ArrayList<>();
        optionsList.add(q.getAnswer());
        for(int i=0;i<q.getOtherOptions().size();i++){
            optionsList.add(q.getOtherOptions().get(i));
        }
        // shuffle options list
        orderProvider.shuffle(optionsList, questionNumber);
        for(int i=0;i<optionsList.size();i++){
            options.get(i).setText(optionsList.get(i));
            if(i == optionNumber) {
                options.get(i).setSelected(true);
            }
        }
        if(optionNumber == OFF_OPTION){
            options.get(OFF_OPTION).setSelected(true);
        }
    }

    public void setActionListener(ActionListener listener){
        for (int i = 0; i < totalOptions; i++) {
            options.get(i).setActionListener(listener);
        }
    }
}