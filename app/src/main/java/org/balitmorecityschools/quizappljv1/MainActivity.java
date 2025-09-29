package org.balitmorecityschools.quizappljv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    RadioButton dRB, selectedRB;
    RadioGroup questionAnswersRG;
    Button doneBTN;
    int clickCounter, score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionAnswersRG = findViewById(R.id.questionAnswers_RG);
        doneBTN = findViewById(R.id.done_BTN);
        dRB = findViewById(R.id.answerD_RB);
        doneBTN.setVisibility(View.INVISIBLE);
        clickCounter = 0;
        score = 0;
        questionAnswersRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                selectedRB = findViewById(checkedId);
                doneBTN.setVisibility(View.VISIBLE);
            }
        });
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickCounter = clickCounter + 1;
                if (clickCounter == 1) {
                    if (selectedRB.getId() == dRB.getId()) {
                        score++;
                        Toast correct = Toast.makeText(MainActivity.this, R.string.q1_correct_msg, Toast.LENGTH_LONG);
                        correct.show();

                    }
                    else {
                        Toast incorrect = Toast.makeText(MainActivity.this, R.string.q1_incorrect_msg, Toast.LENGTH_LONG);
                        incorrect.show();
                    }
                    doneBTN.setText(R.string.str_continue_btn);
                }
                if (clickCounter == 2) {
                    Intent scoreScreen = new Intent(MainActivity.this, ScoreActivity.class);
                    scoreScreen.putExtra("score", score);
                    startActivity(scoreScreen);
                }

            }
        });

    }
}