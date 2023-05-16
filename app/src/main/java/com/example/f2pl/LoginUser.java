package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginUser extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText email, password;
    Button btnLogin, btnMPIN;
    TextView Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        email = findViewById(R.id.tfEmail);
        password = findViewById(R.id.tfPassword);
        btnLogin = findViewById(R.id.btnLogin);
        Signup = findViewById(R.id.lblSignUp);
        btnMPIN = findViewById(R.id.viaMPIN);

        btnLogin.setOnClickListener(this);
        Signup.setOnClickListener(this);
        btnMPIN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        if(view.getId() == btnLogin.getId()) {
            i = new Intent(LoginUser.this, MainPage.class);
            startActivity(i);
        } else if(view.getId() == btnMPIN.getId()) {
            i = new Intent(LoginUser.this, MPIN_Activity.class);
            startActivity(i);
        }else if(view.getId() == Signup.getId()) {
            i = new Intent(LoginUser.this, Register.class);
            Toast.makeText(this, "Sign Up Page", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
     }
}