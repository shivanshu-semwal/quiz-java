package com.shiv.app;

import lombok.Getter;
import lombok.Setter;

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

    private static AppConfig appConfig = null;

    private AppConfig(){
        mongodbUri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.1";
        databaseName = "local";
        collectionName = "quiz";
    }

    public static AppConfig getAppConfig(){
        if(appConfig == null){
            appConfig = new AppConfig();
        }
        return appConfig;
    }
}