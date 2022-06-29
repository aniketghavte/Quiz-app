package com.projectbyaniket.quizapp;

import static com.projectbyaniket.quizapp.MainActivity.listQs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;

    List<ModelClass> allQsList;
    ModelClass modelClass;
    int index = 0;
    TextView question,OA, OB, OC, OD , exitBtn;
    CardView Cardquestion,CardOA, CardOB, CardOC, CardOD;
    int correct_count = 0;
    int worng_count = 0;
    LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Hooks();

        allQsList = listQs;
        Collections.shuffle(allQsList);
        modelClass = listQs.get(index);
        setAllData();

        nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
        resetColor();
        nextBtn.setClickable(false);
        Cardquestion.setBackgroundColor(getResources().getColor(R.color.white));

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {

                Dialog dialog = new Dialog(DashBoardActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.timeout_dialog);
                dialog.findViewById(R.id.idTryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameWon();
                    }
                },5000);
            }
        }.start();
    }

    private void setAllData() {
        question.setText(modelClass.getQuestion());
        OA.setText(modelClass.getOA());
        OB.setText(modelClass.getOB());
        OC.setText(modelClass.getOC());
        OD.setText(modelClass.getOD());
    }


    private void Hooks() {
        progressBar = findViewById(R.id.idProgressbar);
        question = findViewById(R.id.idquestion);
        Cardquestion = findViewById(R.id.idCardQues);
        CardOA = findViewById(R.id.idCardA);
        CardOB = findViewById(R.id.idCardB);
        CardOC = findViewById(R.id.idCardC);
        CardOD = findViewById(R.id.idCardD);

        nextBtn = findViewById(R.id.idNextBtn);
        OA = findViewById(R.id.idOptionA);
        OB = findViewById(R.id.idOptionB);
        OC = findViewById(R.id.idOptionC);
        OD = findViewById(R.id.idOptionD);

        exitBtn = findViewById(R.id.idExit);
    }

    public void Correct(CardView cardView) {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.green));
        disableButton();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correct_count++;
                index++;
                modelClass = listQs.get(index);
                resetColor();
                setAllData();
                enableButton();


            }
        });

    }

    public void Wrong(CardView cardView) {
        cardView.setCardBackgroundColor(getResources().getColor(R.color.red));
        disableButton();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                worng_count++;
                index++;
                modelClass = listQs.get(index);
                resetColor();
                setAllData();
                enableButton();

            }
        });

    }

    private void gameWon() {
        Intent intent = new Intent(DashBoardActivity.this, WonActivity.class);
        intent.putExtra("correct",correct_count);
        intent.putExtra("wrong",worng_count);
        startActivity(intent);
        finish();

    }

    public void enableButton() {
        CardOA.setClickable(true);
        CardOB.setClickable(true);
        CardOC.setClickable(true);
        CardOD.setClickable(true);
    }

    public void disableButton() {
        CardOA.setClickable(false);
        CardOB.setClickable(false);
        CardOC.setClickable(false);
        CardOD.setClickable(false);
    }

    public void resetColor() {
        CardOA.setBackgroundColor(getResources().getColor(R.color.white));
        CardOB.setBackgroundColor(getResources().getColor(R.color.white));
        CardOC.setBackgroundColor(getResources().getColor(R.color.white));
        CardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void option_A_Click(View view) {
        if (index < listQs.size()-1){
            nextBtn.setClickable(true);
            nextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            CardOA.setBackgroundColor(getResources().getColor(R.color.green));
            disableButton();
            if (modelClass.getOA().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    modelClass = listQs.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    nextBtn.setClickable(false);
                    nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                }
            });
        }else {
            if (modelClass.getOA().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            gameWon();
        }

//        if (modelClass.getOA().equals(modelClass.Ans)) {
//            CardOA.setBackgroundColor(getResources().getColor(R.color.green));
//
//            if (index<listQs.size()-1){
//                Correct(CardOA);
//
//            }else {
//                gameWon();
//
//            }
//
//        } else {
//            CardOA.setCardBackgroundColor(getResources().getColor(R.color.red));
//            if (index < listQs.size()-1){
//                Wrong(CardOA);
//            }else {
//                gameWon();
//            }

//        }
    }

    public void option_B_Click(View view) {
        if (index < listQs.size()-1){
            nextBtn.setClickable(true);
            nextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            CardOB.setBackgroundColor(getResources().getColor(R.color.green));
            disableButton();
            if (modelClass.getOB().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    modelClass = listQs.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    nextBtn.setClickable(false);
                    nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                }
            });
        } else {
            if (modelClass.getOB().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            gameWon();
        }

//        if (modelClass.getOB().equals(modelClass.Ans)) {
//            CardOB.setBackgroundColor(getResources().getColor(R.color.green));
//
//            if (index<listQs.size()-1){
//                Correct(CardOB);
//
//            }else {
//                gameWon();
//
//            }
//        } else {
//            CardOB.setCardBackgroundColor(getResources().getColor(R.color.red));
//            if (index < listQs.size()-1){
//                Wrong(CardOB);
//            }else {
//                gameWon();
//            }
//        }
    }

    public void option_C_Click(View view) {
        if (index < listQs.size()-1){
            nextBtn.setClickable(true);
            nextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            CardOC.setBackgroundColor(getResources().getColor(R.color.green));
            disableButton();
            if (modelClass.getOC().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    modelClass = listQs.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    nextBtn.setClickable(false);
                    nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                }
            });
        }else {
            if (modelClass.getOC().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            gameWon();
        }

//        if (modelClass.getOC().equals(modelClass.Ans)) {
//            CardOC.setBackgroundColor(getResources().getColor(R.color.green));
//
//            if (index<listQs.size()-1){
//                Correct(CardOC);
//                return;
//            }else {
//                gameWon();
//
//            }
//        } else {
//            CardOC.setCardBackgroundColor(getResources().getColor(R.color.red));
//            if (index < listQs.size()-1){
//                Wrong(CardOC);
//            }else {
//                gameWon();
//            }
//        }
    }

    public void option_D_Click(View view) {
        if (index < listQs.size()-1){
            nextBtn.setClickable(true);
            nextBtn.setBackgroundColor(getResources().getColor(R.color.white));
            CardOD.setBackgroundColor(getResources().getColor(R.color.green));
            disableButton();
            if (modelClass.getOD().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index++;
                    modelClass = listQs.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                    nextBtn.setClickable(false);
                    nextBtn.setBackgroundColor(getResources().getColor(R.color.green));
                }
            });
        }else {
            if (modelClass.getOD().equals(modelClass.Ans)){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                correct_count++;
            }else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
                worng_count++;
            }
            gameWon();
        }

//        if (modelClass.getOD().equals(modelClass.Ans)) {
//            CardOD.setBackgroundColor(getResources().getColor(R.color.green));
//
//            if (index<listQs.size()-1){
//                Correct(CardOD);
//                return;
//            }else {
//                gameWon();
//
//            }
//        } else {
//            CardOD.setCardBackgroundColor(getResources().getColor(R.color.red));
//            if (index < listQs.size()-1){
//                Wrong(CardOD);
//            }else {
//                gameWon();
//            }
//        }
    }

}