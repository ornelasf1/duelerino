package com.example.defsin.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Random;

public class TurnPicker extends AppCompatActivity {
    private TextSwitcher turnSwitch;
    private Button turnButton;
    private String[] turnChoices = new String[2];
    private String name1, name2;

    private Menu userNames = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn);
        setTitle(R.string.menuButtTurnPicker);
        name1 = userNames.user1Name;
        name2 = userNames.user2Name;
        if(name1.length() != 0){
            turnChoices[0] = name1;
            if(name1.length() > 10){
                turnChoices[0] = name1.substring(0, 10);
            }
        }else{
            turnChoices[0] = "Player 1";
        }
        if(name2.length() != 0){
            turnChoices[1] = name2;
            if(name1.length() > 10){
                turnChoices[1] = name2.substring(0, 10);
            }
        }else{
            turnChoices[1] = "Player 2";
        }

        turnButton = (Button)findViewById(R.id.turnButton);
        turnSwitch = (TextSwitcher)findViewById(R.id.turnSwitcher);

        turnSwitch.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(TurnPicker.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(40);
                myText.setTextColor(Color.parseColor("#d60a03"));
                return myText;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        turnSwitch.setInAnimation(in);
        turnSwitch.setOutAnimation(out);
    }

    public void clickTurnButton(View v){
        Random randGen = new Random();
        int randInt = randGen.nextInt(1000)+1;
        if((randInt%2)==0){
            turnSwitch.setText(turnChoices[0]);
        }else{
            turnSwitch.setText(turnChoices[1]);
        }
    }
}
