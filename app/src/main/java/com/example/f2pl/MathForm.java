package com.example.f2pl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MathForm extends AppCompatActivity implements View.OnClickListener{

    Button ansA, ansB, ansC, ansD, submit;
    TextView totalQuestion, numberQuestion, timer;
    private int score = 0;
    private int ctr_question = 1;
    private int totalQuestions;
    private int currentIndex = 0;
    private String selectedAnswer = "";
    private int numberofHint = 1;
    ImageView hint;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    MathQuestion math = new MathQuestion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startTimer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_form);

        totalQuestions = math.question.length;

        totalQuestion = findViewById(R.id.totalQuestion);
        numberQuestion = findViewById(R.id.question);
        ansA = findViewById(R.id.A);
        ansB = findViewById(R.id.B);
        ansC = findViewById(R.id.C);
        ansD = findViewById(R.id.D);
        submit = findViewById(R.id.submitanswer);
        timer = findViewById(R.id.timer);
        hint = findViewById(R.id.hint);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);
        hint.setOnClickListener(this);

        totalQuestion.setText("Total Question: " + totalQuestions);
        displayQuestion();
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(ContextCompat.getColor(this, R.color.f2plorange));
        ansB.setBackgroundColor(ContextCompat.getColor(this, R.color.f2plorange));
        ansC.setBackgroundColor(ContextCompat.getColor(this, R.color.f2plorange));
        ansD.setBackgroundColor(ContextCompat.getColor(this, R.color.f2plorange));

        if (view.getId() == hint.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer hints = document.getLong("hint").intValue();
                            if(hints != 0) {
                                useHint();
                                docRef.update("hint", --hints);
                            }else {
                                Toast.makeText(MathForm.this, "No more available hints.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                        }
                    } else {
                    }
                }
            });
        } else {
            Button selectedChoice = (Button) view;
            if (selectedChoice.getId() == R.id.submitanswer) {
                if (selectedAnswer.equals(math.answer[currentIndex])) {
                    score++;
                }
                if (ctr_question > 10) {
                    --ctr_question;
                    displayQuestion();
                } else {
                    displayQuestion();
                }
                currentIndex++;
                loadNewQuestion();
            } else {
                selectedAnswer = selectedChoice.getText().toString();
                selectedChoice.setBackgroundColor(Color.DKGRAY);
            }
        }
    }

    public void loadNewQuestion() {

        if(currentIndex == totalQuestions) {
            finishQuiz();
            return;
        }
        numberQuestion.setText(ctr_question++ + "." +math.question[currentIndex]);
        ansA.setText(math.choices[currentIndex][0]);
        ansB.setText(math.choices[currentIndex][1]);
        ansC.setText(math.choices[currentIndex][2]);
        ansD.setText(math.choices[currentIndex][3]);
    }


    void finishQuiz() {
        String uID = user.getUid();
        DocumentReference documentRef = db.collection("user").document(uID);
        Map<String, Object> data = new HashMap<>();

        data.put("math_score", score);

        documentRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }

                });
        String passStatus = "";
        if (score > 6) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Document data exists
                            int coins = Objects.requireNonNull(document.getLong("coins")).intValue();
                            if (score == 10) {
                                docRef.update("coins", coins+=2);
                            } else if (score < 10 && score >= 6) {
                                docRef.update("coins", coins+=1);
                            }
                        } else {
                            // Document doesn't exist
                            Log.d("TAG", "Document does not exist");
                            // Show an appropriate error message or handle the case when the document is not found
                        }
                    } else {
                        // Error getting document
                        Log.d("TAG", "Error getting document: " + task.getException());
                        // Show an appropriate error message or handle the error case
                    }
                }
            });
            if (score > 6) {
                passStatus = "Passed";
            } else {
                passStatus = "Failed";
            }

        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score: " + score + " out of " + totalQuestions)
                .setPositiveButton("Quit", (dialogInterface, i) -> quitQuiz())
                .setCancelable(false)
                .show();
    }

    void quitQuiz() {
        Intent i = new Intent(MathForm.this, MainPage.class);
        startActivity(i);
    }

    public void startTimer() {
        new CountDownTimer(420000, 1000) {
            @Override
            public void onTick(long l) {
                int min = (int) (l/1000) / 60;
                int sec = (int) (l/1000) % 60;

                String time = String.format("%02d:%02d", min, sec);
                timer.setText(time);
                // timer.setText(String.valueOf((l/1000)/60) + ":" + String.valueOf((l/1000)%60));
            }

            @Override
            public void onFinish() {
                finishQuiz();
            }
        }.start();
    }
    void displayQuestion() {
        totalQuestion.setText("Total Question: " + ctr_question + "/" +totalQuestions);
    }

    public void useHint() {
        String ans = math.answer[currentIndex];
        if (ansA.getText().toString().equals(ans)) {
            ansA.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else if (ansB.getText().toString().equals(ans)) {
            ansB.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else if (ansC.getText().toString().equals(ans)) {
            ansC.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        } else if (ansD.getText().toString().equals(ans)) {
            ansD.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        }
    }

}