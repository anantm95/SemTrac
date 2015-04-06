package com.academy.semtrac;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;


public class NewTest extends CustomActivity {

    private final String error = "This field cannot be blank";
    private EditText name;
    private EditText weightage;
    private EditText maxMarks;
    private EditText marksObtained;
    private Subject subject;
    private GlobalClass global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        global = (GlobalClass) getApplication();
        Gson gson = new Gson();
        subject = gson.fromJson(getIntent().getStringExtra("subject"), Subject.class);
        subject = global.getCurrentSubject();
        name = (EditText) findViewById(R.id.testName);
        weightage = (EditText) findViewById(R.id.testWeightage);
        maxMarks = (EditText) findViewById(R.id.testMaxMarks);
        marksObtained = (EditText) findViewById(R.id.testMarksObtained);

        findViewById(R.id.testOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allCool = true;
                if (GlobalClass.isEmpty(name)) {
                    name.setError(error);
                    allCool = false;
                }
                if (GlobalClass.isEmpty(weightage)) {
                    weightage.setError(error);
                    allCool = false;
                }
                if (GlobalClass.isEmpty(maxMarks)) {
                    maxMarks.setError(error);
                    allCool = false;
                }
                if (GlobalClass.isEmpty(marksObtained)) {
                    marksObtained.setError(error);
                    allCool = false;
                }

                if (allCool) {
                    Test test = new Test();
                    test.setName(name.getText().toString().trim());
                    double weightageDouble = Double.parseDouble(weightage.getText().toString().trim());
                    double total = Double.parseDouble(maxMarks.getText().toString().trim());
                    double obtained = Double.parseDouble(marksObtained.getText().toString().trim());
                    test.setWeightage(weightageDouble);
                    test.setTotalMarks(total);
                    test.setMarksObtained(obtained);
                    subject.addTest(test);
                    subject.incrementTotal(weightageDouble);
                    subject.incrementObtained(obtained * weightageDouble / total);
                    setResult(1);
                    finish();
                }
            }
        });

        findViewById(R.id.testCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0);
                finish();
            }
        });
    }
}
