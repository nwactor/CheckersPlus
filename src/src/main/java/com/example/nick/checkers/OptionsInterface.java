package com.example.nick.checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class OptionsInterface extends AppCompatActivity {
   protected CheckersApp appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_interface);

        //get the global state of the app
        this.appState = ((CheckersApp) getApplicationContext());

        RadioGroup difficultyGroup = (RadioGroup) findViewById(R.id.difficultySetting);
        difficultyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.easyRadio: OptionsInterface.this.appState.setDifficulty("Easy"); break;
                    case R.id.hardRadio: OptionsInterface.this.appState.setDifficulty("Hard"); break;
                }
            }
        });

        RadioGroup colorGroup1 = (RadioGroup) findViewById(R.id.p1ColorGroup);
        colorGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.p1Blue: OptionsInterface.this.appState.setP1Color("Blue"); break;
                    case R.id.p1Green: OptionsInterface.this.appState.setP1Color("Green"); break;
                    case R.id.p1Red: OptionsInterface.this.appState.setP1Color("Red"); break;
                    case R.id.p1Purple: OptionsInterface.this.appState.setP1Color("Purple"); break;
                    case R.id.p1Orange: OptionsInterface.this.appState.setP1Color("Orange"); break;
                    case R.id.p1White: OptionsInterface.this.appState.setP1Color("White"); break;
                    case R.id.p1Tan: OptionsInterface.this.appState.setP1Color("Tan"); break;
                }
            }
        });

        RadioGroup colorGroup2 = (RadioGroup) findViewById(R.id.p2ColorGroup);
        colorGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.p2Green: OptionsInterface.this.appState.setP2Color("Green"); break;
                    case R.id.p2Blue: OptionsInterface.this.appState.setP2Color("Blue"); break;
                    case R.id.p2Red: OptionsInterface.this.appState.setP2Color("Red"); break;
                    case R.id.p2Purple: OptionsInterface.this.appState.setP2Color("Purple"); break;
                    case R.id.p2Orange: OptionsInterface.this.appState.setP2Color("Orange"); break;
                    case R.id.p2White: OptionsInterface.this.appState.setP2Color("White"); break;
                    case R.id.p2Tan: OptionsInterface.this.appState.setP2Color("Tan"); break;
                }
            }
        });

        //Have the correct radio button selected on activity start

        RadioButton difficultySelection;
        switch(appState.getDifficulty()) {
            case "Easy": difficultySelection = (RadioButton) findViewById(R.id.easyRadio);
                difficultySelection.setChecked(true); break;
            case "Hard": difficultySelection = (RadioButton) findViewById(R.id.hardRadio);
                difficultySelection.setChecked(true); break;
        }

        RadioButton p1ColorSelection;
        switch(appState.getP1Color()) {
            case "Blue": p1ColorSelection = (RadioButton) findViewById(R.id.p1Blue);
                p1ColorSelection.setChecked(true); break;
            case "Green": p1ColorSelection = (RadioButton) findViewById(R.id.p1Green);
                p1ColorSelection.setChecked(true); break;
            case "Red": p1ColorSelection = (RadioButton) findViewById(R.id.p1Red);
                p1ColorSelection.setChecked(true); break;
            case "Purple": p1ColorSelection = (RadioButton) findViewById(R.id.p1Purple);
                p1ColorSelection.setChecked(true); break;
            case "Orange": p1ColorSelection = (RadioButton) findViewById(R.id.p1Orange);
                p1ColorSelection.setChecked(true); break;
            case "White": p1ColorSelection = (RadioButton) findViewById(R.id.p1White);
                p1ColorSelection.setChecked(true); break;
            case "Tan": p1ColorSelection = (RadioButton) findViewById(R.id.p1Tan);
                p1ColorSelection.setChecked(true); break;
        }

        RadioButton p2ColorSelection;
        switch(appState.getP2Color()) {
            case "Blue": p2ColorSelection = (RadioButton) findViewById(R.id.p2Blue);
                p2ColorSelection.setChecked(true); break;
            case "Green": p2ColorSelection = (RadioButton) findViewById(R.id.p2Green);
                p2ColorSelection.setChecked(true); break;
            case "Red": p2ColorSelection = (RadioButton) findViewById(R.id.p2Red);
                p2ColorSelection.setChecked(true); break;
            case "Purple": p2ColorSelection = (RadioButton) findViewById(R.id.p2Purple);
                p2ColorSelection.setChecked(true); break;
            case "Orange": p2ColorSelection = (RadioButton) findViewById(R.id.p2Orange);
                p2ColorSelection.setChecked(true); break;
            case "White": p2ColorSelection = (RadioButton) findViewById(R.id.p2White);
                p2ColorSelection.setChecked(true); break;
            case "Tan": p2ColorSelection = (RadioButton) findViewById(R.id.p2Tan);
                p2ColorSelection.setChecked(true); break;
        }


    }

    public void returnToMain(View v){
        //Intent intent = new Intent(this, MainMenu.class);
        //startActivity(intent);
        finish();
    }
}