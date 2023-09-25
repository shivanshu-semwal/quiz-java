package com.shiv.app.dao;

import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;
import lombok.Getter;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.shiv.app.database.DatabaseConnection;
import com.shiv.app.model.Question;
import com.shiv.app.AppConfig;

public class QuestionsProvider {

    @Getter
    private ArrayList<Question> questions;

    private static QuestionsProvider questionsProvider = null;

    private QuestionsProvider() {
        String databaseName = AppConfig.getAppConfig().getDatabaseName();
        String collectionName = AppConfig.getAppConfig().getCollectionName();

        MongoClient mongoClient = DatabaseConnection.getDatabaseConnection().getClient();
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        MongoCollection<Question> questionCollection = db.getCollection(collectionName, Question.class);
        
        AggregateIterable<Question> result = questionCollection.aggregate(
            Arrays.asList(
                new Document("$sample", new Document("size", 20))
            )
        );

        questions = new ArrayList<>();
        for(Question question: result)
            this.questions.add(question);
    }

    public static QuestionsProvider getQuestionsProvider() {
        if (questionsProvider == null) {
            questionsProvider = new QuestionsProvider();
        }
        return questionsProvider;
    }

    public Question getQuestionNumber(Integer questionNo) {
        return questions.get(questionNo - 1);
    }

    public static void main(String[] args) {
        QuestionsProvider questionsProvider = QuestionsProvider.getQuestionsProvider();
        // System.out.println(questionsProvider.getQuestions());
    }
}