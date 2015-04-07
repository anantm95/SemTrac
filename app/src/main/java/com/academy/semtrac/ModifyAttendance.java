package com.academy.semtrac;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ModifyAttendance extends CustomActivity {

    LinearLayout mLayout;
    private LayoutInflater inflater;
    private ArrayList<View> views;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_attendance);
        mLayout = (LinearLayout) findViewById(R.id.attendanceLayout);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        global = (GlobalClass) getApplication();
        views = new ArrayList<>();
        for (final Subject subject : global.getStudent().getCurrentSemester().getSubjects()) {
            final View cardView = inflater.inflate(R.layout.row_modify_attendance, mLayout, false);
            final Button subjectCodeAndName = (Button) cardView.findViewById(R.id.subjectCodeAndName);
            String name = subject.getName();
            if (name.length() > 3) {
                String[] things = name.split(" ");
                name = "";
                for (String temp : things) {
                    name += temp.charAt(0);
                }
                if (things[things.length - 1].compareToIgnoreCase("Lab") == 0) {
                    name = name.substring(0, name.length() - 1) + " Lab";
                }
            }
            final String finalName = name;
            subjectCodeAndName.setText(finalName + " : " + subject.getAttendedClasses() + "/" + subject.getTotalClasses());
            Button classHappened = (Button) cardView.findViewById(R.id.classHappened);
            Button classAttended = (Button) cardView.findViewById(R.id.classAttended);
            classHappened.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subject.incrementTotal();
                    subjectCodeAndName.setText(finalName + " : " + subject.getAttendedClasses() + "/" + subject.getTotalClasses());
                }
            });
            classAttended.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (subject.getAttendedClasses() < subject.getTotalClasses())
                    subject.incrementAttended();
                    subjectCodeAndName.setText(finalName + " : " + subject.getAttendedClasses() + "/" + subject.getTotalClasses());
                }
            });

            subjectCodeAndName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    global.setCurrentSubject(subject);
                    startActivityForResult(new Intent(ModifyAttendance.this, EditAttendance.class), views.indexOf(cardView));
                    return false;
                }
            });

            views.add(cardView);
            mLayout.addView(cardView);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Subject subject = global.getCurrentSubject();
        String name = subject.getName();
        if (name.length() > 3) {
            String[] things = name.split(" ");
            name = "";
            for (String temp : things) {
                name += temp.charAt(0);
            }
            if (things[things.length - 1].compareToIgnoreCase("Lab") == 0) {
                name = name.substring(0, name.length() - 1) + " Lab";
            }
        }
        Button changed = (Button) views.get(requestCode).findViewById(R.id.subjectCodeAndName);
        changed.setText(name + " : " + subject.getAttendedClasses() + "/" + subject.getTotalClasses());
    }
}


