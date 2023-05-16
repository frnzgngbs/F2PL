package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    CardView ctgProfile, ctgExchange, ctgScience, ctgSports, ctgGaming, ctgHistory, ctgProg, ctgMath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        
        ctgProfile = findViewById(R.id.cardProfile);
        ctgExchange = findViewById(R.id.cardExchange);
        ctgScience = findViewById(R.id.cardScience);
        ctgSports = findViewById(R.id.cardSports);
        ctgGaming = findViewById(R.id.cardGaming);
        ctgHistory = findViewById(R.id.cardHistory);
        ctgProg = findViewById(R.id.cardProg);
        ctgMath = findViewById(R.id.cardMath);
        
        ctgProfile.setOnClickListener(this);
        ctgExchange.setOnClickListener(this);
        ctgScience.setOnClickListener(this);
        ctgSports.setOnClickListener(this);
        ctgGaming.setOnClickListener(this);
        ctgHistory.setOnClickListener(this);
        ctgProg.setOnClickListener(this);
        ctgMath.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        if (view.getId() == ctgProfile.getId()) Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
        else if (view.getId() == ctgExchange.getId()) Toast.makeText(this, "Exchange coins", Toast.LENGTH_SHORT).show();
        else if (view.getId() == ctgScience.getId()) {
            i = new Intent(MainPage.this, ScienceForm.class);
            startActivity(i);
            Toast.makeText(this, "Science category", Toast.LENGTH_SHORT).show();
        }
        else if (view.getId() == ctgSports.getId()) Toast.makeText(this, "Sports category", Toast.LENGTH_SHORT).show();
        else if(view.getId() == ctgGaming.getId()) Toast.makeText(this, "Gaming category", Toast.LENGTH_SHORT).show();
        else if (view.getId() == ctgHistory.getId()) Toast.makeText(this, "History category", Toast.LENGTH_SHORT).show();
        else if (view.getId() == ctgProg.getId()) Toast.makeText(this, "Programming category", Toast.LENGTH_SHORT).show();
        else if(view.getId() == ctgMath.getId()) Toast.makeText(this, "Mathematics category", Toast.LENGTH_SHORT).show();
    }
}