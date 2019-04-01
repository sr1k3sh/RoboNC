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

public class SemibullOval {
    double x, y, z, i, r, h, k, h1, k1, ht, kt, h1t, k1t;
    double i1, j1, i2, j2;
    List list = new ArrayList();
    List list2 = new ArrayList();
    BufferedWriter bufferedWriter;
    int in = 0;
    Functions functions = new Functions();

    public SemibullOval(float length, float breadth, float height) {
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

            //right side semi circle
            functions.semicircleright(bufferedWriter, h, k, 0, r + y / 2);
            /* */
            bufferedWriter.write("v" + " " + h + " " + 0 + " " + k); ///14
            bufferedWriter.newLine();
            //left side semi circle algorithm
            functions.semicircleleft(bufferedWriter, h1, k1, 0, r + y / 2);
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
            /// to draw legs
            functions.leg(bufferedWriter, x, y, z);

            /// to draw bullnose
            /// find center point of y axis

            //thikness point
            bullnose(functions.translate(0, x / 2), y, z / 2, false, 90);
            bullnose(functions.translate(0, x / 2), y, -z / 2, true, 90);
            bullnose(functions.translate(x, x / 2), y, z / 2, false, 90);
            bullnose(functions.translate(x, x / 2), y, -z / 2, true, 90);
            functions.frontface(bufferedWriter, 89 + 52, 109 + 52, true);
            functions.frontface(bufferedWriter, 99 + 52, 119 + 52, false);

            rightsidebullnose(x / 2, z);
            functions.bface(bufferedWriter, 49, 130 + 52, 3, true);
            functions.bface(bufferedWriter, 130 + 52, 150 + 52, 3, true);
            functions.bface(bufferedWriter, 150 + 52, 170 + 52, 3, true);
            functions.bface(bufferedWriter, 170 + 52, 190 + 52, 3, true);
            functions.bface(bufferedWriter, 190 + 52, 210 + 52, 3, true);
            functions.bface(bufferedWriter, 210 + 52, 230 + 52, 3, true);
            functions.bface(bufferedWriter, 230 + 52, 250 + 52, 3, true);
            functions.bface(bufferedWriter, 250 + 52, 270 + 52, 3, true);
            functions.bface(bufferedWriter, 270 + 52, 290 + 52, 3, true);
            functions.bface(bufferedWriter, 290 + 52, 5, 3, true);

            leftsidebullnose(-x / 2, 0);
            functions.bface(bufferedWriter, 69, 330 + 52, 3, true);
            functions.bface(bufferedWriter, 330 + 52, 350 + 52, 3, true);
            functions.bface(bufferedWriter, 350 + 52, 370 + 52, 3, true);
            functions.bface(bufferedWriter, 370 + 52, 390 + 52, 3, true);
            functions.bface(bufferedWriter, 390 + 52, 410 + 52, 3, true);
            functions.bface(bufferedWriter, 410 + 52, 430 + 52, 3, true);
            functions.bface(bufferedWriter, 430 + 52, 450 + 52, 3, true);
            functions.bface(bufferedWriter, 450 + 52, 470 + 52, 3, true);
            functions.bface(bufferedWriter, 470 + 52, 490 + 52, 3, true);
            functions.bface(bufferedWriter, 490 + 52, 25, 3, true);
            //bottom middle face
            bufferedWriter.write("f 25//4 23//4 5//4 43//4");
            bufferedWriter.newLine();
            //front buttom face
            bufferedWriter.write("f 150//3 170//3 23//3 25//3");
            bufferedWriter.newLine();
            //back buttom face
            bufferedWriter.write("f 160//3 43//3 5//3 180//3");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void bullnose(double x, double y, double z, boolean direction, int angle) {
        double t1 = 0, t2;
        r = y / 2;
        h = y / 2;
        k = z;
        int deg = 0;
        while (deg <= angle) {
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
            if (count > 8)
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
            if (count > 9)
                break;

        }
    }

}
