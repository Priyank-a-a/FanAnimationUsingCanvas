package com.example.animationone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    FanView fanView;
    int c1,c2,c3;
    ImageView iv1;
    Canvas canvas;
    Paint pElement,pText; //pElement for drawing things and pText for drawing text
    Bitmap bitmap;
    Rect r1;//for rectangle

    int factor=100;
    //int factor=20; //by what value do we want to change the width and height..it can be any value
    //greater the value of factor, more will be the dist between two rect
    int start = factor;
    //first way to add another view is to create its object in main activity
    //second way is by creating it in main .xml file and adding sec cons in myView class
//MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
  //  myView = new MyView(this);
    //    setContentView(myView);
        fanView = new FanView(this);
        setContentView(fanView);
        iv1 = findViewById(R.id.iv1);
        c1 = ResourcesCompat.getColor(getResources(),R.color.c1,null);
        c2 = ResourcesCompat.getColor(getResources(),R.color.c2,null);
        c3 = ResourcesCompat.getColor(getResources(),R.color.c3,null);
        pElement = new Paint();
        pText = new Paint(Paint.UNDERLINE_TEXT_FLAG); //can be initialised this way also
        pElement.setTextSize(50);
        r1 = new Rect();




    }

    @Override
    protected void onStop() {

        super.onStop();
        fanView.stopThread();
    }

    public void drawThis(View view) {
    int viewWidth= view.getWidth();
    int viewHeight = view.getHeight();
    int halfWidth = viewWidth/2;
    int halfHeight = viewHeight/2;


    if(start == factor)
    {
        bitmap = Bitmap.createBitmap(viewWidth,viewHeight,Bitmap.Config.ARGB_8888);//importing scheme-argb- alpha red green blue
        //we create a bitmap and then attach it to th imageView and in bitmap we attach the canvas
        iv1.setImageBitmap(bitmap);
        canvas = new Canvas(bitmap); //attaching bitmap to canvas
        canvas.drawColor(c1);
        pText.setColor(Color.BLACK); // for text color
        pText.setTextSize(60);
        canvas.drawText("Keep changing",100,100,pText);
        start = start+factor;
    }
    else
    {
        if(start<halfHeight && start<halfHeight)
        {
          r1.set(start,start,viewWidth-start,viewHeight-start);
          //pElement.setColor(c2); -- if this is done then rectangles will et created when we click again and again but notice nhi hoga kyunki color is but start is
            //changing so rectangle craete honge..so to see the rectangles, we use the below method..we change the value of color so as to observe rectangles
          pElement.setColor(c2- start*200);
          canvas.drawRect(r1,pElement);
          start+=factor;
        }
        else
        {
          //draw a circle
            pElement.setColor(c3);
            canvas.drawCircle(halfWidth,halfHeight,halfWidth/3,pElement); //so that circle appears from centre
            canvas.drawText("Done",halfWidth-100,halfHeight,pText);


        }
    }
    view.invalidate(); //redraws the view



    }
}