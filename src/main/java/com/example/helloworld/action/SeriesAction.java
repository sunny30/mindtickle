package com.example.helloworld.action;

import com.example.helloworld.model.EntityMetadata;
import com.example.helloworld.model.Series;

/**
 * Created by sharad.singh on 13/06/16.
 */
public class SeriesAction {

    private String seriesId;


    public SeriesAction withSeriesId(String seriesId) {
        this.seriesId = seriesId;
        return this;
    }

    public void persist() {
        EntityMetadata metadata = EntityMetadata.getSingleInstance();
        Series series = new Series();
        series.setSeriesId(seriesId);
        metadata.addSeries(seriesId, series);
    }


}
