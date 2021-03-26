package com.instaprofiler.app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.instaprofiler.app.R;

public class HomeActivity extends AppCompatActivity {
    ImageButton home_search_button;
    EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home_search_button = findViewById(R.id.home_search_button);
        userName = findViewById(R.id.home_insta_username);
        home_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                String userId = userName.getText().toString();
                intent.putExtra("userName", (Parcelable) userName);
                startActivity(intent);
            }
        });

    }

}