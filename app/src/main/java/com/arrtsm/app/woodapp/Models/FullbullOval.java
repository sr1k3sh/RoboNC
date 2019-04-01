package com.arrtsm.app.woodapp.Models;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FullbullOval {
    double x, y, z, i, j, r, h, k, h1, k1, ht, kt, h1t, k1t;
    double i1, j1, i2, j2, i1t, j1t, i2t, j2t, i5, j5, i6, j6, i7, j7, i8, j8, i9, j9;
    double L, B;
    List list = new ArrayList();
    List list2 = new ArrayList();
    BufferedWriter bufferedWriter;
    int in = 0;
    Functions functions = new Functions();

    public FullbullOval(float length, float breadth, float height) {
        x = length;
        y = height;
        z = breadth;
        r = z / 2;
        h = functions.translate(x, x / 2);
        k = functions.translate(z / 2, z / 2);
        ht = x;
        kt = z / 2;
        h1 = functions.translate(0, x / 2);
        k1 = functions.translate(z / 2, z / 2);
        h1t = 0;
        k1t = z / 2;
        String fileName = "model.obj";
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
            bufferedWriter.write("mtllib boxtest.mtl"); ///1
            bufferedWriter.newLine();
            /// starts bottom view vertices
            bufferedWriter.write("o Mesh01"); ///1
            bufferedWriter.newLine();
            /// starts buttom box
            functions.topsquare(bufferedWriter, x, 0, z);
            //translate(x,0,z);
            //right side semi circle
            functions.semicircleright(bufferedWriter, h, k, 0, r);
           /* */
            bufferedWriter.write("v" + " " + h + " " + 0 + " " + k); ///14
            bufferedWriter.newLine();
            //left side semi circle algorithm
            functions.semicircleleft(bufferedWriter, h1, k1, 0, r);

            bufferedWriter.write("v" + " " + h1 + " " + 0 + " " + k1);
            bufferedWriter.newLine();
            /* stops bottom view vertices */
            // starts top view vertices
            //right side semi circle
            functions.topsquare(bufferedWriter, x, y, z);
            functions.semicircleright(bufferedWriter, h, k, y, r);
            bufferedWriter.write("v" + " " + h + " " + y + " " + k); ///14
            bufferedWriter.newLine();
            //left side semi circle algorithm
            functions.semicircleleft(bufferedWriter, h1, k1, y, r);
            bufferedWriter.write("v" + " " + h1 + " " + y + " " + k1);
            bufferedWriter.newLine();
            // stops top view vertices

            functions.normalvector(bufferedWriter);
/////// tex coordinates
            ///bottom view texture
            double texx = x + 1, texz = z;
            bufferedWriter.write("vt" + " " + 0 + " " + 0); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + 0 + " " + z / texz); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + x / texx + " " + z / texz); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + x / texx + " " + 0); ///13
            bufferedWriter.newLine();
            //right side semi circle texture coordinates

            functions.Rtexturecoordinates(bufferedWriter, ht, kt, r, texx, texz);
            bufferedWriter.write("vt" + " " + ht / texx + " " + kt / texz); ///14
            bufferedWriter.newLine();
            //left side semi circle texture coordinates
            functions.Ltexturecoordinates(bufferedWriter, h1t, k1t, r, texx, texz);
            bufferedWriter.write("vt" + " " + h1t / texx + " " + k1t / texz);
            bufferedWriter.newLine();
            ///top view texture
            bufferedWriter.write("vt" + " " + 0 + " " + 0); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + 0 + " " + z / texz); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + x / texx + " " + z / texz); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("vt" + " " + x / texx + " " + 0); ///13
            bufferedWriter.newLine();
            //right side semi circle texture coordinates
            functions.Rtexturecoordinates(bufferedWriter, ht, kt, r, texx, texz);
            bufferedWriter.write("vt" + " " + ht / texx + " " + kt / texz); ///14
            bufferedWriter.newLine();
            //left side semi circle texture coordinates
            functions.Ltexturecoordinates(bufferedWriter, h1t, k1t, r, texx, texz);
            bufferedWriter.write("vt" + " " + h1t / texx + " " + k1t / texz);
            bufferedWriter.newLine();

            ////// tex cooordinates
            bufferedWriter.write("usemtl Material.001\n");
            bufferedWriter.newLine();
            bufferedWriter.write("s 1");
            bufferedWriter.newLine();
            bufferedWriter.write("f 1/1/4 4/4/4 3/3/4 2/2/4");
            bufferedWriter.newLine();
            //left side face q=centerpoint=5,n=totalnumbertotheend=24
            functions.face(bufferedWriter, 5, 24, 4, false);
            //right side face q=centerpoint=25,n=totalnumbertotheend=44 +++19 add to each q to get n
            functions.face(bufferedWriter, 25, 44, 4, false);

            bufferedWriter.write("f 45/45/3 46/46/3 47/47/3 48/48/3");
            bufferedWriter.newLine();
            functions.face(bufferedWriter, 49, 68, 3, true);
            functions.face(bufferedWriter, 69, 88, 3, true);

            bufferedWriter.write("f 1/1/3 45/45/3 48/48/3 4/4/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 2/2/3 3/3/3 47/47/3 46/46/3");
            bufferedWriter.newLine();
            //sideface(5,24,49,68);
            // sideface(25,55,69,88);
