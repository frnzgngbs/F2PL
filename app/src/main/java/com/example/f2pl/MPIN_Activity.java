package com.example.f2pl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MPIN_Activity extends AppCompatActivity implements View.OnClickListener {

    View view_01, view_02, view_03, view_04;
    Button btn_01, btn_02, btn_03, btn_04, btn_05,btn_06, btn_07, btn_08, btn_09, btn_00, btn_clear;

    ArrayList<String> numbers_list = new ArrayList<>();
    String passCode = "";
    String MPIN = "1234";
    String num_01, num_02, num_03, num_04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);
        initializeComponenents();
        savePassCode(MPIN);

    }

    private void initializeComponenents() {
        view_01 = findViewById(R.id.view_01);
        view_02 = findViewById(R.id.view_02);
        view_03 = findViewById(R.id.view_03);
        view_04 = findViewById(R.id.view_04);

        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03);
        btn_04 = findViewById(R.id.btn_04);
        btn_05 = findViewById(R.id.btn_05);
        btn_06 = findViewById(R.id.btn_06);
        btn_07 = findViewById(R.id.btn_07);
        btn_08 = findViewById(R.id.btn_08);
        btn_09 = findViewById(R.id.btn_09);
        btn_00 = findViewById(R.id.btn_00);
        btn_clear = findViewById(R.id.btn_clear);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_07.setOnClickListener(this);
        btn_08.setOnClickListener(this);
        btn_09.setOnClickListener(this);
        btn_00.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btn_01) {
            numbers_list.add("1");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_02) {
            numbers_list.add("2");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_03) {
            numbers_list.add("3");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_04) {
            numbers_list.add("4");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_05) {
            numbers_list.add("5");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_06) {
            numbers_list.add("6");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_07) {
            numbers_list.add("7");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_08) {
            numbers_list.add("8");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_09) {
            numbers_list.add("9");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_00) {
            numbers_list.add("0");
            passNumber(numbers_list);
        } else if (view.getId() == R.id.btn_clear) {
            numbers_list.clear();
            passNumber(numbers_list);
        }
    }

    private void passNumber(ArrayList<String> numbers_list) {
        if(numbers_list.size() == 0){
            view_01.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_02.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_03.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_04.setBackgroundResource(R.drawable.bg_view_grey_oval);
        }else{
            switch(numbers_list.size()){
                case 1:
                    num_01 = numbers_list.get(0);
                    view_01.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 2:
                    num_02 = numbers_list.get(1);
                    view_02.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 3:
                    num_03 = numbers_list.get(2);
                    view_03.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 4:
                    num_04 = numbers_list.get(3);
                    view_04.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    passCode = num_01 + num_02 + num_03 + num_04;
                    if(getPassCode().length() == 0){
                        savePassCode(passCode);
                    }else{
                        matchPassCode();
                    }
                    break;
            }
        }
    }

    private void matchPassCode() {
        String enteredMPIN = passCode.trim();  // Trim the entered passcode to remove leading/trailing whitespaces

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        db.collection("user")
                .whereEqualTo("mpin", enteredMPIN)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot document = task.getResult();
                            if (document != null && !document.isEmpty()) {
                                String email = document.getDocuments().get(0).getString("email");
                                String password = document.getDocuments().get(0).getString("password");

                                // Sign in with retrieved email and password
                                mAuth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // User signed in successfully
                                                    startActivity(new Intent(MPIN_Activity.this, MainPage.class));
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    clearMPIN();
                                                    // Perform further actions with the signed-in user if needed
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(MPIN_Activity.this, "Incorrect MPIN", Toast.LENGTH_SHORT).show();
                            clearMPIN();
                        }
                    }
                });
    }

    private SharedPreferences.Editor savePassCode(String passCode){
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.commit();

        return editor;
    }

    private String getPassCode(){
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        return preferences.getString("passcode", "");
    }

    private void clearMPIN() {
        numbers_list.clear();
        view_01.setBackgroundResource(R.drawable.bg_view_grey_oval);
        view_02.setBackgroundResource(R.drawable.bg_view_grey_oval);
        view_03.setBackgroundResource(R.drawable.bg_view_grey_oval);
        view_04.setBackgroundResource(R.drawable.bg_view_grey_oval);
    }
}
