package com.academy.semtrac;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;

import com.google.gson.Gson;


public class CustomActivity extends ActionBarActivity {
    private GlobalClass global;

    @Override
    protected void onPause() {
        super.onPause();

        global = (GlobalClass) getApplication();
        Gson gson = new Gson();
        String studentString = gson.toJson(global.getStudent());

        String SHARED_PREFS_FILE = "com.academy.semtrac.STUDENT_DATA";
        SharedPreferences studentData = getSharedPreferences(SHARED_PREFS_FILE, 0);
        SharedPreferences.Editor editor = studentData.edit();
        editor.putString("student", studentString);
        editor.apply();
    }
}
