package org.balitmorecityschools.quizappljv1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;

public class HintViewActivity extends AppCompatActivity {
    TextView hintTV;
    Intent incomingIntent;
    String hintSTR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint_view);
        hintTV = findViewById(R.id.hint_TV);
        incomingIntent = getIntent();
        hintSTR = incomingIntent.getStringExtra(getString(R.string.hintstr));
        hintTV.setText(hintSTR);


    }
}