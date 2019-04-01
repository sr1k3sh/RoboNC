package com.arrtsm.app.woodapp.Models;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class NormalOval {
    double x, y, z, r, h, k, h1, k1, ht, kt, h1t, k1t;
    BufferedWriter bufferedWriter;
    double cx1, cz1, cx2, cz2, cx3, cz3, cx4, cz4;
    int in = 0;
    Functions functions = new Functions();

    public NormalOval(float length, float breadth, float height) {
        x = length;
        y = height;
        z = breadth;
        r = z / 2;
        h = functions.translate(x, x / 2);
        k = functions.translate(z / 2, z / 2);
        ht = x;
        kt = z / 2;
        //center point


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
            /////faces
            bufferedWriter.write("usemtl Material.001\n");
            bufferedWriter.newLine();
            bufferedWriter.write("s 1");
            bufferedWriter.newLine();
            bufferedWriter.write("f 1/1/4 4/4/4 3/3/4 2/2/4");
            bufferedWriter.newLine();
            //left side face q=centerpoint=5,n=totalnumbertotheend=24
            functions.threefaceview(bufferedWriter, 5, 24, 4, false);
            //right side face q=centerpoint=25,n=totalnumbertotheend=44 +++19 add to each q to get n
            functions.threefaceview(bufferedWriter, 25, 44, 4, false);
            bufferedWriter.write("f 45/45/3 46/46/3 47/47/3 48/48/3");
            bufferedWriter.newLine();
            functions.threefaceview(bufferedWriter, 49, 68, 3, true);
            functions.threefaceview(bufferedWriter, 69, 88, 3, true);

            bufferedWriter.write("f 1/1/3 45/45/3 48/48/3 4/4/3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 2/3/3 3/3/3 47/47/3 46/46/3");
            bufferedWriter.newLine();
            functions.sideface(bufferedWriter, 5, 24, 49, 68);
            functions.sideface(bufferedWriter, 25, 55, 69, 88);


            ///starting legs draw from here
            functions.leg(bufferedWriter, x, y, z);
            ////////
            bufferedWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
