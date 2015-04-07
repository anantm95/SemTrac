package com.academy.semtrac;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;


public class HomeActivity extends ActionBarActivity {

    private Student student;
    private GlobalClass global;
    private RecyclerView mRecyclerView;
    private SubjectAdapter mAdapter;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        global = (GlobalClass) getApplication();
        student = global.getStudent();
        name = (TextView) findViewById(R.id.studentName);
        name.setText(global.getStudent().getStudentName().split(" ")[0]);
        mRecyclerView = (RecyclerView) findViewById(R.id.subjectRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        getSupportActionBar().setLogo(R.drawable.ic_action_edit);

        mAdapter = new SubjectAdapter(student.getCurrentSemester().getSubjects(),
                R.layout.subject_row_layout, this);
        mRecyclerView.setAdapter(mAdapter);
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
        if (id == R.id.update_attendance) {
            startActivity(new Intent(this, com.academy.semtrac.ModifyAttendance.class));
            return true;
        }

        if (id == R.id.add_new_subject) {
            Gson gson = new Gson();
            String studentString = gson.toJson(student);
            Intent intent = new Intent(this, com.academy.semtrac.SubjectAdderActivity.class);
            intent.putExtra("student", studentString);
            startActivityForResult(intent, 1);
        }

        if (id == R.id.wrap_up_semester) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to wrap up this semester?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(HomeActivity.this, com.academy.semtrac.WrapUpSemester.class));
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
            mAdapter.notifyDataSetChanged();
    }
}
