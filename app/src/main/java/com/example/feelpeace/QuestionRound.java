package com.example.feelpeace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionRound extends AppCompatActivity {
    private Button startBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_round);

            startBtn = findViewById(R.id.start_btn);

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent questionsIntent = new Intent(QuestionRound.this,QuestionsActivity.class);
                    startActivity(questionsIntent);
                }
            });

    }
}