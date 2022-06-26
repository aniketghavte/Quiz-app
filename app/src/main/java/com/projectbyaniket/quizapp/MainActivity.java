package com.projectbyaniket.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<ModelClass> listQs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listQs = new ArrayList<>();
        listQs.add(new ModelClass("5-7","2","3","-5","-2","-2"));
        listQs.add(new ModelClass("11-7","2","3","4","2","4"));
        listQs.add(new ModelClass("100-27","2","73","-5","2","73"));
        listQs.add(new ModelClass("80-20","25","60","-5","2","60"));
        listQs.add(new ModelClass("55+10","25","35","65","2","65"));
        listQs.add(new ModelClass("69-69","0","3","-5","2","0"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));
        listQs.add(new ModelClass("what is 5-7","2","3","-5","2","-2"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,DashBoardActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}