package com.example.f2pl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ProfileCardView extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText email, password, confirmpass, MPIN;
    private Button btnSaveChanges, btnCancelChanges;
    private ImageView back;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.profileemail);
        password = findViewById(R.id.profilepassword);
        confirmpass = findViewById(R.id.profileconfimrpasss);
        MPIN = findViewById(R.id.profileMPIN);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnCancelChanges = findViewById(R.id.btnCancelChanges);
        back = findViewById(R.id.backMainPage);


        String uid = user.getUid();
        DocumentReference docRef = db.collection("user").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String user_email = document.getString("email");
                        String user_password = document.getString("password");
                        String user_MPIN = document.getString("mpin");
                        email.setText(user_email);
                        password.setText(user_password);
                        confirmpass.setText(user_password);
                        MPIN.setText(user_MPIN);
                    }
                }
            }
        });
        MPIN.setFocusable(false);
        MPIN.setFocusableInTouchMode(false);
        btnSaveChanges.setOnClickListener(this);
        btnCancelChanges.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == btnSaveChanges.getId()) {
            Toast.makeText(this, "Saving Changes...", Toast.LENGTH_SHORT).show();
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> user_change = new HashMap<>();
                            user_change.put("email", email.getText());
                            user_change.put("password", password.getText());
                            docRef.update(user_change);
                        }
                    }
                }
            });
        } else if(view.getId() == btnCancelChanges.getId()) {
            Toast.makeText(this, "Discarding Changes...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ProfileCardView.this, MainPage.class));
        } else if(view.getId() == back.getId()) {
            startActivity(new Intent(ProfileCardView.this, MainPage.class));
        }
    }
}