package com.example.nick.checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();

        String[] menuItems = {
                getString(R.string.play_computer),
                getString(R.string.play_friend),
                getString(R.string.go_options)
        };


    }

    public void testClick(View v){
        Intent intent = new Intent(this, OptionsInterface.class);
        startActivity(intent);

    }

    public void goToPlayFriend(View v) {
        Intent intent = new Intent(this, PlayFriendHome.class);
        startActivity(intent);
    }



}