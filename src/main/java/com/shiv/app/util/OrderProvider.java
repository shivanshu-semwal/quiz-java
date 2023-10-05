package com.shiv.app.util;

import java.util.*;

public class OrderProvider {
    public static OrderProvider orderProvider = null;
    private HashMap<Integer,ArrayList<Integer>> optionOrder;

    private OrderProvider(){
        optionOrder = new HashMap<>( );
    }

    public static OrderProvider getOrderProvider(){
        if(orderProvider==null){
            orderProvider = new OrderProvider();
        }
        return orderProvider;
    }
    public void shuffle(ArrayList<String> optionsList, Integer questionNumber){
        ArrayList<Integer> order = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        if(!optionOrder.containsKey(questionNumber)){
            Collections.shuffle(order);
            optionOrder.put(questionNumber, order);
        }
        ArrayList<String> tempStorage = (ArrayList) optionsList.clone();
        for(int i=0;i<order.size(); i++){
            optionsList.set(i, tempStorage.get(order.get(i)));
        }
    }
}
