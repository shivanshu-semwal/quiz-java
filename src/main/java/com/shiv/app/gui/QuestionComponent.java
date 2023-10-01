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

public class QuestionComponent{

    @Getter
    JScrollPane container;
    JTextPane question;

    public QuestionComponent(){
        question = new JTextPane();
        question.setEditorKit(new CustomHTMLEditorKit());
        question.setOpaque(false);
        question.setBorder(null);
        question.setEditable(false);
        question.setFocusable(false);
        question.setContentType("text/html");
        question.setText("Sample Question!");

        container = new JScrollPane(question);
        container.setBorder(BorderFactory.createTitledBorder("Question"));
    }

    public void setText(String text){
        question.setText(text);
    }
}