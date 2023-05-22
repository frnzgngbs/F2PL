package com.example.f2pl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText email, password, confirm_pass, MPIN;
    Button btnCreate;
    TextView Signin;
    ImageView back;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


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
        String user_email =email.getText().toString();
        String user_password = password.getText().toString();
        String mpin = MPIN.getText().toString();
        if(view.getId() == btnCreate.getId()) {


            if(!user_password.equals(confirm_pass.getText().toString())) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(user_email, user_password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Created Successfully", Toast.LENGTH_SHORT).show();

                            FirebaseUser currentUser = mAuth.getCurrentUser();

                            String uid = currentUser.getUid();

                            User user = new User(user_email, user_password, mpin,0,0,0,0,0,0, 0, 2, 0, true, true, true, true, true, true);
                            DocumentReference docRef = db.collection("user").document(uid);
                            docRef.set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Register.this, "Created successfully", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(Register.this, "Redirecting to the main page...", Toast.LENGTH_SHORT).show();
                                            mAuth.signInWithEmailAndPassword(user_email, user_password)
                                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            startActivity(new Intent(Register.this, MainPage.class));
                                                            FirebaseUser signedInUser = mAuth.getCurrentUser();
                                                        }
                                                    });
                                        }
                                    });

                            // TODO: Add your desired logic after successful registration
                        } else {
                            String errorMessage = task.getException().getMessage();
                            Log.e("RegistrationError", errorMessage); // Log the error message for debugging
                            Toast.makeText(this, "Creation failed", Toast.LENGTH_SHORT).show();
                            // Registration failed, display a message to the user
                        }
                    });
        } else if(view.getId() == Signin.getId()) {
            i = new Intent(Register.this, LoginUser.class);
            startActivity(i);
        } else if(view.getId() == back.getId()) {
            i = new Intent(Register.this, LoginUser.class);
            startActivity(i);
        }
    }
}