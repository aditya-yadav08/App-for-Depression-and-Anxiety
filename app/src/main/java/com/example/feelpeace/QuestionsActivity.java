package com.example.feelpeace;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question,noIndicator;
    private FloatingActionButton bookmarkBtn;
    private LinearLayout optionscontainer;
    private Button shareBtn,nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        question = findViewById(R.id.question);
        noIndicator = findViewById(R.id.no_indicator);
        optionscontainer = findViewById(R.id.options_container);
        nextBtn = findViewById(R.id.next_btn);

        list = new ArrayList<>();
        list.add(new QuestionModel("Do you experience intense anxiety or worry and find it difficult to control ?","yes","no","yes"));
        list.add(new QuestionModel("Does worry or anxiety interfere with your sleep or ability to concentrate ?","yes","no","yes"));
        list.add(new QuestionModel("Do you experience strong fear that causes panic, shortness of breath, chest pains, a pounding heart, sweating, shaking, nausea, dizziness, and/or fear of dying ? ","yes","no","yes"));
        list.add(new QuestionModel("Do you ever avoid places or social situations for fear of this panic ?","yes","no","yes"));
        list.add(new QuestionModel("Do you ever engage in repetitive behaviors to manage your worry ?(i.e checking the oven is off, washing hands, counting, repeating words etc.)","yes","no","yes"));
        list.add(new QuestionModel("Feeling bad about yourself- or that you are a failure or have let yourself or your family down ","yes","no","yes"));
        list.add(new QuestionModel("Trouble concentrating on things, such as reading the newspaper or watching television","yes","no","yes"));
        list.add(new QuestionModel("Thoughts that you would be better off dead, or of hurting yourself","yes","no","yes"));
        list.add(new QuestionModel("Trouble falling or staying asleep, or sleeping too much","yes","no","yes"));
        list.add(new QuestionModel("Do you often share your thoughts, emotions and feelings with your closed ones ?","yes","no","yes"));

        for(int i = 0; i < 2; i++){
            optionscontainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                  checkAnswer((Button)v);
                }
            });
        }


        playAnim(question,0,list.get(position).getQuestion());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.7f);
                enableOption(true);
                position++;
                if(position == list.size()){
                    Intent scoreIntent = new Intent(QuestionsActivity.this,ScoreActivity.class);
                    scoreIntent.putExtra("score",score);
                    scoreIntent.putExtra("total",list.size());
                    startActivity(scoreIntent);
                    return;
                }
                count = 0;
                playAnim(question,0, list.get(position).getQuestion());
            }
        });
    }

    private void playAnim(final View view, final int value, final String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value == 0 && count < 2){
                    String option = "";
                    if(count == 0){
                      option = list.get(position).getOptionA();
                    }else if(count == 1) {
                        option = list.get(position).getOptionB();
                    }

                    playAnim(optionscontainer.getChildAt(count),0 , option  );
                    count ++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(value == 0){
                    try {
                        ((TextView)view).setText(data);
                        noIndicator.setText(position+1+"/"+list.size());
                    }catch(ClassCastException ex){
                        ((Button)view).setText(data);
                    }

                    playAnim(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption){
        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if(selectedOption.getText().toString().equals(list.get(position).getCorrectANS())){
            //correct YES
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B19CD9")));
        }else{
            //correct NO
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B19CD9")));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable){
        for(int i = 0; i < 2; i++){
            optionscontainer.getChildAt(i).setEnabled(enable);
            if(enable){
                optionscontainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }
}
