package com.academy.semtrac;

import java.util.ArrayList;

public class Student {
    private String studentName = "Anonymous";
    private ArrayList<Semester> pastSemesters;
    private Semester currentSemester;
    private double cumulativeGradePointAverage = 0.0;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }
}
