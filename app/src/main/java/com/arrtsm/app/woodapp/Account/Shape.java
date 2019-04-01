package com.arrtsm.app.woodapp.Account;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Shape implements IShape{

    private final double length;
    private final double breadth;
    private final double length2;
    private final double breadth2;
    private final double thickness;
    private final Sink sink;
    private final TapHole tapHole;

    private Shape(Builder builder){

        this.length=builder.length;
        this.breadth=builder.breadth;
        this.length2=builder.length2;
        this.breadth2=builder.breadth2;
        this.thickness=builder.thickness;
        this.sink=builder.sink;
        this.tapHole=builder.tapHole;
        init();
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getBreadth() {
        return breadth;
    }

    @Override
    public double getThickness() {
        return thickness;
    }
    public static class Builder{

        private double length;
        private double breadth;
        private double length2;
        private double breadth2;
        private double thickness;
        private Sink sink;
        private TapHole tapHole;

        public Builder length(final double length){
            this.length=length;
            return this;
        }
        public Builder breadth(final double breadth){
            this.breadth=breadth;
            return this;
        }
        public Builder length2(final double length2){
            this.length2=length2;
            return this;
        }
        public Builder breadth2(final double breadth2){
            this.breadth2=breadth2;
            return this;
        }
        public Builder thickness(final double thickness){
            this.thickness=thickness;
            return this;
        }
        public Builder Sink(final Sink sink){
            this.sink=sink;
            return this;
        }
        public Builder TapHole(final TapHole tapHole){
            this.tapHole=tapHole;
            return this;
        }
        public Shape build(){
            return new Shape(this);
        }

    }
    public void init(){
        BufferedWriter bufferedWriter;
        String fileName="test.obj";
        FileOutputStream fos = null;
        try {
            final File dir = new File(Environment.getExternalStorageDirectory() + "/CNC DATA/");
            Log.d("folder", dir.toString());
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.e("ALERT", "could not create the directories");
                }
            }
            final File myFile = new File(dir, fileName);
            if (!myFile.exists()) {

                myFile.createNewFile();
            }
            Log.d("testing", "Box: " + myFile);
            FileWriter fileWriter = new FileWriter(myFile);
            Log.d("testing", "Box: " + fileWriter);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("mtllib box1.mtl"); ///1
            bufferedWriter.newLine();
            /// starts bottom view vertices
            bufferedWriter.write("o Mesh01"); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("usemtl test1\n" );
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void Rshape(double length,double breadth,double thickness){
        BufferedWriter bufferedWriter;

    }
}
