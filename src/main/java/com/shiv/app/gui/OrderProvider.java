package com.shiv.app.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class OrderProvider {
    public static OrderProvider orderProvider = null;
    private HashMap<Integer,ArrayList<String>> optionOrder;
    private OrderProvider(){
        optionOrder = new HashMap<>( );
    }

    public static OrderProvider getOrderProvider(){
        if(orderProvider==null){
            orderProvider = new OrderProvider();
        }
        return orderProvider;
    }
    public ArrayList<String> shuffle(ArrayList<String> optionsList, Integer questionNumber){
        if(!optionOrder.containsKey(questionNumber)){
            Collections.shuffle(optionsList);
            optionOrder.put(questionNumber, optionsList);
        }
        return optionOrder.get(questionNumber);
    }
}
