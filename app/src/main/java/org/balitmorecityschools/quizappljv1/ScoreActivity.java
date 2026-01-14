package org.balitmorecityschools.quizappljv1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {
    int totalScore;
    Intent incomingIntent;
    TextView scoreTV;
    SharedPreferences myPrefs;
    SharedPreferences.Editor spEditor;
    final String SHARED_PREF_FILE = "org.baltimorecityschools.sharefpreferenceslj";
    final String SCORE_KEY = "SCORE";
    final String SUBMITTED_KEY = "SUBMITTED";
    final String ID_KEY = "ID";
    final String NAME_KEY = "NAME";
    boolean submitted;
    Button leaderboardBTN, restartBTN;
    EditText nameET;
    String name, savedName, deviceID;
    FirebaseDatabase database;
    DatabaseReference myRef;
    LeaderboardEntry user;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        myPrefs = getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE);
        spEditor = myPrefs.edit();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        totalScore = 0;
        submitted = myPrefs.getBoolean(SUBMITTED_KEY, false);
        deviceID = myPrefs.getString(ID_KEY,"");
        restartBTN = findViewById(R.id.restartBTN);
        leaderboardBTN = findViewById(R.id.leaderboardBTN);
        leaderboardBTN.setVisibility(View.INVISIBLE);
        nameET = findViewById(R.id.nameET);
        nameET.setVisibility(View.INVISIBLE);
        incomingIntent = getIntent();
        totalScore = incomingIntent.getIntExtra(getString(R.string.totalscore),totalScore);
        scoreTV = (TextView) findViewById(R.id.scoreTV);
        scoreTV.setText(getString(R.string.score_msg_pt1) + " " + totalScore + getString(R.string.score_msg_pt2));



        if (!submitted){
            nameET.setVisibility(View.VISIBLE);
            leaderboardBTN.setVisibility(View.VISIBLE);
        }

        leaderboardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameET.getText().toString().isEmpty()){
                    Toast empty = Toast.makeText(ScoreActivity.this, "Please enter a name.", Toast.LENGTH_SHORT);
                    empty.show();
                }
                else {
                    name = nameET.getText().toString();
                    savedName = " " + name;
                    spEditor = myPrefs.edit();
                    spEditor.putString(NAME_KEY, savedName);
                    spEditor.apply();
                    submitted = true;
                    spEditor.putBoolean(SUBMITTED_KEY, submitted);
                    spEditor.apply();
                    deviceID = myRef.push().getKey();
                    user = new LeaderboardEntry(name, totalScore, deviceID);
                    myRef.child(deviceID).setValue(user);
                    Toast uploaded = Toast.makeText(ScoreActivity.this, "Your scores have been uploaded to the leaderboard.", Toast.LENGTH_LONG);
                    uploaded.show();
                    nameET.setVisibility(View.INVISIBLE);
                    leaderboardBTN.setVisibility(View.INVISIBLE);
                    spEditor.putString(ID_KEY, deviceID);
                    spEditor.apply();




                }

            }
        });
        restartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        int highScore = myPrefs.getInt(SCORE_KEY,0);
        String name = myPrefs.getString(NAME_KEY,"user");
        spEditor = myPrefs.edit();
        if(highScore<=totalScore){
            spEditor.putInt(SCORE_KEY, totalScore);
            spEditor.apply();
            deviceID = myPrefs.getString(ID_KEY, "null");
            user = new LeaderboardEntry(name, totalScore, deviceID);
            myRef.child(deviceID).setValue(user);

        }




    }
}