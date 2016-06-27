package com.example.leo.movieadmin;

/**
 * Created by rm48306 on 27/06/2016.
 */
public class Movie {

    private int id;
    private String name;
    private String plot;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String pName) {
        this.name = pName;
    }
    public String getPlot() {
        return plot;
    }
    public void setPlot(String pPlot) {
        this.plot = pPlot;
    }
}
