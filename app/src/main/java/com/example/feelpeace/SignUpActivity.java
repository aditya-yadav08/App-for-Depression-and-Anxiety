package com.example.feelpeace;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
     Toolbar toolbar;
     EditText Uemail,Upassword,Uname;
     CheckBox checkBox;
     ImageButton signup;
     Button signin;
     FirebaseAuth fAuth;
     FirebaseFirestore fstore;
     String userID;
     ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        Uemail = findViewById(R.id.email);
        Upassword = findViewById(R.id.password);
        Uname = findViewById(R.id.name);
        checkBox = findViewById(R.id.checkbox);
        progressBar = findViewById(R.id.progressBar);
        signup =findViewById(R.id.signup);
        signin = findViewById(R.id.signin);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),QuestionRound.class));
            finish();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       final String email = Uemail.getText().toString().trim();
                        String password = Upassword.getText().toString().trim();
                       final String name = Uname.getText().toString().trim();

                       if(TextUtils.isEmpty(email)){
                           Uemail.setError("Email is Recquired.");
                           return;
                       }
                       if(TextUtils.isEmpty(password)){
                           Upassword.setError("Password is Recquired");
                           return;
                       }
                       if(password.length() < 6){
                           Upassword.setError("Password must be >= 6 Characters");
                           return;
                       }
                       progressBar.setVisibility(View.VISIBLE);

                       //Register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(SignUpActivity.this, "User created.", Toast.LENGTH_SHORT).show();
                              userID = fAuth.getCurrentUser().getUid();
                              DocumentReference documentReference = fstore.collection("User").document(userID);
                              Map<String ,Object> user = new HashMap<>();
                              user.put("fname",name);
                              user.put("email",email);
                              documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                  @Override
                                  public void onSuccess(Void aVoid) {
                                      Log.d(TAG, "onSuccess: User profile created for "+ userID);
                                  }
                              });
                              startActivity(new Intent(getApplicationContext(),QuestionRound.class));

                          }else{
                              Toast.makeText(SignUpActivity.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          }
                    }
                });

            }
        });



        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent back_intent = new Intent(SignUpActivity.this,loginActivity.class);
                startActivity(back_intent);
            }
        });


    }
}