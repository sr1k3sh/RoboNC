package com.arrtsm.app.woodapp.CircleModels;

import android.os.Environment;
import android.util.Log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class NormalCircle {
    double r;
    int i,j;
    BufferedWriter bufferedWriter;
    Functions functions = new Functions();
    CircleFunctions cf = new CircleFunctions();
    public NormalCircle(double radius,double thickness,double legheight){
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
           /*  double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
            cx1=r/2;
            cz1=r/2;
            bufferedWriter.write("o Mesh02"); ///1
            bufferedWriter.newLine();
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
            bufferedWriter.newLine();*/
           /* functions.legsquare(bufferedWriter,cx1,0,cz1,r,r);
            functions.legsquare(bufferedWriter,cx2,0,cz2,r,r);
            functions.legsquare(bufferedWriter,cx3,0,cz3,r,r);
            functions.legsquare(bufferedWriter,cx4,0,cz4,r,r);
            functions.legsquare(bufferedWriter,cx1,-y/1.2,cz1,r,r);
            functions.legsquare(bufferedWriter,cx2,-y/1.2,cz2,r,r);
            functions.legsquare(bufferedWriter,cx3,-y/1.2,cz3,r,r);
            functions.legsquare(bufferedWriter,cx4,-y/1.2,cz4,r,r);
            bufferedWriter.write("f "+(n+12)+"/160/3 "+176+"/176/3 "+171+"/171/3 "+155+"/155/3");
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
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
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




            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    /**
     * side faces are drawn here*/
    public void sideface(BufferedWriter bufferedWriter,int p1 ,int n1,int p2,int n2){
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
    public double translate(double t1,double t2){
        double value=t1-t2;
        return value;
    }

    //tex coordinate
}