////////
            ////to draw legs
            functions.leg(bufferedWriter, x, y, z);
            /// to draw bullnose
            /// find center point of y axis

            //thikness point
            bullnose(functions.translate(0, x / 2), y, z / 2, false);
            bullnose(functions.translate(0, x / 2), y, -z / 2, true);
            bullnose(functions.translate(x, x / 2), y, z / 2, false);
            bullnose(functions.translate(x, x / 2), y, -z / 2, true);
            rightsidebullnose(x / 2, z);
            functions.bface(bufferedWriter, 141, 179, 3, true);
            bface(160, 198, false);
            bface(49, 218, true);
            int test = 218, ntest = test + 320;
            while (test <= ntest) {
                bface(test, test + 20, true);
                test = test + 20;
            }
            leftsidebullnose(-x / 2, 0);
            int test1 = 578, ntest1 = test1 + 320;
            while (test1 <= ntest1) {
                bface(test1, test1 + 20, true);
                test1 = test1 + 20;
            }
            bface(25, 918, false);
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void bface(int count1, int count2, boolean clock) {
        int f;
        int tempi = (count1 + 9);
        int tempj = (count2 + 9);
        boolean c = clock;
        int q1 = count1, r1 = q1 + 1;
        int q2 = count2, r2 = q2 + 1;
        while (q1 < count1 + 18 && q2 < count2 + 18) {
            try {
                if (c) {
                    f = 3;
                    bufferedWriter.write("f " + q1 + "//" + f + " " + q2 + "//" + f + " " + r2 + "//" + f + " " + r1 + "//" + f);
                    bufferedWriter.newLine();
                    /*if(q1<tempi&&q2<tempj){
                        f=3;
                        bufferedWriter.write("f "+q1+"//"+f+" "+q2+"//"+f+" "+r2+"//"+f+" "+r1+"//"+f);
                        bufferedWriter.newLine();
                    }
                    else{
                        f=4;
                        bufferedWriter.write("f "+q1+"//"+f+" "+q2+"//"+f+" "+r2+"//"+f+" "+r1+"//"+f);
                        bufferedWriter.newLine();
                    }*/


                } else {
                    f = 3;
                    bufferedWriter.write("f " + q1 + "//" + f + " " + r1 + "//" + f + " " + r2 + "//" + f + " " + q2 + "//" + f);
                    bufferedWriter.newLine();

                }


                q1++;
                r1++;
                q2++;
                r2++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ////coordinates

    public void bullnose(double x, double y, double z, boolean direction) {
        double t1 = 0, t2;
        r = y / 2;
        h = y / 2;
        k = z;
        int deg = 0;
        while (deg <= 180) {
            t1 = r * Math.cos(Math.toRadians(deg)) + h;
            t2 = r * Math.sin(Math.toRadians(deg)) - k;
            list2.add(t2);
            if (direction == true) {
                t2 = r * Math.sin(Math.toRadians(deg)) - k;
            } else {
                t2 = -r * Math.sin(Math.toRadians(deg)) - k;
            }
            list.add(t1);


            deg = deg + 10;
            try {
                bufferedWriter.write("v" + " " + x + " " + t1 + " " + t2);
                bufferedWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rightsidebullnose(double xt, double zt) {
        int count = 0;
        while (in < list.size() && in < list2.size()) {
            Log.d("testing", "test: " + list.get(in) + " " + list.size());
            in++;
            count++;
            double h = (double) list.get(in);
            double k = (double) list2.get(in);
            double x = xt;
            double z = 0;
            double y = h;
            double r = k + zt;
            Log.d("testing", "test: " + h + " " + k);
            try {
                bufferedWriter.write("v" + " " + x + " " + y + " " + z);
                bufferedWriter.newLine();
                functions.semicircleright(bufferedWriter, x, z, y, r);
                //semicircleright(x,h,t2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (count > 17)
                break;

        }
    }

    public void leftsidebullnose(double xt, double zt) {
        int count = 0;
        while (in < list.size() && in < list2.size()) {
            Log.d("testing", "test: " + list.get(in) + " " + list.size());
            in++;
            count++;
            double h = (double) list.get(in);
            double k = (double) list2.get(in);
            double x = xt;
            double z = zt / 2;
            double y = h;
            double r = k - z;
            Log.d("testing", "test: " + h + " " + k);
            try {
                bufferedWriter.write("v" + " " + 0 + " " + y + " " + z);
                bufferedWriter.newLine();
                functions.semicircleleft(bufferedWriter, x, z, y, r);
                //semicircleright(x,h,t2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (count > 17)
                break;

        }
    }

}
