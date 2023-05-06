package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton back;
    Button create_acc;
    EditText name,username,password,birth, mpin;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back = findViewById(R.id.btnback);
        create_acc = findViewById(R.id.btncreatacc);
        name = findViewById(R.id.registername);
        username = findViewById(R.id.registerusername);
        password = findViewById(R.id.registerpassword);
        birth = findViewById(R.id.registerbirth);
        mpin = findViewById(R.id.registermpin);
        login = findViewById(R.id.txtlogin);

        back.setOnClickListener(this);
        create_acc.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent a;
        if(view.getId() == back.getId())  {
            Toast.makeText(this, "Back button has been pressed", Toast.LENGTH_SHORT).show();
            a = new Intent(Register_Activity.this, Login_Activity.class);
            startActivity(a);
        }else if(view.getId() == login.getId()) {
            Toast.makeText(this, "Redirecting to login page", Toast.LENGTH_SHORT).show();
            a = new Intent(Register_Activity.this, Login_Activity.class);
            startActivity(a);
        } else if(view.getId() == create_acc.getId()) {
            Toast.makeText(this, "Create account button has been pressed", Toast.LENGTH_SHORT).show();
        }
    }
}