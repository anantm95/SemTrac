package com.academy.semtrac;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ViewTests extends ActionBarActivity {

    LinearLayout mLayout;
    private LayoutInflater inflater;
    private ArrayList<View> views;
    private GlobalClass global;
    private Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tests);
        mLayout = (LinearLayout) findViewById(R.id.viewTestsLayout);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        global = (GlobalClass) getApplication();
        subject = global.getCurrentSubject();
        views = new ArrayList<>();
        if (subject.getTests() == null || subject.getTests().isEmpty()) {
            TextView warnText = new TextView(this);
            warnText.setText("No tests added");
            warnText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            warnText.setGravity(Gravity.CENTER_HORIZONTAL);
            warnText.setTextSize(30f);
            mLayout.addView(warnText);
        } else {
            for (final Test test : subject.getTests()) {
                View cardView = inflater.inflate(R.layout.row_view_tests, mLayout, false);
                TextView testName = (TextView) cardView.findViewById(R.id.testName);
                TextView testWeightage = (TextView) cardView.findViewById(R.id.testWeightage);
                TextView maxMarks = (TextView) cardView.findViewById(R.id.maxMarks);
                TextView marksObtained = (TextView) cardView.findViewById(R.id.marksObtained);

                testName.setText(test.getName());
                testWeightage.setText("Weightage: " + String.valueOf(test.getWeightage()));
                maxMarks.setText("Maximum Marks: " + String.valueOf(test.getTotalMarks()));
                marksObtained.setText("Marks Obtained: " + String.valueOf(test.getMarksObtained()));

                views.add(cardView);
                mLayout.addView(cardView);
            }
        }

    }
}
