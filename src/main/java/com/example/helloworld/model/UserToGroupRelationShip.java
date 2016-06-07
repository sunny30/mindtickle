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
    private static UserToGroupRelationShip userToGroupRelationShip=null ;

    private UserToGroupRelationShip(){

    }

    public UserToGroupRelationShip getSingleInstance(){
        if(userToGroupRelation==null){

            userToGroupRelationShip = new UserToGroupRelationShip();
            userToGroupRelation = new HashMap<String, ArrayList<String>>() ;


        }
        return userToGroupRelationShip ;
    }


    public void add(String userId,String groupId){
        if(userToGroupRelation.get(userId)==null){
            ArrayList<String> groupList = new ArrayList<String>() ;
            groupList.add(groupId) ;
        }else{
            ArrayList<String> groupList = userToGroupRelation.get(userId) ;
            groupList.add(groupId) ;
        }
    }

}
