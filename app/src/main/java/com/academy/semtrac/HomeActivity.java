package com.academy.semtrac;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class HomeActivity extends ActionBarActivity {

    private LinearLayout layout;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        layout = (LinearLayout) findViewById(R.id.home_layout);

        student = new Student();
        Semester semester = new Semester();
        ArrayList<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("DC"));
        subjects.add(new Subject("SE"));
        subjects.add(new Subject("OS"));
        subjects.add(new Subject("DAA"));
        subjects.add(new Subject("OS Lab"));
        subjects.add(new Subject("SE Lab"));
        subjects.add(new Subject("OOP"));
        subjects.add(new Subject("DMS"));
        semester.setSubjects(subjects);
        student.setCurrentSemester(semester);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
