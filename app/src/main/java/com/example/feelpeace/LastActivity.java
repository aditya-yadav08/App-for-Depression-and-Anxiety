package com.example.feelpeace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LastActivity extends AppCompatActivity {
    private Button homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        homeBtn = findViewById(R.id.btn_backHome);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeintent = new Intent(LastActivity.this,MainActivity.class);
                startActivity(homeintent);
            }
        });

    }
    public void FpWeb(View view){

        openUrl("https://www.google.com/url?sa=t&source=web&rct=j&url=https://www.thebetterindia.com/94553/suicide-helplines-india/&ved=2ahUKEwi5_rTet53sAhWJzTgGHV3kAqMQFjAFegQIBxAB&usg=AOvVaw31xroseMaRd7ZlcfR33zTU&ampcf=1");
    }
    public void openUrl(String url){
        Uri uri =Uri.parse(url);
        Intent launchSite = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(launchSite);

    }
}