package com.example.unifinder.RegisterPassenger;

public class Courses {


    String Description;

    public Courses() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    String enroll;
    String CourseName;

    public Courses(String courseName, String description, String enroll) {
        Description = description;
        this.enroll = enroll;
        CourseName = courseName;
    }
}
