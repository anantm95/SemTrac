package com.academy.semtrac;

import java.util.ArrayList;

public class Semester {
    private int number;
    private double gradePointAverage = 0.0;
    private ArrayList<Subject> subjects;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        if (subjects == null)
            subjects = new ArrayList<>();
        subjects.add(subject);
    }
}

