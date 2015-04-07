package com.academy.semtrac;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.text.DecimalFormat;


public class SemesterResults extends ActionBarActivity {

    GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_results);
        global = (GlobalClass) getApplication();
        TextView grade = (TextView) findViewById(R.id.showCGPA);
        grade.setText((new DecimalFormat("##.##").format(global.getStudent().getCumulativeGradePointAverage())));
    }
}
