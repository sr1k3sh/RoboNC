package com.arrtsm.app.woodapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoadObj extends AppCompatActivity implements getInterface{
    private static ArrayList<String> array0 = new ArrayList<>();
    private static ArrayList<String> array1 = new ArrayList<>();
    private static ArrayList<String> array2 = new ArrayList<>();
   //List array0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_obj);
        download();
       Log.d("rike2", "onCreate: "+setArray(array0));
        //LoadObj();

        //File
       /* try {
            removeLine(root,0);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    void LoadObj() {
        BufferedReader in = null;
        BufferedWriter out = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File root = new File(sdCard.getAbsolutePath() + "/CNC DATA/mo.obj");
        File root1 = new File(sdCard.getAbsolutePath() + "/CNC DATA/mo1.obj");
        try {
            InputStream instream = new FileInputStream(root);
            OutputStream outputStream = new FileOutputStream(root1);
            in = new BufferedReader(new InputStreamReader(instream));
            Log.d("rikin", "LoadObj: "+in);
            out = new BufferedWriter(new OutputStreamWriter(outputStream));
            Log.d("rikout", "LoadObj: "+out);
            //a line in the file
            String line;
            while ((line = in.readLine()) != null) {
                Log.d("rik1", "LoadObj: "+line);
                if (!Pattern.matches("o", line)) {

                    //find the line we want to delete
                    //if it is not the line you want to delete then write it to new file
                    //out.println(line);
                }

            }

            in.close();
            out.flush();
            out.close();
            File oldFile = new File(root, "/CNC DATA/mo.obj");
            oldFile.delete();
            File newFile = new File(root, "/CNC DATA/mo1.obj");
            newFile.renameTo(oldFile);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void removeLine(final File file, final int lineIndex) throws IOException{
        final List<String> lines = new LinkedList<>();
        Log.d("rik1", "removeLine: "+lines);
        final Scanner reader = new Scanner(new FileInputStream(file), "UTF-8");
        while(reader.hasNextLine())
            lines.add(reader.nextLine());
        reader.close();
        assert lineIndex >= 0 && lineIndex <= lines.size() - 1;

        //lines.remove(lineIndex);
        final BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        for(final String line : lines){
            writer.write(line);
            writer.write("\n");

        }
        writer.flush();
        writer.close();

    }
    void download(){
        File sdCard = Environment.getExternalStorageDirectory();
        File root = new File(sdCard.getAbsolutePath() + "/CNC DATA/test.txt");

        BufferedReader br = null;
        String[] characters = new String[1024];

       array0=new ArrayList();
       array1=new ArrayList();
       array2=new ArrayList();
        try {

            String sCurrentLine;
            InputStream instream = new FileInputStream(root);
            br = new BufferedReader(new InputStreamReader(instream));

            int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                String[] arr = sCurrentLine.split(" ");
                array0.add(arr[0]);
                array1.add(arr[1]);
                array2.add(arr[2]);




                if(arr.length == 4){
                    System.out.println("arr[3] = " + arr[3]);
                }


                characters[i] = arr[0];

                i++;
            }

            Log.d("rike1", "download: "+array0);
            Log.d("rike1", "download: "+array1);
            Log.d("rike1", "download: "+array2);

            //Log.d("rike1", "download: "+array1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }


    @Override
    public ArrayList<String> getArray() {
        return new ArrayList<String>(array0);
       // return new Character;
    }

    @Override
    public ArrayList<String> setArray(ArrayList<String> stringList) {
        ArrayList<String> strings= new ArrayList<>();
        strings.set(0,"hello");
        Log.d("rike3", "setArray: "+strings);
        //strings.set(stringList,"2");
       return strings;
    }

}
