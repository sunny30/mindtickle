package com.example.helloworld.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharad.singh on 07/06/16.
 */
@Data
public class ContentSeriesRelationShip {
    private static Map<String, ArrayList<String>> contentToSeriesMap;
    private static Map<String, ArrayList<String>> seriesToContentMap;
    private static ContentSeriesRelationShip contentSeriesRelationShip = null;

    private ContentSeriesRelationShip() {

    }

    public static synchronized ContentSeriesRelationShip getSingleInstance() {

        if (contentSeriesRelationShip == null) {
            contentToSeriesMap = new HashMap<String, ArrayList<String>>();
            seriesToContentMap = new HashMap<String, ArrayList<String>>();
            contentSeriesRelationShip = new ContentSeriesRelationShip();

        }
        return contentSeriesRelationShip;


    }

    public void addRelationShip(String contentId, String seriesId) {
        if (contentToSeriesMap.get(contentId) == null) {
            ArrayList<String> seriesList = new ArrayList<String>();
            seriesList.add(seriesId);
            contentToSeriesMap.put(contentId, seriesList);
        } else {
            ArrayList<String> seriesList = contentToSeriesMap.get(contentId);
            seriesList.add(seriesId);
        }
        if (seriesToContentMap.containsKey(seriesId)) {
            ArrayList<String> contentList = seriesToContentMap.get(seriesId);
            contentList.add(contentId);
        } else {
            ArrayList<String> contentList = new ArrayList<String>();
            contentList.add(contentId);
            seriesToContentMap.put(seriesId, contentList);

        }
    }

}
