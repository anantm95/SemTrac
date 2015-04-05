package com.academy.semtrac;

import android.app.Application;

/**
 * Created by Abhishek Shanthkumar on 17-03-2015.
 */

public class GlobalClass extends Application {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
