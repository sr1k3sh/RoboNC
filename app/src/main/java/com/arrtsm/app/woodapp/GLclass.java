package com.arrtsm.app.woodapp;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.arrtsm.app.woodapp.rendering.ObjectRenderer;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLclass extends AppCompatActivity implements GLSurfaceView.Renderer {
    private GLSurfaceView surfaceView;
    private int mPtrCount = 0;
    private MotionEvent motionEvent;
    private GestureDetector gestureDetector;
    private final ObjectRenderer virtualObject = new ObjectRenderer();
    private String objName , textureName ;
    private Triangle mTriangle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aractivity);
        surfaceView = findViewById(R.id.surfaceview);

        textureName="woodtexture/cherry-wood.jpg";
        objName="box1.obj";
        // Set up renderer.
        surfaceView.setPreserveEGLContextOnPause(true);
        surfaceView.setEGLContextClientVersion(2);
        surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0); // Alpha used for plane blending.
        surfaceView.setRenderer(this);
        surfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        mTriangle = new Triangle();
    }
    private final float[] anchorMatrix = new float[16];
    @Override
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        // Get projection matrix.

        //camera.getViewMatrix(viewmtx, 0);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        mTriangle.draw();
    }
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }
}
