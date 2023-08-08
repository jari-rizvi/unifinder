package com.example.unifinder.RegisterPassenger;

public class Courses {


    String Description;

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

    public Courses(String enroll, String description, String courseName) {
    }

}
