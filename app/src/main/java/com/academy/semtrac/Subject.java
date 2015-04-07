package com.academy.semtrac;

import java.util.ArrayList;

public class Subject {
    private String name = "";
    private String code = "";
    private int credits;
    private int grade;
    private int totalClasses;
    private int attendedClasses;
    private Boolean coursePlanPresent;
    private double attendancePercentage = 100;
    private double marksScored;
    private double totalMarks;
    private ArrayList<Test> tests;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getMarksScored() {

        return marksScored;
    }

    public void setMarksScored(double marksScored) {
        this.marksScored = marksScored;
    }

    public Boolean getCoursePlanPresent() {
        return coursePlanPresent;
    }

    public void setCoursePlanPresent(Boolean coursePlanPresent) {
        this.coursePlanPresent = coursePlanPresent;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
        attendancePercentage = attendedClasses * 100.0 / totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
        attendancePercentage = attendedClasses * 100.0 / totalClasses;
    }

    public double getAttendancePercentage() {
        if (totalClasses == 0)
            return 100;
        else
            return attendancePercentage;
    }

    public void incrementTotal() {
        totalClasses++;
        attendancePercentage = attendedClasses * 100.0 / totalClasses;
    }

    public void incrementAttended() {
        attendedClasses++;
        attendancePercentage = attendedClasses * 100.0 / totalClasses;
    }

    public void addTest(Test test) {
        if (tests == null)
            tests = new ArrayList<>();
        tests.add(test);
    }

    public void incrementTotal(double marks) {
        totalMarks += marks;
    }

    public void incrementObtained(double marks) {
        marksScored += marks;
    }
}