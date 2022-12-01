package com.example.dodge;


import static com.example.dodge.KollisionsErkennung.isCollisionDetected;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;


import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

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
    private ShapeableImageView[] game_IMG_hearts;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        toast("hello");
        moveMeteor(6006, asteroid1);
        moveMeteor(3000, asteroid2);
        moveMeteor(5000, asteroid3);
        // checkForCollision(asteroid1); //vibrate and remove heart implemented but collision doesn't work..


        left_BTN.setOnClickListener(view -> {
            moveCar(-1);
        });
        right_BTN.setOnClickListener(view -> {
            moveCar(1);
        });

    }

    int Strike = 0;

    private void checkForCollision(ShapeableImageView img) {
        int x1= (int) img.getX();
        int  y1= (int) img.getY();
        int x2= (int) carPlacement.getX();
        int  y2= (int) carPlacement.getY();

        Bitmap bitmap1=((BitmapDrawable)asteroid1.getDrawable()).getBitmap();
        Bitmap bitmap2=((BitmapDrawable)carPlacement.getDrawable()).getBitmap();
        if (isCollisionDetected(bitmap1, x1, y1, bitmap2, x2, y2) == true) {
            Strike++;
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            toast("collision detected");
            game_IMG_hearts[game_IMG_hearts.length - Strike].setVisibility(View.INVISIBLE);
        }
    }


    private void moveMeteor(int milliSeconds, ShapeableImageView asteroid) {

        Display display = getWindowManager().getDefaultDisplay();

        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, display.getHeight()); // new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(milliSeconds); // animation duration
        animation.setRepeatCount(Animation.INFINITE); // animation repeat count


        asteroid.startAnimation(animation);


    }

    private void toast(String str) {

        Toast
                .makeText(this, str, Toast.LENGTH_LONG)
                .show();
    }


    private void moveCar(int direction) {
        if (direction == 1 && carPlacement.getX() < 500) {
            carPlacement.getX();
            System.out.println("carPlacement.getX()");
            carPlacement.setX(carPlacement.getX() + 350);
        }
        if (direction == -1 && carPlacement.getX() > 100) {
            carPlacement.getX();
            System.out.println("carPlacement.getX()");
            carPlacement.setX(carPlacement.getX() - 350);
        }
    }


    private void findViews() {
        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3),

        };
        left_BTN = findViewById(R.id.left_BTN);
        right_BTN = findViewById(R.id.right_BTN);
        carPlacement = findViewById(R.id.game_IMG_car);
        asteroid1 = findViewById(R.id.meteor1);
        asteroid2 = findViewById(R.id.meteor2);
        asteroid3 = findViewById(R.id.meteor3);


    }
}