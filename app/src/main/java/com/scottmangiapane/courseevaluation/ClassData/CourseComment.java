package com.scottmangiapane.courseevaluation.ClassData;

import java.util.Date;

public class CourseComment {
    private Integer commentID;//该评论唯一识别ID
    private Integer courseID;//课程ID
    private Integer userID;//用户ID
    private double score;//用户对这门课程的评分
    private Date creationTime;//评论日期（年-月-日）
    private String comment;//评论内容

    public CourseComment(Integer commentID,Integer courseID,Integer userID,double score,Date creationTime,String comment){
        this.commentID=commentID;
        this.courseID=courseID;
        this.userID=userID;
        this.score=score;
        this.creationTime=creationTime;
        this.comment=comment;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public Integer getUserID() {
        return userID;
    }

    public double getScore() {
        return score;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getComment() {
        return comment;
    }

}
