package com.scottmangiapane.courseevaluation.ClassData;

public class CourseInfo {
    private Integer courseID;//唯一标识ID
    private String courseName;//课程名称
    private String courseTeacher;//课程老师
    private int courseType;//课程类型（等同于专业类型）
    private double score;//总评分
    private String summarize;//课程概述

    public CourseInfo(Integer courseId,String courseName,String courseTeacher,int courseType,double score,String summarize){
        this.courseID=courseID;
        this.courseName=courseName;
        this.courseTeacher=courseTeacher;
        this.courseType=courseType;
        this.score=score;
        this.summarize=summarize;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getCourseTeacher(){
        return courseTeacher;
    }

    public int getCourseType() {
        return courseType;
    }

    public double getScore() {
        return score;
    }

    public String getSummarize(){
        return summarize;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
