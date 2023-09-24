package com.shiv.app.dao;

import java.util.ArrayList;
import org.bson.Document;
import lombok.Getter;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.shiv.app.database.DatabaseConnection;
import com.shiv.app.model.Question;
import com.shiv.app.AppConfig;

public class QuestionsProvider {

    @Getter
    private ArrayList<Question> questions;

    private String databaseName;
    private String collectionName;

    private static QuestionsProvider questionsProvider = null;

    private QuestionsProvider() {
        databaseName = AppConfig.getAppConfig().getDatabaseName();
        collectionName = AppConfig.getAppConfig().getCollectionName();

        MongoClient mongoClient = DatabaseConnection.getDatabaseConnection().getClient();
        MongoDatabase db = mongoClient.getDatabase(databaseName);
        MongoCollection<Question> questionCollection = db.getCollection(collectionName, Question.class);
        
        questions = new ArrayList<>();
        for (Question question : questionCollection.find()) {
            this.questions.add(question);
        }
    }

    public static QuestionsProvider getQuestionsProvider() {
        if (questionsProvider == null) {
            questionsProvider = new QuestionsProvider();
        }
        return questionsProvider;
    }

    public Question getQuestionNumber(Integer questionNo) {
        return questions.get(questionNo);
    }

    public static void main(String[] args) {
        QuestionsProvider questionsProvider = QuestionsProvider.getQuestionsProvider();
        System.out.println(questionsProvider.getQuestions());
    }
}