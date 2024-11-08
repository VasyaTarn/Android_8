package com.example.android_8;

public class Question {
    private final String text;
    private final boolean correct;

    public Question(String text, boolean correct)
    {
        this.text = text;
        this.correct = correct;
    }

    public String getText()
    {
        return text;
    }

    public boolean isCorrect()
    {
        return correct;
    }
}
