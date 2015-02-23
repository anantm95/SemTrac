package com.academy.semtrac;

import java.util.ArrayList;

public class Student {
    String studentName = "Anonymous";
    ArrayList<Semester> pastSemesters;
    Semester currentSemester;
    double cumulativeGradePointAverage = 0.0;

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
