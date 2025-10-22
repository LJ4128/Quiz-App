package org.balitmorecityschools.quizappljv1;

import androidx.annotation.NonNull;

public class Question
{
    String qText;
    String correctAnswer;
    String answer1;
    String answer2;
    String answer3;
    String hintText;
    String correctMessage;
    String incorrectMessage;


    public Question(String qText, String correctAnswer, String answer1, String answer2, String answer3, String hintText, String correctMessage, String incorrectMessage)
    {
        this.qText = qText;
        this.correctAnswer = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.hintText = hintText;
        this.correctMessage = correctMessage;
        this.incorrectMessage = incorrectMessage;
    }
    public Question()
    {
        qText = "";
        correctAnswer = "";
        answer1 = "";
        answer2 = "";
        answer3 = "";
    }
    public void setQText(String qText){this.qText = qText;}
    public void setCorrectAnswer(String correctAnswer){this.correctAnswer = correctAnswer;}
    public void setAnswer1(String answer1) {this.answer1 = answer1;}
    public void setAnswer2(String answer2) {this.answer2 = answer2;}
    public void setAnswer3(String answer3) {this.answer3 = answer3;}
    public void setHintText(String hintText) {this.hintText = hintText;}
    public void setCorrectMessage(String correctMessage) {this.correctMessage = correctMessage;}
    public void setIncorrectMessage(String incorrectMessage) {this.incorrectMessage = incorrectMessage;}

    public String getQText() {return qText;}
    public String getCorrectAnswer() {return correctAnswer;}
    public String getAnswer1() {return answer1;}
    public String getAnswer2() {return answer2;}
    public String getAnswer3() {return answer3;}
    public String getHintText() {return hintText;}
    public String getCorrectMessage() {return correctMessage;}
    public String getIncorrectMessage() {return incorrectMessage;}
}
