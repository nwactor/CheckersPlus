package com.example.nick.checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class OptionsInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_interface);
        Intent intent = getIntent();
        String message = ("DeezNuts");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

    }

    public void returnToMain(View v){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);

    }


}