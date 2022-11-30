package com.example.dodge;





import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;


import android.view.Display;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;




public class MainActivity extends AppCompatActivity {
    private MaterialButton left_BTN;
    private MaterialButton right_BTN;
    private ShapeableImageView carPlacement;
    private ShapeableImageView asteroid1;
    private ShapeableImageView asteroid2;
    private ShapeableImageView asteroid3;
    private RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        toast("hello");
        moveMeteor(6006,asteroid1);
        moveMeteor(3000,asteroid2);
        moveMeteor(5000,asteroid3);
       // checkForCollision();



            left_BTN.setOnClickListener(view -> {
                moveCar(-1);
            });
            right_BTN.setOnClickListener(view -> {
                moveCar(1);
            });

    }

    /*private void checkForCollision() {
           if(isCollisionDetected(x1,y1,bitmap1,x2,y2,bitmap2)==true){
            vibrate.
            toast("collision detected");
        }
    }*/


    private void moveMeteor(int milliSeconds,ShapeableImageView asteroid) {

        Display display = getWindowManager().getDefaultDisplay();

        TranslateAnimation animation = new TranslateAnimation(0,  0, 0, display.getHeight()); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(milliSeconds); // animation duration
        animation.setRepeatCount(Animation.INFINITE); // animation repeat count





        asteroid.startAnimation(animation);




    }

    private void toast(String str) {

        Toast
                .makeText(this,str,Toast.LENGTH_LONG)
                .show();
    }



    private void moveCar(int direction) {
        if (direction==1 &&carPlacement.getX()<500){
         carPlacement.getX();
         System.out.println( "carPlacement.getX()");
         carPlacement.setX( carPlacement.getX()+350);
        }
        if (direction==-1&&carPlacement.getX()>100){
            carPlacement.getX();
            System.out.println( "carPlacement.getX()");
            carPlacement.setX( carPlacement.getX()-350);
        }
    }


    private void findViews() {
    left_BTN=findViewById(R.id.left_BTN);
    right_BTN=findViewById(R.id.right_BTN);
    carPlacement=findViewById(R.id.game_IMG_car);
    asteroid1=findViewById(R.id.meteor1);
    asteroid2=findViewById(R.id.meteor2);
    asteroid3=findViewById(R.id.meteor3);


    }
}