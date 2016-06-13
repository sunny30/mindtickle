package com.example.helloworld.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharad.singh on 13/06/16.
 */
public class SeriesToGroupRelationShip {

    private  static Map<String,ArrayList<String>> seriesToGroupMap ;
    private  static Map<String,ArrayList<String>> groupToSeriesMap ;
    private static SeriesToGroupRelationShip seriesToGroupRelationShip = null;
    private SeriesToGroupRelationShip(){

    }
    public static synchronized SeriesToGroupRelationShip getSingleInstance(){

        if(seriesToGroupRelationShip==null){
            seriesToGroupMap = new HashMap<String, ArrayList<String>>() ;
            groupToSeriesMap = new HashMap<String, ArrayList<String>>() ;
            seriesToGroupRelationShip = new SeriesToGroupRelationShip() ;
        }
        return seriesToGroupRelationShip ;
    }

    public void addRelationship(String seriesId,String groupId){

        if(seriesToGroupMap.containsKey(seriesId)){
            ArrayList<String> groupList = seriesToGroupMap.get(seriesId) ;
            groupList.add(groupId) ;
        }else{
            ArrayList<String> groupList = new ArrayList<String>() ;
            groupList.add(groupId);
            seriesToGroupMap.put(seriesId,groupList) ;
        }

        if(groupToSeriesMap.containsKey(groupId)){
            ArrayList<String> seriesList = new ArrayList<String>() ;
            seriesList.add(seriesId);
        }else{
            ArrayList<String> seriesList = new ArrayList<String>() ;
            seriesList.add(seriesId) ;
            groupToSeriesMap.put(groupId,seriesList) ;
        }
    }
}
