package com.example.defsin.myapplication;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FlipCoin extends AppCompatActivity {
    ImageView img;
    AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_coin);
        setTitle(R.string.menuButtCoinFlip);
        img = (ImageView)findViewById(R.id.coinDisplay);
        img.setBackgroundResource(R.drawable.coin_animation);
        frameAnimation = (AnimationDrawable)img.getBackground();
    }

    public void clickFlipButton(View v){
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
                        resultCoin();
                        img.setBackgroundResource(R.drawable.coin_animation);
                        frameAnimation = (AnimationDrawable)img.getBackground();
                    }
                });
            }
        },5500);
    }

    public void resultCoin(){
        Random randGen = new Random();
        int randInt = randGen.nextInt(100)+1;
        if((randInt%2)==0){
            img.setImageResource(R.drawable.headscoin);
        }else{
            img.setImageResource(R.drawable.tailscoin);
        }
    }

}
