package com.example.f2pl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ScienceForm extends AppCompatActivity implements View.OnClickListener{

    Button ansA, ansB, ansC, ansD, submit;
    TextView totalQuestion, numberQuestion, timer;
    ImageView back;
    private int score = 0;
    private int ctr_question = 1;
    private int totalQuestions;
    private int currentIndex = 0;
    private String selectedAnswer = "";
    ImageView hint;
    private int numberofHint = 1;
    ScienceQuestion science = new ScienceQuestion();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startTimer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_form);

        totalQuestions = science.question.length;

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
            if (numberofHint == 1) {
                useHint();
                numberofHint--;
            } else {
                Toast.makeText(this, "No more available hints.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Button selectedChoice = (Button) view;
            if (selectedChoice.getId() == R.id.submitanswer) {
                if (selectedAnswer.equals(science.answer[currentIndex])) {
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
        numberQuestion.setText(ctr_question++ + "." +science.question[currentIndex]);
        ansA.setText(science.choices[currentIndex][0]);
        ansB.setText(science.choices[currentIndex][1]);
        ansC.setText(science.choices[currentIndex][2]);
        ansD.setText(science.choices[currentIndex][3]);
    }


    void finishQuiz() {
        String passStatus = "";
            if(score > 6) {
                passStatus = "Passed";
            } else {
                passStatus = "Failed";
            }

            new AlertDialog.Builder(this)
                    .setTitle(passStatus)
                    .setMessage("Score: " + score + " out of " + totalQuestions)
                    .setPositiveButton("Quit", (dialogInterface, i) -> quitQuiz())
                    .setCancelable(false)
                    .show();
    }

    void quitQuiz() {
        Intent i = new Intent(ScienceForm.this, MainPage.class);
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
        String ans = science.answer[currentIndex];
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