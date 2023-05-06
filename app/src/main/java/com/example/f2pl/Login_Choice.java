package com.example.f2pl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Choice extends AppCompatActivity implements View.OnClickListener{

    Button login, mpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        login = (Button) findViewById(R.id.btnLogin);
        mpin = (Button) findViewById(R.id.btnMPIN);

        login.setOnClickListener(this);
        mpin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        if(view.getId() == login.getId()) {
            i = new Intent(Login_Choice.this, Login_Activity.class);
            startActivity(i);
        } else if(view.getId() == mpin.getId()) {
            i = new Intent(Login_Choice.this, MPIN_Activity.class);
            startActivity(i);
        }
    }
}