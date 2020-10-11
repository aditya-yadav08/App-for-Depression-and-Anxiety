package com.example.feelpeace;

public class QuestionModel {
    private String question,optionA,optionB,correctANS;

    public QuestionModel(){

    }

    public QuestionModel(String question, String optionA, String optionB, String correctANS) {

        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.correctANS = correctANS;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getCorrectANS() {
        return correctANS;
    }

    public void setCorrectANS(String correctANS) {this.correctANS = correctANS;}

}
