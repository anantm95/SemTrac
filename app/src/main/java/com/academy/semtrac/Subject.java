package com.academy.semtrac;

public class Subject {
    private String name = "";
    private String code = "";
    private int credits;
    private int grade;
    private int totalClasses;
    private int attendedClasses;
    private Boolean coursePlanPresent;
    private double attendancePercentage;

    public Subject() {
    }
    public Subject(String name) {
        this.name = name;
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

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void incrementTotal() {
        totalClasses++;
    }

    public void incrementAttended() {
        attendedClasses++;
    }
}