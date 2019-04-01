package com.arrtsm.app.woodapp;

import android.content.Context;
import android.content.res.AssetManager;

import com.arrtsm.app.woodapp.util.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//import robo.arrtsm.com.robonc11.util.Constants;

/**
 * Created by Hitech on 3/29/2018.
 */

public class MaterialFileEditor {
    Context context;

    FileOutputStream outputStream;
    FileOutputStream outputStream2;
    FileInputStream inputStream;


    AssetManager assetManager;

    public MaterialFileEditor(Context context) {
        this.context = context;
        assetManager = context.getAssets();
    }

    public String getNewMaterialFromTexture(String shape, String textureName) {

        String materialPath="";
        switch (shape) {
            case Constants.Rectangle:
                materialPath = "ar/asset/normal.mtl";
                break;
            case Constants.Oval:
                materialPath = "ar/asset/normaloval.mtl";
                break;
            case Constants.Circle:
                materialPath = "ar/asset/normalcircle.mtl";
                break;
        }

        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(assetManager.open(materialPath)));

            File materialFile = File.createTempFile("newMaterial", ".mtl", context.getExternalCacheDir());
            outputStream = new FileOutputStream(materialFile);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            String line;
            StringBuilder stringBuffer = new StringBuilder();

            while ((line = file.readLine()) != null) {
                if(shape.equals(Constants.Oval)){
                    if (line.contains("map_Kd a.jpg") ) {
                        line = line.replace(line, "map_Kd "+textureName);
                    }
                }else if(shape.equals(Constants.Circle)){
                    if (line.contains("map_Kd a.jpg") ) {
                        line = line.replace(line, "map_Kd "+textureName);
                    }
                }else if(shape.equals(Constants.O_SHAPE)){
                    if (line.contains("map_Kd a.jpg")) {
                        line = line.replace(line, "map_Kd "+textureName);
                    }
                }else if(shape.equals(Constants.Rectangle)){
                    if (line.contains("map_Kd a.jpg")) {
                        line = line.replace(line, "map_Kd "+textureName);
                    }
                }
                stringBuffer.append(line);
                stringBuffer.append('\n');
            }
            outputStreamWriter.write(stringBuffer.toString());
            outputStreamWriter.flush();
            System.out.println(materialFile.getAbsolutePath());
            System.out.println(stringBuffer.toString());
            return materialFile.getAbsolutePath();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        return null;
    }
}
