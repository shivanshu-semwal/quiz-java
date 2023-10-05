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

import com.shiv.app.AppConfig;

public class QuestionListComponent{
    
    @Getter
    private ArrayList<JRadioButton> buttons;
    private JPanel questionsNoPanel;
    @Getter
    private JScrollPane container;
    
    public QuestionListComponent(){
        buttons = new ArrayList<JRadioButton>(AppConfig.getAppConfig().getTotalQuestions());
        questionsNoPanel = new JPanel();
        questionsNoPanel.setLayout(new BoxLayout(questionsNoPanel, BoxLayout.Y_AXIS));

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < AppConfig.getAppConfig().getTotalQuestions(); i++) {
            JRadioButton radioButton = new JRadioButton(Integer.toString(i + 1));
            buttons.add(i, radioButton);
            questionsNoPanel.add(radioButton);
            buttonGroup.add(radioButton);
        }

        questionsNoPanel.setBorder(BorderFactory.createTitledBorder("Question List"));
        container = new JScrollPane(questionsNoPanel);
    }

    public void setActionListener(ActionListener listener){
        for (JRadioButton button: buttons) button.addActionListener(listener);
    }

    public void setQuestion(int questionNumber){
        buttons.get(questionNumber).setSelected(true);
    }
}