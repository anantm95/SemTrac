package com.academy.semtrac;

import android.app.Application;
import android.widget.EditText;



public class GlobalClass extends Application {
    private Student student;
    private Subject currentSubject;

    public static boolean isEmpty(EditText editText) {
        return (editText == null || editText.getText().toString().trim().isEmpty());
    }

    public Subject getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(Subject currentSubject) {
        this.currentSubject = currentSubject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
