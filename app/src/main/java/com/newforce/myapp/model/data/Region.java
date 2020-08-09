package com.newforce.myapp.model.data;

public class Region {
    private final int region_id;
    private final String name;


    public Region(int region_id, String name) {
        this.region_id = region_id;
        this.name = name;
    }

    public int getRegionId(){
        return region_id;
    }

    public String getName(){
        return name;
    }

}
