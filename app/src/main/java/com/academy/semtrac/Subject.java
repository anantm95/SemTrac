package com.academy.semtrac;

public class Subject {
    String name;
    String code;
    int grade;
    int totalClasses;
    int attendedClasses;
    double attendancePercentage;

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