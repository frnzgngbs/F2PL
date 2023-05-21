package com.example.f2pl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ProfileCardView extends AppCompatActivity {
    private ImageView profileImageView;
    private static final int PICK_IMAGE_REQUEST = 1;

    private String userEmail;
    private String userPassword;
    private String userMpin;

    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView mpinTextView;
    private Button editButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve the user data from the intent's extras
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        userPassword = intent.getStringExtra("password");
        userMpin = intent.getStringExtra("mpin");

        profileImageView = findViewById(R.id.profileImageView);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        mpinTextView = findViewById(R.id.mpinTextView);

        // Set the retrieved user data to the EditText elements
        emailTextView.setText(userEmail);
        passwordTextView.setText(userPassword);
        mpinTextView.setText(userMpin);

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTextView.requestFocus();
            }
        });

        passwordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordTextView.requestFocus();
            }
        });

        mpinTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mpinTextView.requestFocus();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void saveChanges() {
        userEmail = emailTextView.getText().toString();
        userPassword = passwordTextView.getText().toString();
        userMpin = mpinTextView.getText().toString();

        // Perform saving logic here

        // Optionally, you can update the TextViews with the saved values
        emailTextView.setText(userEmail);
        passwordTextView.setText(userPassword);
        mpinTextView.setText(userMpin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}