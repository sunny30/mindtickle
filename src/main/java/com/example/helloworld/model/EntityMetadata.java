package com.example.helloworld.model;

import lombok.Data;
import org.yaml.snakeyaml.tokens.BlockEndToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharad.singh on 07/06/16.
 */
@Data
public class EntityMetadata {

    private static EntityMetadata entityMetadata = null;
    private static Map<String, User> userMetadata;
    private static Map<String, Group> groupMetaData;
    private static Map<String, Series> serieMetaData;
    private static Map<String, Content> contentMetaData;

    private EntityMetadata() {

    }

    public static synchronized EntityMetadata getSingleInstance() {
        if (entityMetadata == null) {
            userMetadata = new HashMap<String, User>();
            groupMetaData = new HashMap<String, Group>();
            serieMetaData = new HashMap<String, Series>();
            contentMetaData = new HashMap<String, Content>();
            entityMetadata = new EntityMetadata();
        }
        return entityMetadata;
    }


    public synchronized void addUser(String id, User user) {
        EntityMetadata.userMetadata.put(id, user);
    }

    public synchronized void addGroup(String id, Group group) {
        EntityMetadata.groupMetaData.put(id, group);
    }

    public synchronized void removeGroup(String id) {
        if (EntityMetadata.groupMetaData.containsKey(id))
            EntityMetadata.groupMetaData.remove(id);
    }

    public synchronized void removeUser(String id) {
        if (EntityMetadata.userMetadata.containsKey(id))
            EntityMetadata.userMetadata.remove(id);
    }

    public boolean checkGroup(String groupId) {
        return EntityMetadata.groupMetaData.containsKey(groupId);
    }

    public boolean isGroupPresent(String groupId) {
        return EntityMetadata.groupMetaData.containsKey(groupId);
    }

    public boolean isUserPresent(String userId) {
        return EntityMetadata.userMetadata.containsKey(userId);
    }

    public synchronized void addContent(String contentId, Content content) {
        EntityMetadata.contentMetaData.put(contentId, content);

    }

    public synchronized void addSeries(String seriesId, Series series) {
        EntityMetadata.serieMetaData.put(seriesId, series);

    }



}
