package com.arrtsm.app.woodapp.CircleModels;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;

public class CircleFunctions {
    double r;
    public void circle(BufferedWriter bufferedWriter,double x,double y,double z,double radius){
        int deg=0;
        double yt=y;
        double i,j;
        double r=radius;
        while (deg<=360){
            i= r*Math.sin(Math.toRadians(deg))+x;
            j= r*Math.cos(Math.toRadians(deg))+z;
            deg=deg+5;
            try {
                Log.d("testing", "TestBox: thir56d");
                bufferedWriter.write("v"+" "+i+" "+yt+" "+j);
                bufferedWriter.newLine();
                Log.d("testing", "TestBox: third");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void circleface(BufferedWriter bufferedWriter,int i ,int face,boolean clock){

        int in=i+1;
        int n=i+360/5;
        try {
            while (in <= n) {
                if(clock){
                    bufferedWriter.write("f "+i+"/"+i+"/"+face+" " + in + "/"+in+"/"+face+" " + (in+1) + "/"+(in+1)+"/"+face);
                    bufferedWriter.newLine();
                }
                else{
                    bufferedWriter.write("f "+i+"/"+i+"/"+face+" " + (in+1) + "/"+(in+1)+"/"+face+" " + in + "/"+in+"/"+face);
                    bufferedWriter.newLine();
                }
                in++;

            }
            if(clock){
                bufferedWriter.write("f "+i+"/"+i+"/"+face+" " + (n + 1) + "/"+(n+1)+"/"+face+" " + (i+1) + "/"+(i+1)+"/"+face);
                bufferedWriter.newLine();
            }
            else{
                bufferedWriter.write("f "+i+"/"+i+"/"+face+" " + (i + 1) + "/"+(i+1)+"/"+face+" " + (n+1) + "/"+(n+1)+"/"+face);
                bufferedWriter.newLine();
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void bface(BufferedWriter bufferedWriter,int count1, int count2,int face,boolean clock){
        int f=face;

        boolean c=clock;
        int q1=count1,r1=q1+1;
        int q2=count2,r2=q2+1;
        while(q1<count1+72&&q2<count2+72){
            try {
                if(c){
                    // f=3;
                    bufferedWriter.write("f "+q1+"/"+q1+"/"+f+" "+q2+"/"+q2+"/"+f+" "+r2+"/"+r2+"/"+f+" "+r1+"/"+r1+"/"+f);
                    bufferedWriter.newLine();
                }
                else{
                    //f=3;
                    bufferedWriter.write("f "+q1+"/"+q1+"/"+f+" "+r1+"/"+r1+"/"+f+" "+r2+"/"+r2+"/"+f+" "+q2+"/"+q2+"/"+f);
                    bufferedWriter.newLine();

                }


                q1++;
                r1++;
                q2++;
                r2++;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void cirtex(BufferedWriter bufferedWriter,double x,double z,double radius){
        int deg=0;
        double i,j;
        double r=radius;
        while (deg<=360){
            i= Math.abs((r*Math.sin(Math.toRadians(deg))+x)/(2*x));
            j= Math.abs((r*Math.cos(Math.toRadians(deg))+z)/(2*x));
            deg=deg+5;
            try {
                Log.d("testing", "TestBox: thir56d");
                bufferedWriter.append("vt"+" "+i+" "+j);
                bufferedWriter.newLine();
                Log.d("testing", "TestBox: third");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void vt(BufferedWriter bufferedWriter,double r){
        try{
            double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
            cx1=r/2;
            cz1=r/2;
            bufferedWriter.write("vt "+cx1/4+" "+cz1/4);
            bufferedWriter.newLine();
            cx2=-r/2;
            cz2=r/2;
            bufferedWriter.write("vt "+cx2/4+" "+cz2/4);
            bufferedWriter.newLine();
            cx3=-r/2;
            cz3=-r/2;
            bufferedWriter.write("vt "+cx3/4+" "+cz3/4);
            bufferedWriter.newLine();
            cx4=r/2;
            cz4=-r/2;
            bufferedWriter.write("vt "+cx4/4+" "+cz4/4);
            bufferedWriter.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void legvt(BufferedWriter bufferedWriter, double cx1, double cy1, double cz1, double x, double z){
        //top-bottom point for square
        double ctz1,cbz1;
        try {
            ctz1 = cz1 - 4 * z / 100;
            cbz1 = cz1 + 4 * z / 100;
            ////points
            double px1, pz1, px2, pz2, px3, pz3, px4, pz4;
            px1 = cx1 - 4 * x /100 ;
            pz1 = ctz1;
            bufferedWriter.write("vt " + px1/4 + " " + pz1/4);
            bufferedWriter.newLine();
            px2 = cx1 + 4 * x / 100 ;
            pz2 = ctz1;
            bufferedWriter.write("vt " + px2/4 + " " + pz2/4);
            bufferedWriter.newLine();
            px4 = cx1 + 4 * x / 100;
            pz4 = cbz1;
            bufferedWriter.write("vt " + px4/4 + " " + pz4/4);
            bufferedWriter.newLine();
            px3 = cx1 - 4 * x / 100;
            pz3 = cbz1;
            bufferedWriter.write("vt " + px3/4 + " " + pz3/4);
            bufferedWriter.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void legface(BufferedWriter bufferedWriter,int q,int r,int face){
        int a1=q,a2=a1+1,n1=a1+4;
        int b1=r,b2=b1+1,n2=b1+4;
        int f = face;
        try{
            while(a1<n1-1&&b1<n2-1) {
                bufferedWriter.write("f " + a1 + "/"+a1+"/"+f+" " + a2 + "/"+a2+"/"+f+" " + b2 + "/"+b2+"/"+f+" " + b1+"/"+b1+"/"+f);
                bufferedWriter.newLine();
                a1++;
                a2++;
                b2++;
                b1++;

                //bufferedWriter.write("f " n);
            }
            bufferedWriter.write("f " + a1 + "/"+a1+"/"+f+" " + q + "/"+q+"/"+f+" " + r + "/"+r+"/"+f+" " + b1+"/"+b1+"/"+f);
            bufferedWriter.newLine();
            bufferedWriter.write("f "+r+"/"+r+"/4 "+(r+1)+"/"+(r+1)+"/4 "+(r+2)+"/"+(r+2)+"/4 "+(r+3)+"/"+(r+3)+"/4");
            bufferedWriter.newLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }



    /// circular leg functions
    public void leg1(BufferedWriter bufferedWriter, double x, double y, double z, int inital,double legheight,double radius){
        double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
        r=radius;
        try {

            int no=inital+5;
            //double cx1,cz1,cx2,cz2,cx3,cz3,cx4,cz4;
            cx1=-r/2;
            cz1=-r/2;

            bufferedWriter.write("v "+cx1+" 0 "+cz1);
            bufferedWriter.newLine();
            bufferedWriter.write("vt "+cx1+" "+cz1);
            bufferedWriter.newLine();
            cx2=-r/2;
            cz2=r/2;
            bufferedWriter.write("v "+cx2+" 0 "+cz2);
            bufferedWriter.newLine();
            bufferedWriter.write("vt "+cx2+" "+cz2);
            bufferedWriter.newLine();
            cx3=+r/2;
            cz3=-r/2;
            bufferedWriter.write("v "+cx3+" 0 "+cz3);
            bufferedWriter.newLine();
            bufferedWriter.write("vt "+cx3+" "+cz3);
            bufferedWriter.newLine();
            cx4=r/2;
            cz4=r/2;
            bufferedWriter.write("v "+cx4+" 0 "+cz4);
            bufferedWriter.newLine();
            bufferedWriter.write("vt "+cx4+" "+cz4);
            bufferedWriter.newLine();
            //left-top
            legsquare1(bufferedWriter, cx1, 0, cz1, x, z);
            //left-buttom
            legsquare1(bufferedWriter, cx2, 0, cz2, x, z);
            //right-top
            legsquare1(bufferedWriter, cx3, 0, cz3, x, z);
            //right-bottom
            legsquare1(bufferedWriter, cx4, 0, cz4, x, z);
            //left-top
            legsquare1(bufferedWriter, cx1, -0, cz1, x, z);
            //left-buttom
            legsquare1(bufferedWriter, cx2, -0, cz2, x, z);
            //right-top
            legsquare1(bufferedWriter, cx3, -0, cz3, x, z);
            //right-bottom
            legsquare1(bufferedWriter, cx4, -0, cz4, x, z);
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
            legsquare1(bufferedWriter, cx1, -0.03, cz1, x, z);
            legface1(bufferedWriter, no+16, no+32, 3);

            //left-buttom
            legsquare1(bufferedWriter, cx2, -0.03, cz2, x, z);
            legface1(bufferedWriter, no+20, no+36, 3);
            //right-top
            legsquare1(bufferedWriter, cx3, -0.03, cz3, x, z);
            legface1(bufferedWriter, no+24, no+40, 3);
            //right-bottom
            legsquare1(bufferedWriter, cx4, -0.03, cz4, x, z);
            legface1(bufferedWriter, no+28, no+44, 3);
            circle1(bufferedWriter,cx1,-0.03,cz1,4 * z / 70);
            circle1(bufferedWriter,cx1,-0.035,cz1,4 * z / 90);
            circle1(bufferedWriter,cx1,-y-y,cz1,4 * z / 110);
            circle1(bufferedWriter,cx1,-5,cz1,4 * z / 160);
            // length can be varied here
            ///example circle(bufferedWriter,cx1,-9,cz1,4 * z / 230);
            /// above change -legheight to any -ve integer
            circle1(bufferedWriter,cx1,-legheight,cz1,4 * z / 180);
            clegface1(bufferedWriter,no+48);
            clegface1(bufferedWriter,no+48+36+1);
            clegface1(bufferedWriter,no+48+72+2);
            clegface1(bufferedWriter,no+48+72+2+36+1);
            circle1(bufferedWriter,cx2,-0.03,cz2,4 * z / 70);
            circle1(bufferedWriter,cx2,-0.035,cz2,4 * z / 90);
            circle1(bufferedWriter,cx2,-y-y,cz2,4 * z / 110);
            circle1(bufferedWriter,cx2,-5,cz2,4 * z / 160);
            // length can be varied here
            ///example circle(bufferedWriter,cx1,-9,cz1,4 * z / 230);
            /// above change -legheight to any -ve integer
            circle1(bufferedWriter,cx2,-legheight,cz2,4 * z / 180);
            clegface1(bufferedWriter,no+233);
            clegface1(bufferedWriter,no+233+36+1);
            clegface1(bufferedWriter,no+233+72+2);
            clegface1(bufferedWriter,no+233+72+2+36+1);
            circle1(bufferedWriter,cx3,-0.03,cz3,4 * z / 70);
            circle1(bufferedWriter,cx3,-0.035,cz3,4 * z / 90);
            circle1(bufferedWriter,cx3,-y-y,cz3,4 * z / 110);
            circle1(bufferedWriter,cx3,-5,cz3,4 * z / 160);
            // length can be varied here
            ///example circle(bufferedWriter,cx1,-9,cz1,4 * z / 230);
            /// above change -legheight to any -ve integer
            circle1(bufferedWriter,cx3,-legheight,cz3,4 * z / 180);
            clegface1(bufferedWriter,no+418);
            clegface1(bufferedWriter,no+418+36+1);
            clegface1(bufferedWriter,no+418+72+2);
            clegface1(bufferedWriter,no+418+72+2+36+1);
            circle1(bufferedWriter,cx4,-0.03,cz4,4 * z / 70);
            circle1(bufferedWriter,cx4,-0.035,cz4,4 * z / 90);
            circle1(bufferedWriter,cx4,-y-y,cz4,4 * z / 110);
            circle1(bufferedWriter,cx4,-5,cz4,4 * z / 160);
            // length can be varied here
            ///example circle(bufferedWriter,cx1,-9,cz1,4 * z / 230);
            /// above change -legheight to any -ve integer
            circle1(bufferedWriter,cx4,-legheight,cz4,4 * z / 180);
            clegface1(bufferedWriter,no+418+185);
            clegface1(bufferedWriter,no+418+185+36+1);
            clegface1(bufferedWriter,no+418+185+72+2);
            clegface1(bufferedWriter,no+418+185+72+2+36+1);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }
    public void clegface1(BufferedWriter bufferedWriter, int fnum){
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
    public void legsquare1(BufferedWriter bufferedWriter, double cx1, double cy1, double cz1, double x, double z){
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
    public void legface1(BufferedWriter bufferedWriter, int q, int r, int face){
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
    public void circle1(BufferedWriter bufferedWriter, double h, double y, double k, double radius){
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
