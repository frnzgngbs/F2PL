package com.example.f2pl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class LoginUser extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText txtemail, txtpassword;
    Button btnLogin, btnMPIN;
    TextView Signup;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtemail = findViewById(R.id.tfEmail);
        txtpassword = findViewById(R.id.tfPassword);
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

        String email = String.valueOf(txtemail.getText());
        String password = String.valueOf(txtpassword.getText());

        if(view.getId() == btnLogin.getId()) {
            if(email.equals("") || password.equals("")) {
                Toast.makeText(this, "Please input all fields.", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginUser.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginUser.this, MainPage.class));
                                finish();
                            } else {
                                Toast.makeText(LoginUser.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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