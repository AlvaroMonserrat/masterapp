package com.newforce.myapp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newforce.myapp.model.data.Comuna;
import com.newforce.myapp.model.data.Provincia;
import com.newforce.myapp.model.data.Region;
import com.newforce.myapp.shared.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagerLocation {

    private Context mContext;

    public ManagerLocation(Context context){
        this.mContext = context;
    }

    public Region getRegion(int id){
        String jsonData;
        jsonData = Utils.getJsonFromAssets(mContext, "regiones.json");

        Gson gson = new Gson();
        Type listRegionType = new TypeToken<List<Region>>(){}.getType();

        List<Region> regions = gson.fromJson(jsonData, listRegionType);

        return regions != null ? regions.get(id-1) : null;
    }

    public List<Region> getListRegion(){
        String jsonData;
        jsonData = Utils.getJsonFromAssets(mContext, "regiones.json");

        Gson gson = new Gson();
        Type listRegionType = new TypeToken<List<Region>>(){}.getType();

        return gson.fromJson(jsonData, listRegionType);
    }

    public List<String> getListStringRegion(){
        List<String> regions = new ArrayList<>();

        List<Region> regionList = getListRegion();

        for(Region region: regionList){
            regions.add(region.getName());
        }

        return regions;
    }

    public List<Comuna> getListComuna(int id){
        String jsonData;
        jsonData = Utils.getJsonFromAssets(mContext, "comunas.json");

        Gson gson = new Gson();
        Provincia provincia = gson.fromJson(jsonData, Provincia.class);

        return provincia != null ? provincia.provincia_key.get(id): null;
    }


    public List<Comuna> getListComunaByRegionId(int regionId){
        String jsonData;
        jsonData = Utils.getJsonFromAssets(mContext, "comunas.json");

        Gson gson = new Gson();
        Provincia provincia = gson.fromJson(jsonData, Provincia.class);

        //Obtener Solo las comunas que pertenecen a la region
        //i.e. RegionId: 13 -> 131, 132, 133, 134, 135, 136.
        //Crear un Listado de Comunas Vacio
        //Obtener el listado de la provincia 1 de la region
        //Agregar todas las comunas a listado de Comunas
        //Pasar a la siguiente provincia 2. Repetir proceso
        List<Comuna> comunaList = new ArrayList<>();

        String regionIDString = String.valueOf(regionId);

        for(int p = 1; p < 10; p++ ){
            String provinciaIdString = regionIDString + p; // "131"
            int provinciaId = Integer.parseInt(provinciaIdString);

            boolean checkProvincia = provincia.provincia_key.containsKey(provinciaId);
            if(checkProvincia){
                List<Comuna> byProvincia = provincia.provincia_key.get(provinciaId);
                if(byProvincia != null){
                    comunaList.addAll(byProvincia);
                }
            }


        }

        return comunaList;

    }

    public List<String> getListStringComunaByRegionId(int regionId){
        List<String> comunas = new ArrayList<>();

        List<Comuna> comunaList = getListComunaByRegionId(regionId);

        for(Comuna comuna: comunaList){
            comunas.add(comuna.getName());
        }

        return comunas;
    }

}
