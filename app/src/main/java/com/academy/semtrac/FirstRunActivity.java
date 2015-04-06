package com.academy.semtrac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


public class FirstRunActivity extends ActionBarActivity {

    private final String error = "This field cannot be empty";
    private EditText name;
    private EditText currentSem;
    private EditText cgpa;
    private EditText credits;
    private Button proceed;
    private Student newStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run);

        name = (EditText) findViewById(R.id.studentName);
        currentSem = (EditText) findViewById(R.id.studentCurrentSem);
        cgpa = (EditText) findViewById(R.id.studentCgpa);
        credits = (EditText) findViewById(R.id.studentCredits);
        proceed = (Button) findViewById(R.id.proceedSubject);

        newStudent = new Student();

        hideCredits(true);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notValid = false;

                if (GlobalClass.isEmpty(name)) {
                    name.setError(error);
                    notValid = true;
                }
                if (GlobalClass.isEmpty(currentSem)) {
                    currentSem.setError(error);
                    notValid = true;
                }
                if (!GlobalClass.isEmpty(cgpa) && GlobalClass.isEmpty(credits)) {
                    credits.setError(error);
                    notValid = true;
                }

                if (!notValid) {
                    newStudent.setStudentName(name.getText().toString().trim());
                    Semester newSemester = new Semester();
                    newSemester.setNumber(Integer.parseInt(currentSem.getText().toString().trim()));
                    if (!GlobalClass.isEmpty(cgpa)) {
                        newStudent.setCumulativeGradePointAverage(Double.parseDouble(cgpa.getText().toString().trim()));
                        newStudent.setCreditsEarned(Integer.parseInt(credits.getText().toString().trim()));
                    }
                    newStudent.setCurrentSemester(newSemester);

                    Gson gson = new Gson();
                    String studentString = gson.toJson(newStudent);
                    Intent intent = new Intent(FirstRunActivity.this, com.academy.semtrac.SubjectAdderActivity.class);
                    intent.putExtra("student", studentString);
                    startActivity(intent);
                }
            }
        });

        cgpa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    hideCredits(false);
                else if (GlobalClass.isEmpty(cgpa))
                    hideCredits(true);
            }
        });

    }

    private void hideCredits(boolean hide) {
        if (hide) {
            credits.setVisibility(View.INVISIBLE);
        } else {
            credits.setVisibility(View.VISIBLE);
        }
    }
}
