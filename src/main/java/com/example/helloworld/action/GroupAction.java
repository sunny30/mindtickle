package com.example.helloworld.action;

import com.example.helloworld.model.EntityMetadata;
import com.example.helloworld.model.Group;

/**
 * Created by sharad.singh on 12/06/16.
 */
public class GroupAction {
    private String groupId ;


    public void persist(){
        Group group   = new Group() ;
        group.setGroupId(groupId);
        EntityMetadata entityMetadata = EntityMetadata.getSingleInstance() ;
        entityMetadata.addGroup(groupId,group);

    }

    public void removeGroup(){
        EntityMetadata entityMetadata = EntityMetadata.getSingleInstance() ;
        entityMetadata.removeGroup(groupId);
    }


    public GroupAction withGroupId(String groupId){
        this.groupId  = groupId;
        return this ;
    }
}
