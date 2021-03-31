package com.instaprofiler.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.instaprofiler.app.R;

public class HomeActivity extends AppCompatActivity {
    ImageButton home_search_button, hamburger;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_search_button = findViewById(R.id.home_search_button);
        userName = findViewById(R.id.home_insta_username);
        hamburger = findViewById(R.id.hamburger);
        home_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                String userId = userName.getText().toString();
                intent.putExtra("userName", userId);
                startActivity(intent);
            }
        });

        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DrawerLayout.class);
                startActivity(intent);
            }
        });

    }

}