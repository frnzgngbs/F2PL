package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.ktx.Firebase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText email, password, confirm_pass, MPIN;
    Button btnCreate;
    TextView Signin;
    ImageView back;

    DatabaseReference dbuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        email = findViewById(R.id.registeremail);
        password = findViewById(R.id.registerpassword);
        confirm_pass = findViewById(R.id.confirmpass);
        MPIN = findViewById(R.id.MPIN);
        btnCreate = findViewById(R.id.btnRegister);
        Signin = findViewById(R.id.lblSignin);
        back = findViewById(R.id.backlogin);

        btnCreate.setOnClickListener(this);
        Signin.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        if(view.getId() == btnCreate.getId()) {
            String email, password, confirmpass, mpin;
            email = String.valueOf(this.email.getText());
            password = String.valueOf(this.password.getText());
            confirmpass = String.valueOf(confirm_pass.getText());
            mpin = String.valueOf(MPIN.getText());


        } else if(view.getId() == Signin.getId()) {
            i = new Intent(Register.this, LoginUser.class);
            startActivity(i);
        } else if(view.getId() == back.getId()) {
            i = new Intent(Register.this, LoginUser.class);
            startActivity(i);
        }
    }
}