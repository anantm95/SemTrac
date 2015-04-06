package com.academy.semtrac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;


public class SubjectInfo extends ActionBarActivity implements View.OnClickListener {

    private final int ADD_TEST = 1;
    private Subject subject;
    private TextView codeName;
    private TextView credits;
    private TextView classesAttended;
    private TextView classesTotal;
    private TextView marks;
    private Button addTest;
    private Button viewTests;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);

        global = (GlobalClass) getApplication();
        Gson gson = new Gson();
        subject = gson.fromJson(getIntent().getStringExtra("subject"), Subject.class);
        subject = global.getCurrentSubject();

        codeName = (TextView) findViewById(R.id.subjectCodeAndName);
        credits = (TextView) findViewById(R.id.subjectCredits);
        classesAttended = (TextView) findViewById(R.id.classesAttended);
        classesTotal = (TextView) findViewById(R.id.totalClasses);
        marks = (TextView) findViewById(R.id.marks);
        addTest = (Button) findViewById(R.id.addTest);
        viewTests = (Button) findViewById(R.id.viewTests);

        addTest.setOnClickListener(this);
        viewTests.setOnClickListener(this);

        updateLayout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addTest:
                Gson gson = new Gson();
                String subjectString = gson.toJson(subject);
                Intent intent = new Intent(this, com.academy.semtrac.NewTest.class);
                intent.putExtra("subject", subjectString);
                startActivityForResult(intent, ADD_TEST);
                break;

            case R.id.viewTests:
                Intent viewIntent = new Intent(this, com.academy.semtrac.ViewTests.class);
                startActivity(viewIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TEST)
            updateLayout();
    }

    private void updateLayout() {
        codeName.setText(subject.getCode() + ": " + subject.getName());
        credits.setText("Credits: " + String.valueOf(subject.getCredits()));
        classesAttended.setText("Classes Attended: " + String.valueOf(subject.getAttendedClasses()));
        classesTotal.setText("Total Classes: " + String.valueOf(subject.getTotalClasses()));
        marks.setText("Marks scored till date: " + subject.getMarksScored() + " on " + subject.getTotalMarks());
    }
}
