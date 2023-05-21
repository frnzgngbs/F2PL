package com.example.f2pl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.Map;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    CardView ctgProfile, ctgExchange, ctgScience, ctgSports, ctgGaming, ctgHistory, ctgProg, ctgMath, ctgLocation, ctgCalendar, ctgContacts, ctgTheme;
    TextView profile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView numberOfCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

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
        numberOfCoins = findViewById(R.id.coins);
        profile = findViewById(R.id.profile);

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
    }

    @Override
    public void onClick(View view) {
        Intent i;
        if (view.getId() == ctgProfile.getId()) {
            Toast.makeText(this, "User Profile", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgExchange.getId()) {
            Toast.makeText(this, "Exchange coins", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgScience.getId()) {
            i = new Intent(MainPage.this, ScienceForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Science category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgSports.getId()) {
            i = new Intent(MainPage.this, SportsForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Sports category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgGaming.getId()) {
            i = new Intent(MainPage.this, GamingForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Gaming category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgHistory.getId()) {
            i = new Intent(MainPage.this, HistoryForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "History category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgProg.getId()) {
            i = new Intent(MainPage.this, ProgForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Programming category", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == ctgMath.getId()) {
            i = new Intent(MainPage.this, MathForm.class);
            startActivity(i);
            notifyUser();
            Toast.makeText(this, "Mathematics category", Toast.LENGTH_SHORT).show();
        }else if(ctgTheme.getId() == view.getId()) {
            Toast.makeText(this, "Theme has been clicked", Toast.LENGTH_SHORT).show();
        } else if(ctgContacts.getId() == view.getId()) {
            Toast.makeText(this, "Contacts has been clicked", Toast.LENGTH_SHORT).show();
        } else if(ctgCalendar.getId() == view.getId()) {
            Toast.makeText(this, "Calendar has been clicked", Toast.LENGTH_SHORT).show();
        } else if(ctgLocation.getId() == view.getId()) {
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