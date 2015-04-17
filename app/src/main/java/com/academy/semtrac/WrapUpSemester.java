package com.academy.semtrac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WrapUpSemester extends ActionBarActivity {

    LinearLayout mLayout;
    double netProductCGPA, netProductSGPA, totalCreditsCGPA, totalCreditsSGPA;
    private LayoutInflater inflater;
    private ArrayList<View> views;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_up_semester);
        mLayout = (LinearLayout) findViewById(R.id.wrapUpSemesterLayout);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        global = (GlobalClass) getApplication();
        views = new ArrayList<>();
        for (Subject subject : global.getStudent().getCurrentSemester().getSubjects()) {
            View cardView = inflater.inflate(R.layout.row_wrap_semester, mLayout, false);
            Button subjectCodeAndName = (Button) cardView.findViewById(R.id.subjectCodeAndName);
            String name = subject.getName();
            subjectCodeAndName.setText(name);

            views.add(cardView);
            mLayout.addView(cardView);
        }
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allCool = true;
                for (int i = 0; i < views.size(); i++) {
                    Subject subject = global.getStudent().getCurrentSemester().getSubjects().get(i);
                    EditText grade = (EditText) views.get(i).findViewById(R.id.grade);
                    if (GlobalClass.isEmpty(grade)) {
                        allCool = false;
                        grade.setError("This field cannot be empty");
                        break;
                    }
                    subject.setGrade(Integer.parseInt(grade.getText().toString().trim()));

                    netProductSGPA += subject.getCredits() * subject.getGrade();
                    totalCreditsSGPA += subject.getCredits();
                }
                if (allCool) {
                    netProductCGPA = netProductSGPA + global.getStudent().getCumulativeGradePointAverage() * global.getStudent().getCreditsEarned();
                    totalCreditsCGPA = totalCreditsSGPA + global.getStudent().getCreditsEarned();
                    double cgpa = (double) netProductCGPA / totalCreditsCGPA;
                    double sgpa = (double) netProductSGPA / totalCreditsSGPA;
                    global.getStudent().setCumulativeGradePointAverage(cgpa);
                    global.getStudent().getCurrentSemester().setGradePointAverage(sgpa);
                    Intent intent = new Intent(WrapUpSemester.this, com.academy.semtrac.SemesterResults.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    String SHARED_PREFS_FILE = "com.academy.semtrac.STUDENT_DATA";
                    SharedPreferences studentData = getSharedPreferences(SHARED_PREFS_FILE, 0);
                    SharedPreferences.Editor editor = studentData.edit();
                    editor.putBoolean("wrappedUp", true);
                    Gson gson = new Gson();
                    editor.putString("student", gson.toJson(global.getStudent()));
                    editor.apply();

                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wrap_up_semester, menu);
        return true;
    }

}
