package com.academy.semtrac;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class EditAttendance extends ActionBarActivity {

    private final String error = "This field cannot be empty";
    private EditText totalClasses;
    private EditText classesAttended;
    private TextView subjectName;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attendance);
        global = (GlobalClass) getApplication();
        subjectName = (TextView) findViewById(R.id.subjectCodeAndName);
        subjectName.setText(global.getCurrentSubject().getName());
        totalClasses = (EditText) findViewById(R.id.totalClasses);
        classesAttended = (EditText) findViewById(R.id.classesAttended);
        totalClasses.setText(String.valueOf(global.getCurrentSubject().getTotalClasses()));
        classesAttended.setText(String.valueOf(global.getCurrentSubject().getAttendedClasses()));

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalClass.isEmpty(classesAttended))
                    classesAttended.setError(error);
                else if (GlobalClass.isEmpty(totalClasses))
                    totalClasses.setError(error);
                else {
                    int attended = Integer.parseInt(classesAttended.getText().toString().trim());
                    int total = Integer.parseInt(totalClasses.getText().toString().trim());

                    if (attended > total)
                        classesAttended.setError("Invalid entry");
                    else {
                        global.getCurrentSubject().setAttendedClasses(attended);
                        global.getCurrentSubject().setTotalClasses(total);
                        finish();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_attendance, menu);
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
