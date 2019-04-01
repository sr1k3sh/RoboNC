package com.arrtsm.app.woodapp;

import android.os.Environment;
import android.util.Log;

import com.arrtsm.app.woodapp.CircleModels.CircleFunctions;
import com.arrtsm.app.woodapp.CircleModels.Functions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class BuilderTest {

    private final int c,d,e,f;

   // private final double radius,thickness,legheight;
    public static class Builder{
        //private final int a,b;
        private int c=0,d=0,e=0,f=0,test=0;
        double r,radius,thickness,legheight;
        BufferedWriter bufferedWriter;
        Functions functions = new Functions();
        CircleFunctions cf = new CircleFunctions();
        public Builder(double radius,double thickness,double legheight){
            r=radius/100;
            double y=thickness/100,x=r,z=r;
            double leg=legheight/100;
            String fileName="model.obj";
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
                bufferedWriter.write("v "+functions.translate(x,x)+" 0 "+functions.translate(z,z));
                bufferedWriter.newLine();
                cf.circle(bufferedWriter,functions.translate(x,x),0,functions.translate(z,z),r);
                bufferedWriter.write("v "+functions.translate(x,x)+" "+y+" "+functions.translate(z,z));
                bufferedWriter.newLine();
                cf.circle(bufferedWriter,functions.translate(x,x),y,functions.translate(z,z),r);
                bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
                bufferedWriter.newLine();
                cf.cirtex(bufferedWriter,r,r,r);
                bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
                bufferedWriter.newLine();
                cf.cirtex(bufferedWriter,r,r,r);
                functions.normalvector(bufferedWriter);

                bufferedWriter.write("usemtl test1\n" );
                bufferedWriter.newLine();
                cf.circleface(bufferedWriter,1,4,false);
                cf.circleface(bufferedWriter,75,3,true);
                sideface(bufferedWriter,2,74,76,148);
                int n= 148;
                bufferedWriter.write("o Mesh02"); ///1
                bufferedWriter.newLine();
                bufferedWriter.write("usemtl test2\n" );
                bufferedWriter.newLine();
                cf.leg1(bufferedWriter,x,y,z,n,leg,r);




                bufferedWriter.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Log.d("rikesh", "Builder: "+test);
        }
        public Builder c(int val){
            c=val+test;
            Log.d("rikesh", "Builder: "+c);
            return this;
        }
        public Builder d(int val){
            d=val;
            return this;
        }
        public Builder e(int val){
            e=val;
            return this;
        }
        public Builder f(int val){
            f=val;
            return this;
        }
        public BuilderTest build(){
            return new BuilderTest(this);
        }
    }
    public BuilderTest(Builder builder){
        c=builder.c;
        d=builder.d;
        e=builder.e;
        f=builder.f;

    }
    public static void sideface(BufferedWriter bufferedWriter,int p1 ,int n1,int p2,int n2){
        int i1=p1,i2=i1+1,j1=p2,j2=j1+1;
        int tempi=p1+(Math.abs(i1-n1)),tempj=p2+Math.abs(j2-n2);
        int f;
        Log.d("testing", "sideface1: "+tempi+"   "+tempj);
        int num1=n1,num2=n2;
        while(i1<num1&&i2<num1+1&&j1<num2&&j2<num2+1){
            try
            {   if(i1<tempi&&j1<tempj)
                f=3;
            else
                f=3;
                bufferedWriter.write("f "+i1+"/"+i1+"/"+f+" "+i2+"/"+i2+"/"+f+" "+j2+"/"+j2+"/"+f+" "+j1+"/"+j1+"/"+f);
                bufferedWriter.newLine();
                i1=i1+1;
                i2=i2+1;
                j1=j1+1;
                j2=j2+1;
                Log.d("testing", "sideface: "+i1+""+i2+""+f);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
