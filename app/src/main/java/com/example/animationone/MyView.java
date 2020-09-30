package com.example.animationone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.Color.RED;

public class MyView extends View {
    //Paint is a class and its obj acts like a paint brush, we can change its properties like brush's thickness
   Paint paint;
   int x=0;
    public MyView(Context context) {
        super(context);
        init();

    } //cons used when view created using java code

    //cons used when view created using xml code
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //method for initialization
    void init()
    {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(RED);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(30);
       /* canvas.drawLine(0,0,400,400,paint); //coordinates- x1,y1 and x2,y2
        paint.setColor(Color.BLACK);
        float arr[] = {400,400,600,0,600,0,800,400,800,400,1000,0}; //one line coordinates = 3
        canvas.drawLines(arr,paint); //method to make more than one line at a time
        paint.setColor(Color.GREEN);
        canvas.drawRect(400,400,800,900,paint); //creates a rectangle filled with color..if want a hollow rect, use drawlines method
        canvas.drawCircle(600,300,100,paint);
        canvas.drawArc(400,600,1200,1400,0,90,true,paint);
        //when usecentre is true- it will show a filled arc where as if false, only a simple arc is drawn*/
       canvas.drawArc(300,300,1000,1000,x,30,true,paint);
        canvas.drawArc(300,300,1000,1000,x+120,30,true,paint);
        canvas.drawArc(300,300,1000,1000,x+240,30,true,paint);
        x=x+10;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN: //touching the surface--try action.MOVE
                 invalidate();
                break;
            case MotionEvent.ACTION_UP: //releasing the surface
                break;
                //surface View class for making something move continuously
        }
        return true;
    }
}
