package org.balitmorecityschools.quizappljv1;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    int totalScore;
    Intent incomingIntent;
    TextView scoreTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        totalScore = 0;
        incomingIntent = getIntent();
        totalScore = incomingIntent.getIntExtra(getString(R.string.totalscore),totalScore);
        scoreTV = (TextView) findViewById(R.id.scoreTV);
        scoreTV.setText(getString(R.string.score_msg_pt1) + " " + totalScore + getString(R.string.score_msg_pt2));


    }
}