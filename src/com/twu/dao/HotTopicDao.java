package com.twu.dao;

import com.twu.pojo.HotTopic;

import java.util.List;

public class HotTopicDao {

    boolean isExit = false;

    public Boolean selectByName (List<HotTopic> list,String name){
        list.forEach(i->{
            if(i.getHotTopicName().equals(name)){
                isExit=true;
            }
        });
        return isExit;
    }

}
