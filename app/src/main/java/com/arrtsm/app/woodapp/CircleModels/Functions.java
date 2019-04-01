package com.arrtsm.app.woodapp.CircleModels;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Functions {
    List list = new ArrayList();
    List list2 = new ArrayList();
    double i, j;
    public double translate(double t1, double t2) {
        double value = t1 - t2;
        return value;
    }

    public void topsquare(BufferedWriter bufferedWriter, double xt, double yt, double zt) {
        try {
            double x = xt, y = yt, z = zt;
            bufferedWriter.write("v" + " " + translate(0, x / 2) + " " + y + " " + translate(0, z / 2)); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("v" + " " + translate(0, x / 2) + " " + y + " " + translate(z, z / 2)); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("v" + " " + translate(x, x / 2) + " " + y + " " + translate(z, z / 2)); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("v" + " " + translate(x, x / 2) + " " + y + " " + translate(0, z / 2)); ///13
            bufferedWriter.newLine();
           /* bufferedWriter.write("v"+" "+0+" "+y+" "+0); ///1
            bufferedWriter.newLine();
            bufferedWriter.write("v"+" "+0+" "+y+" "+z); ///2
            bufferedWriter.newLine();
            bufferedWriter.write("v"+" "+x+" "+y+" "+z); ///3
            bufferedWriter.newLine();
            bufferedWriter.write("v"+" "+x+" "+y+" "+0); ///13
            bufferedWriter.newLine();*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void semicircleright(BufferedWriter bufferedWriter, double x, double z, double y, double radius) {
        int deg = 0;
        double yt = y;
        double r = radius;
        while (deg <= 180) {
            i = r * Math.sin(Math.toRadians(deg)) + x;
            j = r * Math.cos(Math.toRadians(deg)) + z;
            deg = deg + 10;
            try {
                Log.d("testing", "TestBox: thir56d");
                bufferedWriter.append("v" + " " + i + " " + yt + " " + j);
                bufferedWriter.newLine();
                Log.d("testing", "TestBox: third");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void semicircleleft(BufferedWriter bufferedWriter, double x, double z, double y, double radius) {
        int deg = 0;
        double yt = y;
        double r = radius;
        while (deg <= 180) {
            i = -r * Math.sin(Math.toRadians(deg)) + x;
            j = -r * Math.cos(Math.toRadians(deg)) + z;
            deg = deg + 10;
            try {
                bufferedWriter.write("v" + " " + i + " " + yt + " " + j);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void normalvector(BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("vn 1.00 0.00 0.00"); //1
            bufferedWriter.newLine();
            bufferedWriter.write("vn -1.00 0.00 0.00"); //2
            bufferedWriter.newLine();
            bufferedWriter.write("vn 0.00 1.00 0.00"); //3
            bufferedWriter.newLine();
            bufferedWriter.write("vn 0.00 -1.00 0.00"); //4
            bufferedWriter.newLine();
            bufferedWriter.write("vn 0.00 0.00 1.00");  //5
            bufferedWriter.newLine();
            bufferedWriter.write("vn 0.00 0.00 -1.00"); //6
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /// faces functions
    public void threefaceview(BufferedWriter bufferedWriter, int q, int n, int view, boolean clock) {
        int qb = q;
        boolean c = clock; ///TRUE vayo vane clock False vayo vane anticlock
        int v = view;
        int rb = qb + 1;
        int nb = n;
        while (qb < nb - 1 && rb < nb) {
            try {
                if (c) {
                    bufferedWriter.write("f" + " " + nb + "/" + nb + "/" + v + " " + qb + "/" + qb + "/" + v + " " + rb + "/" + rb + "/" + v);
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write("f" + " " + nb + "/" + nb + "/" + v + " " + rb + "/" + rb + "/" + v + " " + qb + "/" + qb + "/" + v);
                    bufferedWriter.newLine();
                }
                qb = qb + 1;
                rb = rb + 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Log.d("vertices", "Box: "+"f"+" "+n+"//6"+" "+q+"//6"+" "+r+"//6");
        }
    }
    public void sideface(BufferedWriter bufferedWriter, int p1, int n1, int p2, int n2) {
        int i1 = p1, i2 = i1 + 1, j1 = p2, j2 = j1 + 1;
        int tempi = p1 + (Math.abs(i1 - n1) / 2), tempj = p2 + Math.abs(j2 - n2) / 2;
        int f;
        Log.d("testing", "sideface1: " + tempi + "   " + tempj);
        int num1 = n1, num2 = n2;
        while (i1 < num1 && i2 < num1 + 1 && j1 < num2 && j2 < num2 + 1) {
            try {
                if (i1 < tempi && j1 < tempj)
                    f = 3;
                else
                    f = 3;
                bufferedWriter.write("f " + i1 + "/" + i1 + "/" + f + " " + i2 + "/" + i2 + "/" + f + " " + j2 + "/" + j2 + "/" + f + " " + j1 + "/" + j1 + "/" + f);
                bufferedWriter.newLine();
                i1 = i1 + 1;
                i2 = i2 + 1;
                j1 = j1 + 1;
                j2 = j2 + 1;
                Log.d("testing", "sideface: " + i1 + "" + i2 + "" + f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void legsquare(BufferedWriter bufferedWriter, double cx1, double cy1, double cz1, double x, double z) {
        //top-bottom point for square
        double ctz1, cbz1;
        try {
            ctz1 = cz1 - 4 * z / 100;
            cbz1 = cz1 + 4 * z / 100;
            ////points
            double px1, pz1, px2, pz2, px3, pz3, px4, pz4;
            px1 = cx1 - 4 * x / 100;
            pz1 = ctz1;
            bufferedWriter.write("v " + px1 + " " + cy1 + " " + pz1);
            bufferedWriter.newLine();
            px2 = cx1 + 4 * x / 100;
            pz2 = ctz1;
            bufferedWriter.write("v " + px2 + " " + cy1 + " " + pz2);
            bufferedWriter.newLine();
            px4 = cx1 + 4 * x / 100;
            pz4 = cbz1;
            bufferedWriter.write("v " + px4 + " " + cy1 + " " + pz4);
            bufferedWriter.newLine();
            px3 = cx1 - 4 * x / 100;
            pz3 = cbz1;
            bufferedWriter.write("v " + px3 + " " + cy1 + " " + pz3);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void legface(BufferedWriter bufferedWriter, int q, int r, int face) {
        int a1 = q, a2 = a1 + 1, n1 = a1 + 4;
        int b1 = r, b2 = b1 + 1, n2 = b1 + 4;
        int f = face;
        try {
            while (a1 < n1 - 1 && b1 < n2 - 1) {
                bufferedWriter.write("f " + a1 + "//" + f + " " + a2 + "//" + f + " " + b2 + "//" + f + " " + b1 + "//" + f);
                bufferedWriter.newLine();
                a1++;
                a2++;
                b2++;
                b1++;

                //bufferedWriter.write("f " n);
            }
            bufferedWriter.write("f " + a1 + "//" + f + " " + q + "//" + f + " " + r + "//" + f + " " + b1 + "//" + f);
            bufferedWriter.newLine();
            bufferedWriter.write("f " + r + "//4 " + (r + 1) + "//4 " + (r + 2) + "//4 " + (r + 3) + "//4");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void face(BufferedWriter bufferedWriter, int q, int n, int view, boolean clock) {
        int qb = q;
        boolean c = clock; ///TRUE vayo vane clock False vayo vane anticlock
        int v = view;
        int rb = qb + 1;
        int nb = n;
        while (qb < nb - 1 && rb < nb) {
            try {
                if (c) {
                    bufferedWriter.write("f" + " " + nb + "/" + nb + "/" + v + " " + qb + "/" + qb + "/" + v + " " + rb + "/" + rb + "/" + v);
                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write("f" + " " + nb + "/" + nb + "/" + v + " " + rb + "/" + rb + "/" + v + " " + qb + "/" + qb + "/" + v);
                    bufferedWriter.newLine();
                }
                qb = qb + 1;
                rb = rb + 1;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Log.d("vertices", "Box: "+"f"+" "+n+"//6"+" "+q+"//6"+" "+r+"//6");
        }
    }
    public void bface(BufferedWriter bufferedWriter, int count1, int count2, int face, boolean clock) {
        int f = face;
        int tempi = (count1 + 9);
        int tempj = (count2 + 9);
        boolean c = clock;
        int q1 = count1, r1 = q1 + 1;
        int q2 = count2, r2 = q2 + 1;
        while (q1 < count1 + 18 && q2 < count2 + 18) {
            try {
                if (c) {
                    // f=3;
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
                    //f=3;
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
    public void frontface(BufferedWriter bufferedWriter, int count1, int count2, boolean clock) {
        int f;
        int tempi = (count1 + 9);
        int tempj = (count2 + 9);
        boolean c = clock;
        int q1 = count1, r1 = q1 + 1;
        int q2 = count2, r2 = q2 + 1;
        while (q1 < count1 + 9 && q2 < count2 + 9) {
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
    ////texture coordinates
    public void Rtexturecoordinates(BufferedWriter bufferedWriter, double h, double k, double radius, double texx, double texz) {
        double texdeg = 0;
        double x = texx;
        double z = texz;
        double r = radius;
        while (texdeg <= 180) {
            i = (r * Math.sin(Math.toRadians(texdeg)) + h) / x;
            j = (r * Math.cos(Math.toRadians(texdeg)) + k) / z;
            texdeg = texdeg + 10;
            try {
                bufferedWriter.write("vt" + " " + i + " " + j);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Ltexturecoordinates(BufferedWriter bufferedWriter, double h, double k, double radius, double texx, double texz) {
        double texdeg1 = 0;
        double x = texx;
        double r = radius;
        double z = texz;
        while (texdeg1 <= 180) {
            i = (r * Math.sin(Math.toRadians(texdeg1)) + h) / x;
            j = (r * Math.cos(Math.toRadians(texdeg1)) + k) / z;
            texdeg1 = texdeg1 + 10;
            try {
                bufferedWriter.write("vt" + " " + i + " " + j);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void leg(BufferedWriter bufferedWriter, double x, double y, double z) {
        double cx1, cz1, cx2, cz2, cx3, cz3, cx4, cz4;

        try {
            cx1 = translate(x - x, x / 2);
            cz1 = translate(z / 4, z / 2);
            bufferedWriter.write("v " + cx1 + " " + 0 + " " + cz1);
            bufferedWriter.newLine();
            cx2 = translate(x - x, x / 2);
            cz2 = translate(3 * z / 4, z / 2);
            bufferedWriter.write("v " + cx2 + " " + 0 + " " + cz2);
            bufferedWriter.newLine();
            cx3 = translate(x, x / 2);
            cz3 = translate(z / 4, z / 2);
            bufferedWriter.write("v " + cx3 + " " + 0 + " " + cz3);
            bufferedWriter.newLine();
            cx4 = translate(x, x / 2);
            cz4 = translate(3 * z / 4, z / 2);
            bufferedWriter.write("v " + cx4 + " " + 0 + " " + cz4);
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
            legsquare(bufferedWriter, cx1, -y / 1.5, cz1, x, z);
            //left-buttom
            legsquare(bufferedWriter, cx2, -y / 1.5, cz2, x, z);
            //right-top
            legsquare(bufferedWriter, cx3, -y / 1.5, cz3, x, z);
            //right-bottom
            legsquare(bufferedWriter, cx4, -y / 1.5, cz4, x, z);
            bufferedWriter.write("f 109//4 118//4 119//4 112//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f 113//4 122//4 123//4 116//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f 111//4 114//4 113//4 112//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f 119//4 122//4 121//4 120//4");
            bufferedWriter.newLine();
            bufferedWriter.write("f 100//3 116//3 123//3 107//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 107//3 123//3 118//3 102//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 102//3 118//3 109//3 93//3");
            bufferedWriter.newLine();
            bufferedWriter.write("f 93//3 109//3 116//3 100//3");
            bufferedWriter.newLine();
            //left-top
            legsquare(bufferedWriter, cx1, -1, cz1, x, z);
            legface(bufferedWriter, 109, 125, 3);

            //left-buttom
            legsquare(bufferedWriter, cx2, -1, cz2, x, z);
            legface(bufferedWriter, 113, 129, 3);
            //right-top
            legsquare(bufferedWriter, cx3, -1, cz3, x, z);
            legface(bufferedWriter, 117, 133, 3);
            //right-bottom
            legsquare(bufferedWriter, cx4, -1, cz4, x, z);
            legface(bufferedWriter, 121, 137, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
