package org.balitmorecityschools.quizappljv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {
    TextView greetingTV;
    Button startBTN, leaderboardBTN;
    SharedPreferences myPrefs;
    String name;
    final String SHARED_PREF_FILE = "org.baltimorecityschools.sharefpreferenceslj";
    final String SCORE_KEY = "SCORE";
    final String NAME_KEY = "NAME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        greetingTV = findViewById(R.id.greetingTV);
        startBTN = findViewById(R.id.startBTN);
        leaderboardBTN = findViewById(R.id.leaderboardBTN);
        myPrefs = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
        int highScore = myPrefs.getInt(SCORE_KEY, 0);
        name = myPrefs.getString(NAME_KEY, "");
        if (highScore == 100){
            greetingTV.setText("Welcome back" + name + ", You're highest score is a 100, lets see if you can get that again.");
        } else if (highScore > 0) {
            greetingTV.setText("Welcome back" + name + ", You're highest score so far is a " + highScore + getString(R.string.welcome_back_part_2));
        }
        else {
            greetingTV.setText(R.string.welcome_new_player);
        }
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizScreen = new Intent(WelcomeActivity.this, QuizActivity.class);
                startActivity(quizScreen);
            }
        });
        leaderboardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardScreen = new Intent(WelcomeActivity.this, LeaderboardScreen.class);
                startActivity(leaderboardScreen);
            }
        });

    }
}