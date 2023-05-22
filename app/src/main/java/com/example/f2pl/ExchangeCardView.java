package com.example.f2pl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExchangeCardView extends AppCompatActivity implements View.OnClickListener{

    Button reward1, reward2, reward3, reward4, reward5;
    ImageView backlogin;
    TextView coin1, coin2, coin3, coin4, coin5;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docRef;
    private String uid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);


        this.reward1 = findViewById(R.id.reward1);
        this.reward2 = findViewById(R.id.reward2);
        this.reward3 = findViewById(R.id.reward3);
        this.reward4 = findViewById(R.id.reward4);
        this.reward5 = findViewById(R.id.reward5);
        this.backlogin = findViewById(R.id.backlogin);
        coin1 = findViewById(R.id.item1cost);
        coin2 = findViewById(R.id.item2cost);
        coin3 = findViewById(R.id.item3cost);
        coin4 = findViewById(R.id.item4cost);
        coin5 = findViewById(R.id.item5cost);

        this.reward1.setOnClickListener(this);
        this.reward2.setOnClickListener(this);
        this.reward3.setOnClickListener(this);
        this.reward4.setOnClickListener(this);
        this.reward5.setOnClickListener(this);
        this.backlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == reward1.getId()) {
            uid = user.getUid();
            docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            Integer coins = document.getLong("coins").intValue();
                            int itemPrice = Integer.parseInt(coin1.getText().toString());
                            if(coins >= itemPrice) {
                                docRef.update("coins", coins-=itemPrice);
                                Toast.makeText(ExchangeCardView.this, "Coins have been exchanged for flashlight.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ExchangeCardView.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else if(view.getId() == reward2.getId()) {
            uid = user.getUid();
            docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            Integer coins = document.getLong("coins").intValue();
                            int itemPrice = Integer.parseInt(coin2.getText().toString());
                            if(coins >= itemPrice) {
                                docRef.update("coins", coins-=itemPrice);
                                Toast.makeText(ExchangeCardView.this, "Coins have been exchanged for mug.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ExchangeCardView.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else if(view.getId() == reward3.getId()) {
            uid = user.getUid();
            docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            Integer coins = document.getLong("coins").intValue();
                            int itemPrice = Integer.parseInt(coin3.getText().toString());
                            if(coins >= itemPrice) {
                                docRef.update("coins", coins-=itemPrice);
                                Toast.makeText(ExchangeCardView.this, "Coins have been exchanged for plate.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ExchangeCardView.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else if(view.getId() == reward4.getId()) {
            uid = user.getUid();
            docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            Integer coins = document.getLong("coins").intValue();
                            int itemPrice = Integer.parseInt(coin4.getText().toString());
                            if(coins >= itemPrice) {
                                docRef.update("coins", coins-=itemPrice);
                                Toast.makeText(ExchangeCardView.this, "Coins have been exchanged for cap.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ExchangeCardView.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else if(view.getId() == reward5.getId()) {
            uid = user.getUid();
            docRef = db.collection("user").document(uid);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()) {
                            Integer coins = document.getLong("coins").intValue();
                            int itemPrice = Integer.parseInt(coin5.getText().toString());
                            if(coins >= itemPrice) {
                                docRef.update("coins", coins-=itemPrice);
                                Toast.makeText(ExchangeCardView.this, "Coins have been exchanged for teddy bear.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ExchangeCardView.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        } else if(view.getId() == backlogin.getId()) {
            startActivity(new Intent(ExchangeCardView.this, MainPage.class));
        }
    }
}
