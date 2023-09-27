package com.shiv.app;

import lombok.Getter;
import lombok.Setter;

import javax.swing.UIManager;

public class AppConfig{
    @Getter
    private String mongodbUri;
    @Getter
    private String mongodbUser;
    @Getter
    private String mongodbPassword;
    @Getter
    private String jsonLocation;
    @Getter
    private String databaseName;
    @Getter
    private String collectionName;
    @Getter
    private String lookAndFeel;
    @Getter
    private Integer totalQuestions;

    private static AppConfig appConfig = null;

    private AppConfig(){
        mongodbUri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+1.10.5";
        databaseName = "local";
        collectionName = "quiz";
        totalQuestions = 20;
        lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        System.out.println(lookAndFeel);
    }

    public static AppConfig getAppConfig(){
        if(appConfig == null){
            appConfig = new AppConfig();
        }
        return appConfig;
    }
}