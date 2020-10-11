package com.example.feelpeace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class ScoreActivity extends AppCompatActivity {

    private TextView scored,total;
    private Button doneBtn;
    private Button feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scored =findViewById(R.id.scored);
        total = findViewById(R.id.total);
        doneBtn = findViewById(R.id.done_btn);
        feedbackBtn = findViewById(R.id.feedback);

        scored.setText(String.valueOf(getIntent().getIntExtra("score", 0)));

        total.setText("OUT OF "+String.valueOf(getIntent().getIntExtra("total", 0)));

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doneIntent = new Intent(ScoreActivity.this,LastActivity.class);
                startActivity(doneIntent);
            }
        });

        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();

            }
        });
    }
    private void showDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Feedback form");
        dialog.setMessage("Provide us your valuable feedback");

        LayoutInflater inflater = LayoutInflater.from(this);

        View reg_layout = inflater.inflate(R.layout.send_feedback,null);

        final MaterialEditText edtEmail = reg_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtName = reg_layout.findViewById(R.id.edtName);
        final MaterialEditText edtFeedback = reg_layout.findViewById(R.id.edtFeedback);

        dialog.setView(reg_layout);

        // set button
        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Check Validation
                if(TextUtils.isEmpty(edtEmail.getText().toString())){
                    Toast.makeText(ScoreActivity.this, "Please type your email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtName.getText().toString())){
                    Toast.makeText(ScoreActivity.this, "name field cannot be empty...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtFeedback.getText().toString())){
                    Toast.makeText(ScoreActivity.this, "feedback field cannot be empty...", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
        

    }
}