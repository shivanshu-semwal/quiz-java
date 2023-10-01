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

public class OptionComponent{

    @Getter
    JPanel panel;

    @Getter
    JRadioButton radioButton;
    JTextPane option;
    JScrollPane optionPane;

    @Getter
    int number;

    public OptionComponent(int number, String optionText){
        this.number = number;

        option = new JTextPane();
        option.setEditorKit(new CustomHTMLEditorKit());
        option.setOpaque(false);
        option.setBorder(null);
        option.setEditable(false);
        option.setFocusable(false);
        option.setContentType("text/html");
        option.setText(optionText);

        optionPane = new JScrollPane(option);

        radioButton = new JRadioButton();

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panel.add(radioButton);
        panel.add(optionPane);
    }

    public void setText(String text){
        option.setText(text);
    }
}