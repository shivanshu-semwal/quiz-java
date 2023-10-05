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
        if(!optionOrder.containsKey(questionNumber)){
            ArrayList<Integer> orderTemp = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
            Collections.shuffle(orderTemp);
            optionOrder.put(questionNumber, orderTemp);
        }

        ArrayList<Integer> order = optionOrder.get(questionNumber);
        ArrayList<String> tempStorage = (ArrayList) optionsList.clone();
        for(int i=0;i<order.size(); i++){
            optionsList.set(i, tempStorage.get(order.get(i)));
        }
    }
}
