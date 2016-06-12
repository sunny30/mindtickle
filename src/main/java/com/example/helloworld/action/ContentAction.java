package com.example.helloworld.action;

import com.example.helloworld.model.Content;
import com.example.helloworld.model.EntityMetadata;
import lombok.Data;

/**
 * Created by sharad.singh on 13/06/16.
 */
@Data
public class ContentAction {

    private String contentId ;

    public void persist(){

        EntityMetadata entityMetadata = EntityMetadata.getSingleInstance() ;
        Content content = new Content() ;
        content.setContentId(contentId);
        entityMetadata.addContent(contentId,content);

    }

    public ContentAction withContent(String contentId){
        this.contentId = contentId ;
        return this ;
    }

}
