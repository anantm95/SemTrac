package com.academy.semtrac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class SubjectAdderActivity extends ActionBarActivity implements View.OnClickListener {

    private Button addMore;
    private Button done;
    private LayoutInflater inflater;
    private LinearLayout mLayout;
    private ArrayList<View> views;
    private ScrollView mScrollView;
    private Student newStudent;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_adder);

        Gson gson = new Gson();
        newStudent = gson.fromJson(getIntent().getStringExtra("student"), Student.class);

        global = (GlobalClass) getApplication();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) findViewById(R.id.subjectLayout);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);

        addMore = (Button) findViewById(R.id.addMore);
        done = (Button) findViewById(R.id.done);

        addMore.setOnClickListener(this);
        done.setOnClickListener(this);
        views = new ArrayList<>();

        addViewToLayout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMore:
                addViewToLayout();
                break;
            case R.id.done:
                for (int i = 0; i < views.size(); i++) {
                    Subject subject = new Subject();
                    View view = views.get(i);

                    EditText code = (EditText) view.findViewById(R.id.entry_subject_code);
                    EditText name = (EditText) view.findViewById(R.id.entry_subject_name);
                    EditText credits = (EditText) view.findViewById(R.id.entry_subject_credits);

                    subject.setCode(code.getText().toString().trim());
                    subject.setName(name.getText().toString().trim());
                    subject.setCredits(Integer.parseInt(credits.getText().toString().trim()));

                    newStudent.getCurrentSemester().addSubject(subject);
                }

                global.setStudent(newStudent);
                Gson gson = new Gson();
                String studentString = gson.toJson(newStudent);

                String SHARED_PREFS_FILE = "com.academy.semtrac.STUDENT_DATA";
                SharedPreferences studentData = getSharedPreferences(SHARED_PREFS_FILE, 0);
                SharedPreferences.Editor editor = studentData.edit();
                editor.putString("student", studentString);
                editor.commit();

                Intent intent = new Intent(this, com.academy.semtrac.HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
        }
    }

    private void addViewToLayout() {
        View cardView = inflater.inflate(R.layout.subject_entry_row_layout, mLayout, false);
        views.add(cardView);
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                views.get(views.size() - 1).findViewById(R.id.entry_subject_code).requestFocus();
            }
        });
        mLayout.addView(cardView);
    }
}
