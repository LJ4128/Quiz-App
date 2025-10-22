package org.balitmorecityschools.quizappljv1;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView questionTV;
    Question[] questionList;
    Question currentQuestion, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10;
    RadioButton aRB, bRB, cRB, dRB, selectedRB;
    RadioGroup questionAnswersRG;
    Button doneBTN, hintBTN;
    boolean hintViewed;
    int doneCounter, hintCounter, totalScore, questionCounter, questionPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTV = findViewById(R.id.Question_TV);
        questionAnswersRG = findViewById(R.id.questionAnswers_RG);
        doneBTN = findViewById(R.id.done_BTN);
        hintBTN = findViewById(R.id.hint_BTN);
        aRB = findViewById(R.id.answerA_RB);
        bRB = findViewById(R.id.answerB_RB);
        cRB = findViewById(R.id.answerC_RB);
        dRB = findViewById(R.id.answerD_RB);
        q1 = new Question(getString(R.string.str_q1),getString(R.string.steve_kerr),getString(R.string.stephen_curry),getString(R.string.steve_nash),getString(R.string.joe_harris),"This player is currently the head coach of an nba team.", getString(R.string.correct_msg),getString(R.string.q1_incorrect_msg));
        q2 = new Question("What year was the NBA founded?", "1946", "1951", "1937", "1944","This was the year before the start of The Cold War.", "Correct.", "Incorrect, the NBA was founded in 1946.");
        q3 = new Question("What team had the longest win streak in NBA history?", "Los Angeles Lakers", "Golden State Warriors", "Miami Heat", "Houston Rockets", "This team won 17 Championships.", "Correct.", "Incorrect, the Los Angeles Lakers has the longest win streak in NBA history.");
        q4 = new Question("Who won MVP of 1983?", "Moses Malone", "Larry Bird", "Magic Johnson","Kareem Abdul-Jabbar", "This player was on the Philadelphia 76ers.", "Correct,", "Incorrect, Moses Malone won MVP of 1983.");
        q5 = new Question("Who won Rookie of the Year in 1990", "David Robinson", "Derrick Coleman", "Gary Payton", "Tyrone Hill", "This player was nicknamed \"The Admiral\" for his time spend in the U.S. Navy.", "Correct", "Incorrect, David Robinson won Rookie of the Year in 1990");
        q6 = new Question("Who was the #1 overall draft pick in 2003", "LeBron James", "Carmelo Anthony", "Chris Bosh", "Dwayne Wade", "","Correct","Incorrect, LeBron James was the #1 overall draft pick in 2003.");
        doneBTN.setVisibility(View.INVISIBLE);
        hintViewed = false;
        doneCounter = 0;
        hintCounter = 0;
        totalScore = 0;
        questionCounter = 0;
        questionPoints = 0;
        questionList = new Question[]{q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};
        ArrayList<RadioButton> choiceList = new ArrayList<>();
        choiceList.add(aRB);
        choiceList.add(bRB);
        choiceList.add(cRB);
        choiceList.add(dRB);
        nextQuestion(choiceList);
        questionAnswersRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                selectedRB = findViewById(checkedId);
                doneBTN.setVisibility(View.VISIBLE);
            }
        });
        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintCounter += 1;
                if (hintCounter == 1){
                    Toast warning = Toast.makeText(MainActivity.this, "Viewing the hint will reduce the points you earn,", Toast.LENGTH_LONG);
                    warning.show();
                }
                if (hintCounter == 2){
                    hintViewed = true;
                    Intent hintScreen = new Intent(MainActivity.this, HintViewActivity.class);
                    hintScreen.putExtra("hintSTR",currentQuestion.getHintText());
                    startActivity(hintScreen);
                }
            }
        });
        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doneCounter = doneCounter + 1;
                if (doneCounter == 1)
                {
                    if (selectedRB.getText().equals(currentQuestion.getCorrectAnswer()) && hintViewed == false) {
                        questionPoints = 10;
                        Toast correct = Toast.makeText(MainActivity.this,currentQuestion.correctMessage, Toast.LENGTH_LONG);
                        correct.show();

                    }
                    else if(selectedRB.getText().equals(currentQuestion.getCorrectAnswer()) && hintViewed == true){
                        questionPoints = 5;
                        Toast correct = Toast.makeText(MainActivity.this,currentQuestion.correctMessage, Toast.LENGTH_LONG);
                    }
                    else {
                        Toast incorrect = Toast.makeText(MainActivity.this, currentQuestion.getIncorrectMessage(), Toast.LENGTH_LONG);
                        incorrect.show();
                        questionPoints = 0;
                    }
                    doneBTN.setText(R.string.str_continue_btn);
                    hintBTN.setVisibility(View.INVISIBLE);
                }
                else if((doneCounter == 2) && (questionCounter == questionList.length))
                {
                    totalScore += questionPoints;
                    Intent scoreScreen = new Intent(MainActivity.this, ScoreActivity.class);
                    scoreScreen.putExtra("totalScore", totalScore);
                    startActivity(scoreScreen);
                }
                else if(doneCounter == 2)
                {
                    totalScore += questionPoints;
                    nextQuestion(choiceList);
                    doneBTN.setText("DONE");
                }

            }
        });

    }
    private void setQnA(Question question, ArrayList<RadioButton> choices,TextView questionDisplayTV)
    {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.add(question.getAnswer1());
        answers.add(question.getAnswer2());
        answers.add(question.getAnswer3());
        questionDisplayTV.setText(question.getQText());
        int choice = 0;

        while(!answers.isEmpty())
        {
            int number = (int)(answers.size() * Math.random());
            choices.get(choice).setText(answers.remove(number));
            choice++;

        }
    }
    private void nextQuestion(ArrayList<RadioButton> choiceList)
    {
        currentQuestion = questionList[questionCounter];
        setQnA(currentQuestion,choiceList,questionTV);
        doneCounter = 0;
        hintCounter = 0;
        questionPoints = 10;
        questionCounter++;
        hintViewed = false;
        hintBTN.setVisibility(View.VISIBLE);

    }













}