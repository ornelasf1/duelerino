package com.example.defsin.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton;
import android.widget.ViewSwitcher;


public class MainActivity extends AppCompatActivity {
    String msg = "Android: ";
    private TextView screen;
    private TextView user1Points;
    private TextView user2Points;
    private LifePointAnim activeUser;
    private ToggleButton user1TogButt;
    private ToggleButton user2TogButt;
    private String str, str2, str3, result;
    private static String p1SavedPoints, p2SavedPoints;
    private int a, b, c;
    LifePointAnim LP1;
    LifePointAnim LP2;
    ImageView imgSwitch;

    private Menu userNames = new Menu();
    private String p1Name = userNames.user1Name;
    private String p2Name = userNames.user2Name;

    public static boolean pointsBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.menuButtLifePoints);
        Log.d(msg, "The onCreate() event");
        screen = (TextView)findViewById(R.id.selectedPoints);
        user1TogButt = (ToggleButton)findViewById(R.id.user1Button);
        user2TogButt = (ToggleButton)findViewById(R.id.user2Button);

        user1Points = (TextView)findViewById(R.id.user1);
        user2Points = (TextView)findViewById(R.id.user2);
        LinearLayout ly = (LinearLayout)findViewById(R.id.pointsRow);
        ly.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resetPoints(v);
                return true;
            }
        });

        imgSwitch = (ImageView)findViewById(R.id.turnAnimLeft);
        user2Points.setTextColor(Color.BLACK);

        if(p1SavedPoints != null) {
            user1Points.setText(p1SavedPoints);
        }
        if(p2SavedPoints != null){
            user2Points.setText(p2SavedPoints);
        }

        LP1 = new LifePointAnim(user1Points,8000);
        LP2 = new LifePointAnim(user2Points,8000);

        activeUser = LP1;
        str = "";

        user1TogButt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    activeUser = LP1;
                } else {
                    // The toggle is disabled
                    activeUser = LP2;
                }
            }
        });

        preserveNames();

    }

    public void preserveNames(){
        if(p1Name.length() != 0){
            user1TogButt.setTextOn(p1Name);
            user1TogButt.setTextOff(p1Name);
            user1TogButt.setText(p1Name);
            checkIfSpecial(user1TogButt, p1Name, "IVAN", "OVEN");
            checkIfSpecial(user1TogButt, p1Name, "JESUS", "JZ");
        }else{
            user1TogButt.setTextOn("Player 1");
            user1TogButt.setTextOff("Player 1");
            user1TogButt.setText("Player 1");
        }
        if(p2Name.length() != 0){
            user2TogButt.setTextOn(p2Name);
            user2TogButt.setTextOff(p2Name);
            user2TogButt.setText(p2Name);
            checkIfSpecial(user2TogButt, p2Name, "IVAN", "OVEN");
            checkIfSpecial(user2TogButt, p2Name, "JESUS", "JZ");
        }else{
            user2TogButt.setTextOn("Player 2");
            user2TogButt.setTextOff("Player 2");
            user2TogButt.setText("Player 2");
        }
    }

    public void numClick(View v){
        final Button button = (Button)v;
        str += button.getText().toString();
        screen.setText(str);
//        button.setOnTouchListener(new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event){
//                if(event.getAction()==MotionEvent.ACTION_DOWN){
//                    button.setBackgroundResource(R.drawable.points_color_pressedbuttons);
//                }
//                if(event.getAction()==MotionEvent.ACTION_UP){
//                    button.setBackgroundResource(R.drawable.points_color_buttons);
//                }
//                return true;
//            }
//        });
        //a = Double.parseDouble(str);
        Log.d("Log", str);
    }

    public void multiplyPoints(View v){
        final Button btn = (Button)v;
        int num = Integer.parseInt(screen.getText().toString());
        if(btn.getTag().toString().compareTo("10") == 0){
            num *= 10;
        }else if(btn.getTag().toString().compareTo("100") == 0){
            num *= 100;
        }else{
            num *= 1000;
        }
        str = Integer.toString(num);
        Log.d("Log",str);
        screen.setText(str);
        str = "";

    }

    public void clearClick(View v){
        screen.setText("0");
        str="";
    }

    public void resetPoints(View v){
        LP1.resetLP();
        LP2.resetLP();
    }

    public void gainClick(View v){
        a = Integer.parseInt(screen.getText().toString());
        b = activeUser.getLifePoints();
        c = a + b;
        if(!pointsBusy) {
            activeUser.increaseLifePoints(c);
        }
        screen.setText("0");
        str = "";
    }

    public void attckClick(View v){
        a = Integer.parseInt(screen.getText().toString());
        b = activeUser.getLifePoints();
        c = b - a;
        if(!pointsBusy) {
            activeUser.decreaseLifePoints(c);
        }
        screen.setText("0");
        str="";
    }

    public void user1Toggle(View v){
        user1TogButt.setChecked(false);
        user2TogButt.setChecked(true);
        if((!user1TogButt.isChecked()) && user2TogButt.isChecked()){
            user1Points.setTextColor(Color.WHITE);
            user2Points.setTextColor(Color.BLACK);
            user1TogButt.toggle();
            user2TogButt.toggle();
            alternateTurns(imgSwitch, "left");
        }
    }

    public void user2Toggle(View v){
        user1TogButt.setChecked(true);
        user2TogButt.setChecked(false);
        if((!user2TogButt.isChecked()) && user1TogButt.isChecked()){
            user1Points.setTextColor(Color.BLACK);
            user2Points.setTextColor(Color.WHITE);
            user1TogButt.toggle();
            user2TogButt.toggle();
            alternateTurns(imgSwitch, "right");
        }
    }

    public void alternateTurns(View v, String direc){
        ObjectAnimator animatorx;
        float xorig = user1Points.getLeft()-20;
        float xalt = user2Points.getLeft()-20;
        Log.d("Log", "Xorig: " + xorig + " xalt: " + xalt);
        if(direc.compareTo("right") == 0) {
            animatorx = ObjectAnimator.ofFloat(imgSwitch, "x", xalt);
        }else{
            animatorx = ObjectAnimator.ofFloat(imgSwitch, "x", xorig);
            //https://stackoverflow.com/questions/24217357/determining-translationx-y-values-for-objectanimator-how-to-move-a-view-to-an-e
        }
        animatorx.setDuration(100);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorx);
        animatorSet.start();
    }

    public void checkIfSpecial(ToggleButton togButt, String sName, String person, String insult){
        if(sName.toUpperCase().equals(person)){
            togButt.setText(insult);
            togButt.setTextOn(insult);
            togButt.setTextOff(insult);
        }
    }


    protected void onStart(){
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    protected void onResume(){
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    protected void onPause(){
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    protected void onStop(){
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    protected void onDestroy(){
        super.onDestroy();
        p1SavedPoints = (String)user1Points.getText();
        p2SavedPoints = (String)user2Points.getText();
        Log.d(msg, "The onDestroy() event");
    }
}
