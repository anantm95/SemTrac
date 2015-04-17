package com.academy.semtrac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;


public class SemesterResults extends ActionBarActivity {

    GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_results);
        global = (GlobalClass) getApplication();
        TextView sgpa = (TextView) findViewById(R.id.showSGPA);
        TextView grade = (TextView) findViewById(R.id.showCGPA);
        sgpa.setText(new DecimalFormat("##.##").format(global.getStudent().getCurrentSemester().getGradePointAverage()));
        grade.setText("Your CGPA is: " + (new DecimalFormat("##.##").format(global.getStudent().getCumulativeGradePointAverage())));

        findViewById(R.id.addNewSemester).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SemesterResults.this, AddNewSemester.class);
                startActivity(intent);
            }
        });
    }
}
