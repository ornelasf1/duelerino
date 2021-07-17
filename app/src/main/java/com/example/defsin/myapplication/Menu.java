package com.example.defsin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Menu extends AppCompatActivity {
    EditText p1Name;
    EditText p2Name;
    public static String user1Name;
    public static String user2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("");
        p1Name = (EditText)findViewById(R.id.player1Input);
        p2Name = (EditText)findViewById(R.id.player2Input);
        p1Name.setText(user1Name);
        p2Name.setText(user2Name);
    }

    public void clickLifePoints(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        user1Name = p1Name.getText().toString();
        user2Name = p2Name.getText().toString();

        startActivity(intent);
    }

    public void clickTurnPicker(View v){
        Intent intent = new Intent(this, TurnPicker.class);
        user1Name = p1Name.getText().toString();
        user2Name = p2Name.getText().toString();

        startActivity(intent);
    }

    public void clickDie(View v){
        Intent intent = new Intent(this, DieRoll.class);
        startActivity(intent);
    }

    public void clickCoin(View v){
        Intent intent = new Intent(this, FlipCoin.class);
        startActivity(intent);
    }

}
