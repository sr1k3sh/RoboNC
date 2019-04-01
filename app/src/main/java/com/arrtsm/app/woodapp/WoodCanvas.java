package com.arrtsm.app.woodapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.rgb;

public class WoodCanvas extends View implements View.OnTouchListener {
    private ScaleGestureDetector scaleDetector;
    private float displayScale = 1.0f;
    //grid array
    public ArrayList<Integer> x11 = new ArrayList<Integer>();
    public ArrayList<Integer> y11 = new ArrayList<Integer>();
    public ArrayList<Integer> x22 = new ArrayList<Integer>();
    public ArrayList<Integer> y22 = new ArrayList<Integer>();

    //rectangle
    RectF rect =new RectF();
    private float left_topX=0,left_topY=0,right_bottomX=0,right_bottomY=0;
    //event initilization
    private float eventX,eventY;
    //Canvas
    Paint paint;
    private int grid_color_id = -16776216; //grid color line

    //circle
    private float circle_radius=0;

    //rectangle
    private float rectangle_length=0,rectangle_breadth=0;

    //oval
    private float oval_length=0,oval_breadth=0,oval_arc=0;

    public WoodCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
       // scaleDetector = new ScaleGestureDetector(this.getContext(), new ScaleListener());
        //setOnTouchListener(this);
       // this.setWillNotDraw(false);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.scale(displayScale,displayScale);
        //paint.setColor(rgb(255, 245, 240));
       //// paint.setColor(Color.RED);
       // paint.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(0.7f, -0.7f);
        //draw grid line
        paint.setColor(grid_color_id);
        paint.setColor(rgb(207, 216, 220));
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        if (x11.size() != 0) {
            for (int i = 0; i < x11.size(); i++) {
                canvas.drawLine(x11.get(i), y11.get(i), x22.get(i), y22.get(i), paint);
            }
        }
       paint.setColor(rgb(207, 216, 220));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(-5000, 0, 5000, 0, paint);
        canvas.drawLine(0, -5000, 0, 5000, paint);
        //draw grid view end of line

        //scaling and translating
        //canvas.translate(-getWidth() / 2, getHeight() / 2);
       // canvas.scale(1,-1);
        //drawing rectangle
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);

        if(circle_radius!=0) {
            canvas.drawCircle(0, 0, circle_radius, paint);
        }
        if(rectangle_length!=0 && rectangle_breadth!=0){
            canvas.drawRect(-rectangle_length/2,rectangle_breadth/2,rectangle_length/2,-rectangle_breadth/2,paint);

        }
        if(oval_length!=0&& oval_breadth!=0){
            float oval_length1=oval_length-(oval_breadth/2);
           // canvas.drawRect(0-oval_length/2,0,oval_length/2,oval_breadth,paint);
            canvas.drawLine((-oval_length1/2),0,(oval_length1/2),0,paint);
            canvas.drawLine((-oval_length1/2),(oval_breadth),(oval_length1/2),(oval_breadth),paint);
            rect.set(-oval_length1/2-oval_breadth/2,0,-oval_length1/2+oval_breadth/2,oval_breadth);
            Log.d("woodcanvas_oval",""+oval_length1+"  "+oval_breadth);
            canvas.drawArc(rect,90,180,false,paint);
            rect.set(oval_length1/2-oval_breadth/2,0,oval_length1/2+oval_breadth/2,oval_breadth);
            canvas.drawArc(rect,270,180,false,paint);
        }
       // RectF rect = new RectF(0-400, 400, 400,0-400);
       //// rect.set(0-400, 400, 400,0-400);
     //   canvas.drawRect(0,0,400,400,paint);
     //   canvas.drawRect(rect,paint);
     //   canvas.drawArc(rect,270,180,true,paint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        Toast.makeText(getContext(),"This is first test", Toast.LENGTH_LONG).show();
       // scaleDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                Log.d("WoodCanvas_action"," down");
                eventX=event.getX();
                eventY=event.getY();
               // RectTop(eventX,eventY);
                invalidate();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                Log.d("WoodCanvas_action"," move");
                eventX=event.getX();
                eventY=event.getY();
               // RectBottom(eventY,eventY);
                invalidate();
                break;
            }
            case MotionEvent.ACTION_UP: {
                Log.d("WoodCanvas_action"," up");
                invalidate();
                break;
            }
            default:{
                invalidate();
                    break;
            }

        }
        return true;
    }


    public void grid() {
        int i = -5000;
        while (i <= 5000) {
            x11.add(-5000);
            y11.add(i);
            x22.add(5000);
            y22.add(i);
            i = i + 100;
        }
        i = -5000;
        while (i <= 5000) {
            x11.add(i);
            y11.add(-5000);
            x22.add(i);
            y22.add(5000);
            i = i + 100;
        }
        invalidate();

    }

    public void createCircle(float radius) {
        circle_radius=radius;
        invalidate();
    }

    public void rectangleBreadth(float breadth) {
        rectangle_breadth=breadth;
        invalidate();
        Log.d("woodcanvas_breadth",""+breadth);

    }

    public void rectangleLength(float length) {
        rectangle_length=length;
        invalidate();
        Log.d("woodcanvas_length",""+rectangle_length);

    }


    public void ovalLength(float length) {
        oval_length=length;
        Log.d("woodcanvas_length",""+oval_length);
        invalidate();
    }

    public void ovalBreadth(float breadth) {
        oval_breadth=breadth;
        Log.d("woodcanvas_breadth",""+oval_breadth);
        invalidate();
    }


    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float newDisplayScale = displayScale * scaleGestureDetector.getScaleFactor();
            displayScale = newDisplayScale;
            invalidate();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

        }
    }
}
