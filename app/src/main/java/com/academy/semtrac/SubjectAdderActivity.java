package com.academy.semtrac;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;


public class SubjectAdderActivity extends ActionBarActivity implements View.OnClickListener {

    private final String error = "This field cannot be blank";
    private final int SELECT_IMAGE = 3;
    private Button addMore;
    private Button done;
    private LayoutInflater inflater;
    private LinearLayout mLayout;
    private ArrayList<View> views;
    private ScrollView mScrollView;
    private Student newStudent;
    private GlobalClass global;
    private String mSelectedImagePath;
    private HashMap map;

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

        map = new HashMap();
        addViewToLayout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addMore:
                addViewToLayout();
                break;
            case R.id.done:
                boolean allSuperCool = true;
                for (int i = 0; i < views.size(); i++) {
                    Subject subject = new Subject();
                    View view = views.get(i);

                    EditText code = (EditText) view.findViewById(R.id.entry_subject_code);
                    EditText name = (EditText) view.findViewById(R.id.entry_subject_name);
                    EditText credits = (EditText) view.findViewById(R.id.entry_subject_credits);
                    boolean allCool = true;

                    if (GlobalClass.isEmpty(code)) {
                        code.setError(error);
                        allCool = false;
                    }
                    if (GlobalClass.isEmpty(name)) {
                        name.setError(error);
                        allCool = false;
                    }
                    if (GlobalClass.isEmpty(credits)) {
                        credits.setError(error);
                        allCool = false;
                    }

                    if (allCool) {
                        subject.setCode(code.getText().toString().trim());
                        subject.setName(name.getText().toString().trim());
                        subject.setCredits(Integer.parseInt(credits.getText().toString().trim()));

                        newStudent.getCurrentSemester().addSubject(subject);
                    } else
                        allSuperCool = false;
                }

                if (allSuperCool) {
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
    }

    private void addViewToLayout() {
        View cardView = inflater.inflate(R.layout.subject_entry_row_layout, mLayout, false);
        views.add(cardView);
        //map.put("")

        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                views.get(views.size() - 1).findViewById(R.id.entry_subject_code).requestFocus();
            }
        });
        mLayout.addView(cardView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "Got an image", Toast.LENGTH_SHORT).show();
            mSelectedImagePath = ImageFilePath.getPath(this, data.getData());
            try {
                File sd = Environment.getExternalStorageDirectory();
                File fileData = Environment.getDataDirectory();
                if (sd.canWrite()) {
                    String sourceImagePath = mSelectedImagePath;
                    String destinationImagePath = Environment.getExternalStorageDirectory().getPath() + newStudent.getCurrentSemester().getSubjects().get(requestCode);
                    File source = new File(sd, sourceImagePath);
                    File destination = new File(sd, destinationImagePath);
                    if (source.exists()) {
                        FileChannel src = new FileInputStream(source).getChannel();
                        FileChannel dst = new FileOutputStream(destination).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();
                        Toast.makeText(this, "Successfully imported the course plan", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Didn't work!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
