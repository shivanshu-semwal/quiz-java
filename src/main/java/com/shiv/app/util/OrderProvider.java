package com.shiv.app.util;

import java.util.*;

import com.shiv.app.AppConfig;

public class OrderProvider {
    
    private static OrderProvider orderProvider = null;

    public static OrderProvider getOrderProvider(){
        if(orderProvider == null){
            orderProvider = new OrderProvider();
        }
        return orderProvider;
    }
    public ArrayList<ArrayList<Integer>> getOrder() {
        ArrayList<Integer> optionIndex = new ArrayList<>();
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        for (int i = 0; i < AppConfig.getAppConfig().getOptionSize(); i++) {
            optionIndex.add(i);
        }

        for (int i = 0; i < AppConfig.getAppConfig().getTotalQuestions(); i++) {
            Collections.shuffle(optionIndex);
            combinations.add(optionIndex);
        }

        return combinations;
    }

    // public static void main(String[] args) {
    //     OrderProvider orderProvider = OrderProvider.getOrderProvider();
    //     System.out.println(orderProvider.getOrder());

    // }
}
