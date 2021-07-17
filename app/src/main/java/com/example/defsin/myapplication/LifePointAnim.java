package com.example.defsin.myapplication;

import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Defsin on 11/6/2017.
 */

public class LifePointAnim {
    private int userLP;
    private final int origLP;
    private TextView user = null;

    LifePointAnim(View v, int num){
        userLP = num;
        origLP = num;
        user = (TextView)v;
    }

    public int getLifePoints(){
        return userLP;
    }

    public void decreaseLifePoints(final int target){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(userLP > target){
                    MainActivity.pointsBusy = true;
                    int time, diff = target - userLP;
                    if(diff < 2000){
                        time = 5;
                        if(diff%20 == 0){
                            userLP -= 20;
                        }else if(diff%10 == 0){
                            userLP -= 10;
                        }else{
                            userLP -= 1;
                        }
                    }else{
                        time = 5;
                        if(diff%100 == 0){
                            userLP -= 100;
                        }else if(diff%70 == 0){
                            userLP -= 70;
                        }else if(diff%20 == 0){
                            userLP -= 20;
                        }else{
                            time = 1;
                            userLP -= 1;
                        }
                    }
                    setLifePoints(userLP);
                    handler.postDelayed(this,time);
                }else{
                    MainActivity.pointsBusy = false;
                }
            }
        });
    }

    public void increaseLifePoints(final int target){

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(userLP < target){
                    MainActivity.pointsBusy = true;
                    int time, diff = target - userLP;
                    if(diff < 2000){
                        time = 5;
                        if(diff%20 == 0){
                            userLP += 20;
                        }else if(diff%10 == 0){
                            userLP += 10;
                        }else{
                            userLP += 1;
                        }
                    }else{
                        time = 5;
                        if(diff%100 == 0){
                            userLP += 100;
                        }else if(diff%70 == 0){
                            userLP += 70;
                        }else if(diff%20 == 0){
                            userLP += 20;
                        }else{
                            time = 1;
                            userLP += 1;
                        }
                    }
                    setLifePoints(userLP);
                    handler.postDelayed(this,time);
                }else{
                    MainActivity.pointsBusy = false;
                }
            }
        });
    }

    private void setLifePoints(int lp){
        if(lp >= 100000){
            user.setText("100000");
            userLP = 100000;
        }else if(lp <= 0){
            user.setText("0");
            userLP = 0;
        }else{
            user.setText(Integer.toString(userLP));
        }
    }

    public void resetLP(){
        userLP = origLP;
        user.setText(Integer.toString(userLP));
    }
}
