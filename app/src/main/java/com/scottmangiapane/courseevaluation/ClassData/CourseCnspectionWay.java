package com.scottmangiapane.courseevaluation.ClassData;

public class CourseCnspectionWay {
    private Integer courseID;//课程ID
    private Integer open_test;//开卷考试
    private Integer close_test;//闭卷考试
    private Integer small_test;//课堂小测
    private Integer question_points;//提问加分
    private Integer presentation;//PPT展示
    private Integer paper;//论文
    private Integer sign_in;//点名、签到
    private Integer others;//其他

    public CourseCnspectionWay(Integer courseID, Integer open_test, Integer close_test, Integer small_test,
                               Integer question_points, Integer presentation, Integer paper, Integer sign_in, Integer others) {
        this.courseID = courseID;
        this.open_test = open_test;
        this.close_test = close_test;
        this.small_test = small_test;
        this.question_points = question_points;
        this.presentation = presentation;
        this.paper = paper;
        this.sign_in = sign_in;
        this.others = others;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public Integer getClose_test() {
        return close_test;
    }

    public Integer getOpen_test() {
        return open_test;
    }

    public Integer getQuestion_points() {
        return question_points;
    }

    public Integer getPresentation() {
        return presentation;
    }

    public Integer getPaper() {
        return paper;
    }

    public Integer getOthers() {
        return others;
    }

    public Integer getSign_in() {
        return sign_in;
    }

    public Integer getSmall_test() {
        return small_test;
    }

    public void setClose_test(Integer close_test) {
        this.close_test = close_test;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public void setOpen_test(Integer open_test) {
        this.open_test = open_test;
    }

    public void setPaper(Integer paper) {
        this.paper = paper;
    }

    public void setPresentation(Integer presentation) {
        this.presentation = presentation;
    }

    public void setQuestion_points(Integer question_points) {
        this.question_points = question_points;
    }

    public void setSmall_test(Integer small_test) {
        this.small_test = small_test;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }

    public void setSign_in(Integer sign_in) {
        this.sign_in = sign_in;
    }

}
