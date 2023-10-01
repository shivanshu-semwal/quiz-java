package com.shiv.app.gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.UIManager;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;

import com.shiv.app.dao.QuestionsProvider;
import com.shiv.app.gui.OptionComponent;
import com.shiv.app.gui.QuestionComponent;
import com.shiv.app.model.Question;

public class QuizComponent{

    @Getter
    JPanel panel;
    @Getter
    private QuestionComponent question;
    @Getter
    private ArrayList<OptionComponent> options;

    private QuestionsProvider questionsProvider;

    QuizComponent(){
        questionsProvider = QuestionsProvider.getQuestionsProvider();

        question = new QuestionComponent();
        options = new ArrayList<>();
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i=0;i<4;i++){
            OptionComponent option = new OptionComponent(i, Integer.toString(i));
            buttonGroup.add(option.getRadioButton());
            options.add(option);
        }
        panel = new JPanel();
        go();
    }

    public void go(){
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        for (OptionComponent option : options) {
            optionsPanel.add(option.getPanel());
        }
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question.getContainer());
        panel.add(optionsPanel);
    }

    public void setQuestion(Integer questionNumber) {
        Question q = questionsProvider.getQuestionNumber(questionNumber);
    
        question.setText(q.getQuestion());

        ArrayList<String> optionsList = new ArrayList<>();
        optionsList.add(q.getAnswer());
        for(int i=0;i<q.getOtherOptions().size();i++){
            optionsList.add(q.getOtherOptions().get(i));
        }

        // shuffle options list
        // ...

        for(int i=0;i<optionsList.size();i++){
            options.get(i).setText(optionsList.get(i));
        }
    }
}