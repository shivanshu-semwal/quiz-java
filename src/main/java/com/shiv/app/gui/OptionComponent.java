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
    JScrollPane optionPanel;

    public OptionComponent(int number, String optionText){
        option = new JTextPane();
        option.setEditorKit(new CustomHTMLEditorKit());
        option.setOpaque(false);
        option.setBorder(null);
        option.setEditable(false);
        option.setFocusable(false);
        option.setContentType("text/html");
        option.setText(optionText);

        optionPanel = new JScrollPane(option);

        radioButton = new JRadioButton();
        radioButton.setName(Integer.toString(number));

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        panel.add(radioButton);
        panel.add(optionPanel);
    }

    public void setText(String text){
        option.setText(text);
    }

    public void setActionListener(ActionListener listener){
        radioButton.addActionListener(listener);
    }

    public void setSelected(boolean flag){
        radioButton.setSelected(flag);
    }
}