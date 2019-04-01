package com.arrtsm.app.woodapp;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arrtsm.app.woodapp.Models.TestModel;
import com.arrtsm.app.woodapp.ValueFragment.Button1Fragment;
import com.arrtsm.app.woodapp.ValueFragment.Value_Fragment2;
import com.arrtsm.app.woodapp.ValueFragment.ValueFragment;
import com.arrtsm.app.woodapp.fragment_shape.Circle_fragment;
import com.arrtsm.app.woodapp.fragment_shape.Oval_fragment;
import com.arrtsm.app.woodapp.fragment_shape.Rectangle_fragment;
import com.arrtsm.app.woodapp.json.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class DrawActivity extends AppCompatActivity {
    ImageButton btn3d;
    LinearLayout right;
    // Button btn3;
    Button ok;
    RadioGroup edge_group;
    RadioButton rect;
    RadioButton circle;
    RadioButton oval;
    RadioButton btn_connect;
    RadioButton fourty_five_degree;
    RadioButton full_nose;
    RadioButton demi_nose;
    RadioButton normal;
    RadioButton radioButton;
    FragmentManager fragmentManager;
    EditText thick;
    String thickness_value;
    FrameLayout edit_text;
    WoodCanvas woodCanvas;
    float rect_length;
    float rect_breadth;
    float oval_length;
    float oval_breadth;
    float circle_radius;
    String edge="";
    String shape="";
    private String m_Text = "";
    private Activity activity;
    public String path= Environment.getExternalStorageDirectory().getAbsolutePath();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_activity);

        btn3d = findViewById(R.id.btn3d);
        circle = findViewById(R.id.btn_circle);
        ok = findViewById(R.id.ok);
        right = findViewById(R.id.right);
        rect = findViewById(R.id.btn_rect);
        oval = findViewById(R.id.btn_oval);
        btn_connect=findViewById(R.id.btn_connect);
        //btn8 = findViewById(R.id.btn8);
        thick = findViewById(R.id.thick);
        woodCanvas = findViewById(R.id.view_woodcanvas);
        fourty_five_degree=findViewById(R.id.fourty_five_degree);
        full_nose=findViewById(R.id.full_nose);
        demi_nose=findViewById(R.id.demi_nose);
        normal=findViewById(R.id.normal);
        edge_group=findViewById(R.id.edge_group);
        init();

    }

    private void init() {
        woodCanvas.grid();
        activity=this;
        rect.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.buttom, new Rectangle_fragment(), "DOWN_MENU")
                    .commit();
            oval_breadth(0);
            oval_length(0);
            circle_radius(0);
        });
        oval.setOnClickListener(v -> {

            fragmentManager.beginTransaction()
                    .replace(R.id.buttom, new Oval_fragment(), "DOWN_MENU")
                    .commit();
            rectangle_length(0);
            rectangle_breadth(0);
            circle_radius(0);
        });
        circle.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.buttom, new Circle_fragment(), "DOWN_MENU")
                    .commit();
            rectangle_length(0);
            rectangle_breadth(0);
            oval_length(0);
            oval_breadth(0);
        });

        btn_connect.setOnClickListener(v->{
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DrawActivity.this);
            builder.setTitle("Enter your ip address");

            final EditText input = new EditText(DrawActivity.this);

            input.setHint("192.168.0.118");
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = "http://"+input.getText().toString();
                    new IsServerOnline(activity).execute(m_Text);

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        });

        ok.setOnClickListener(v -> {

            int selectedId=edge_group.getCheckedRadioButtonId();
            radioButton=findViewById(selectedId);
            edge=radioButton.getText().toString();

            thickness_value = thick.getText().toString();

            if (TextUtils.isEmpty(thickness_value)) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(DrawActivity.this);
                a_builder.setMessage("Thickness Shouldnot be Empty.!!!")
                        .setCancelable(false)
                        .setPositiveButton("Got it", (dialog, which) -> {
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();

            }

            else if(((rect_length==0 && rect_breadth==0)) && ((oval_length==0 && oval_breadth==0)) &&((circle_radius==0))){
                AlertDialog.Builder a_builder = new AlertDialog.Builder(DrawActivity.this);
                a_builder.setMessage("Incomplete Data!!!")
                        .setCancelable(false)
                        .setPositiveButton("Got it", (dialog, which) -> {
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
            }else{
                isStoragePermissionGranted();
                Log.d("edge", ""+radioButton.getText().toString());


                Log.d("data","rect_length:"+rect_length+"rect_breadth:"+rect_breadth+"oval_length:"+oval_length+"oval_breadth"+oval_breadth+"radius"+circle_radius);

            }
        });

        btn3d.setOnClickListener(v -> {
            if (right.getVisibility() == View.VISIBLE) {
                right.setVisibility(View.GONE);
                btn3d.setImageDrawable(getDrawable(R.drawable.right3d));
            } else if (right.getVisibility() == View.GONE) {
                right.setVisibility(View.VISIBLE);
                btn3d.setImageDrawable(getResources().getDrawable(R.drawable.right3d1));
            }
        });

        fragmentManager = getSupportFragmentManager();

    }


    private void file() {
        File directory=new File(path+"/WoodAppData");
        File file=new File(directory+"/data.csv");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String[] savetext={};
        if (circle_radius!=0){
            shape="circle";
            savetext= new String[]{"shape" + "," + "radius" + "," + "thickness" + "," + "edge",
                    "circle" + "," + circle_radius + "," + thickness_value+","+ edge};

        }
        if ((rect_length!=0 && rect_breadth!=0)){
            shape="rectangle";
            savetext= new String[]{"shape" + "," + "length" +","+"breadth" +"," + "thickness" + "," + "edge",
                    "rectangle" + "," + rect_length +","+rect_breadth +"," + thickness_value+","+ edge};
        }
        if (oval_length!=0 && oval_breadth!=0){
            shape="oval";
            savetext= new String[]{"shape" + "," + "length" +","+"breadth" +"," + "thickness" + "," + "edge",
                    "oval" + "," + oval_length +","+oval_breadth +"," + thickness_value+","+ edge};
        }


        Save(file,savetext);
    }

    private void Save(File file, String[] savetext) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            try {
                for (int i = 0; i < savetext.length; i++) {
                    fos.write(savetext[i].getBytes());
                    if (i < savetext.length - 1) {
                        fos.write("\n".getBytes());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Intent intent1 = new Intent(getBaseContext(), MainActivity.class);
        intent1.putExtra("edge",edge);
        intent1.putExtra("shape",shape);
        intent1.putExtra("thickness",thickness_value);
        startActivity(intent1);
        Bundle b = new Bundle();


    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                file();
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            file();
            return true;
        }
    }

    public void rectangle_length(float length) {
         rect_length=length;
        woodCanvas.rectangleLength(rect_length);

    }

    public void rectangle_breadth(float breadth) {
        rect_breadth=breadth;
        woodCanvas.rectangleBreadth(rect_breadth);
    }

    public void oval_length(float length) {
        oval_length=length;
        woodCanvas.ovalLength(length);

    }

    public void oval_breadth(float breadth) {
        oval_breadth=breadth;
        woodCanvas.ovalBreadth(breadth);

    }

    public void circle_radius(float radius) {
        circle_radius=radius;
        woodCanvas.createCircle(radius);

    }


    void showPopupWindow(final View view) {

        final PopupMenu popup = new PopupMenu(DrawActivity.this, view);
        try {
            Field[] fields = popup.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popup);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper.getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod("setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popup.getMenuInflater().inflate(R.menu.pencil_popup, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.l:
                    fragmentManager.beginTransaction()
                            .replace(R.id.buttom, new ValueFragment(), "DOWN_MENU")
                            .commit();
                    break;
                case R.id.s:
                    fragmentManager.beginTransaction()
                            .replace(R.id.buttom, new Value_Fragment2(), "DOWN_MENU")
                            .commit();
                    break;
                case R.id.x:
                    fragmentManager.beginTransaction()
                            .replace(R.id.buttom, new ValueFragment(), "DOWN_MENU")
                            .commit();
                    break;

                case R.id.y:
                    fragmentManager.beginTransaction()
                            .replace(R.id.buttom, new Value_Fragment2(), "DOWN_MENU")
                            .commit();
                    break;

                default:
            }
            return false;
        });

    }

}
