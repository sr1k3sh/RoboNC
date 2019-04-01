package com.arrtsm.app.woodapp.CircleModels;

import android.os.Environment;
import android.util.Log;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullBullCircle {
    double r;
    double i,j;
    int in;
    List list= new ArrayList();
    List list2=new ArrayList();
    BufferedWriter bufferedWriter;
    CircleFunctions cf = new CircleFunctions();
    Functions functions = new Functions();
    public FullBullCircle(double radius,double thickness,double legheight){
        r=radius/100;
        double leg=legheight/100;
        double y=thickness/100,x=r,z=r;
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
            functions.normalvector(bufferedWriter);
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
           /* cf.circleface(bufferedWriter,1,4,false);
            cf.circleface(bufferedWriter,75,3,true);*/
            //sideface(bufferedWriter,2,74,76,148);
            bufferedWriter.write("o Mesh02"); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("usemtl test2\n" );
            bufferedWriter.newLine();
            int n=148;
           //cf.leg1(bufferedWriter,x,y,z,n,leg,r);
           /* double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
            cx1=r/2;
            cz1=r/2;
            bufferedWriter.write("v "+cx1+" 0 "+cz1);
            bufferedWriter.newLine();
            cx2=-r/2;
            cz2=r/2;
            bufferedWriter.write("v "+cx2+" 0 "+cz2);
            bufferedWriter.newLine();
            cx3=-r/2;
            cz3=-r/2;
            bufferedWriter.write("v "+cx3+" 0 "+cz3);
            bufferedWriter.newLine();
            cx4=r/2;
            cz4=-r/2;
            bufferedWriter.write("v "+cx4+" 0 "+cz4);
            bufferedWriter.newLine();
            functions.legsquare(bufferedWriter,cx1,0,cz1,r,r);
            functions.legsquare(bufferedWriter,cx2,0,cz2,r,r);
            functions.legsquare(bufferedWriter,cx3,0,cz3,r,r);
            functions.legsquare(bufferedWriter,cx4,0,cz4,r,r);
            functions.legsquare(bufferedWriter,cx1,-y/1.2,cz1,r,r);
            functions.legsquare(bufferedWriter,cx2,-y/1.2,cz2,r,r);
            functions.legsquare(bufferedWriter,cx3,-y/1.2,cz3,r,r);
            functions.legsquare(bufferedWriter,cx4,-y/1.2,cz4,r,r);
            bufferedWriter.write("f "+160+"/160/3 "+176+"/176/3 "+171+"/171/3 "+155+"/155/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(89+72)+"/161/3 "+(105+72)+"/177/3 "+(104+72)+"/176/3 "+(88+72)+"/160/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(89+72)+"/161/3 "+(94+72)+"/166/3 "+(110+72)+"/182/3 "+(105+72)+"/177/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(94+72)+"/166/3 "+(83+72)+"/155/3 "+(99+72)+"/171/3 "+(110+72)+"/182/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(98+72)+"/170/4 "+(99+72)+"/171/4 "+(104+72)+"/176/4 "+(101+72)+"/173/4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(103+72)+"/175/4 "+(104+72)+"/176/4 "+(105+72)+"/177/4 "+(106+72)+"/178/4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(108+72)+"/180/4 "+(105+72)+"/177/4 "+(110+72)+"/182/4 "+(111+72)+"/183/4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(109+72)+"/181/4 "+(110+72)+"/182/4 "+(99+72)+"/171/4 "+(100+72)+"/172/4");
            bufferedWriter.newLine();
            functions.legsquare(bufferedWriter,cx1,-5,cz1,r,r);
            cf.legface(bufferedWriter,97+72,113+72,3);
            functions.legsquare(bufferedWriter,cx2,-5,cz2,r,r);
            cf.legface(bufferedWriter,101+72,117+72,3);
            functions.legsquare(bufferedWriter,cx3,-5,cz3,r,r);
            cf.legface(bufferedWriter,105+72,121+72,3);
            functions.legsquare(bufferedWriter,cx4,-5,cz4,r,r);
            cf.legface(bufferedWriter,109+72,125+72,3);
            ///tex coordinates

            cf.vt(bufferedWriter,r);
            cf.legvt(bufferedWriter,cx1,0,cz1,r,r);
            cf.legvt(bufferedWriter,cx2,0,cz2,r,r);
            cf.legvt(bufferedWriter,cx3,0,cz3,r,r);
            cf.legvt(bufferedWriter,cx4,0,cz4,r,r);
            cf.legvt(bufferedWriter,cx1,-y/1.2,cz1,r,r);
            cf.legvt(bufferedWriter,cx2,-y/1.2,cz2,r,r);
            cf.legvt(bufferedWriter,cx3,-y/1.2,cz3,r,r);
            cf.legvt(bufferedWriter,cx4,-y/1.2,cz4,r,r);
            cf.legvt(bufferedWriter,cx1,-1,cz1,r,r);
            cf.legvt(bufferedWriter,cx2,-1,cz2,r,r);
            cf.legvt(bufferedWriter,cx3,-1,cz3,r,r);
            cf.legvt(bufferedWriter,cx4,-1,cz4,r,r);*/

            bufferedWriter.write("o Mesh01"); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("usemtl test3\n" );
            bufferedWriter.newLine();
            bullnose(x,y,z,r,true);
            rightsidebullnose(x,z);
            /// n number +21
            int n1=940+21;
            cf.bface(bufferedWriter,76,n1,3,true);
            int p=n1,nt=(n1+(74*16));
            while(p<=nt){
                cf.bface(bufferedWriter,p,p+74,3,true);
                p=p+74;
            }

            vtbullnose(x,y,z,r,true);
            vtrightsidebullnose(x,z);
            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void bullnose(double x,double y,double z,double radius,boolean direction){
        double t1=0,t2;
        r=y/2;
        double h=r;
        double k=y/2;
        int deg=0;
        while(deg<=180) {
            t1 = r * Math.cos(Math.toRadians(deg)) + h;
            t2 = r * Math.sin(Math.toRadians(deg)) ;
            list2.add(t2);
            list.add(t1);


            deg=deg+10;
            try {
                bufferedWriter.write("v"+" "+0+" "+t1+" "+0);
                bufferedWriter.newLine();
                //fullcircle(bufferedWriter,x,t2,t1,r);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void rightsidebullnose(double xt,double zt){
        int count=0;
        while(in<list.size()&&in<list2.size()){
            Log.d("testing", "test: "+list.get(in)+" "+ list.size());
            in++;
            count++;
            double h = (double)list.get(in);
            double k = (double)list2.get(in);
            double x = 0;
            double z = 0;
            double y=h;
            double r=k+zt;
            Log.d("testing", "test: "+h+" "+k);
            try {
                bufferedWriter.write("v"+" "+x+" "+y+" "+z);
                bufferedWriter.newLine();
                fullcircle(bufferedWriter,x,z,y,r);

                //semicircleright(x,h,t2);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if(count>17)
                break;

        }
    }
    public void fullcircle(BufferedWriter bufferedWriter,double x,double z,double y,double radius){
        int deg=0;
        double yt=y;
        double r=radius;

        while (deg<=360){
            i= r*Math.sin(Math.toRadians(deg))+x;
            j= r*Math.cos(Math.toRadians(deg))+z;
            deg=deg+5;
            try {
                Log.d("testing", "TestBox: thir56d");
                bufferedWriter.append("v"+" "+i+" "+yt+" "+j);
                bufferedWriter.newLine();
                Log.d("testing", "TestBox: third");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void vtbullnose(double x,double y,double z,double radius,boolean direction){
        double t1=0,t2;
        r=y/2;
        double h=r;
        double k=y/2;
        int deg=0;
        while(deg<=180) {
            t1 = r * Math.cos(Math.toRadians(deg)) + h;
            t2 = r * Math.sin(Math.toRadians(deg)) ;
            list2.add(t2);
            list.add(t1);


            deg=deg+10;
            try {
                bufferedWriter.write("vt"+" "+0+" "+t1);
                bufferedWriter.newLine();
                //fullcircle(bufferedWriter,x,t2,t1,r);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void vtrightsidebullnose(double xt,double zt){
        int count=0;
        while(in<list.size()&&in<list2.size()){
            Log.d("testing", "test: "+list.get(in)+" "+ list.size());
            in++;
            count++;
            double h = (double)list.get(in);
            double k = (double)list2.get(in);
            double x = 0;
            double z = 0;
            double y=h;
            double r=k+zt;
            Log.d("testing", "test: "+h+" "+k);
            try {
                bufferedWriter.write("vt"+" "+x+" "+y);
                bufferedWriter.newLine();
                vtfullcircle(bufferedWriter,x,z,y,r);

                //semicircleright(x,h,t2);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if(count>17)
                break;

        }
    }
    public void vtfullcircle(BufferedWriter bufferedWriter,double x,double z,double y,double radius){
        int deg=0;
        double yt=y;
        double r=radius;

        while (deg<=360){
            i= r*Math.sin(Math.toRadians(deg))+x;
            j= r*Math.cos(Math.toRadians(deg))+z;
            deg=deg+5;
            try {
                Log.d("testing", "TestBox: thir56d");
                bufferedWriter.append("vt"+" "+i+" "+yt);
                bufferedWriter.newLine();
                Log.d("testing", "TestBox: third");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
