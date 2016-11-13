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

        String[] menuItems = {
                getString(R.string.play_computer),
                getString(R.string.play_friend),
                getString(R.string.go_options)
        };

        ListView list = (ListView) findViewById(R.id.mainMenuList);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems);
        list.setAdapter(adapter);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        if(parent.getChildAt(position).equals(getString(R.string.play_computer))) {
            Intent intent = new Intent(this, PlayComputerHome.class);
            startActivity(intent);
        } else if(parent.getChildAt(position).equals(getString(R.string.play_friend))) {
            Intent intent = new Intent(this, PlayFriendHome.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, OptionsInterface.class);
            startActivity(intent);
        }
    }
}
