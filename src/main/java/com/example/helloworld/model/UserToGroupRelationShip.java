package com.example.helloworld.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharad.singh on 07/06/16.
 */
@Data
public class UserToGroupRelationShip {

    private  static Map<String,ArrayList<String>>  userToGroupRelation ;
    private static Map<String,ArrayList<String>> groupToUserRelation ;
    private static UserToGroupRelationShip userToGroupRelationShip=null ;

    private UserToGroupRelationShip(){

    }

    public static synchronized  UserToGroupRelationShip getSingleInstance(){
        if(userToGroupRelation==null){

            userToGroupRelationShip = new UserToGroupRelationShip();

            userToGroupRelation = new HashMap<String, ArrayList<String>>() ;
            groupToUserRelation = new HashMap<String, ArrayList<String>>() ;
        }
        return userToGroupRelationShip ;
    }


    public synchronized void  add(String userId,String groupId){
        if(userToGroupRelation.get(userId)==null ){
            ArrayList<String> groupList = new ArrayList<String>() ;
            groupList.add(groupId) ;
        }else{
            ArrayList<String> groupList = userToGroupRelation.get(userId) ;
            groupList.add(groupId) ;
        }
        if(groupToUserRelation.get(groupId)==null){
            ArrayList<String> groupList = new ArrayList<String>() ;
            groupList.add(userId);
        }else{
            ArrayList<String> userList = groupToUserRelation.get(groupId) ;
            userList.add(groupId);
        }

    }

    public synchronized void remove(String userId,String groupId){
        if(userToGroupRelation.containsKey(userId)){
            userToGroupRelation.get(userId).remove(groupId) ;
        }
        if(groupToUserRelation.containsKey(groupId)){
            groupToUserRelation.get(groupId).remove(userId) ;
        }
    }

}
