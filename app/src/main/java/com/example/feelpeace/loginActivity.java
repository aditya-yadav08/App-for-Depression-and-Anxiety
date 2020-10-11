package com.example.feelpeace;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public EditText iemail,ipassword;
    public CheckBox checkBox;
    public ImageButton signin;
    public Button signup;
    public FirebaseAuth fAuth;
    public Button signuptol;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iemail = (EditText) findViewById(R.id.email);
        ipassword = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        signin = (ImageButton) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        signuptol =(Button) findViewById(R.id.signuptol);

        fAuth = FirebaseAuth.getInstance();

        signuptol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(loginActivity.this,SignUpActivity.class);
                startActivity(backIntent);
            }
        });

     signin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String email = iemail.getText().toString().trim();
             String password = ipassword.getText().toString().trim();

             if(TextUtils.isEmpty(email)){
                 iemail.setError("Email is Recquired.");
                 return;
             }
             if(TextUtils.isEmpty(password)){
                 ipassword.setError("Password is Recquired");
                 return;
             }
             if(password.length() < 6){
                 ipassword.setError("Password must be >= 6 Characters");
                 return;
             }

             //Authenticate the user
             fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(loginActivity.this, "Logged In Successfully.", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(),QuestionRound.class));
                     }else{
                         Toast.makeText(loginActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
             });

         }
     });



    }
}