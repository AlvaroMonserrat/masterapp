package com.newforce.myapp.model.data;

public class Comuna {
    private int comuna_id;
    private String name;


    public Comuna(int comuna_id, String name) {
        this.comuna_id = comuna_id;
        this.name = name;
    }

    public int getComunaId(){
        return comuna_id;
    }

    public String getName(){
        return name;
    }

}
