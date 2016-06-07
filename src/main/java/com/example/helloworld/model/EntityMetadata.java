package com.example.helloworld.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharad.singh on 07/06/16.
 */
@Data
public class EntityMetadata {

    private static EntityMetadata entityMetadata = null ;
    private static Map<String,User> userMetadata ;
    private static Map<String,Group> groupMetaData ;
    private static Map<String,Series> serieMetaData ;
    private static Map<String,Content> contentMetaData ;
    private EntityMetadata(){

    }

    public static synchronized EntityMetadata getSingleInstance(){
        if(entityMetadata == null){
            userMetadata = new HashMap<String, User>() ;
            groupMetaData = new HashMap<String, Group>() ;
            serieMetaData = new HashMap<String, Series>() ;
            contentMetaData = new HashMap<String, Content>() ;
            entityMetadata = new EntityMetadata() ;
        }
        return entityMetadata ;
    }



  //  public void addUser(String id,user)
}
