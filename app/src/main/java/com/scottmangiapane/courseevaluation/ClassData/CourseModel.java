package com.scottmangiapane.courseevaluation.ClassData;

public class CourseModel {
    private Integer courseID;//ID
    private String name;//名称
    private Integer type;//类型
    private String teacher;//教师
    private Integer score;//评分
    private Integer com_num;//成员数
    private String academy;//学院
    private String detail;//明细



    public CourseModel(){}

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCom_num() {
        return com_num;
    }

    public void setCom_num(Integer com_num) {
        this.com_num = com_num;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}