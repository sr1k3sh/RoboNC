package com.arrtsm.app.woodapp.Models;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TestModel {
    double x,y,z,r,h,k,h1,k1,ht,kt,h1t,k1t;
    BufferedWriter bufferedWriter;
    double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
    int in=0;
    Functions functions= new Functions();
    public TestModel(float length , float breadth , float height){
        x=length;
        y=height;
        z=breadth;
        r=z/2;
        h=functions.translate(x,x/2);
        k=functions.translate(z/2,z/2);
        ht=x;
        kt=z/2;
        //center point


        h1=functions.translate(0,x/2);
        k1=functions.translate(z/2,z/2);
        h1t=0;
        k1t=z/2;
        String fileName="model.obj";
        FileOutputStream fos = null;
        try{
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
            Log.d("testing", "Box: "+myFile);
            FileWriter fileWriter = new FileWriter(myFile);
            Log.d("testing", "Box: "+fileWriter);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("mtllib boxtest.mtl"); ///1
            bufferedWriter.newLine();
            /// starts bottom view vertices
            bufferedWriter.write("o Mesh01"); ///1
            bufferedWriter.newLine();
            /// starts buttom box
            functions.topsquare(bufferedWriter,x,0,z);
            //right side semi circle
            functions.semicircleright(bufferedWriter,h,k,0,r);
            /* */
            bufferedWriter.write("v"+" "+h+" "+0+" "+k); ///14
            bufferedWriter.newLine();
            //left side semi circle algorithm
            functions.semicircleleft(bufferedWriter,h1,k1,0,r);

            bufferedWriter.write("v"+" "+h1+" "+0+" "+k1);
            bufferedWriter.newLine();
            /* stops bottom view vertices */
            // starts top view vertices
            //right side semi circle
            functions.topsquare(bufferedWriter,x,y,z);
            functions.semicircleright(bufferedWriter,h,k,y,r);
            bufferedWriter.write("v"+" "+h+" "+y+" "+k); ///14
            bufferedWriter.newLine();
            //left side semi circle algorithm
            functions.semicircleleft(bufferedWriter,h1,k1,y,r);
            bufferedWriter.write("v"+" "+h1+" "+y+" "+k1);
            bufferedWriter.newLine();
            // stops top view vertices
            functions.normalvector(bufferedWriter);
/////// tex coordinates
            ///bottom view texture
            double texx=x+(x/2),texz=z;
            bufferedWriter.write("vt"+" "+0+" "+0); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+0+" "+z/texz); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+x/texx+" "+z/texz); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+x/texx+" "+0); ///13
            bufferedWriter.newLine();
            //right side semi circle texture coordinates

            functions.Rtexturecoordinates(bufferedWriter,ht,kt,r,texx,texz);
            bufferedWriter.write("vt"+" "+ht/texx+" "+kt/texz); ///14
            bufferedWriter.newLine();
            //left side semi circle texture coordinates
            functions.Ltexturecoordinates(bufferedWriter,h1t,k1t,r,texx,texz);
            bufferedWriter.write("vt"+" "+h1t/texx+" "+k1t/texz);
            bufferedWriter.newLine();
            ///top view texture
            bufferedWriter.write("vt"+" "+0+" "+0); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+0+" "+z/texz); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+x/texx+" "+z/texz); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("vt"+" "+x/texx+" "+0); ///13
            bufferedWriter.newLine();
            //right side semi circle texture coordinates
            functions.Rtexturecoordinates(bufferedWriter,ht,kt,r,texx,texz);
            bufferedWriter.write("vt"+" "+ht/texx+" "+kt/texz); ///14
            bufferedWriter.newLine();
            //left side semi circle texture coordinates
            functions.Ltexturecoordinates(bufferedWriter,h1t,k1t,r,texx,texz);
            bufferedWriter.write("vt"+" "+h1t/texx+" "+k1t/texz);
            bufferedWriter.newLine();
            ////// tex cooordinates
            /////faces
            bufferedWriter.write("usemtl Material.001\n" );
            bufferedWriter.newLine();
            bufferedWriter.write("s 1");
            bufferedWriter.newLine();
            bufferedWriter.write("f 1/1/4 4/4/4 3/3/4 2/2/4");
            bufferedWriter.newLine();
            //left side face q=centerpoint=5,n=totalnumbertotheend=24
            functions.threefaceview(bufferedWriter,5,24,4,false);
            //right side face q=centerpoint=25,n=totalnumbertotheend=44 +++19 add to each q to get n
            functions.threefaceview(bufferedWriter,25,44,4,false);
            bufferedWriter.write("f 45/45/3 46/46/3 47/47/3 48/48/3");
            bufferedWriter.newLine();
            functions.threefaceview(bufferedWriter,49,68,3,true);
            functions.threefaceview(bufferedWriter,69,88,3,true);

            bufferedWriter.write("f 1/1/3 45/45/3 48/48/3 4/4/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 2/3/3 3/3/3 47/47/3 46/46/3");
            bufferedWriter.newLine();
            functions.sideface(bufferedWriter,5,24,49,68);
            functions.sideface(bufferedWriter,25,55,69,88);
            bufferedWriter.write("o Mesh02"); ///1
            bufferedWriter.newLine();
            ///starting legs draw from here
            leg(bufferedWriter,x,y,z,93);
            ////////
            bufferedWriter.close();
        }

        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public double translate(double t1,double t2){
        double value=t1-t2;
        return value;
    }
    public void leg(BufferedWriter bufferedWriter, double x, double y, double z, int inital){
        double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;

        try {
            int no=inital;
            cx1=translate(x-x,x/2);
            cz1=translate(z/4,z/2);
            bufferedWriter.write("v " + cx1 + " " + 0 + " " + cz1);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + cx1 + " " + cz1);
            bufferedWriter.newLine();
            cx2 = translate(x - x, x / 2);
            cz2 = translate(3 * z / 4, z / 2);
            bufferedWriter.write("v " + cx2 + " " + 0 + " " + cz2);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + cx2 + " " + cz2);
            bufferedWriter.newLine();
            cx3 = translate(x, x / 2);
            cz3 = translate(z / 4, z / 2);
            bufferedWriter.write("v " + cx3 + " " + 0 + " " + cz3);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + cx3 + " " + cz3);
            bufferedWriter.newLine();
            cx4 = translate(x, x / 2);
            cz4 = translate(3 * z / 4, z / 2);
            bufferedWriter.write("v " + cx4 + " " + 0 + " " + cz4);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + cx4 + " " + cz4);
            bufferedWriter.newLine();
            //left-top
            legsquare(bufferedWriter, cx1, 0, cz1, x, z);
            //left-buttom
            legsquare(bufferedWriter, cx2, 0, cz2, x, z);
            //right-top
            legsquare(bufferedWriter, cx3, 0, cz3, x, z);
            //right-bottom
            legsquare(bufferedWriter, cx4, 0, cz4, x, z);
            //left-top
            legsquare(bufferedWriter, cx1, -0, cz1, x, z);
            //left-buttom
            legsquare(bufferedWriter, cx2, -0, cz2, x, z);
            //right-top
            legsquare(bufferedWriter, cx3, -0, cz3, x, z);
            //right-bottom
            legsquare(bufferedWriter, cx4, -0, cz4, x, z);
            bufferedWriter.write("f "+(no+16)+"//4 "+(no+25)+"//4 "+(no+26)+"//4 "+(no+19)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+20)+"//4 "+(no+29)+"//4 "+(no+30)+"//4 "+(no+23)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+18)+"//4 "+(no+21)+"//4 "+(no+20)+"//4 "+(no+19)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+26)+"//4 "+(no+29)+"//4 "+(no+28)+"//4 "+(no+27)+"//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+7)+"//3 "+(no+23)+"//3 "+(no+30)+"//3 "+(no+14)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+14)+"//3 "+(no+30)+"//3 "+(no+25)+"//3 "+(no+9)+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+(no+9)+"//3 "+(no+25)+"//3 "+(no+16)+"//3 "+no+"//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f "+no+"//3 "+(no+16)+"//3 "+(no+23)+"//3 "+(no+7)+"//3");
            bufferedWriter.newLine();
            //left-top
            legsquare(bufferedWriter, cx1, -0.03, cz1, x, z);
            legface(bufferedWriter, no+16, no+32, 3);

            //left-buttom
            legsquare(bufferedWriter, cx2, -0.03, cz2, x, z);
            legface(bufferedWriter, no+20, no+36, 3);
            //right-top
            legsquare(bufferedWriter, cx3, -0.03, cz3, x, z);
            legface(bufferedWriter, no+24, no+40, 3);
            //right-bottom
            legsquare(bufferedWriter, cx4, -0.03, cz4, x, z);
            legface(bufferedWriter, no+28, no+44, 3);
            circle(bufferedWriter,cx1,-0.03,cz1,4 * z / 100);
            circle(bufferedWriter,cx1,-0.035,cz1,4 * z / 120);
            circle(bufferedWriter,cx1,-y-y,cz1,4 * z / 150);
            circle(bufferedWriter,cx1,-2,cz1,4 * z / 200);
            circle(bufferedWriter,cx1,-3.4,cz1,4 * z / 230);
            clegface(bufferedWriter,no+48);
            clegface(bufferedWriter,no+48+36+1);
            clegface(bufferedWriter,no+48+72+2);
            clegface(bufferedWriter,no+48+72+2+36+1);
            circle(bufferedWriter,cx2,-0.03,cz2,4 * z / 100);
            circle(bufferedWriter,cx2,-0.035,cz2,4 * z / 120);
            circle(bufferedWriter,cx2,-y-y,cz2,4 * z / 150);
            circle(bufferedWriter,cx2,-2,cz2,4 * z / 200);
            circle(bufferedWriter,cx2,-3.4,cz2,4 * z / 230);
            clegface(bufferedWriter,no+233);
            clegface(bufferedWriter,no+233+36+1);
            clegface(bufferedWriter,no+233+72+2);
            clegface(bufferedWriter,no+233+72+2+36+1);
            circle(bufferedWriter,cx3,-0.03,cz3,4 * z / 100);
            circle(bufferedWriter,cx3,-0.035,cz3,4 * z / 120);
            circle(bufferedWriter,cx3,-y-y,cz3,4 * z / 150);
            circle(bufferedWriter,cx3,-2,cz3,4 * z / 200);
            circle(bufferedWriter,cx3,-3.4,cz3,4 * z / 230);
            clegface(bufferedWriter,no+418);
            clegface(bufferedWriter,no+418+36+1);
            clegface(bufferedWriter,no+418+72+2);
            clegface(bufferedWriter,no+418+72+2+36+1);
            circle(bufferedWriter,cx4,-0.03,cz4,4 * z / 100);
            circle(bufferedWriter,cx4,-0.035,cz4,4 * z / 120);
            circle(bufferedWriter,cx4,-y-y,cz4,4 * z / 150);
            circle(bufferedWriter,cx4,-2,cz4,4 * z / 200);
            circle(bufferedWriter,cx4,-3.4,cz4,4 * z / 230);
            clegface(bufferedWriter,no+418+185);
            clegface(bufferedWriter,no+418+185+36+1);
            clegface(bufferedWriter,no+418+185+72+2);
            clegface(bufferedWriter,no+418+185+72+2+36+1);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }
    public void clegface(BufferedWriter bufferedWriter, int fnum){
        int n1=fnum,t1=n1+36;
        int n2=t1+1,t2=n2+36;
        try
        {
           /* bufferedWriter.write("f "+(t1)+"//3 "+fnum+"//3 "+n2+"//3 "+t2+"//3" );
            bufferedWriter.newLine();*/
            while(n1<(t1)&&n2<(t2)){
                bufferedWriter.write("f "+n1+"/"+n1+"/3 "+(n1+1)+"/"+(n1+1)+"/3 "+(n2+1)+"/"+(n2+1)+"/3 "+n2+"/"+n2+"/3");
                bufferedWriter.newLine();
                n1++;
                n2++;
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void legsquare(BufferedWriter bufferedWriter, double cx1, double cy1, double cz1, double x, double z){
        //top-bottom point for square
        double ctz1,cbz1;
        try {
            ctz1 = cz1 - 4 * z / 100;
            cbz1 = cz1 + 4 * z / 100;
            ////points
            double px1, pz1, px2, pz2, px3, pz3, px4, pz4;
            px1 = cx1 - 4 * z /100 ;
            pz1 = ctz1;
            bufferedWriter.write("v " + px1 + " " + cy1 + " " + pz1);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + px1 + " " + pz1);
            bufferedWriter.newLine();
            px2 = cx1 + 4 * z / 100 ;
            pz2 = ctz1;
            bufferedWriter.write("v " + px2 + " " + cy1 + " " + pz2);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + px2 + " " + pz2);
            bufferedWriter.newLine();
            px4 = cx1 + 4 * z / 100;
            pz4 = cbz1;
            bufferedWriter.write("v " + px4 + " " + cy1+ " " + pz4);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + px4 + " " + pz4);
            bufferedWriter.newLine();
            px3 = cx1 - 4 * z / 100;
            pz3 = cbz1;
            bufferedWriter.write("v " + px3 + " " + cy1 + " " + pz3);
            bufferedWriter.newLine();
            bufferedWriter.write("vt " + px3 + " " + pz3);
            bufferedWriter.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void legface(BufferedWriter bufferedWriter, int q, int r, int face){
        int a1=q,a2=a1+1,n1=a1+4;
        int b1=r,b2=b1+1,n2=b1+4;
        int f = face;
        try{
            while(a1<n1-1&&b1<n2-1) {
                bufferedWriter.write("f " + a1 + "/"+ a1 +"/"+f+" " + a2 + "/"+ a2 +"/"+f+" " + b2 + "/"+ b2 +"/"+f+" " + b1+"/"+ b1+"/"+f);
                bufferedWriter.newLine();
                a1++;
                a2++;
                b2++;
                b1++;

                //bufferedWriter.write("f " n);
            }
            bufferedWriter.write("f " + a1 + "/"+ a1 +"/"+f+" " + q + "/"+ q +"/"+f+" " + r + "/"+ r +"/"+f+" " + b1+"/"+ b1+"/"+f);
            bufferedWriter.newLine();
            bufferedWriter.write("f "+r+"/"+r+"/4 "+(r+1)+"/"+(r+1)+"/4 "+(r+2)+"/"+(r+2)+"/4 "+(r+3)+"/"+(r+3)+"/4");
            bufferedWriter.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void circle(BufferedWriter bufferedWriter, double h, double y, double k, double radius){
        double t1,t2;
        double deg=0;
        double r=radius;
        try{
            while(deg<=360){
                t1= r* Math.cos(Math.toRadians(deg))+h;
                t2= r* Math.sin(Math.toRadians(deg))+k;
                bufferedWriter.write("v "+t1+" "+y+" "+t2);
                bufferedWriter.newLine();
                bufferedWriter.write("vt "+t1+" "+t2);
                bufferedWriter.newLine();
                deg=deg+10;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
