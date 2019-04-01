package com.arrtsm.app.woodapp.json;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.arrtsm.app.woodapp.AR.ArActivity;

import com.arrtsm.app.woodapp.Account.Account;
import com.arrtsm.app.woodapp.Account.Address;
import com.arrtsm.app.woodapp.Account.Name;
import com.arrtsm.app.woodapp.Account.Shape;
import com.arrtsm.app.woodapp.BuilderTest;
import com.arrtsm.app.woodapp.CircleModels.FullBullCircle;
import com.arrtsm.app.woodapp.CircleModels.NormalCircle;
import com.arrtsm.app.woodapp.CircleModels.SemibullCircle;
import com.arrtsm.app.woodapp.DrawActivity;
import com.arrtsm.app.woodapp.HelloArActivity;
import com.arrtsm.app.woodapp.MaterialFileEditor;
import com.arrtsm.app.woodapp.Models.NormalOval;
import com.arrtsm.app.woodapp.OnMenuClickedListener2;
import com.arrtsm.app.woodapp.R;
import com.arrtsm.app.woodapp.Robot.OldRobotBuilder;
import com.arrtsm.app.woodapp.Robot.Robot;
import com.arrtsm.app.woodapp.Robot.RobotBuilder;
import com.arrtsm.app.woodapp.Robot.RobotEngineer;

