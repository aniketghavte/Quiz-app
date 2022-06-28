package com.projectbyaniket.quizapp;

import static com.projectbyaniket.quizapp.MainActivity.listQs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText , exitBtn;
    int correct;
    int wrong;
    LinearLayout shareBtn;
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resultText = findViewById(R.id.idResultText);
        shareBtn = findViewById(R.id.idbtnShare);
        exitBtn = findViewById(R.id.idExit);
        backBtn = findViewById(R.id.idBack);

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);

        circularProgressBar.setProgress(correct);
        circularProgressBar.setProgressMax(listQs.size());
        resultText.setText(correct+"/"+listQs.size());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plane");
                    i.putExtra(Intent.EXTRA_SUBJECT,"Quiz app");
                    String shareMassage = "hey I got "+ correct +" out of 20 You can?? \n Check this quiz app now";
                    i.putExtra(Intent.EXTRA_TEXT,shareMassage);
                    startActivity(Intent.createChooser(i,"choose one"));
                }catch (Exception e){
                    Toast.makeText(WonActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}