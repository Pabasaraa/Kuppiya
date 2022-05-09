package com.example.mad_app;

public class AnswerHelperClass {

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;

    String answer;

    public AnswerHelperClass(){
    }

    public AnswerHelperClass(String answer, String key) {
        this.answer = answer;
        this.key = key;
    }

    public String getAnswer() {
        return answer;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
