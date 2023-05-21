package com.example.f2pl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    CardView ctgProfile, ctgExchange, ctgScience, ctgSports, ctgGaming, ctgHistory, ctgProg, ctgMath, ctgLocation, ctgCalendar, ctgContacts, ctgTheme,ctgleaderboards, ctgSignout;
    TextView profile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView numberOfCoins;
    private int bgcolor;
    private int clickedcolor;
    private int whitecolor;
    Map<String, Object> state = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        bgcolor = ContextCompat.getColor(getBaseContext(), R.color.f2plorange);
        clickedcolor = ContextCompat.getColor(getBaseContext(), R.color.selectedchoice);
        whitecolor = ContextCompat.getColor(getBaseContext(), R.color.white);
        ctgProfile = findViewById(R.id.cardProfile);
        ctgExchange = findViewById(R.id.cardExchange);
        ctgScience = findViewById(R.id.cardScience);
        ctgSports = findViewById(R.id.cardSports);
        ctgGaming = findViewById(R.id.cardGaming);
        ctgHistory = findViewById(R.id.cardHistory);
        ctgProg = findViewById(R.id.cardProg);
        ctgMath = findViewById(R.id.cardMath);
        ctgLocation = findViewById(R.id.cardlocation);
        ctgCalendar = findViewById(R.id.cardcalender);
        ctgContacts = findViewById(R.id.cardcontact);
        ctgTheme = findViewById(R.id.theme);
        ctgleaderboards = findViewById(R.id.cardRank);
        ctgSignout = findViewById(R.id.cardSignout);
        numberOfCoins = findViewById(R.id.coins);
        profile = findViewById(R.id.profile);

        ctgProfile.setCardBackgroundColor(bgcolor);
        ctgExchange.setCardBackgroundColor(bgcolor);
        ctgScience.setCardBackgroundColor(bgcolor);
        ctgScience.setCardBackgroundColor(bgcolor);
        ctgGaming.setCardBackgroundColor(bgcolor);
        ctgHistory.setCardBackgroundColor(bgcolor);
        ctgMath.setCardBackgroundColor(bgcolor);
        ctgLocation.setCardBackgroundColor(bgcolor);
        ctgleaderboards.setCardBackgroundColor(bgcolor);
        ctgCalendar.setCardBackgroundColor(bgcolor);
        ctgContacts.setCardBackgroundColor(bgcolor);
        ctgTheme.setCardBackgroundColor(bgcolor);
        ctgProg.setCardBackgroundColor(bgcolor);
        ctgSports.setCardBackgroundColor(bgcolor);
        ctgSignout.setCardBackgroundColor(bgcolor);

        ctgProfile.setOnClickListener(this);
        ctgExchange.setOnClickListener(this);
        ctgScience.setOnClickListener(this);
        ctgSports.setOnClickListener(this);
        ctgGaming.setOnClickListener(this);
        ctgHistory.setOnClickListener(this);
        ctgProg.setOnClickListener(this);
        ctgMath.setOnClickListener(this);
        ctgLocation.setOnClickListener(this);
        ctgCalendar.setOnClickListener(this);
        ctgContacts.setOnClickListener(this);
        ctgTheme.setOnClickListener(this);

        displayCoins();
        fetchProfileText();

        // TODO: get the state for each quizzes and set unclickable to false.
        String uid = user.getUid();
        DocumentReference docRef = db.collection("user").document(uid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Boolean math_state = document.getBoolean("math_state");
                        if(!math_state) {
                            ctgMath.setClickable(false);
                            ctgMath.setCardBackgroundColor(whitecolor);
                        }
                        Boolean science_state = document.getBoolean("science_state");
                        if(!science_state) {
                            ctgScience.setClickable(false);
                            ctgScience.setCardBackgroundColor(clickedcolor);
                        }
                        Boolean sports_state = document.getBoolean("sports_state");
                        if(!sports_state) {
                            ctgSports.setClickable(false);
                            ctgSports.setCardBackgroundColor(clickedcolor);
                        }
                        Boolean prog_state =document.getBoolean("prog_state");
                        if(!prog_state) {
                            ctgProg.setClickable(false);
                            ctgProg.setCardBackgroundColor(clickedcolor);
                        }
                        Boolean gaming_state = document.getBoolean("gaming_state");
                        if(!gaming_state) {
                            ctgGaming.setClickable(false);
                            ctgGaming.setCardBackgroundColor(clickedcolor);
                        }
                        Boolean history_state = document.getBoolean("history_state");
                        if(!history_state) {
                            ctgHistory.setClickable(false);
                            ctgHistory.setCardBackgroundColor(clickedcolor);
                        }
                        Integer takenQuiz = document.getLong("quizzes_taken").intValue();
                        if(takenQuiz == 3) {
                            ctgSports.setCardBackgroundColor(whitecolor);
                            ctgSports.setClickable(false);
                            ctgScience.setCardBackgroundColor(whitecolor);
                            ctgScience.setClickable(false);
                            ctgProg.setCardBackgroundColor(whitecolor);
                            ctgProg.setClickable(false);
                            ctgGaming.setCardBackgroundColor(whitecolor);
                            ctgGaming.setClickable(false);
                            ctgHistory.setCardBackgroundColor(whitecolor);
                            ctgHistory.setClickable(false);
                            ctgMath.setCardBackgroundColor(whitecolor);
                            ctgMath.setClickable(false);
                        }
                    } else {
                    }
                } else {
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        Intent i;
        if (view.getId() == ctgProfile.getId()) {
            Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgExchange.getId()) {
            Toast.makeText(this, "Exchange coins", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgScience.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("science_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, ScienceForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Science category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgSports.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("sports_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, SportsForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Sports category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgGaming.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("gaming_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, GamingForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Gaming category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgHistory.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("history_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, HistoryForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "History category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgProg.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("prog_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, ProgForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Programming category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgMath.getId()) {
            String uid = user.getUid();
            DocumentReference docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Integer quizTaken = document.getLong("quizzes_taken").intValue();
                            docRef.update("quizzes_taken", ++quizTaken);
                            docRef.update("math_state", false);
                        } else {
                        }
                    } else {
                    }
                }
            });
            i = new Intent(MainPage.this, MathForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Mathematics category", Toast.LENGTH_SHORT).show();
        } else if (ctgTheme.getId() == view.getId()) {
            Toast.makeText(this, "Theme has been clicked", Toast.LENGTH_SHORT).show();
        } else if (ctgContacts.getId() == view.getId()) {
            Toast.makeText(this, "Contacts has been clicked", Toast.LENGTH_SHORT).show();
        } else if (ctgCalendar.getId() == view.getId()) {
            Toast.makeText(this, "Calendar has been clicked", Toast.LENGTH_SHORT).show();
        } else if (ctgLocation.getId() == view.getId()) {
            Toast.makeText(this, "Location has been clicked", Toast.LENGTH_SHORT).show();
        }
    }

    public void notifyUser() {
        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(MainPage.this, "notification");
            builder.setContentTitle("F2PL");
            builder.setContentText("Hello! You are currently taking a quiz.");
            builder.setSmallIcon(R.drawable.baseline_notifications_active_24);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainPage.this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(1, builder.build());

        }
    }

    private void fetchProfileText() {

        // Assuming you have stored the user ID after login in a variable named "userId"
        String userId = user.getUid();
        db.collection("user")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String email = documentSnapshot.getString("email");
                        profile.setText(email);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to fetch profile", Toast.LENGTH_SHORT).show();
                });
    }

    private void displayCoins() {
        String uid = user.getUid();
        DocumentReference docRef = db.collection("user").document(uid);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Document data exists
                        Integer coins = document.getLong("coins").intValue();
                        numberOfCoins.setText(String.valueOf(coins));
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
    }
}