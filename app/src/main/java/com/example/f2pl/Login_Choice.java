package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_Choice extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin, btnMPIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnMPIN = (Button) findViewById(R.id.btnMPIN);

        btnLogin.setOnClickListener(this);
        btnMPIN.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        if(view.getId() == R.id.btnLogin) {
            i = new Intent(Login_Choice.this, Login_Activity.class);
            startActivity(i);
        } else if(view.getId() == R.id.btnMPIN) {
            i = new Intent(Login_Choice.this, Login_Activity.class);
            startActivity(i);
        }
    }
}