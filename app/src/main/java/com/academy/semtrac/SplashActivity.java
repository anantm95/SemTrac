package com.academy.semtrac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;

import com.google.gson.Gson;

public class SplashActivity extends Activity {

    private boolean firstTimeUse;
    private GlobalClass global;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        global = (GlobalClass) getApplication();

        int SPLASH_DISPLAY_LENGTH = 2000;
        new CountDownTimer(SPLASH_DISPLAY_LENGTH, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (firstTimeUse) {
                    intent = new Intent(SplashActivity.this, com.academy.semtrac.FirstRunActivity.class);
                } else {
                    if (!wrappedUp())
                        intent = new Intent(SplashActivity.this, com.academy.semtrac.HomeActivity.class);
                    else
                        intent = new Intent(SplashActivity.this, com.academy.semtrac.SemesterResults.class);
                }
                startActivity(intent);
                finish();
            }
        }.start();

        firstTimeUse = initialiseUser();
    }

    private boolean initialiseUser() {
        String SHARED_PREFS_FILE = "com.academy.semtrac.STUDENT_DATA";
        SharedPreferences studentData = getSharedPreferences(SHARED_PREFS_FILE, 0);
        String student = studentData.getString("student", null);
        if (student != null) {
            Gson gson = new Gson();
            global.setStudent(gson.fromJson(student, Student.class));
            return false;
        } else {
            return true;
        }
    }

    private boolean wrappedUp() {
        String SHARED_PREFS_FILE = "com.academy.semtrac.STUDENT_DATA";
        SharedPreferences studentData = getSharedPreferences(SHARED_PREFS_FILE, 0);
        return studentData.getBoolean("wrappedUp", false);
    }
}