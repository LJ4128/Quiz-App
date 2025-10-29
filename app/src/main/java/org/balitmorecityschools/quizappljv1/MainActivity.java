package org.balitmorecityschools.quizappljv1;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        q1 = new Question(getString(R.string.str_q1),getString(R.string.steve_kerr),getString(R.string.stephen_curry),getString(R.string.steve_nash),getString(R.string.joe_harris),getString(R.string.str_q1_hint), getString(R.string.correct_msg),getString(R.string.q1_incorrect_msg));
        q2 = new Question(getString(R.string.str_q2), getString(R.string.str_1946), getString(R.string.str_1951), getString(R.string.str_1937), getString(R.string.str_1944),getString(R.string.str_q2_hint), getString(R.string.correct_msg), getString(R.string.q2_incorrect_msg));
        q3 = new Question(getString(R.string.str_q3), getString(R.string.los_angeles_lakers), getString(R.string.golden_state_warriors), getString(R.string.miami_heat), getString(R.string.houston_rockets), getString(R.string.str_q3_hint), getString(R.string.correct_msg), getString(R.string.q3_incorrect_msg));
        q4 = new Question(getString(R.string.str_q4), getString(R.string.moses_malone), getString(R.string.larry_bird), getString(R.string.magic_johnson),getString(R.string.kareem_abdul_jabbar), getString(R.string.str_q4_hint), getString(R.string.correct_msg), getString(R.string.q4_incorrect_msg));
        q5 = new Question(getString(R.string.str_q5), getString(R.string.david_robinson), getString(R.string.derrick_coleman), getString(R.string.gary_payton), getString(R.string.tyrone_hill), getString(R.string.str_q5_hint), getString(R.string.correct_msg), getString(R.string.q5_incorrect_msg));
        q6 = new Question(getString(R.string.str_q6), getString(R.string.lebron_james), getString(R.string.carmelo_anthony), getString(R.string.chris_bosh), getString(R.string.dwayne_wade), getString(R.string.str_q6_hint),getString(R.string.correct_msg),getString(R.string.q6_incorrect_msg));
        q7 = new Question(getString(R.string.str_q7), getString(R.string.charles_barkley), getString(R.string.hakeem_olajuwon), getString(R.string.michael_jordan), getString(R.string.shaquille_o_neal), getString(R.string.str_q7_hint), getString(R.string.correct_msg), getString(R.string.q7_incorrect_msg));
        q8 = new Question(getString(R.string.str_q8), getString(R.string.str_1996), getString(R.string.str_1994), getString(R.string.str_1997), getString(R.string.str_1992), getString(R.string.str_q8_hint), getString(R.string.correct_msg), getString(R.string.q8_incorrect_msg));
        q9 = new Question(getString(R.string.str_q9), getString(R.string.str_1978), getString(R.string.str_1980), getString(R.string.str_1977), getString(R.string.str_1976), getString(R.string.str_q9_hint), getString(R.string.correct_msg), getString(R.string.q9_incorrect_msg));
        q10 = new Question(getString(R.string.str_q10), getString(R.string.lebron_james), getString(R.string.kobe_bryant), getString(R.string.michael_jordan), getString(R.string.kareem_abdul_jabbar), getString(R.string.str_q10_hint), getString(R.string.correct_msg), getString(R.string.q10_incorrect_msg));

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
                    Toast warning = Toast.makeText(MainActivity.this, R.string.str_hint_warning, Toast.LENGTH_LONG);
                    warning.show();
                }
                if (hintCounter > 1
                ){
                    hintViewed = true;
                    Intent hintScreen = new Intent(MainActivity.this, HintViewActivity.class);
                    hintScreen.putExtra(getString(R.string.hintstr),currentQuestion.getHintText());
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
                    if (selectedRB.getText().equals(currentQuestion.getCorrectAnswer()) && !hintViewed) {
                        questionPoints = 10;
                        Toast correct = Toast.makeText(MainActivity.this,currentQuestion.correctMessage, Toast.LENGTH_LONG);
                        correct.show();

                    }
                    else if(selectedRB.getText().equals(currentQuestion.getCorrectAnswer()) && hintViewed){
                        questionPoints = 5;
                        Toast correct = Toast.makeText(MainActivity.this,currentQuestion.correctMessage, Toast.LENGTH_LONG);
                        correct.show();
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
                    scoreScreen.putExtra(getString(R.string.totalscore), totalScore);
                    startActivity(scoreScreen);
                }
                else if(doneCounter == 2)
                {
                    totalScore += questionPoints;
                    nextQuestion(choiceList);
                    doneBTN.setText(R.string.done);
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
        questionAnswersRG.clearCheck();
        doneBTN.setVisibility(View.INVISIBLE);
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