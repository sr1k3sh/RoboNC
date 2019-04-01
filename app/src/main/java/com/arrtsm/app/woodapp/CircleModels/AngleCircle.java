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

public class AngleCircle {
    double r;
    double i,j;
    int in;
    List list= new ArrayList();
    List list2=new ArrayList();
    BufferedWriter bufferedWriter;
    Functions functions = new Functions();
    CircleFunctions cf = new CircleFunctions();
    public AngleCircle(double radius,double thickness,double legheight){
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

          /*  bufferedWriter.write("usemtl test1\n" );
            bufferedWriter.newLine();*/
            bufferedWriter.write("v "+functions.translate(x,x)+" 0 "+functions.translate(z,z));
            bufferedWriter.newLine();
            cf.circle(bufferedWriter,functions.translate(x,x),0,functions.translate(z,z),r+y/2);
            bufferedWriter.write("v "+functions.translate(x,x)+" "+y+" "+functions.translate(z,z));
            bufferedWriter.newLine();
            cf.circle(bufferedWriter,functions.translate(x,x),y,functions.translate(z,z),r);
            functions.normalvector(bufferedWriter);
            bufferedWriter.write("usemtl test1\n" );
            bufferedWriter.newLine();
            cf.circleface(bufferedWriter,1,4,false);
            cf.circleface(bufferedWriter,75,3,true);
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
            bufferedWriter.write("vt "+x/(2*x)+" "+z/(2*z));
            bufferedWriter.newLine();
            cf.cirtex(bufferedWriter,r,r,r);
            bufferedWriter.write("o Mesh02"); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("usemtl test2\n" );
            bufferedWriter.newLine();
            int n=148;
            cf.leg1(bufferedWriter,x,y,z,n,leg,r);
            /*double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
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
            bufferedWriter.write("f "+(88+72)+"//3 "+(104+72)+"//3 "+(99+72)+"//3 "+(83+72)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(89+72)+"//3 "+(105+72)+"//3 "+(104+72)+"//3 "+(88+72)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(89+72)+"//3 "+(94+72)+"//3 "+(110+72)+"//3 "+(105+72)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(94+72)+"//3 "+(83+72)+"//3 "+(99+72)+"//3 "+(110+72)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(98+72)+"//4 "+(99+72)+"//4 "+(104+72)+"//4 "+(101+72)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(103+72)+"//4 "+(104+72)+"//4 "+(105+72)+"//4 "+(106+72)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(108+72)+"//4 "+(105+72)+"//4 "+(110+72)+"//4 "+(111+72)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(109+72)+"//4 "+(110+72)+"//4 "+(99+72)+"//4 "+(100+72)+"//4");
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
            cf.legvt(bufferedWriter,cx4,-1,cz4,r,r);
            cf.cirtex(bufferedWriter,r,r,r);*/
            ///tex coordinates


            int n1=940+1;
            bufferedWriter.write("o Mesh01"); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("usemtl test3\n" );
            bufferedWriter.newLine();
            cf.circle(bufferedWriter,functions.translate(x,x),y/2,functions.translate(z,z),r+y/2);
            cf.cirtex(bufferedWriter,r,r,r);
            cf.bface(bufferedWriter,2,n1,3,false);
            cf.bface(bufferedWriter,76,n1,3,true);


            bufferedWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    //tex coordinate



}
