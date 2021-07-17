package com.example.defsin.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DieRoll extends AppCompatActivity {
    ImageView img;
    AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_die_roll);
        setTitle(R.string.menuButtRollDie);
        img = (ImageView)findViewById(R.id.dieDisplay);
        img.setBackgroundResource(R.drawable.die_animation);
        frameAnimation = (AnimationDrawable)img.getBackground();
        img.setImageResource(R.drawable.dieunk);
    }

    public void clickRollButton(View v){
        img.setImageResource(android.R.color.transparent);
        frameAnimation.setVisible(false, true);
        frameAnimation.start();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timer.cancel();
                        resultRoll();
                        img.setBackgroundResource(R.drawable.die_animation);
                        frameAnimation = (AnimationDrawable)img.getBackground();
                    }
                });
            }
        },2300);
    }

    public void resultRoll(){
        Random randGen = new Random();
        int randInt = randGen.nextInt(6) + 1;
        switch (randInt){
            case 1:
                img.setImageResource(R.drawable.dieone);
                break;
            case 2:
                img.setImageResource(R.drawable.dietwo);
                break;
            case 3:
                img.setImageResource(R.drawable.diethree);
                break;
            case 4:
                img.setImageResource(R.drawable.diefour);
                break;
            case 5:
                img.setImageResource(R.drawable.diefive);
                break;
            case 6:
                img.setImageResource(R.drawable.diesix);
                break;
            default:
                break;
        }
    }

}