import com.arrtsm.app.woodapp.util.Constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import min3d.Min3d;
import min3d.Shared;
import min3d.Utils;
import min3d.core.Object3d;
import min3d.core.Object3dContainer;
import min3d.core.Renderer;
import min3d.core.Scene;
import min3d.interfaces.ISceneController;
import min3d.objectPrimitives.Box;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import min3d.vos.TextureVo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnMenuClickedListener2,ISceneController {
    IParser parser;
    String  ip;
    FragmentManager fragmentManager;
    ImageView mainImage;
    FrameLayout left_list;
    ImageButton hideLeft;
    ImageButton ar;
    LinearLayout l1;
    ImageButton ok;
    //3d model code starts from here
    private Object3dContainer objModel;
    public String modelurl;
    Bitmap bitmap,bit;
    Button leg1,leg2;
SeekBar seekBar;
int value;
    TextureVo vo,vo1;
    int x1,y1,z1;
    Bitmap b1;
    int num;
    String edge,shape,thickness;
    public Scene scene;
    protected GLSurfaceView _glSurfaceView;
    protected Handler _initSceneHander;
    protected Handler _updateSceneHander;
    private boolean _renderContinuously;
    final Runnable _initSceneRunnable = new Runnable()
    {
        public void run() {
                    }
    };

    final Runnable _updateSceneRunnable = new Runnable()
    {
        public void run() {

            onUpdateScene();
        }
    };


    /**
     * Called _after_ updateScene()
     * Unlike initScene(), gets called from the UI thread.
     */
    public void onUpdateScene()
    {

    }
    protected void glSurfaceViewConfig()
    {

    }



    /**
     * Separated out for easier overriding...
     */

    @Override
    protected void onResume()
    {
        super.onResume();
        _glSurfaceView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        _glSurfaceView.onPause();
    }

    public void initScene()
    {
        Light _light;

        _light = new Light();
        _light.position.setAll(0, 0, 3);
        _light.diffuse.setAll(255, 255, 255, 255);
        _light.ambient.setAll(0, 0, 0, 0);
        _light.specular.setAll(0, 0, 0, 0);
        _light.emissive.setAll(0, 0, 0, 0);
        scene.lights().add(_light);
        _light = new Light();
        _light.position.setAll(3, 0, 0);
        scene.lights().add(_light);
        _light = new Light();
        _light.position.setAll(0, 3, 0);
        scene.lights().add(_light);
        _light = new Light();
        _light.position.setAll(0, -3, 0);
        scene.lights().add(_light);
        //scene.lights().add(new Light());
       parser = Parser.createParser(Parser.Type.OBJ, getResources(), "RectangleAngle.obj" +
     "", true);
        parser.parse();
        objModel = parser.getParsedObject();
        objModel.scale().x=objModel.scale().y=objModel.scale().z=1f/10;
        /*objModel.scale().x=objModel.scale().y=objModel.scale().z=10f;
        body = objModel.getChildByName("Mesh01");

        body.textures().removeAll();
        scene.addChild(objModel);
        scene.camera().target = objModel.position();
        Bitmap b =bitmap;
        Shared.textureManager().addTextureId(b,"test");
        b.recycle();
        TextureVo vo = new TextureVo("test");
        body.textures().add(vo);*/
      /*  InputStream istr;
        bit = null;
        try {
            istr = getAssets().open(test);
            Log.d("url", "initScene: "+istr);
            bit = BitmapFactory.decodeStream(istr);
            Log.d("url", "OnMenuClicked: "+bit);
        } catch (IOException e) {
            // handle exception
        }
        Shared.textureManager().addTextureId(bit,"test1");
        bit.recycle();
        vo= new TextureVo("test1");*/


       /* b1=bitmap;
        if(bitmap==null){

        }
        else{
            Shared.textureManager().addTextureId(b1,"testing1");
            b1.recycle();
            vo1 = new TextureVo("testing1");

        }*/

      //objModel.getChildByName("Mesh01").textures().add(vo);
        //b1 =bitmap;
      /* Utils.makeBitmapFromAssets(this,"woodtexture/"+"ash-wood.jpg");
        Shared.textureManager().addTextureId(bit,"test1");
        bit.recycle();
        vo= new TextureVo("test1");*/
       /* Utils.makeBitmapFromAssets(this,test);
        Shared.textureManager().addTextureId(b1,"test3");
        b1.recycle();*/

       /* b1=Utils.makeBitmapFromResourceId(R.drawable.images);
        Shared.textureManager().addTextureId(b1,"test2");
        b1.recycle();*/
        //bit=bitmap;
        /*if(bit==null){
            bit=Utils.makeBitmapFromAssets(this,"woodtexture/"+"ash-wood.jpg");
            //bit=Utils.makeBitmapFromResourceId(R.drawable.images);
        }
        Shared.textureManager().addTextureId(bit,"test3");
        bit.recycle();
        vo1= new TextureVo("test3");*/

        Bitmap b1 =bitmap;
        if(bitmap==null){
            Log.d("rikesh", "initScene: null bitmap");
            b1=Utils.makeBitmapFromResourceId(R.drawable.images);
            Shared.textureManager().addTextureId(b1,"test1");
            b1.recycle();
            vo = new TextureVo("test1");
        }
        else{
            Shared.textureManager().addTextureId(b1,"test1");
            b1.recycle();

            vo = new TextureVo("test1");
            Log.d("rikesh", "initScene:  bitmap available");
            Log.d("rikesh", "OnMenuClicked: "+value);
            objModel.getChildByName("CircLeftB").scale().y=value;
            objModel.getChildByName("CircLeftF").scale().y=value;
            objModel.getChildByName("CircRightB").scale().y=value;
            objModel.getChildByName("CircRightF").scale().y=value;
            objModel.getChildByName("RectLeftB").scale().y=value;
            objModel.getChildByName("RectLeftF").scale().y=value;
            objModel.getChildByName("RectRightB").scale().y=value;
            objModel.getChildByName("RectRightF").scale().y=value;

        }

        b1=Utils.makeBitmapFromResourceId(R.drawable.images);
        Shared.textureManager().addTextureId(b1,"test2");
        b1.recycle();
        num = objModel.numChildren();
        for(int i=0;i<num;i++){
            String name=objModel.getChildAt(i).name();
            x1=20;
            y1=10;
            z1=10;
            if(name.indexOf("Mesh01")!=-1){
                objModel.getChildAt(i).scale().x=x1;
                objModel.getChildAt(i).scale().z=z1;
                objModel.getChildAt(i).scale().y=y1/10;
                objModel.getChildAt(i).textures().add(vo);
            }

            if(name.indexOf("CircLeftF")!=-1){

                objModel.getChildAt(i).position().x=-(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=(z1/2-20%z1-1);

                objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test2");
            }
            if(name.indexOf("CircLeftB")!=-1){
                objModel.getChildAt(i).position().x=-(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=-(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test2");
            }
            if(name.indexOf("CircRightF")!=-1){
                objModel.getChildAt(i).position().x=(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test2");
            }
            if(name.indexOf("CircRightB")!=-1){
                objModel.getChildAt(i).position().x=(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=-(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test2");
            }
            if(name.indexOf("RectLeftF")!=-1){
                objModel.getChildAt(i).position().x=-(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().add(vo);
               /* objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test1");*/
            }
            if(name.indexOf("RectLeftB")!=-1){
                objModel.getChildAt(i).position().x=-(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=-(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().add(vo);
               /* objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test1");*/
            }
            if(name.indexOf("RectRightF")!=-1){
                objModel.getChildAt(i).position().x=(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().add(vo);
                /*objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test1");*/
            }
            if(name.indexOf("RectRightB")!=-1){
                objModel.getChildAt(i).position().x=(x1/2-20%z1-1);
                objModel.getChildAt(i).position().z=-(z1/2-20%z1-1);
                objModel.getChildAt(i).textures().add(vo);
                /*objModel.getChildAt(i).textures().clear();
                objModel.getChildAt(i).textures().addById("test1");*/
            }
        }


        scene.camera().target = objModel.position();
        scene.addChild(objModel);

//        objModel = new Box(1,1,1);
//        scene.addChild(objModel);
//        scene.camera().target = objModel.position();

    }

    public void onInitScene()
    {

    }

    public void updateScene()
    {

    }
    public Handler getInitSceneHandler()
    {
        return _initSceneHander;
    }

    public Handler getUpdateSceneHandler()
    {

        return _updateSceneHander;
    }

    public Runnable getInitSceneRunnable()
    {
        return _initSceneRunnable;
    }

    public Runnable getUpdateSceneRunnable()
    {
        return _updateSceneRunnable;
    }
    float touchedX, touchedY;
    float lastplanePosition,planePosition;
    float maxScal = 5f,minScal = .1f;
    float scalSpeed = .5f;
    float totalRatio = .9f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("test_obj", "onTouchEvent: touched");

                    touchedX = event.getX();
                    touchedY = event.getY();
                    break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 2) {
                    lastplanePosition = spacing(event);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                switch (event.getPointerCount()) {
                    case 1:

                        objModel.rotation().y -= (touchedX - event.getX()) / 2f;
                        objModel.rotation().x -= (touchedY - event.getY()) / 2f;
                        touchedX = event.getX();
                        touchedY = event.getY();
                        break;
                    case 2:
                        planePosition = spacing(event);
                        float scaledRatio = planePosition/ lastplanePosition;
                        totalRatio = totalRatio *scaledRatio;

                        if(totalRatio >maxScal) totalRatio = maxScal;
                        if(totalRatio<minScal) totalRatio = minScal;

                        objModel.scale().x = objModel.scale().y = objModel.scale().z = totalRatio;
                        lastplanePosition = planePosition;
                        break;
                }
                break;
        }
        return true;
    }
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (x * x + y * y)*(x * x + y * y);
    }

    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        edge=in.getStringExtra("edge");
        shape=in.getStringExtra("shape");
        thickness=in.getStringExtra("thickness");
        Log.d("value", "onCreate: "+edge+shape+thickness);
        _initSceneHander = new Handler();
        _updateSceneHander = new Handler();
        //new SemibullCircle(400,10,200);
        //
        // These 4 lines are important.
        //
        Shared.context(this);
        scene = new Scene(this);


        Renderer r = new Renderer(scene);
        Shared.renderer(r);

        _glSurfaceView = new GLSurfaceView(this);
        glSurfaceViewConfig();
        _glSurfaceView.setRenderer(r);
        _glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(R.layout.test);
         l1 = findViewById(R.id.test);
        l1.addView(_glSurfaceView);
        leg1=findViewById(R.id.leg1);
        leg2=findViewById(R.id.leg2);

        left_list = findViewById(R.id.left_list);
        hideLeft = findViewById(R.id.hideleft);

        fragmentManager = getSupportFragmentManager();

        ok = findViewById(R.id.ok);
        ar = findViewById(R.id.ar);

        fragmentManager = getSupportFragmentManager();

        DisplayFragment dis = new DisplayFragment();
        dis.setOnMenuClickedListener(this);

        fragmentManager.beginTransaction()
                .add(R.id.left_list, dis, "Left_List")
                .commit();
        leg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objModel.getChildByName("CircLeftB").isVisible(Boolean.FALSE);
                objModel.getChildByName("CircLeftF").isVisible(Boolean.FALSE);
                objModel.getChildByName("CircRightB").isVisible(Boolean.FALSE);
                objModel.getChildByName("CircRightF").isVisible(Boolean.FALSE);
                objModel.getChildByName("RectLeftB").isVisible(Boolean.TRUE);
                objModel.getChildByName("RectLeftF").isVisible(Boolean.TRUE);
                objModel.getChildByName("RectRightB").isVisible(Boolean.TRUE);
                objModel.getChildByName("RectRightF").isVisible(Boolean.TRUE);
              //objModel.addChild(objModel.getChildByName("Mesh03"));
           // objModel.getChildByName("Mesh03").ignoreFaces();
            }
        });
        leg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objModel.getChildByName("CircLeftB").isVisible(Boolean.TRUE);
                objModel.getChildByName("CircLeftF").isVisible(Boolean.TRUE);
                objModel.getChildByName("CircRightB").isVisible(Boolean.TRUE);
                objModel.getChildByName("CircRightF").isVisible(Boolean.TRUE);
                objModel.getChildByName("RectLeftB").isVisible(Boolean.FALSE);
                objModel.getChildByName("RectLeftF").isVisible(Boolean.FALSE);
                objModel.getChildByName("RectRightB").isVisible(Boolean.FALSE);
                objModel.getChildByName("RectRightF").isVisible(Boolean.FALSE);

               // objModel.removeChildAt(objModel.getChildByName("Mesh02"));
            }
        });
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                value=i;
                objModel.getChildByName("CircLeftB").scale().y=value;
                objModel.getChildByName("CircLeftF").scale().y=value;
                objModel.getChildByName("CircRightB").scale().y=value;
                objModel.getChildByName("CircRightF").scale().y=value;
                objModel.getChildByName("RectLeftB").scale().y=value;
                objModel.getChildByName("RectLeftF").scale().y=value;
                objModel.getChildByName("RectRightB").scale().y=value;
                objModel.getChildByName("RectRightF").scale().y=value;

                //objModel.getChildByName("CircLeftB").scale().x=objModel.getChildByName("CircLeftB").scale().z=i/10;
                Toast.makeText(MainActivity.this, "bar"+value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view){

                                      num = objModel.numChildren();
                                      for(int i=0;i<num;i++){
                                          String name=objModel.getChildAt(i).name();

                                          if(name.indexOf("Mesh01")!=-1){
                                             /* objModel.getChildAt(i).scale().x=10;
                                              objModel.getChildAt(i).scale().z=10;
                                              objModel.getChildAt(i).scale().y=1;*/
                                              objModel.getChildByName("Mesh01").textures().removeAll();
                                             /* objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test1");*/
                                          }
                                          if(name.indexOf("CircLeftF")!=-1){

                                             /* objModel.getChildAt(i).position().x=-4;
                                              objModel.getChildAt(i).position().z=4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test2");
                                          }
                                          if(name.indexOf("CircLeftB")!=-1){
                                             /* objModel.getChildAt(i).position().x=-4;
                                              objModel.getChildAt(i).position().z=-4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test2");
                                          }
                                          if(name.indexOf("CircRightF")!=-1){
                                             /* objModel.getChildAt(i).position().x=4;
                                              objModel.getChildAt(i).position().z=4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test2");
                                          }
                                          if(name.indexOf("CircRightB")!=-1){
                                             /* objModel.getChildAt(i).position().x=4;
                                              objModel.getChildAt(i).position().z=-4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test2");
                                          }
                                          if(name.indexOf("RectLeftF")!=-1){
                                             /* objModel.getChildAt(i).position().x=-4;
                                              objModel.getChildAt(i).position().z=4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test1");
                                          }
                                          if(name.indexOf("RectLeftB")!=-1){
                                             /* objModel.getChildAt(i).position().x=-4;
                                              objModel.getChildAt(i).position().z=-4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test1");
                                          }
                                          if(name.indexOf("RectRightF")!=-1){
                                             /* objModel.getChildAt(i).position().x=4;
                                              objModel.getChildAt(i).position().z=4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test1");
                                          }
                                          if(name.indexOf("RectRightB")!=-1){
                                              /*objModel.getChildAt(i).position().x=4;
                                              objModel.getChildAt(i).position().z=-4;*/
                                              objModel.getChildAt(i).textures().clear();
                                              objModel.getChildAt(i).textures().addById("test1");
                                          }
                                      }
                                      //objModel.getChildByName("Mesh01").textures().removeAll();
                                   /* Name name = new Name.Builder()
                                            .firstName("Rikesh")
                                            .middleName("k")
                                            .lastName("Shrestha")
                                            .build();
                                      Address address=new Address.Builder()
                                              .houseNumber(11)
                                              .street("Kusunti")
                                              .zipCode("1111")
                                              .city("Kathmandu")
                                              .build();
                                      Account account = new Account.Builder()
                                              .id(01)
                                              .email("sr1k3sh@gmail.com")
                                              .Address(address)
                                              .Name(name)
                                              .build();
                                      Log.d("rikesh", "onClick: "+account.getAddress().getCity());*/

                                  Shape shape= new Shape.Builder().length(1).breadth(1).thickness(1).build();
                                      Log.d("rikesh", "onClick: "+shape.getLength()+shape.getBreadth()+shape.getThickness() );
                                    new BuilderTest.Builder(500,80,400).c(1).build();

                                      //new NormalCircle(400,80,400);
                                    /*  if(objModel!=null){
                                          Log.d("objectmodel", "OnMenuClicked: object loaded");
                                          l1.removeView(_glSurfaceView);
                                          l1.addView(_glSurfaceView);
                                          //scene.reset();
                                          //scene.removeChild(objModel);
                                      }
                                      if(objModel==null){

                                          l1.addView(_glSurfaceView);
                                          Log.d("objectmodel", "OnMenuClicked: nulll object");
                                          //l1.removeAllViews();


                                      }*/
                                     //send();
                                      //Toast.makeText(MainActivity.this, "Data saved inside the storage", Toast.LENGTH_SHORT).show();
                                  }
                              });


        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(objModel==null){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            MainActivity.this);
                    alertDialogBuilder.setMessage("Please select the Texture first");
                    alertDialogBuilder.setPositiveButton("ok",null);
                    alertDialogBuilder.create();
                    alertDialogBuilder.show();
                    Toast.makeText(MainActivity.this, "model is not selected with texture", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (Build.VERSION.SDK_INT >= 27) {
                        Intent intent = new Intent(MainActivity.this, HelloArActivity.class);
                        startActivity(intent);
                    }
                    else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                MainActivity.this);
                        alertDialogBuilder.setMessage("Your Device doesnt support AR with best Experience");
                        alertDialogBuilder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Intent ar_intent = new Intent(MainActivity.this, ArActivity.class);
                                startActivity(ar_intent);
                            }
                        });
                        alertDialogBuilder.create();
                        alertDialogBuilder.show();

                    }
                    Toast.makeText(MainActivity.this, "You clicked AR", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //Toggle Left panel
        hideLeft.setOnClickListener(view -> {
            if (left_list.getVisibility() == View.VISIBLE) {
                left_list.setVisibility(View.GONE);
                hideLeft.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.textures));
            } else if (left_list.getVisibility() == View.GONE) {
                left_list.setVisibility(View.VISIBLE);
                hideLeft.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.textures1));
            }
        });

    }
    String test;
    public void send() {
        SharedPreferences shared =getSharedPreferences("IpAddress", Context.MODE_PRIVATE);
        ip =shared.getString("ip",null);
        Log.d("ip","ip :"+ip);

//call send() function on ok button

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if(ip==null){
                 ip="http://192.168.0.1";
                }
                String BASE_URL = ip+"/wood.php";
               // String BASE_URL = "http://192.168.0.103/uploads/test.php";
                String filename = "/data.CSV";
                File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/WoodAppData", filename);
                Uri path = Uri.fromFile(filelocation);
                String content_type = "text/csv";

                String file_path = filelocation.getAbsolutePath();
                OkHttpClient client = new OkHttpClient();
                RequestBody file_body = RequestBody.create(MediaType.parse(content_type), filelocation);
                RequestBody request_body = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("type", content_type)
                        .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                        .build();
                Request request = new Request.Builder()
                        .url(BASE_URL)
                        .post(request_body)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
Log.d("hello",response.toString());
                    if (!response.isSuccessful()) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog();
                            }
                        });

                        throw new IOException("Error : " + response);
                    }

                    if (response.body().string().equals("success")) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // hidePDialog();
                                positivedialog();
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog();
                        }
                    });
                }
            }
        });
        t.start();
    }
    private void positivedialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Data Successfully Sent")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Error Connecting to Server")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public void OnMenuClicked(String url) {

        //test="woodtexture/beech-wood.jpg";
        test=url;
        InputStream istr;

         bitmap = null;
        try {
            istr = getAssets().open("woodtexture/"+test);
            Log.d("url", "initScene: "+istr);
            bitmap = BitmapFactory.decodeStream(istr);
            Log.d("url", "OnMenuClicked: "+bitmap);
        } catch (IOException e) {
            // handle exception
        }
        SharedPreferences shared = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("ok",test);
        editor.putString("shape",shape);
        editor.apply();
        editor.commit();
        if(objModel!=null){
            Log.d("objectmodel", "OnMenuClicked: object loaded");

           //l1.removeViewInLayout(_glSurfaceView);
            l1.removeView(_glSurfaceView);
            l1.addView(_glSurfaceView);
            //scene.reset();
            //scene.removeChild(objModel);
        }
        if(objModel==null){

            l1.addView(_glSurfaceView);
            Log.d("objectmodel", "OnMenuClicked: nulll object");
            //l1.removeAllViews();


        }
       // Toast.makeText(this, "loaded"+url, Toast.LENGTH_SHORT).show();
        Log.d("url", "OnMenuClicked: "+test);
        MaterialFileEditor editor1 = new MaterialFileEditor(this);
        editor1.getNewMaterialFromTexture(Constants.Circle,test);
    }

   /* private class GetImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {

                //Log.d("url", "doInBackground: "+downloadDataFromUrl(this,strings[0]));

                return downloadDataFromUrl(MainActivity.this,test_obj);
            } catch (IOException e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap image) {


            Log.d("imagetest", "onPostExecute: "+image);
*//*

             Bitmap b;
            b = Utils.makeBitmapFromResourceId(R.drawable.design);
            Shared.textureManager().addTextureId(b,"test_obj");
            b.recycle();
            TextureVo vo = new TextureVo("test_obj");
            body.textures().add(vo);
*//*




        }
    }*/

    public Bitmap downloadDataFromUrl(Context context,String string) throws IOException {

        String[] files = null;
        InputStream in = null;
        OutputStream out = null;

        InputStream is = null;
        try {
            in = context.getAssets().open(string);
            Log.d("response code","dfsd"+in);
           /* URL url = new URL(string);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(20000);
            con.setDoInput(true);
            con.connect();
            is = con.getInputStream();*/
            Bitmap myBitmap = BitmapFactory.decodeStream(in);

            return myBitmap;

        } finally {
            if (is != null) {
                is.close();
            }

        }

    }


}
