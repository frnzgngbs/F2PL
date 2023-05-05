package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login =  findViewById(R.id.Login);
        signup = findViewById(R.id.Signup);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent a;
        if (view.getId() == R.id.Login) Toast.makeText(this, "Login has been pressed", Toast.LENGTH_SHORT).show();
        else if(view.getId() == R.id.Signup) {
            Toast.makeText(this, "Sign Up has been pressed", Toast.LENGTH_SHORT).show();
            a = new Intent(Login_Activity.this, Register_Activity.class);
            startActivity(a);
        }
    }
}