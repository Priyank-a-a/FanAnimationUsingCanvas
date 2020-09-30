package com.example.animationone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
//here direct drawing is not allowed..first the surface is locked..drawn and then it is released
//surface view class has its own surface and is not related to our thread
public class FanView extends SurfaceView implements Runnable{
    Canvas canvas;
    SurfaceHolder surfaceHolder;
    Paint paint;
    Thread thread = null;
    int x=0;
    boolean isRotating;

    public FanView(Context context) {
        super(context);
        init();
    }

    public void init()
    {
      paint = new Paint();

      surfaceHolder = getHolder();
      surfaceHolder.addCallback(new SurfaceHolder.Callback() {
          @Override
          public void surfaceCreated(SurfaceHolder surfaceHolder) {
              startThread();

          }

          @Override
          public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

          }

          @Override
          public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

          }
      });

    }



    public void startThread()
    {
        isRotating= true;
        thread = new Thread(this);
        thread.start();
    }
    public void stopThread()
    {
        isRotating = false;
        try {
       thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (isRotating)
        {
         if(surfaceHolder.getSurface().isValid())
         {
             canvas = surfaceHolder.lockCanvas();
             synchronized (this) //this makes sure that till i am not done creating my things, all other threads have to wait
             {
                 canvas.drawColor(Color.GREEN);
                 paint.setColor(Color.WHITE);
                 canvas.drawArc(300,300,1000,1000,x,30,true,paint);
                 canvas.drawArc(300,300,1000,1000,x+120,30,true,paint);
                 canvas.drawArc(300,300,1000,1000,x+240,30,true,paint);
                 x=x+10;


                 try {
                  Thread.sleep(200);
                 }
                 catch (InterruptedException e)
                 {
                 e.printStackTrace();
                 }
                 finally {
                     if(canvas!=null)
                     {
                         surfaceHolder.unlockCanvasAndPost(canvas);
                     }
                 }

             }

         }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startThread();
                break;
            case MotionEvent.ACTION_UP:
                stopThread();
                break;
        }
        return true;
    }
}
