package com.example.helloworld.action;

import com.example.helloworld.model.EntityMetadata;
import com.example.helloworld.model.User;
import lombok.Data;

/**
 * Created by sharad.singh on 07/06/16.
 */
@Data
public class UserAction {

    private String userId ;
    private User user ;

    public void persist(){
        user = new User() ;

        user.setUserId(userId);
        EntityMetadata entityMetadata = EntityMetadata.getSingleInstance() ;
        entityMetadata.addUser(userId,user);
    }

    public void removeUser(){
        EntityMetadata entityMetadata = EntityMetadata.getSingleInstance() ;
        entityMetadata.removeUser(userId);
    }


    public UserAction withUserId(String userId){
        this.userId = userId ;
        return  this ;
    }
}
