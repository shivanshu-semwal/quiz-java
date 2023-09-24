package com.shiv.app.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class Question {

    @Setter
    @Getter
    private ObjectId id;
    
    @Setter
    @Getter
    @BsonProperty(value = "question")
    private String question;
    
    @Setter
    @Getter
    @BsonProperty(value = "answer")
    private String answer;

    @Setter
    @Getter
    @BsonProperty(value = "other_options")
    private List<String> otherOptions;

    public Question() {}

    public String toString(){
        return "quesion: " + question + "\n" +
                "answer: " + answer + "\n" +
                "other_options" + otherOptions;
    }
}