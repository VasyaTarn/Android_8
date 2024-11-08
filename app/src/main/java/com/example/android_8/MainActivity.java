package com.example.android_8;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView questionText, scoreText;
    private Button buttonCorrect, buttonIncorrect;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        buttonCorrect = findViewById(R.id.buttonCorrect);
        buttonIncorrect = findViewById(R.id.buttonIncorrect);

        preferences = getSharedPreferences("QuizPreferences", MODE_PRIVATE);

        loadGameState();

        loadQuestions();

        showQuestion();

        buttonCorrect.setOnClickListener(v -> checkAnswer(true));
        buttonIncorrect.setOnClickListener(v -> checkAnswer(false));
    }

    private void loadQuestions()
    {
        questions = new ArrayList<>();
        questions.add(new Question("2 + 2 = 5?", false));
        questions.add(new Question("3 + 3 = 6?", true));
        questions.add(new Question("5 - 2 = 3?", true));
        questions.add(new Question("7 * 2 = 15?", false));

    }

    private void showQuestion()
    {
        if (currentQuestionIndex < questions.size())
        {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionText.setText(currentQuestion.getText());
            scoreText.setText("Score: " + score);
        }
        else
        {
            questionText.setText("Finish");
            scoreText.setText("Score: " + score);
            buttonCorrect.setEnabled(false);
            buttonIncorrect.setEnabled(false);
        }
    }

    private void checkAnswer(boolean userAnswer)
    {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.isCorrect() == userAnswer)
        {
            score++;
        }

        currentQuestionIndex++;
        saveGameState();
        showQuestion();
    }

    private void loadGameState()
    {
        currentQuestionIndex = preferences.getInt("currentQuestionIndex", 0);
        score = preferences.getInt("score", 0);
    }

    private void saveGameState()
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("currentQuestionIndex", currentQuestionIndex);
        editor.putInt("score", score);
        editor.apply();
    }
}