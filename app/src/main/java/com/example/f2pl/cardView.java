package com.example.f2pl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class cardView extends AppCompatActivity implements View.OnClickListener{
    private CardView prof, exchange, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        prof = (CardView) findViewById(R.id.cardProfile);
        exchange = (CardView) findViewById(R.id.cardExchange);
        calendar = (CardView) findViewById(R.id.cardcalendar);

        prof.setOnClickListener(this);
        exchange.setOnClickListener(this);
        calendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Intent i;

        if(v.getId() == R.id.cardProfile){
            i = new Intent(this, ProfileCardView.class);
        }else if(v.getId() == R.id.cardExchange){
            i = new Intent(this, ExchangeCardView.class);
        }else if(v.getId() == R.id.calendar){
            i = new Intent(this, Calender.class);
        }
    }
}
