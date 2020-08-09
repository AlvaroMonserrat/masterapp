package com.newforce.myapp.shared;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String getJsonFromAssets(Context context, String fileName){

        String jsonString;

        try{
            InputStream openFile = context.getAssets().open(fileName);

            int size = openFile.available();
            byte[] buffer = new byte[size];
            int read = openFile.read(buffer);
            Log.i("JSON", String.valueOf(read));
            openFile.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

}
