package org.balitmorecityschools.quizappljv1;

import androidx.annotation.NonNull;

public class Question
{
    String qText;
    int correctAnswerID;
    String answer1;
    String answer2;
    String answer3;

    public Question(String qText, int correctAnswer, String answer1, String answer2, String answer3)
    {
        this.qText = qText;
        this.correctAnswerID = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }
    public void setQText(String qText){this.qText = qText;}
    public void setCorrectAnswerID(int correctAnswer){this.correctAnswerID = correctAnswer;}
    public void setAnswer1(String answer1) {this.answer1 = answer1;}
    public void setAnswer2(String answer2) {this.answer2 = answer2;}
    public void setAnswer3(String answer3) {this.answer3 = answer3;}

    public String getQText() {return qText;}
    public int getCorrectAnswerID() {return correctAnswerID;}
    public String getAnswer1() {return answer1;}
    public String getAnswer2() {return answer2;}
    public String getAnswer3() {return answer3;}


}
